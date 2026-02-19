package org.jivesoftware.smack;

import com.kenai.jbosh.BOSHClient;
import com.kenai.jbosh.BOSHClientConfig;
import com.kenai.jbosh.BOSHClientConnEvent;
import com.kenai.jbosh.BOSHClientConnListener;
import com.kenai.jbosh.BOSHClientRequestListener;
import com.kenai.jbosh.BOSHClientResponseListener;
import com.kenai.jbosh.BOSHException;
import com.kenai.jbosh.BOSHMessageEvent;
import com.kenai.jbosh.BodyQName;
import com.kenai.jbosh.ComposableBody;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.Writer;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.util.StringUtils;

public class BOSHConnection extends Connection {
    public static final String BOSH_URI = "http://jabber.org/protocol/httpbind";
    public static final String XMPP_BOSH_NS = "urn:xmpp:xbosh";
    private boolean anonymous;
    protected String authID;
    private boolean authenticated;
    private BOSHClient client;
    /* access modifiers changed from: private */
    public final BOSHConfiguration config;
    /* access modifiers changed from: private */
    public boolean connected;
    /* access modifiers changed from: private */
    public boolean done;
    /* access modifiers changed from: private */
    public boolean isFirstInitialization;
    private ExecutorService listenerExecutor;
    /* access modifiers changed from: private */
    public Thread readerConsumer;
    /* access modifiers changed from: private */
    public PipedWriter readerPipe;
    private Roster roster;
    protected String sessionID;
    private String user;
    /* access modifiers changed from: private */
    public boolean wasAuthenticated;

    public boolean isSecureConnection() {
        return false;
    }

    public boolean isUsingCompression() {
        return false;
    }

    public BOSHConnection(boolean z, String str, int i, String str2, String str3) {
        super(new BOSHConfiguration(z, str, i, str2, str3));
        this.connected = false;
        this.authenticated = false;
        this.anonymous = false;
        this.isFirstInitialization = true;
        this.wasAuthenticated = false;
        this.done = false;
        this.authID = null;
        this.sessionID = null;
        this.user = null;
        this.roster = null;
        this.config = (BOSHConfiguration) getConfiguration();
    }

    public BOSHConnection(BOSHConfiguration bOSHConfiguration) {
        super(bOSHConfiguration);
        this.connected = false;
        this.authenticated = false;
        this.anonymous = false;
        this.isFirstInitialization = true;
        this.wasAuthenticated = false;
        this.done = false;
        this.authID = null;
        this.sessionID = null;
        this.user = null;
        this.roster = null;
        this.config = bOSHConfiguration;
    }

    public void connect() throws XMPPException {
        if (!this.connected) {
            this.done = false;
            try {
                BOSHClient bOSHClient = this.client;
                if (bOSHClient != null) {
                    bOSHClient.close();
                    this.client = null;
                }
                this.saslAuthentication.init();
                this.sessionID = null;
                this.authID = null;
                BOSHClientConfig.Builder create = BOSHClientConfig.Builder.create(this.config.getURI(), this.config.getServiceName());
                if (this.config.isProxyEnabled()) {
                    create.setProxy(this.config.getProxyAddress(), this.config.getProxyPort());
                }
                this.client = BOSHClient.create(create.build());
                this.listenerExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() {
                    public Thread newThread(Runnable runnable) {
                        Thread thread = new Thread(runnable, "Smack Listener Processor (" + BOSHConnection.this.connectionCounterValue + ")");
                        thread.setDaemon(true);
                        return thread;
                    }
                });
                this.client.addBOSHClientConnListener(new BOSHConnectionListener(this));
                this.client.addBOSHClientResponseListener(new BOSHPacketReader(this));
                if (this.config.isDebuggerEnabled()) {
                    initDebugger();
                    if (this.isFirstInitialization) {
                        if (this.debugger.getReaderListener() != null) {
                            addPacketListener(this.debugger.getReaderListener(), (PacketFilter) null);
                        }
                        if (this.debugger.getWriterListener() != null) {
                            addPacketSendingListener(this.debugger.getWriterListener(), (PacketFilter) null);
                        }
                    }
                }
                this.client.send(ComposableBody.builder().setNamespaceDefinition("xmpp", XMPP_BOSH_NS).setAttribute(BodyQName.createWithPrefix(XMPP_BOSH_NS, "version", "xmpp"), "1.0").build());
                synchronized (this) {
                    long currentTimeMillis = System.currentTimeMillis() + ((long) (SmackConfiguration.getPacketReplyTimeout() * 6));
                    while (!this.connected && System.currentTimeMillis() < currentTimeMillis) {
                        try {
                            wait(Math.abs(currentTimeMillis - System.currentTimeMillis()));
                        } catch (InterruptedException unused) {
                        }
                    }
                }
                if (!this.connected && !this.done) {
                    this.done = true;
                    String str = "Timeout reached for the connection to " + getHost() + ":" + getPort() + ".";
                    throw new XMPPException(str, new XMPPError(XMPPError.Condition.remote_server_timeout, str));
                }
            } catch (Exception e) {
                throw new XMPPException("Can't connect to " + getServiceName(), (Throwable) e);
            }
        } else {
            throw new IllegalStateException("Already connected to a server.");
        }
    }

    public String getConnectionID() {
        if (!this.connected) {
            return null;
        }
        String str = this.authID;
        if (str != null) {
            return str;
        }
        return this.sessionID;
    }

    public Roster getRoster() {
        if (this.roster == null) {
            return null;
        }
        if (!this.config.isRosterLoadedAtLogin()) {
            this.roster.reload();
        }
        if (!this.roster.rosterInitialized) {
            try {
                synchronized (this.roster) {
                    long packetReplyTimeout = (long) SmackConfiguration.getPacketReplyTimeout();
                    long currentTimeMillis = System.currentTimeMillis();
                    while (true) {
                        if (this.roster.rosterInitialized) {
                            break;
                        } else if (packetReplyTimeout <= 0) {
                            break;
                        } else {
                            this.roster.wait(packetReplyTimeout);
                            long currentTimeMillis2 = System.currentTimeMillis();
                            packetReplyTimeout -= currentTimeMillis2 - currentTimeMillis;
                            currentTimeMillis = currentTimeMillis2;
                        }
                    }
                }
            } catch (InterruptedException unused) {
            }
        }
        return this.roster;
    }

    public String getUser() {
        return this.user;
    }

    public boolean isAnonymous() {
        return this.anonymous;
    }

    public boolean isAuthenticated() {
        return this.authenticated;
    }

    public boolean isConnected() {
        return this.connected;
    }

    public void login(String str, String str2, String str3) throws XMPPException {
        String str4;
        if (!isConnected()) {
            throw new IllegalStateException("Not connected to server.");
        } else if (!this.authenticated) {
            String trim = str.toLowerCase().trim();
            if (!this.config.isSASLAuthenticationEnabled() || !this.saslAuthentication.hasNonAnonymousAuthentication()) {
                str4 = new NonSASLAuthentication(this).authenticate(trim, str2, str3);
            } else if (str2 != null) {
                str4 = this.saslAuthentication.authenticate(trim, str2, str3);
            } else {
                str4 = this.saslAuthentication.authenticate(trim, str3, this.config.getCallbackHandler());
            }
            if (str4 != null) {
                this.user = str4;
                this.config.setServiceName(StringUtils.parseServer(str4));
            } else {
                this.user = trim + "@" + getServiceName();
                if (str3 != null) {
                    this.user += "/" + str3;
                }
            }
            if (this.roster == null) {
                if (this.rosterStorage == null) {
                    this.roster = new Roster(this);
                } else {
                    this.roster = new Roster(this, this.rosterStorage);
                }
            }
            if (this.config.isRosterLoadedAtLogin()) {
                this.roster.reload();
            }
            if (this.config.isSendPresence()) {
                sendPacket(new Presence(Presence.Type.available));
            }
            this.authenticated = true;
            this.anonymous = false;
            this.config.setLoginInfo(trim, str2, str3);
            if (this.config.isDebuggerEnabled() && this.debugger != null) {
                this.debugger.userHasLogged(this.user);
            }
        } else {
            throw new IllegalStateException("Already logged in to server.");
        }
    }

    public void loginAnonymously() throws XMPPException {
        String str;
        if (!isConnected()) {
            throw new IllegalStateException("Not connected to server.");
        } else if (!this.authenticated) {
            if (!this.config.isSASLAuthenticationEnabled() || !this.saslAuthentication.hasAnonymousAuthentication()) {
                str = new NonSASLAuthentication(this).authenticateAnonymously();
            } else {
                str = this.saslAuthentication.authenticateAnonymously();
            }
            this.user = str;
            this.config.setServiceName(StringUtils.parseServer(str));
            this.roster = null;
            if (this.config.isSendPresence()) {
                sendPacket(new Presence(Presence.Type.available));
            }
            this.authenticated = true;
            this.anonymous = true;
            if (this.config.isDebuggerEnabled() && this.debugger != null) {
                this.debugger.userHasLogged(this.user);
            }
        } else {
            throw new IllegalStateException("Already logged in to server.");
        }
    }

    public void sendPacket(Packet packet) {
        if (isConnected()) {
            Objects.requireNonNull(packet, "Packet is null.");
            if (!this.done) {
                firePacketInterceptors(packet);
                try {
                    send(ComposableBody.builder().setPayloadXML(packet.toXML()).build());
                    firePacketSendingListeners(packet);
                } catch (BOSHException e) {
                    e.printStackTrace();
                }
            }
        } else {
            throw new IllegalStateException("Not connected to server.");
        }
    }

    public void disconnect(Presence presence) {
        if (this.connected) {
            shutdown(presence);
            Roster roster2 = this.roster;
            if (roster2 != null) {
                roster2.cleanup();
                this.roster = null;
            }
            this.sendListeners.clear();
            this.recvListeners.clear();
            this.collectors.clear();
            this.interceptors.clear();
            this.wasAuthenticated = false;
            this.isFirstInitialization = true;
            for (ConnectionListener connectionClosed : getConnectionListeners()) {
                try {
                    connectionClosed.connectionClosed();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void shutdown(Presence presence) {
        setWasAuthenticated(this.authenticated);
        this.authID = null;
        this.sessionID = null;
        this.done = true;
        this.authenticated = false;
        this.connected = false;
        this.isFirstInitialization = false;
        try {
            this.client.disconnect(ComposableBody.builder().setNamespaceDefinition("xmpp", XMPP_BOSH_NS).setPayloadXML(presence.toXML()).build());
            Thread.sleep(150);
        } catch (Exception unused) {
        }
        PipedWriter pipedWriter = this.readerPipe;
        if (pipedWriter != null) {
            try {
                pipedWriter.close();
            } catch (Throwable unused2) {
            }
            this.reader = null;
        }
        if (this.reader != null) {
            try {
                this.reader.close();
            } catch (Throwable unused3) {
            }
            this.reader = null;
        }
        if (this.writer != null) {
            try {
                this.writer.close();
            } catch (Throwable unused4) {
            }
            this.writer = null;
        }
        ExecutorService executorService = this.listenerExecutor;
        if (executorService != null) {
            executorService.shutdown();
        }
        this.readerConsumer = null;
    }

    private void setWasAuthenticated(boolean z) {
        if (!this.wasAuthenticated) {
            this.wasAuthenticated = z;
        }
    }

    /* access modifiers changed from: protected */
    public void send(ComposableBody composableBody) throws BOSHException {
        if (this.connected) {
            Objects.requireNonNull(composableBody, "Body mustn't be null!");
            if (this.sessionID != null) {
                composableBody = composableBody.rebuild().setAttribute(BodyQName.create(BOSH_URI, "sid"), this.sessionID).build();
            }
            this.client.send(composableBody);
            return;
        }
        throw new IllegalStateException("Not connected to a server!");
    }

    /* access modifiers changed from: protected */
    public void processPacket(Packet packet) {
        if (packet != null) {
            for (PacketCollector processPacket : getPacketCollectors()) {
                processPacket.processPacket(packet);
            }
            this.listenerExecutor.submit(new ListenerNotification(packet));
        }
    }

    /* access modifiers changed from: protected */
    public void initDebugger() {
        this.writer = new Writer() {
            public void close() {
            }

            public void flush() {
            }

            public void write(char[] cArr, int i, int i2) {
            }
        };
        try {
            this.readerPipe = new PipedWriter();
            this.reader = new PipedReader(this.readerPipe);
        } catch (IOException unused) {
        }
        super.initDebugger();
        this.client.addBOSHClientResponseListener(new BOSHClientResponseListener() {
            public void responseReceived(BOSHMessageEvent bOSHMessageEvent) {
                if (bOSHMessageEvent.getBody() != null) {
                    try {
                        BOSHConnection.this.readerPipe.write(bOSHMessageEvent.getBody().toXML());
                        BOSHConnection.this.readerPipe.flush();
                    } catch (Exception unused) {
                    }
                }
            }
        });
        this.client.addBOSHClientRequestListener(new BOSHClientRequestListener() {
            public void requestSent(BOSHMessageEvent bOSHMessageEvent) {
                if (bOSHMessageEvent.getBody() != null) {
                    try {
                        BOSHConnection.this.writer.write(bOSHMessageEvent.getBody().toXML());
                    } catch (Exception unused) {
                    }
                }
            }
        });
        Thread readerThread = new Thread() {
            private int bufferLength = 1024;
            private Thread thread = this;

            public void run() {
                try {
                    char[] cArr = new char[this.bufferLength];
                    while (BOSHConnection.this.readerConsumer == this.thread && !BOSHConnection.this.done) {
                        BOSHConnection.this.reader.read(cArr, 0, this.bufferLength);
                    }
                } catch (IOException unused) {
                }
            }
        };
        this.readerConsumer = readerThread;
        readerThread.setDaemon(true);
        this.readerConsumer.start();
    }

    /* access modifiers changed from: protected */
    public void notifyConnectionError(Exception exc) {
        shutdown(new Presence(Presence.Type.unavailable));
        exc.printStackTrace();
        for (ConnectionListener connectionClosedOnError : getConnectionListeners()) {
            try {
                connectionClosedOnError.connectionClosedOnError(exc);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class BOSHConnectionListener implements BOSHClientConnListener {
        private final BOSHConnection connection;

        public BOSHConnectionListener(BOSHConnection bOSHConnection) {
            this.connection = bOSHConnection;
        }

        public void connectionEvent(BOSHClientConnEvent bOSHClientConnEvent) {
            try {
                if (bOSHClientConnEvent.isConnected()) {
                    boolean unused = BOSHConnection.this.connected = true;
                    if (BOSHConnection.this.isFirstInitialization) {
                        boolean unused2 = BOSHConnection.this.isFirstInitialization = false;
                        for (ConnectionCreationListener connectionCreated : Connection.getConnectionCreationListeners()) {
                            connectionCreated.connectionCreated(this.connection);
                        }
                    } else {
                        if (BOSHConnection.this.wasAuthenticated) {
                            this.connection.login(BOSHConnection.this.config.getUsername(), BOSHConnection.this.config.getPassword(), BOSHConnection.this.config.getResource());
                        }
                        for (ConnectionListener reconnectionSuccessful : BOSHConnection.this.getConnectionListeners()) {
                            reconnectionSuccessful.reconnectionSuccessful();
                        }
                    }
                } else {
                    if (bOSHClientConnEvent.isError()) {
                        try {
                            bOSHClientConnEvent.getCause();
                        } catch (Exception e) {
                            BOSHConnection.this.notifyConnectionError(e);
                        }
                    }
                    boolean unused3 = BOSHConnection.this.connected = false;
                }
            } catch (XMPPException e2) {
                for (ConnectionListener reconnectionFailed : BOSHConnection.this.getConnectionListeners()) {
                    reconnectionFailed.reconnectionFailed(e2);
                }
            } catch (Throwable th) {
                synchronized (this.connection) {
                    this.connection.notifyAll();
                    throw th;
                }
            }
            synchronized (this.connection) {
                this.connection.notifyAll();
            }
        }
    }

    private class ListenerNotification implements Runnable {
        private Packet packet;

        public ListenerNotification(Packet packet2) {
            this.packet = packet2;
        }

        public void run() {
            for (Connection.ListenerWrapper notifyListener : BOSHConnection.this.recvListeners.values()) {
                notifyListener.notifyListener(this.packet);
            }
        }
    }

    public void setRosterStorage(RosterStorage rosterStorage) throws IllegalStateException {
        if (this.roster == null) {
            this.rosterStorage = rosterStorage;
            return;
        }
        throw new IllegalStateException("Roster is already initialized");
    }
}
