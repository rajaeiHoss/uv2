package com.kenai.jbosh;

import com.kenai.jbosh.ComposableBody;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class BOSHClient {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean ASSERTIONS;
    private static final int DEFAULT_EMPTY_REQUEST_DELAY = 100;
    private static final int DEFAULT_PAUSE_MARGIN = 500;
    private static final int EMPTY_REQUEST_DELAY;
    private static final String ERROR = "error";
    private static final String INTERRUPTED = "Interrupted";
    private static final Logger LOG;
    private static final String NULL_LISTENER = "Listener may not be null";
    private static final int PAUSE_MARGIN;
    private static final String TERMINATE = "terminate";
    private static final String UNHANDLED = "Unhandled Exception";
    private final BOSHClientConfig cfg;
    private CMSessionParams cmParams;
    private final Set<BOSHClientConnListener> connListeners = new CopyOnWriteArraySet();
    private final Condition drained;
    private ScheduledFuture emptyRequestFuture;
    private final Runnable emptyRequestRunnable;
    private final AtomicReference<ExchangeInterceptor> exchInterceptor;
    private Queue<HTTPExchange> exchanges;
    private final HTTPSender httpSender;
    private final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;
    private List<ComposableBody> pendingRequestAcks;
    private SortedSet<Long> pendingResponseAcks;
    private final Runnable procRunnable;
    private Thread procThread;
    private final RequestIDSequence requestIDSeq;
    private final Set<BOSHClientRequestListener> requestListeners = new CopyOnWriteArraySet();
    private Long responseAck;
    private final Set<BOSHClientResponseListener> responseListeners = new CopyOnWriteArraySet();
    private final ScheduledExecutorService schedExec;

    static {
        Class<BOSHClient> cls = BOSHClient.class;
        LOG = Logger.getLogger(cls.getName());
        EMPTY_REQUEST_DELAY = Integer.getInteger(cls.getName() + ".emptyRequestDelay", 100).intValue();
        PAUSE_MARGIN = Integer.getInteger(cls.getName() + ".pauseMargin", DEFAULT_PAUSE_MARGIN).intValue();
        String str = cls.getSimpleName() + ".assertionsEnabled";
        boolean z = false;
        if (System.getProperty(str) != null) {
            z = Boolean.getBoolean(str);
        }
        ASSERTIONS = z;
    }

    static abstract class ExchangeInterceptor {
        /* access modifiers changed from: package-private */
        public abstract HTTPExchange interceptExchange(HTTPExchange hTTPExchange);

        ExchangeInterceptor() {
        }
    }

    private BOSHClient(BOSHClientConfig bOSHClientConfig) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.lock = reentrantLock;
        this.notEmpty = reentrantLock.newCondition();
        this.notFull = reentrantLock.newCondition();
        this.drained = reentrantLock.newCondition();
        this.procRunnable = new Runnable() {
            public void run() {
                BOSHClient.this.processMessages();
            }
        };
        this.emptyRequestRunnable = new Runnable() {
            public void run() {
                BOSHClient.this.sendEmptyRequest();
            }
        };
        this.httpSender = new ApacheHTTPSender();
        this.exchInterceptor = new AtomicReference<>();
        this.requestIDSeq = new RequestIDSequence();
        this.schedExec = Executors.newSingleThreadScheduledExecutor();
        this.exchanges = new LinkedList();
        this.pendingResponseAcks = new TreeSet();
        this.responseAck = -1L;
        this.pendingRequestAcks = new ArrayList();
        this.cfg = bOSHClientConfig;
        init();
    }

    public static BOSHClient create(BOSHClientConfig bOSHClientConfig) {
        if (bOSHClientConfig != null) {
            return new BOSHClient(bOSHClientConfig);
        }
        throw new IllegalArgumentException("Client configuration may not be null");
    }

    public BOSHClientConfig getBOSHClientConfig() {
        return this.cfg;
    }

    public void addBOSHClientConnListener(BOSHClientConnListener bOSHClientConnListener) {
        if (bOSHClientConnListener != null) {
            this.connListeners.add(bOSHClientConnListener);
            return;
        }
        throw new IllegalArgumentException(NULL_LISTENER);
    }

    public void removeBOSHClientConnListener(BOSHClientConnListener bOSHClientConnListener) {
        if (bOSHClientConnListener != null) {
            this.connListeners.remove(bOSHClientConnListener);
            return;
        }
        throw new IllegalArgumentException(NULL_LISTENER);
    }

    public void addBOSHClientRequestListener(BOSHClientRequestListener bOSHClientRequestListener) {
        if (bOSHClientRequestListener != null) {
            this.requestListeners.add(bOSHClientRequestListener);
            return;
        }
        throw new IllegalArgumentException(NULL_LISTENER);
    }

    public void removeBOSHClientRequestListener(BOSHClientRequestListener bOSHClientRequestListener) {
        if (bOSHClientRequestListener != null) {
            this.requestListeners.remove(bOSHClientRequestListener);
            return;
        }
        throw new IllegalArgumentException(NULL_LISTENER);
    }

    public void addBOSHClientResponseListener(BOSHClientResponseListener bOSHClientResponseListener) {
        if (bOSHClientResponseListener != null) {
            this.responseListeners.add(bOSHClientResponseListener);
            return;
        }
        throw new IllegalArgumentException(NULL_LISTENER);
    }

    public void removeBOSHClientResponseListener(BOSHClientResponseListener bOSHClientResponseListener) {
        if (bOSHClientResponseListener != null) {
            this.responseListeners.remove(bOSHClientResponseListener);
            return;
        }
        throw new IllegalArgumentException(NULL_LISTENER);
    }

    /* JADX INFO: finally extract failed */
    public void send(ComposableBody composableBody) throws BOSHException {
        ComposableBody composableBody2;
        assertUnlocked();
        if (composableBody != null) {
            this.lock.lock();
            try {
                blockUntilSendable(composableBody);
                if (!isWorking()) {
                    if (!isTermination(composableBody)) {
                        throw new BOSHException("Cannot send message when session is closed");
                    }
                }
                long nextRID = this.requestIDSeq.getNextRID();
                CMSessionParams cMSessionParams = this.cmParams;
                if (cMSessionParams != null || !this.exchanges.isEmpty()) {
                    composableBody2 = applySessionData(nextRID, composableBody);
                    if (this.cmParams.isAckingRequests()) {
                        this.pendingRequestAcks.add(composableBody2);
                    }
                } else {
                    composableBody2 = applySessionCreationRequest(nextRID, composableBody);
                }
                HTTPExchange hTTPExchange = new HTTPExchange(composableBody2);
                this.exchanges.add(hTTPExchange);
                this.notEmpty.signalAll();
                clearEmptyRequest();
                this.lock.unlock();
                AbstractBody request = hTTPExchange.getRequest();
                hTTPExchange.setHTTPResponse(this.httpSender.send(cMSessionParams, request));
                fireRequestSent(request);
            } catch (Throwable th) {
                this.lock.unlock();
                throw th;
            }
        } else {
            throw new IllegalArgumentException("Message body may not be null");
        }
    }

    public boolean pause() {
        assertUnlocked();
        this.lock.lock();
        try {
            CMSessionParams cMSessionParams = this.cmParams;
            if (cMSessionParams != null) {
                AttrMaxPause maxPause = cMSessionParams.getMaxPause();
                if (maxPause != null) {
                    this.lock.unlock();
                    try {
                        send(ComposableBody.builder().setAttribute(Attributes.PAUSE, maxPause.toString()).build());
                        return true;
                    } catch (BOSHException e) {
                        LOG.log(Level.FINEST, "Could not send pause", e);
                        return true;
                    }
                }
            }
            return false;
        } finally {
            this.lock.unlock();
        }
    }

    public void disconnect() throws BOSHException {
        disconnect(ComposableBody.builder().build());
    }

    public void disconnect(ComposableBody composableBody) throws BOSHException {
        if (composableBody != null) {
            ComposableBody.Builder rebuild = composableBody.rebuild();
            rebuild.setAttribute(Attributes.TYPE, TERMINATE);
            send(rebuild.build());
            return;
        }
        throw new IllegalArgumentException("Message body may not be null");
    }

    public void close() {
        dispose(new BOSHException("Session explicitly closed by caller"));
    }

    /* access modifiers changed from: package-private */
    public CMSessionParams getCMSessionParams() {
        this.lock.lock();
        try {
            return this.cmParams;
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void drain() {
        this.lock.lock();
        try {
            LOG.finest("Waiting while draining...");
            ScheduledFuture<?> emptyFuture;
            while (isWorking() && ((emptyFuture = this.emptyRequestFuture) == null || emptyFuture.isDone())) {
                this.drained.await();
            }
            LOG.finest("Drained");
            this.lock.unlock();
        } catch (InterruptedException e) {
            LOG.log(Level.FINEST, INTERRUPTED, e);
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public void setExchangeInterceptor(ExchangeInterceptor exchangeInterceptor) {
        this.exchInterceptor.set(exchangeInterceptor);
    }

    private void init() {
        assertUnlocked();
        this.lock.lock();
        try {
            this.httpSender.init(this.cfg);
            Thread thread = new Thread(this.procRunnable);
            this.procThread = thread;
            thread.setDaemon(true);
            Thread thread2 = this.procThread;
            thread2.setName(BOSHClient.class.getSimpleName() + "[" + System.identityHashCode(this) + "]: Receive thread");
            this.procThread.start();
        } finally {
            this.lock.unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    private void dispose(Throwable th) {
        assertUnlocked();
        this.lock.lock();
        try {
            if (this.procThread != null) {
                this.procThread = null;
                this.lock.unlock();
                if (th == null) {
                    fireConnectionClosed();
                } else {
                    fireConnectionClosedOnError(th);
                }
                this.lock.lock();
                try {
                    clearEmptyRequest();
                    this.exchanges = null;
                    this.cmParams = null;
                    this.pendingResponseAcks = null;
                    this.pendingRequestAcks = null;
                    this.notEmpty.signalAll();
                    this.notFull.signalAll();
                    this.drained.signalAll();
                    this.lock.unlock();
                    this.httpSender.destroy();
                    this.schedExec.shutdownNow();
                } catch (Throwable th2) {
                    this.lock.unlock();
                    throw th2;
                }
            }
        } finally {
            this.lock.unlock();
        }
    }

    private static boolean isPause(AbstractBody abstractBody) {
        return abstractBody.getAttribute(Attributes.PAUSE) != null;
    }

    private static boolean isTermination(AbstractBody abstractBody) {
        return TERMINATE.equals(abstractBody.getAttribute(Attributes.TYPE));
    }

    private TerminalBindingCondition getTerminalBindingCondition(int i, AbstractBody abstractBody) {
        assertLocked();
        if (isTermination(abstractBody)) {
            return TerminalBindingCondition.forString(abstractBody.getAttribute(Attributes.CONDITION));
        }
        CMSessionParams cMSessionParams = this.cmParams;
        if (cMSessionParams == null || cMSessionParams.getVersion() != null) {
            return null;
        }
        return TerminalBindingCondition.forHTTPResponseCode(i);
    }

    private boolean isImmediatelySendable(AbstractBody abstractBody) {
        int intValue;
        assertLocked();
        CMSessionParams cMSessionParams = this.cmParams;
        if (cMSessionParams == null) {
            return this.exchanges.isEmpty();
        }
        AttrRequests requests = cMSessionParams.getRequests();
        if (requests == null || this.exchanges.size() < (intValue = requests.intValue())) {
            return true;
        }
        if (this.exchanges.size() != intValue) {
            return false;
        }
        if (isTermination(abstractBody) || isPause(abstractBody)) {
            return true;
        }
        return false;
    }

    private boolean isWorking() {
        assertLocked();
        return this.procThread != null;
    }

    private void blockUntilSendable(AbstractBody abstractBody) {
        assertLocked();
        while (isWorking() && !isImmediatelySendable(abstractBody)) {
            try {
                this.notFull.await();
            } catch (InterruptedException e) {
                LOG.log(Level.FINEST, INTERRUPTED, e);
            }
        }
    }

    private ComposableBody applySessionCreationRequest(long j, ComposableBody composableBody) throws BOSHException {
        assertLocked();
        ComposableBody.Builder rebuild = composableBody.rebuild();
        rebuild.setAttribute(Attributes.TO, this.cfg.getTo());
        rebuild.setAttribute(Attributes.XML_LANG, this.cfg.getLang());
        rebuild.setAttribute(Attributes.VER, AttrVersion.getSupportedVersion().toString());
        rebuild.setAttribute(Attributes.WAIT, "60");
        rebuild.setAttribute(Attributes.HOLD, "1");
        rebuild.setAttribute(Attributes.RID, Long.toString(j));
        applyRoute(rebuild);
        applyFrom(rebuild);
        rebuild.setAttribute(Attributes.ACK, "1");
        rebuild.setAttribute(Attributes.SID, (String) null);
        return rebuild.build();
    }

    private void applyRoute(ComposableBody.Builder builder) {
        assertLocked();
        String route = this.cfg.getRoute();
        if (route != null) {
            builder.setAttribute(Attributes.ROUTE, route);
        }
    }

    private void applyFrom(ComposableBody.Builder builder) {
        assertLocked();
        String from = this.cfg.getFrom();
        if (from != null) {
            builder.setAttribute(Attributes.FROM, from);
        }
    }

    private ComposableBody applySessionData(long j, ComposableBody composableBody) throws BOSHException {
        assertLocked();
        ComposableBody.Builder rebuild = composableBody.rebuild();
        rebuild.setAttribute(Attributes.SID, this.cmParams.getSessionID().toString());
        rebuild.setAttribute(Attributes.RID, Long.toString(j));
        applyResponseAcknowledgement(rebuild, j);
        return rebuild.build();
    }

    private void applyResponseAcknowledgement(ComposableBody.Builder builder, long j) {
        assertLocked();
        if (!this.responseAck.equals(-1L)) {
            if (!this.responseAck.equals(Long.valueOf(j - 1))) {
                builder.setAttribute(Attributes.ACK, this.responseAck.toString());
            }
        }
    }

    /* access modifiers changed from: private */
    public void processMessages() {
        LOG.log(Level.FINEST, "Processing thread starting");
        while (true) {
            try {
                HTTPExchange nextExchange = nextExchange();
                if (nextExchange == null) {
                    LOG.log(Level.FINEST, "Processing thread exiting");
                    return;
                }
                ExchangeInterceptor exchangeInterceptor = this.exchInterceptor.get();
                if (exchangeInterceptor != null) {
                    HTTPExchange interceptExchange = exchangeInterceptor.interceptExchange(nextExchange);
                    if (interceptExchange == null) {
                        Logger logger = LOG;
                        Level level = Level.FINE;
                        logger.log(level, "Discarding exchange on request of test hook: RID=" + nextExchange.getRequest().getAttribute(Attributes.RID));
                        this.lock.lock();
                        this.exchanges.remove(nextExchange);
                        this.lock.unlock();
                    } else {
                        nextExchange = interceptExchange;
                    }
                }
                processExchange(nextExchange);
            } catch (Throwable th) {
                LOG.log(Level.FINEST, "Processing thread exiting");
                throw th;
            }
        }
    }

    private HTTPExchange nextExchange() {
        assertUnlocked();
        Thread currentThread = Thread.currentThread();
        this.lock.lock();
        HTTPExchange hTTPExchange = null;
        while (true) {
            try {
                if (currentThread.equals(this.procThread)) {
                    hTTPExchange = this.exchanges.peek();
                    if (hTTPExchange == null) {
                        this.notEmpty.await();
                        continue;
                    }
                    if (hTTPExchange != null) {
                        break;
                    }
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                LOG.log(Level.FINEST, INTERRUPTED, e);
                continue;
            } catch (Throwable th) {
                this.lock.unlock();
                throw th;
            }
        }
        this.lock.unlock();
        return hTTPExchange;
    }

    /* JADX INFO: finally extract failed */
    private void processExchange(HTTPExchange hTTPExchange) {
        assertUnlocked();
        try {
            HTTPResponse hTTPResponse = hTTPExchange.getHTTPResponse();
            AbstractBody body = hTTPResponse.getBody();
            int hTTPStatus = hTTPResponse.getHTTPStatus();
            fireResponseReceived(body);
            AbstractBody request = hTTPExchange.getRequest();
            this.lock.lock();
            try {
                if (!isWorking()) {
                    this.lock.unlock();
                    if (this.lock.isHeldByCurrentThread()) {
                        try {
                            this.exchanges.remove(hTTPExchange);
                            if (this.exchanges.isEmpty()) {
                                scheduleEmptyRequest(processPauseRequest(request));
                            }
                            this.notFull.signalAll();
                        } finally {
                            this.lock.unlock();
                        }
                    }
                } else {
                    if (this.cmParams == null) {
                        this.cmParams = CMSessionParams.fromSessionInit(request, body);
                        fireConnectionEstablished();
                    }
                    CMSessionParams cMSessionParams = this.cmParams;
                    checkForTerminalBindingConditions(body, hTTPStatus);
                    ArrayList<HTTPExchange> arrayList = null;
                    if (isTermination(body)) {
                        this.lock.unlock();
                        dispose((Throwable) null);
                        if (this.lock.isHeldByCurrentThread()) {
                            try {
                                this.exchanges.remove(hTTPExchange);
                                if (this.exchanges.isEmpty()) {
                                    scheduleEmptyRequest(processPauseRequest(request));
                                }
                                this.notFull.signalAll();
                            } finally {
                                this.lock.unlock();
                            }
                        }
                    } else {
                        if (isRecoverableBindingCondition(body)) {
                            arrayList = new ArrayList<>(this.exchanges.size());
                            for (HTTPExchange request2 : this.exchanges) {
                                arrayList.add(new HTTPExchange(request2.getRequest()));
                            }
                            for (HTTPExchange add : arrayList) {
                                this.exchanges.add(add);
                            }
                        } else {
                            processRequestAcknowledgements(request, body);
                            processResponseAcknowledgementData(request);
                            HTTPExchange processResponseAcknowledgementReport = processResponseAcknowledgementReport(body);
                            if (processResponseAcknowledgementReport != null) {
                                arrayList = new ArrayList<>(1);
                                arrayList.add(processResponseAcknowledgementReport);
                                this.exchanges.add(processResponseAcknowledgementReport);
                            }
                        }
                        if (this.lock.isHeldByCurrentThread()) {
                            try {
                                this.exchanges.remove(hTTPExchange);
                                if (this.exchanges.isEmpty()) {
                                    scheduleEmptyRequest(processPauseRequest(request));
                                }
                                this.notFull.signalAll();
                            } finally {
                                this.lock.unlock();
                            }
                        }
                        if (arrayList != null) {
                            for (HTTPExchange hTTPExchange2 : arrayList) {
                                hTTPExchange2.setHTTPResponse(this.httpSender.send(cMSessionParams, hTTPExchange2.getRequest()));
                                fireRequestSent(hTTPExchange2.getRequest());
                            }
                        }
                    }
                }
            } catch (BOSHException e) {
                try {
                    LOG.log(Level.FINEST, "Could not process response", e);
                    this.lock.unlock();
                    dispose(e);
                    if (this.lock.isHeldByCurrentThread()) {
                        this.exchanges.remove(hTTPExchange);
                        if (this.exchanges.isEmpty()) {
                            scheduleEmptyRequest(processPauseRequest(request));
                        }
                        this.notFull.signalAll();
                    }
                } catch (Throwable th) {
                    if (this.lock.isHeldByCurrentThread()) {
                        this.exchanges.remove(hTTPExchange);
                        if (this.exchanges.isEmpty()) {
                            scheduleEmptyRequest(processPauseRequest(request));
                        }
                        this.notFull.signalAll();
                    }
                    throw th;
                } finally {
                    this.lock.unlock();
                }
            } catch (Throwable th2) {
                this.lock.unlock();
                throw th2;
            }
        } catch (BOSHException e2) {
            LOG.log(Level.FINEST, "Could not obtain response", e2);
            dispose(e2);
        } catch (InterruptedException e3) {
            LOG.log(Level.FINEST, INTERRUPTED, e3);
            dispose(e3);
        }
    }

    private void clearEmptyRequest() {
        assertLocked();
        ScheduledFuture scheduledFuture = this.emptyRequestFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            this.emptyRequestFuture = null;
        }
    }

    private long getDefaultEmptyRequestDelay() {
        int i;
        assertLocked();
        AttrPolling pollingInterval = this.cmParams.getPollingInterval();
        if (pollingInterval == null) {
            i = EMPTY_REQUEST_DELAY;
        } else {
            i = pollingInterval.getInMilliseconds();
        }
        return (long) i;
    }

    private void scheduleEmptyRequest(long j) {
        assertLocked();
        if (j >= 0) {
            clearEmptyRequest();
            if (isWorking()) {
                Logger logger = LOG;
                if (logger.isLoggable(Level.FINER)) {
                    logger.finer("Scheduling empty request in " + j + "ms");
                }
                try {
                    this.emptyRequestFuture = this.schedExec.schedule(this.emptyRequestRunnable, j, TimeUnit.MILLISECONDS);
                } catch (RejectedExecutionException e) {
                    LOG.log(Level.FINEST, "Could not schedule empty request", e);
                }
                this.drained.signalAll();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Empty request delay must be >= 0 (was: " + j + ")");
    }

    /* access modifiers changed from: private */
    public void sendEmptyRequest() {
        assertUnlocked();
        LOG.finest("Sending empty request");
        try {
            send(ComposableBody.builder().build());
        } catch (BOSHException e) {
            dispose(e);
        }
    }

    private void assertLocked() {
        if (ASSERTIONS && !this.lock.isHeldByCurrentThread()) {
            throw new AssertionError("Lock is not held by current thread");
        }
    }

    private void assertUnlocked() {
        if (ASSERTIONS && this.lock.isHeldByCurrentThread()) {
            throw new AssertionError("Lock is held by current thread");
        }
    }

    private void checkForTerminalBindingConditions(AbstractBody abstractBody, int i) throws BOSHException {
        TerminalBindingCondition terminalBindingCondition = getTerminalBindingCondition(i, abstractBody);
        if (terminalBindingCondition != null) {
            throw new BOSHException("Terminal binding condition encountered: " + terminalBindingCondition.getCondition() + "  (" + terminalBindingCondition.getMessage() + ")");
        }
    }

    private static boolean isRecoverableBindingCondition(AbstractBody abstractBody) {
        return "error".equals(abstractBody.getAttribute(Attributes.TYPE));
    }

    private long processPauseRequest(AbstractBody abstractBody) {
        assertLocked();
        CMSessionParams cMSessionParams = this.cmParams;
        if (!(cMSessionParams == null || cMSessionParams.getMaxPause() == null)) {
            try {
                AttrPause createFromString = AttrPause.createFromString(abstractBody.getAttribute(Attributes.PAUSE));
                if (createFromString != null) {
                    long inMilliseconds = (long) (createFromString.getInMilliseconds() - PAUSE_MARGIN);
                    return inMilliseconds < 0 ? (long) EMPTY_REQUEST_DELAY : inMilliseconds;
                }
            } catch (BOSHException e) {
                LOG.log(Level.FINEST, "Could not extract", e);
            }
        }
        return getDefaultEmptyRequestDelay();
    }

    private void processRequestAcknowledgements(AbstractBody abstractBody, AbstractBody abstractBody2) {
        Long l;
        assertLocked();
        if (this.cmParams.isAckingRequests() && abstractBody2.getAttribute(Attributes.REPORT) == null) {
            String attribute = abstractBody2.getAttribute(Attributes.ACK);
            if (attribute == null) {
                l = Long.valueOf(Long.parseLong(abstractBody.getAttribute(Attributes.RID)));
            } else {
                l = Long.valueOf(Long.parseLong(attribute));
            }
            Logger logger = LOG;
            if (logger.isLoggable(Level.FINEST)) {
                logger.finest("Removing pending acks up to: " + l);
            }
            Iterator<ComposableBody> it = this.pendingRequestAcks.iterator();
            while (it.hasNext()) {
                if (Long.valueOf(Long.parseLong(it.next().getAttribute(Attributes.RID))).compareTo(l) <= 0) {
                    it.remove();
                }
            }
        }
    }

    private void processResponseAcknowledgementData(AbstractBody abstractBody) {
        assertLocked();
        Long valueOf = Long.valueOf(Long.parseLong(abstractBody.getAttribute(Attributes.RID)));
        if (this.responseAck.equals(-1L)) {
            this.responseAck = valueOf;
            return;
        }
        this.pendingResponseAcks.add(valueOf);
        Long valueOf2 = Long.valueOf(this.responseAck.longValue() + 1);
        while (!this.pendingResponseAcks.isEmpty() && valueOf2.equals(this.pendingResponseAcks.first())) {
            this.responseAck = valueOf2;
            this.pendingResponseAcks.remove(valueOf2);
            valueOf2 = Long.valueOf(valueOf2.longValue() + 1);
        }
    }

    private HTTPExchange processResponseAcknowledgementReport(AbstractBody abstractBody) throws BOSHException {
        assertLocked();
        String attribute = abstractBody.getAttribute(Attributes.REPORT);
        AbstractBody abstractBody2 = null;
        if (attribute == null) {
            return null;
        }
        Long valueOf = Long.valueOf(Long.parseLong(attribute));
        Long valueOf2 = Long.valueOf(Long.parseLong(abstractBody.getAttribute(Attributes.TIME)));
        Logger logger = LOG;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("Received report of missing request (RID=" + valueOf + ", time=" + valueOf2 + "ms)");
        }
        Iterator<ComposableBody> it = this.pendingRequestAcks.iterator();
        while (it.hasNext() && abstractBody2 == null) {
            AbstractBody next = it.next();
            if (valueOf.equals(Long.valueOf(Long.parseLong(next.getAttribute(Attributes.RID))))) {
                abstractBody2 = next;
            }
        }
        if (abstractBody2 != null) {
            HTTPExchange hTTPExchange = new HTTPExchange(abstractBody2);
            this.exchanges.add(hTTPExchange);
            this.notEmpty.signalAll();
            return hTTPExchange;
        }
        throw new BOSHException("Report of missing message with RID '" + attribute + "' but local copy of that request was not found");
    }

    private void fireRequestSent(AbstractBody abstractBody) {
        assertUnlocked();
        BOSHMessageEvent bOSHMessageEvent = null;
        for (BOSHClientRequestListener next : this.requestListeners) {
            if (bOSHMessageEvent == null) {
                bOSHMessageEvent = BOSHMessageEvent.createRequestSentEvent(this, abstractBody);
            }
            try {
                next.requestSent(bOSHMessageEvent);
            } catch (Exception e) {
                LOG.log(Level.WARNING, UNHANDLED, e);
            }
        }
    }

    private void fireResponseReceived(AbstractBody abstractBody) {
        assertUnlocked();
        BOSHMessageEvent bOSHMessageEvent = null;
        for (BOSHClientResponseListener next : this.responseListeners) {
            if (bOSHMessageEvent == null) {
                bOSHMessageEvent = BOSHMessageEvent.createResponseReceivedEvent(this, abstractBody);
            }
            try {
                next.responseReceived(bOSHMessageEvent);
            } catch (Exception e) {
                LOG.log(Level.WARNING, UNHANDLED, e);
            }
        }
    }

    private void fireConnectionEstablished() {
        boolean isHeldByCurrentThread = this.lock.isHeldByCurrentThread();
        if (isHeldByCurrentThread) {
            this.lock.unlock();
        }
        BOSHClientConnEvent bOSHClientConnEvent = null;
        try {
            for (BOSHClientConnListener next : this.connListeners) {
                if (bOSHClientConnEvent == null) {
                    bOSHClientConnEvent = BOSHClientConnEvent.createConnectionEstablishedEvent(this);
                }
                next.connectionEvent(bOSHClientConnEvent);
            }
            if (isHeldByCurrentThread) {
                this.lock.lock();
            }
        } catch (Exception e) {
            LOG.log(Level.WARNING, UNHANDLED, e);
        } catch (Throwable th) {
            if (isHeldByCurrentThread) {
                this.lock.lock();
            }
            throw th;
        }
    }

    private void fireConnectionClosed() {
        assertUnlocked();
        BOSHClientConnEvent bOSHClientConnEvent = null;
        for (BOSHClientConnListener next : this.connListeners) {
            if (bOSHClientConnEvent == null) {
                bOSHClientConnEvent = BOSHClientConnEvent.createConnectionClosedEvent(this);
            }
            try {
                next.connectionEvent(bOSHClientConnEvent);
            } catch (Exception e) {
                LOG.log(Level.WARNING, UNHANDLED, e);
            }
        }
    }

    private void fireConnectionClosedOnError(Throwable th) {
        assertUnlocked();
        BOSHClientConnEvent bOSHClientConnEvent = null;
        for (BOSHClientConnListener next : this.connListeners) {
            if (bOSHClientConnEvent == null) {
                bOSHClientConnEvent = BOSHClientConnEvent.createConnectionClosedOnErrorEvent(this, this.pendingRequestAcks, th);
            }
            try {
                next.connectionEvent(bOSHClientConnEvent);
            } catch (Exception e) {
                LOG.log(Level.WARNING, UNHANDLED, e);
            }
        }
    }
}
