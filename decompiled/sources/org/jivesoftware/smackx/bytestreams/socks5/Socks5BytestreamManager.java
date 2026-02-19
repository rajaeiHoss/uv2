package org.jivesoftware.smackx.bytestreams.socks5;

import com.google.android.gms.search.SearchAuth;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import org.jivesoftware.smack.AbstractConnectionListener;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.bytestreams.BytestreamListener;
import org.jivesoftware.smackx.bytestreams.BytestreamManager;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import org.jivesoftware.smackx.packet.DiscoverInfo;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.packet.SyncPacketSend;

public final class Socks5BytestreamManager implements BytestreamManager {
    public static final String NAMESPACE = "http://jabber.org/protocol/bytestreams";
    private static final String SESSION_ID_PREFIX = "js5_";
    private static final Map<Connection, Socks5BytestreamManager> managers = new HashMap();
    private static final Random randomGenerator = new Random();
    private final List<BytestreamListener> allRequestListeners = Collections.synchronizedList(new LinkedList());
    private final Connection connection;
    private List<String> ignoredBytestreamRequests = Collections.synchronizedList(new LinkedList());
    private final InitiationListener initiationListener;
    private String lastWorkingProxy = null;
    private final List<String> proxyBlacklist = Collections.synchronizedList(new LinkedList());
    private int proxyConnectionTimeout = SearchAuth.StatusCodes.AUTH_DISABLED;
    private boolean proxyPrioritizationEnabled = true;
    private int targetResponseTimeout = SearchAuth.StatusCodes.AUTH_DISABLED;
    private final Map<String, BytestreamListener> userListeners = new ConcurrentHashMap();

    static {
        Connection.addConnectionCreationListener(new ConnectionCreationListener() {
            public void connectionCreated(Connection connection) {
                final Socks5BytestreamManager bytestreamManager = Socks5BytestreamManager.getBytestreamManager(connection);
                connection.addConnectionListener(new AbstractConnectionListener() {
                    public void connectionClosed() {
                        bytestreamManager.disableService();
                    }
                });
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager getBytestreamManager(org.jivesoftware.smack.Connection r3) {
        /*
            java.lang.Class<org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager> r0 = org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager.class
            monitor-enter(r0)
            if (r3 != 0) goto L_0x0008
            r3 = 0
            monitor-exit(r0)
            return r3
        L_0x0008:
            java.util.Map<org.jivesoftware.smack.Connection, org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager> r1 = managers     // Catch:{ all -> 0x001f }
            java.lang.Object r2 = r1.get(r3)     // Catch:{ all -> 0x001f }
            org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager r2 = (org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager) r2     // Catch:{ all -> 0x001f }
            if (r2 != 0) goto L_0x001d
            org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager r2 = new org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager     // Catch:{ all -> 0x001f }
            r2.<init>(r3)     // Catch:{ all -> 0x001f }
            r1.put(r3, r2)     // Catch:{ all -> 0x001f }
            r2.activate()     // Catch:{ all -> 0x001f }
        L_0x001d:
            monitor-exit(r0)
            return r2
        L_0x001f:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager.getBytestreamManager(org.jivesoftware.smack.Connection):org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager");
    }

    private Socks5BytestreamManager(Connection connection2) {
        this.connection = connection2;
        this.initiationListener = new InitiationListener(this);
    }

    public void addIncomingBytestreamListener(BytestreamListener bytestreamListener) {
        this.allRequestListeners.add(bytestreamListener);
    }

    public void removeIncomingBytestreamListener(BytestreamListener bytestreamListener) {
        this.allRequestListeners.remove(bytestreamListener);
    }

    public void addIncomingBytestreamListener(BytestreamListener bytestreamListener, String str) {
        this.userListeners.put(str, bytestreamListener);
    }

    public void removeIncomingBytestreamListener(String str) {
        this.userListeners.remove(str);
    }

    public void ignoreBytestreamRequestOnce(String str) {
        this.ignoredBytestreamRequests.add(str);
    }

    public synchronized void disableService() {
        this.connection.removePacketListener(this.initiationListener);
        this.initiationListener.shutdown();
        this.allRequestListeners.clear();
        this.userListeners.clear();
        this.lastWorkingProxy = null;
        this.proxyBlacklist.clear();
        this.ignoredBytestreamRequests.clear();
        Map<Connection, Socks5BytestreamManager> map = managers;
        map.remove(this.connection);
        if (map.size() == 0) {
            Socks5Proxy.getSocks5Proxy().stop();
        }
        ServiceDiscoveryManager instanceFor = ServiceDiscoveryManager.getInstanceFor(this.connection);
        if (instanceFor != null) {
            instanceFor.removeFeature(NAMESPACE);
        }
    }

    public int getTargetResponseTimeout() {
        if (this.targetResponseTimeout <= 0) {
            this.targetResponseTimeout = SearchAuth.StatusCodes.AUTH_DISABLED;
        }
        return this.targetResponseTimeout;
    }

    public void setTargetResponseTimeout(int i) {
        this.targetResponseTimeout = i;
    }

    public int getProxyConnectionTimeout() {
        if (this.proxyConnectionTimeout <= 0) {
            this.proxyConnectionTimeout = SearchAuth.StatusCodes.AUTH_DISABLED;
        }
        return this.proxyConnectionTimeout;
    }

    public void setProxyConnectionTimeout(int i) {
        this.proxyConnectionTimeout = i;
    }

    public boolean isProxyPrioritizationEnabled() {
        return this.proxyPrioritizationEnabled;
    }

    public void setProxyPrioritizationEnabled(boolean z) {
        this.proxyPrioritizationEnabled = z;
    }

    public Socks5BytestreamSession establishSession(String str) throws XMPPException, IOException, InterruptedException {
        return establishSession(str, getNextSessionID());
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:27|28|29) */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00ac, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b5, code lost:
        throw new java.io.IOException("Timeout while connecting to SOCKS5 proxy");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b6, code lost:
        r8.removeTransfer(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00b9, code lost:
        throw r11;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x00ae */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamSession establishSession(java.lang.String r11, java.lang.String r12) throws org.jivesoftware.smack.XMPPException, java.io.IOException, java.lang.InterruptedException {
        /*
            r10 = this;
            boolean r0 = r10.supportsSocks5(r11)
            if (r0 == 0) goto L_0x00c2
            java.util.List r0 = r10.determineProxies()
            java.util.List r0 = r10.determineStreamHostInfos(r0)
            org.jivesoftware.smack.Connection r1 = r10.connection
            java.lang.String r1 = r1.getUser()
            java.lang.String r1 = org.jivesoftware.smackx.bytestreams.socks5.Socks5Utils.createDigest(r12, r1, r11)
            boolean r2 = r0.isEmpty()
            if (r2 != 0) goto L_0x00ba
            boolean r2 = r10.proxyPrioritizationEnabled
            if (r2 == 0) goto L_0x004d
            java.lang.String r2 = r10.lastWorkingProxy
            if (r2 == 0) goto L_0x004d
            r2 = 0
            java.util.Iterator r3 = r0.iterator()
        L_0x002b:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0044
            java.lang.Object r4 = r3.next()
            org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream$StreamHost r4 = (org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHost) r4
            java.lang.String r5 = r4.getJID()
            java.lang.String r6 = r10.lastWorkingProxy
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x002b
            r2 = r4
        L_0x0044:
            if (r2 == 0) goto L_0x004d
            r0.remove(r2)
            r3 = 0
            r0.add(r3, r2)
        L_0x004d:
            org.jivesoftware.smackx.bytestreams.socks5.Socks5Proxy r8 = org.jivesoftware.smackx.bytestreams.socks5.Socks5Proxy.getSocks5Proxy()
            r8.addTransfer(r1)     // Catch:{ TimeoutException -> 0x00ae }
            org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream r0 = r10.createBytestreamInitiation(r12, r11, r0)     // Catch:{ TimeoutException -> 0x00ae }
            org.jivesoftware.smack.Connection r2 = r10.connection     // Catch:{ TimeoutException -> 0x00ae }
            int r3 = r10.getTargetResponseTimeout()     // Catch:{ TimeoutException -> 0x00ae }
            long r3 = (long) r3     // Catch:{ TimeoutException -> 0x00ae }
            org.jivesoftware.smack.packet.Packet r2 = org.jivesoftware.smackx.packet.SyncPacketSend.getReply(r2, r0, r3)     // Catch:{ TimeoutException -> 0x00ae }
            org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream r2 = (org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream) r2     // Catch:{ TimeoutException -> 0x00ae }
            org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream$StreamHostUsed r2 = r2.getUsedHost()     // Catch:{ TimeoutException -> 0x00ae }
            java.lang.String r2 = r2.getJID()     // Catch:{ TimeoutException -> 0x00ae }
            org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream$StreamHost r0 = r0.getStreamHost(r2)     // Catch:{ TimeoutException -> 0x00ae }
            if (r0 == 0) goto L_0x00a4
            org.jivesoftware.smackx.bytestreams.socks5.Socks5ClientForInitiator r9 = new org.jivesoftware.smackx.bytestreams.socks5.Socks5ClientForInitiator     // Catch:{ TimeoutException -> 0x00ae }
            org.jivesoftware.smack.Connection r5 = r10.connection     // Catch:{ TimeoutException -> 0x00ae }
            r2 = r9
            r3 = r0
            r4 = r1
            r6 = r12
            r7 = r11
            r2.<init>(r3, r4, r5, r6, r7)     // Catch:{ TimeoutException -> 0x00ae }
            int r11 = r10.getProxyConnectionTimeout()     // Catch:{ TimeoutException -> 0x00ae }
            java.net.Socket r11 = r9.getSocket(r11)     // Catch:{ TimeoutException -> 0x00ae }
            java.lang.String r12 = r0.getJID()     // Catch:{ TimeoutException -> 0x00ae }
            r10.lastWorkingProxy = r12     // Catch:{ TimeoutException -> 0x00ae }
            org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamSession r12 = new org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamSession     // Catch:{ TimeoutException -> 0x00ae }
            java.lang.String r0 = r0.getJID()     // Catch:{ TimeoutException -> 0x00ae }
            org.jivesoftware.smack.Connection r2 = r10.connection     // Catch:{ TimeoutException -> 0x00ae }
            java.lang.String r2 = r2.getUser()     // Catch:{ TimeoutException -> 0x00ae }
            boolean r0 = r0.equals(r2)     // Catch:{ TimeoutException -> 0x00ae }
            r12.<init>(r11, r0)     // Catch:{ TimeoutException -> 0x00ae }
            r8.removeTransfer(r1)
            return r12
        L_0x00a4:
            org.jivesoftware.smack.XMPPException r11 = new org.jivesoftware.smack.XMPPException     // Catch:{ TimeoutException -> 0x00ae }
            java.lang.String r12 = "Remote user responded with unknown host"
            r11.<init>((java.lang.String) r12)     // Catch:{ TimeoutException -> 0x00ae }
            throw r11     // Catch:{ TimeoutException -> 0x00ae }
        L_0x00ac:
            r11 = move-exception
            goto L_0x00b6
        L_0x00ae:
            java.io.IOException r11 = new java.io.IOException     // Catch:{ all -> 0x00ac }
            java.lang.String r12 = "Timeout while connecting to SOCKS5 proxy"
            r11.<init>(r12)     // Catch:{ all -> 0x00ac }
            throw r11     // Catch:{ all -> 0x00ac }
        L_0x00b6:
            r8.removeTransfer(r1)
            throw r11
        L_0x00ba:
            org.jivesoftware.smack.XMPPException r11 = new org.jivesoftware.smack.XMPPException
            java.lang.String r12 = "no SOCKS5 proxies available"
            r11.<init>((java.lang.String) r12)
            throw r11
        L_0x00c2:
            org.jivesoftware.smack.XMPPException r12 = new org.jivesoftware.smack.XMPPException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r11)
            java.lang.String r11 = " doesn't support SOCKS5 Bytestream"
            r0.append(r11)
            java.lang.String r11 = r0.toString()
            r12.<init>((java.lang.String) r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager.establishSession(java.lang.String, java.lang.String):org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamSession");
    }

    private boolean supportsSocks5(String str) throws XMPPException {
        return ServiceDiscoveryManager.getInstanceFor(this.connection).discoverInfo(str).containsFeature(NAMESPACE);
    }

    private List<String> determineProxies() throws XMPPException {
        ServiceDiscoveryManager instanceFor = ServiceDiscoveryManager.getInstanceFor(this.connection);
        ArrayList arrayList = new ArrayList();
        Iterator<DiscoverItems.Item> items = instanceFor.discoverItems(this.connection.getServiceName()).getItems();
        while (items.hasNext()) {
            DiscoverItems.Item next = items.next();
            if (!this.proxyBlacklist.contains(next.getEntityID())) {
                try {
                    Iterator<DiscoverInfo.Identity> identities = instanceFor.discoverInfo(next.getEntityID()).getIdentities();
                    while (true) {
                        if (!identities.hasNext()) {
                            break;
                        }
                        DiscoverInfo.Identity next2 = identities.next();
                        if ("proxy".equalsIgnoreCase(next2.getCategory()) && "bytestreams".equalsIgnoreCase(next2.getType())) {
                            arrayList.add(next.getEntityID());
                            break;
                        }
                        this.proxyBlacklist.add(next.getEntityID());
                    }
                } catch (XMPPException unused) {
                    this.proxyBlacklist.add(next.getEntityID());
                }
            }
        }
        return arrayList;
    }

    private List<Bytestream.StreamHost> determineStreamHostInfos(List<String> list) {
        ArrayList arrayList = new ArrayList();
        List<Bytestream.StreamHost> localStreamHost = getLocalStreamHost();
        if (localStreamHost != null) {
            arrayList.addAll(localStreamHost);
        }
        for (String next : list) {
            try {
                arrayList.addAll(((Bytestream) SyncPacketSend.getReply(this.connection, createStreamHostRequest(next))).getStreamHosts());
            } catch (XMPPException unused) {
                this.proxyBlacklist.add(next);
            }
        }
        return arrayList;
    }

    private Bytestream createStreamHostRequest(String str) {
        Bytestream bytestream = new Bytestream();
        bytestream.setType(IQ.Type.GET);
        bytestream.setTo(str);
        return bytestream;
    }

    private List<Bytestream.StreamHost> getLocalStreamHost() {
        Socks5Proxy socks5Proxy = Socks5Proxy.getSocks5Proxy();
        if (!socks5Proxy.isRunning()) {
            return null;
        }
        List<String> localAddresses = socks5Proxy.getLocalAddresses();
        int port = socks5Proxy.getPort();
        if (localAddresses.size() < 1) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String streamHost : localAddresses) {
            Bytestream.StreamHost streamHost2 = new Bytestream.StreamHost(this.connection.getUser(), streamHost);
            streamHost2.setPort(port);
            arrayList.add(streamHost2);
        }
        return arrayList;
    }

    private Bytestream createBytestreamInitiation(String str, String str2, List<Bytestream.StreamHost> list) {
        Bytestream bytestream = new Bytestream(str);
        for (Bytestream.StreamHost addStreamHost : list) {
            bytestream.addStreamHost(addStreamHost);
        }
        bytestream.setType(IQ.Type.SET);
        bytestream.setTo(str2);
        return bytestream;
    }

    /* access modifiers changed from: protected */
    public void replyRejectPacket(IQ iq) {
        this.connection.sendPacket(IQ.createErrorResponse(iq, new XMPPError(XMPPError.Condition.no_acceptable)));
    }

    private void activate() {
        Connection connection2 = this.connection;
        InitiationListener initiationListener2 = this.initiationListener;
        connection2.addPacketListener(initiationListener2, initiationListener2.getFilter());
        enableService();
    }

    private void enableService() {
        ServiceDiscoveryManager instanceFor = ServiceDiscoveryManager.getInstanceFor(this.connection);
        if (!instanceFor.includesFeature(NAMESPACE)) {
            instanceFor.addFeature(NAMESPACE);
        }
    }

    private String getNextSessionID() {
        return SESSION_ID_PREFIX + Math.abs(randomGenerator.nextLong());
    }

    /* access modifiers changed from: protected */
    public Connection getConnection() {
        return this.connection;
    }

    /* access modifiers changed from: protected */
    public BytestreamListener getUserListener(String str) {
        return this.userListeners.get(str);
    }

    /* access modifiers changed from: protected */
    public List<BytestreamListener> getAllRequestListeners() {
        return this.allRequestListeners;
    }

    /* access modifiers changed from: protected */
    public List<String> getIgnoredBytestreamRequests() {
        return this.ignoredBytestreamRequests;
    }
}
