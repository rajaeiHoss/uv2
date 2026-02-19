package org.jivesoftware.smack;

import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import org.jivesoftware.smack.debugger.SmackDebugger;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;

public abstract class Connection {
    public static boolean DEBUG_ENABLED;
    private static final AtomicInteger connectionCounter = new AtomicInteger(0);
    private static final Set<ConnectionCreationListener> connectionEstablishedListeners = new CopyOnWriteArraySet();
    private AccountManager accountManager = null;
    private ChatManager chatManager = null;
    protected final Collection<PacketCollector> collectors = new ConcurrentLinkedQueue();
    protected final ConnectionConfiguration config;
    protected final int connectionCounterValue = connectionCounter.getAndIncrement();
    protected final Collection<ConnectionListener> connectionListeners = new CopyOnWriteArrayList();
    protected SmackDebugger debugger = null;
    protected final Map<PacketInterceptor, InterceptorWrapper> interceptors = new ConcurrentHashMap();
    protected Reader reader;
    protected final Map<PacketListener, ListenerWrapper> recvListeners = new ConcurrentHashMap();
    protected RosterStorage rosterStorage;
    protected SASLAuthentication saslAuthentication = new SASLAuthentication(this);
    protected final Map<PacketListener, ListenerWrapper> sendListeners = new ConcurrentHashMap();
    protected Writer writer;

    public abstract void connect() throws XMPPException;

    public abstract void disconnect(Presence presence);

    public abstract String getConnectionID();

    public abstract Roster getRoster();

    public abstract String getUser();

    public abstract boolean isAnonymous();

    public abstract boolean isAuthenticated();

    public abstract boolean isConnected();

    public abstract boolean isSecureConnection();

    public abstract boolean isUsingCompression();

    public abstract void login(String str, String str2, String str3) throws XMPPException;

    public abstract void loginAnonymously() throws XMPPException;

    public abstract void sendPacket(Packet packet);

    public abstract void setRosterStorage(RosterStorage rosterStorage2) throws IllegalStateException;

    static {
        DEBUG_ENABLED = false;
        try {
            DEBUG_ENABLED = Boolean.getBoolean("smack.debugEnabled");
        } catch (Exception unused) {
        }
        SmackConfiguration.getVersion();
    }

    protected Connection(ConnectionConfiguration connectionConfiguration) {
        this.config = connectionConfiguration;
    }

    /* access modifiers changed from: protected */
    public ConnectionConfiguration getConfiguration() {
        return this.config;
    }

    public String getServiceName() {
        return this.config.getServiceName();
    }

    public String getHost() {
        return this.config.getHost();
    }

    public String getCapsNode() {
        return this.config.getCapsNode();
    }

    public int getPort() {
        return this.config.getPort();
    }

    /* access modifiers changed from: protected */
    public boolean isReconnectionAllowed() {
        return this.config.isReconnectionAllowed();
    }

    public void login(String str, String str2) throws XMPPException {
        login(str, str2, "Smack");
    }

    public AccountManager getAccountManager() {
        if (this.accountManager == null) {
            this.accountManager = new AccountManager(this);
        }
        return this.accountManager;
    }

    public synchronized ChatManager getChatManager() {
        if (this.chatManager == null) {
            this.chatManager = new ChatManager(this);
        }
        return this.chatManager;
    }

    public SASLAuthentication getSASLAuthentication() {
        return this.saslAuthentication;
    }

    public void disconnect() {
        disconnect(new Presence(Presence.Type.unavailable));
    }

    public static void addConnectionCreationListener(ConnectionCreationListener connectionCreationListener) {
        connectionEstablishedListeners.add(connectionCreationListener);
    }

    public static void removeConnectionCreationListener(ConnectionCreationListener connectionCreationListener) {
        connectionEstablishedListeners.remove(connectionCreationListener);
    }

    protected static Collection<ConnectionCreationListener> getConnectionCreationListeners() {
        return Collections.unmodifiableCollection(connectionEstablishedListeners);
    }

    public void addConnectionListener(ConnectionListener connectionListener) {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected to server.");
        } else if (connectionListener != null && !this.connectionListeners.contains(connectionListener)) {
            this.connectionListeners.add(connectionListener);
        }
    }

    public void removeConnectionListener(ConnectionListener connectionListener) {
        this.connectionListeners.remove(connectionListener);
    }

    /* access modifiers changed from: protected */
    public Collection<ConnectionListener> getConnectionListeners() {
        return this.connectionListeners;
    }

    public PacketCollector createPacketCollector(PacketFilter packetFilter) {
        PacketCollector packetCollector = new PacketCollector(this, packetFilter);
        this.collectors.add(packetCollector);
        return packetCollector;
    }

    /* access modifiers changed from: protected */
    public void removePacketCollector(PacketCollector packetCollector) {
        this.collectors.remove(packetCollector);
    }

    /* access modifiers changed from: protected */
    public Collection<PacketCollector> getPacketCollectors() {
        return this.collectors;
    }

    public void addPacketListener(PacketListener packetListener, PacketFilter packetFilter) {
        Objects.requireNonNull(packetListener, "Packet listener is null.");
        this.recvListeners.put(packetListener, new ListenerWrapper(packetListener, packetFilter));
    }

    public void removePacketListener(PacketListener packetListener) {
        this.recvListeners.remove(packetListener);
    }

    /* access modifiers changed from: protected */
    public Map<PacketListener, ListenerWrapper> getPacketListeners() {
        return this.recvListeners;
    }

    public void addPacketSendingListener(PacketListener packetListener, PacketFilter packetFilter) {
        Objects.requireNonNull(packetListener, "Packet listener is null.");
        this.sendListeners.put(packetListener, new ListenerWrapper(packetListener, packetFilter));
    }

    public void removePacketSendingListener(PacketListener packetListener) {
        this.sendListeners.remove(packetListener);
    }

    /* access modifiers changed from: protected */
    public Map<PacketListener, ListenerWrapper> getPacketSendingListeners() {
        return this.sendListeners;
    }

    /* access modifiers changed from: protected */
    public void firePacketSendingListeners(Packet packet) {
        for (ListenerWrapper notifyListener : this.sendListeners.values()) {
            notifyListener.notifyListener(packet);
        }
    }

    public void addPacketInterceptor(PacketInterceptor packetInterceptor, PacketFilter packetFilter) {
        Objects.requireNonNull(packetInterceptor, "Packet interceptor is null.");
        this.interceptors.put(packetInterceptor, new InterceptorWrapper(packetInterceptor, packetFilter));
    }

    public void removePacketInterceptor(PacketInterceptor packetInterceptor) {
        this.interceptors.remove(packetInterceptor);
    }

    public boolean isSendPresence() {
        return this.config.isSendPresence();
    }

    /* access modifiers changed from: protected */
    public Map<PacketInterceptor, InterceptorWrapper> getPacketInterceptors() {
        return this.interceptors;
    }

    /* access modifiers changed from: protected */
    public void firePacketInterceptors(Packet packet) {
        if (packet != null) {
            for (InterceptorWrapper notifyListener : this.interceptors.values()) {
                notifyListener.notifyListener(packet);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initDebugger() {
        String str;
        if (this.reader == null || this.writer == null) {
            throw new NullPointerException("Reader or writer isn't initialized.");
        } else if (this.config.isDebuggerEnabled()) {
            SmackDebugger smackDebugger = this.debugger;
            if (smackDebugger == null) {
                Class<?> cls = null;
                try {
                    str = System.getProperty("smack.debuggerClass");
                } catch (Throwable unused) {
                    str = null;
                }
                if (str != null) {
                    try {
                        cls = Class.forName(str);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (cls == null) {
                    try {
                        cls = Class.forName("de.measite.smack.AndroidDebugger");
                    } catch (Exception unused2) {
                        try {
                            cls = Class.forName("org.jivesoftware.smack.debugger.ConsoleDebugger");
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                try {
                    SmackDebugger smackDebugger2 = (SmackDebugger) cls.getConstructor(new Class[]{Connection.class, Writer.class, Reader.class}).newInstance(new Object[]{this, this.writer, this.reader});
                    this.debugger = smackDebugger2;
                    this.reader = smackDebugger2.getReader();
                    this.writer = this.debugger.getWriter();
                } catch (Exception e3) {
                    throw new IllegalArgumentException("Can't initialize the configured debugger!", e3);
                }
            } else {
                this.reader = smackDebugger.newConnectionReader(this.reader);
                this.writer = this.debugger.newConnectionWriter(this.writer);
            }
        }
    }

    protected static class ListenerWrapper {
        private PacketFilter packetFilter;
        private PacketListener packetListener;

        public ListenerWrapper(PacketListener packetListener2, PacketFilter packetFilter2) {
            this.packetListener = packetListener2;
            this.packetFilter = packetFilter2;
        }

        public void notifyListener(Packet packet) {
            PacketFilter packetFilter2 = this.packetFilter;
            if (packetFilter2 == null || packetFilter2.accept(packet)) {
                this.packetListener.processPacket(packet);
            }
        }
    }

    protected static class InterceptorWrapper {
        private PacketFilter packetFilter;
        private PacketInterceptor packetInterceptor;

        public InterceptorWrapper(PacketInterceptor packetInterceptor2, PacketFilter packetFilter2) {
            this.packetInterceptor = packetInterceptor2;
            this.packetFilter = packetFilter2;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj instanceof InterceptorWrapper) {
                return ((InterceptorWrapper) obj).packetInterceptor.equals(this.packetInterceptor);
            }
            if (obj instanceof PacketInterceptor) {
                return obj.equals(this.packetInterceptor);
            }
            return false;
        }

        public void notifyListener(Packet packet) {
            PacketFilter packetFilter2 = this.packetFilter;
            if (packetFilter2 == null || packetFilter2.accept(packet)) {
                this.packetInterceptor.interceptPacket(packet);
            }
        }
    }
}
