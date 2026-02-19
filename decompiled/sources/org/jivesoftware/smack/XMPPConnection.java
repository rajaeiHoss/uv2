package org.jivesoftware.smack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Objects;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.util.StringUtils;

public class XMPPConnection extends Connection {
    private boolean anonymous = false;
    private boolean authenticated = false;
    private Collection<String> compressionMethods;
    private boolean connected = false;
    String connectionID = null;
    PacketReader packetReader;
    PacketWriter packetWriter;
    Roster roster = null;
    protected Socket socket;
    private String user = null;
    private boolean usingCompression;
    private boolean usingTLS = false;
    private boolean wasAuthenticated = false;

    public XMPPConnection(String str, CallbackHandler callbackHandler) {
        super(new ConnectionConfiguration(str));
        this.config.setCompressionEnabled(false);
        this.config.setSASLAuthenticationEnabled(true);
        this.config.setDebuggerEnabled(DEBUG_ENABLED);
        this.config.setCallbackHandler(callbackHandler);
    }

    public XMPPConnection(String str) {
        super(new ConnectionConfiguration(str));
        this.config.setCompressionEnabled(false);
        this.config.setSASLAuthenticationEnabled(true);
        this.config.setDebuggerEnabled(DEBUG_ENABLED);
    }

    public XMPPConnection(ConnectionConfiguration connectionConfiguration) {
        super(connectionConfiguration);
    }

    public XMPPConnection(ConnectionConfiguration connectionConfiguration, CallbackHandler callbackHandler) {
        super(connectionConfiguration);
        connectionConfiguration.setCallbackHandler(callbackHandler);
    }

    public String getConnectionID() {
        if (!isConnected()) {
            return null;
        }
        return this.connectionID;
    }

    public String getUser() {
        if (!isAuthenticated()) {
            return null;
        }
        return this.user;
    }

    public synchronized void login(String str, String str2, String str3) throws XMPPException {
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
            if (this.config.isCompressionEnabled()) {
                useCompression();
            }
            this.authenticated = true;
            this.anonymous = false;
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
                this.packetWriter.sendPacket(new Presence(Presence.Type.available));
            }
            this.config.setLoginInfo(trim, str2, str3);
            if (this.config.isDebuggerEnabled() && this.debugger != null) {
                this.debugger.userHasLogged(this.user);
            }
        } else {
            throw new IllegalStateException("Already logged in to server.");
        }
    }

    public synchronized void loginAnonymously() throws XMPPException {
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
            if (this.config.isCompressionEnabled()) {
                useCompression();
            }
            this.packetWriter.sendPacket(new Presence(Presence.Type.available));
            this.authenticated = true;
            this.anonymous = true;
            if (this.config.isDebuggerEnabled() && this.debugger != null) {
                this.debugger.userHasLogged(this.user);
            }
        } else {
            throw new IllegalStateException("Already logged in to server.");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0 = r8.roster;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0017, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = (long) org.jivesoftware.smack.SmackConfiguration.getPacketReplyTimeout();
        r3 = java.lang.System.currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
        if (r8.roster.rosterInitialized != false) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002b, code lost:
        if (r1 > 0) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002e, code lost:
        r8.roster.wait(r1);
        r5 = java.lang.System.currentTimeMillis();
        r1 = r1 - (r5 - r3);
        r3 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003c, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        if (r8.roster.rosterInitialized != false) goto L_0x0041;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.jivesoftware.smack.Roster getRoster() {
        /*
            r8 = this;
            monitor-enter(r8)
            boolean r0 = r8.isAuthenticated()     // Catch:{ all -> 0x0053 }
            if (r0 == 0) goto L_0x0044
            boolean r0 = r8.isAnonymous()     // Catch:{ all -> 0x0053 }
            if (r0 == 0) goto L_0x000e
            goto L_0x0044
        L_0x000e:
            monitor-exit(r8)     // Catch:{ all -> 0x0053 }
            org.jivesoftware.smack.Roster r0 = r8.roster
            boolean r0 = r0.rosterInitialized
            if (r0 != 0) goto L_0x0041
            org.jivesoftware.smack.Roster r0 = r8.roster     // Catch:{ InterruptedException -> 0x0041 }
            monitor-enter(r0)     // Catch:{ InterruptedException -> 0x0041 }
            int r1 = org.jivesoftware.smack.SmackConfiguration.getPacketReplyTimeout()     // Catch:{ all -> 0x003e }
            long r1 = (long) r1     // Catch:{ all -> 0x003e }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x003e }
        L_0x0021:
            org.jivesoftware.smack.Roster r5 = r8.roster     // Catch:{ all -> 0x003e }
            boolean r5 = r5.rosterInitialized     // Catch:{ all -> 0x003e }
            if (r5 != 0) goto L_0x003c
            r5 = 0
            int r7 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r7 > 0) goto L_0x002e
            goto L_0x003c
        L_0x002e:
            org.jivesoftware.smack.Roster r5 = r8.roster     // Catch:{ all -> 0x003e }
            r5.wait(r1)     // Catch:{ all -> 0x003e }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x003e }
            long r3 = r5 - r3
            long r1 = r1 - r3
            r3 = r5
            goto L_0x0021
        L_0x003c:
            monitor-exit(r0)     // Catch:{ all -> 0x003e }
            goto L_0x0041
        L_0x003e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003e }
            throw r1     // Catch:{ InterruptedException -> 0x0041 }
        L_0x0041:
            org.jivesoftware.smack.Roster r0 = r8.roster
            return r0
        L_0x0044:
            org.jivesoftware.smack.Roster r0 = r8.roster     // Catch:{ all -> 0x0053 }
            if (r0 != 0) goto L_0x004f
            org.jivesoftware.smack.Roster r0 = new org.jivesoftware.smack.Roster     // Catch:{ all -> 0x0053 }
            r0.<init>(r8)     // Catch:{ all -> 0x0053 }
            r8.roster = r0     // Catch:{ all -> 0x0053 }
        L_0x004f:
            org.jivesoftware.smack.Roster r0 = r8.roster     // Catch:{ all -> 0x0053 }
            monitor-exit(r8)     // Catch:{ all -> 0x0053 }
            return r0
        L_0x0053:
            r0 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x0053 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.XMPPConnection.getRoster():org.jivesoftware.smack.Roster");
    }

    public boolean isConnected() {
        return this.connected;
    }

    public boolean isSecureConnection() {
        return isUsingTLS();
    }

    public boolean isAuthenticated() {
        return this.authenticated;
    }

    public boolean isAnonymous() {
        return this.anonymous;
    }

    /* access modifiers changed from: protected */
    public void shutdown(Presence presence) {
        PacketWriter packetWriter2 = this.packetWriter;
        if (packetWriter2 != null) {
            packetWriter2.sendPacket(presence);
        }
        setWasAuthenticated(this.authenticated);
        this.authenticated = false;
        this.connected = false;
        PacketReader packetReader2 = this.packetReader;
        if (packetReader2 != null) {
            packetReader2.shutdown();
        }
        PacketWriter packetWriter3 = this.packetWriter;
        if (packetWriter3 != null) {
            packetWriter3.shutdown();
        }
        try {
            Thread.sleep(150);
        } catch (Exception unused) {
        }
        if (this.reader != null) {
            try {
                this.reader.close();
            } catch (Throwable unused2) {
            }
            this.reader = null;
        }
        if (this.writer != null) {
            try {
                this.writer.close();
            } catch (Throwable unused3) {
            }
            this.writer = null;
        }
        try {
            this.socket.close();
        } catch (Exception unused4) {
        }
        this.saslAuthentication.init();
    }

    public void disconnect(Presence presence) {
        if (this.packetReader != null && this.packetWriter != null) {
            shutdown(presence);
            Roster roster2 = this.roster;
            if (roster2 != null) {
                roster2.cleanup();
                this.roster = null;
            }
            this.wasAuthenticated = false;
            this.packetWriter.cleanup();
            this.packetWriter = null;
            this.packetReader.cleanup();
            this.packetReader = null;
        }
    }

    public void sendPacket(Packet packet) {
        if (isConnected()) {
            Objects.requireNonNull(packet, "Packet is null.");
            this.packetWriter.sendPacket(packet);
            return;
        }
        throw new IllegalStateException("Not connected to server.");
    }

    public void addPacketWriterInterceptor(PacketInterceptor packetInterceptor, PacketFilter packetFilter) {
        addPacketInterceptor(packetInterceptor, packetFilter);
    }

    public void removePacketWriterInterceptor(PacketInterceptor packetInterceptor) {
        removePacketInterceptor(packetInterceptor);
    }

    public void addPacketWriterListener(PacketListener packetListener, PacketFilter packetFilter) {
        addPacketSendingListener(packetListener, packetFilter);
    }

    public void removePacketWriterListener(PacketListener packetListener) {
        removePacketSendingListener(packetListener);
    }

    private void connectUsingConfiguration(ConnectionConfiguration connectionConfiguration) throws XMPPException {
        String host = connectionConfiguration.getHost();
        int port = connectionConfiguration.getPort();
        try {
            if (connectionConfiguration.getSocketFactory() == null) {
                this.socket = new Socket(host, port);
            } else {
                this.socket = connectionConfiguration.getSocketFactory().createSocket(host, port);
            }
            initConnection();
        } catch (UnknownHostException e) {
            String str = "Could not connect to " + host + ":" + port + ".";
            throw new XMPPException(str, new XMPPError(XMPPError.Condition.remote_server_timeout, str), e);
        } catch (IOException e2) {
            String str2 = "XMPPError connecting to " + host + ":" + port + ".";
            throw new XMPPException(str2, new XMPPError(XMPPError.Condition.remote_server_error, str2), e2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0096 A[SYNTHETIC, Splitter:B:35:0x0096] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x009f A[SYNTHETIC, Splitter:B:41:0x009f] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00aa A[SYNTHETIC, Splitter:B:47:0x00aa] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00b5 A[SYNTHETIC, Splitter:B:53:0x00b5] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initConnection() throws org.jivesoftware.smack.XMPPException {
        /*
            r5 = this;
            org.jivesoftware.smack.PacketReader r0 = r5.packetReader
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x000d
            org.jivesoftware.smack.PacketWriter r0 = r5.packetWriter
            if (r0 != 0) goto L_0x000b
            goto L_0x000d
        L_0x000b:
            r0 = 0
            goto L_0x000e
        L_0x000d:
            r0 = 1
        L_0x000e:
            if (r0 != 0) goto L_0x0012
            r5.usingCompression = r2
        L_0x0012:
            r5.initReaderAndWriter()
            r3 = 0
            if (r0 == 0) goto L_0x0049
            org.jivesoftware.smack.PacketWriter r4 = new org.jivesoftware.smack.PacketWriter     // Catch:{ XMPPException -> 0x0088 }
            r4.<init>(r5)     // Catch:{ XMPPException -> 0x0088 }
            r5.packetWriter = r4     // Catch:{ XMPPException -> 0x0088 }
            org.jivesoftware.smack.PacketReader r4 = new org.jivesoftware.smack.PacketReader     // Catch:{ XMPPException -> 0x0088 }
            r4.<init>(r5)     // Catch:{ XMPPException -> 0x0088 }
            r5.packetReader = r4     // Catch:{ XMPPException -> 0x0088 }
            org.jivesoftware.smack.ConnectionConfiguration r4 = r5.config     // Catch:{ XMPPException -> 0x0088 }
            boolean r4 = r4.isDebuggerEnabled()     // Catch:{ XMPPException -> 0x0088 }
            if (r4 == 0) goto L_0x0053
            org.jivesoftware.smack.debugger.SmackDebugger r4 = r5.debugger     // Catch:{ XMPPException -> 0x0088 }
            org.jivesoftware.smack.PacketListener r4 = r4.getReaderListener()     // Catch:{ XMPPException -> 0x0088 }
            r5.addPacketListener(r4, r3)     // Catch:{ XMPPException -> 0x0088 }
            org.jivesoftware.smack.debugger.SmackDebugger r4 = r5.debugger     // Catch:{ XMPPException -> 0x0088 }
            org.jivesoftware.smack.PacketListener r4 = r4.getWriterListener()     // Catch:{ XMPPException -> 0x0088 }
            if (r4 == 0) goto L_0x0053
            org.jivesoftware.smack.debugger.SmackDebugger r4 = r5.debugger     // Catch:{ XMPPException -> 0x0088 }
            org.jivesoftware.smack.PacketListener r4 = r4.getWriterListener()     // Catch:{ XMPPException -> 0x0088 }
            r5.addPacketSendingListener(r4, r3)     // Catch:{ XMPPException -> 0x0088 }
            goto L_0x0053
        L_0x0049:
            org.jivesoftware.smack.PacketWriter r4 = r5.packetWriter     // Catch:{ XMPPException -> 0x0088 }
            r4.init()     // Catch:{ XMPPException -> 0x0088 }
            org.jivesoftware.smack.PacketReader r4 = r5.packetReader     // Catch:{ XMPPException -> 0x0088 }
            r4.init()     // Catch:{ XMPPException -> 0x0088 }
        L_0x0053:
            org.jivesoftware.smack.PacketWriter r4 = r5.packetWriter     // Catch:{ XMPPException -> 0x0088 }
            r4.startup()     // Catch:{ XMPPException -> 0x0088 }
            org.jivesoftware.smack.PacketReader r4 = r5.packetReader     // Catch:{ XMPPException -> 0x0088 }
            r4.startup()     // Catch:{ XMPPException -> 0x0088 }
            r5.connected = r1     // Catch:{ XMPPException -> 0x0088 }
            org.jivesoftware.smack.PacketWriter r1 = r5.packetWriter     // Catch:{ XMPPException -> 0x0088 }
            r1.startKeepAliveProcess()     // Catch:{ XMPPException -> 0x0088 }
            if (r0 == 0) goto L_0x007e
            java.util.Collection r0 = getConnectionCreationListeners()     // Catch:{ XMPPException -> 0x0088 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ XMPPException -> 0x0088 }
        L_0x006e:
            boolean r1 = r0.hasNext()     // Catch:{ XMPPException -> 0x0088 }
            if (r1 == 0) goto L_0x0087
            java.lang.Object r1 = r0.next()     // Catch:{ XMPPException -> 0x0088 }
            org.jivesoftware.smack.ConnectionCreationListener r1 = (org.jivesoftware.smack.ConnectionCreationListener) r1     // Catch:{ XMPPException -> 0x0088 }
            r1.connectionCreated(r5)     // Catch:{ XMPPException -> 0x0088 }
            goto L_0x006e
        L_0x007e:
            boolean r0 = r5.wasAuthenticated     // Catch:{ XMPPException -> 0x0088 }
            if (r0 != 0) goto L_0x0087
            org.jivesoftware.smack.PacketReader r0 = r5.packetReader     // Catch:{ XMPPException -> 0x0088 }
            r0.notifyReconnection()     // Catch:{ XMPPException -> 0x0088 }
        L_0x0087:
            return
        L_0x0088:
            r0 = move-exception
            org.jivesoftware.smack.PacketWriter r1 = r5.packetWriter
            if (r1 == 0) goto L_0x0092
            r1.shutdown()     // Catch:{ all -> 0x0090 }
        L_0x0090:
            r5.packetWriter = r3
        L_0x0092:
            org.jivesoftware.smack.PacketReader r1 = r5.packetReader
            if (r1 == 0) goto L_0x009b
            r1.shutdown()     // Catch:{ all -> 0x0099 }
        L_0x0099:
            r5.packetReader = r3
        L_0x009b:
            java.io.Reader r1 = r5.reader
            if (r1 == 0) goto L_0x00a6
            java.io.Reader r1 = r5.reader     // Catch:{ all -> 0x00a4 }
            r1.close()     // Catch:{ all -> 0x00a4 }
        L_0x00a4:
            r5.reader = r3
        L_0x00a6:
            java.io.Writer r1 = r5.writer
            if (r1 == 0) goto L_0x00b1
            java.io.Writer r1 = r5.writer     // Catch:{ all -> 0x00af }
            r1.close()     // Catch:{ all -> 0x00af }
        L_0x00af:
            r5.writer = r3
        L_0x00b1:
            java.net.Socket r1 = r5.socket
            if (r1 == 0) goto L_0x00ba
            r1.close()     // Catch:{ Exception -> 0x00b8 }
        L_0x00b8:
            r5.socket = r3
        L_0x00ba:
            boolean r1 = r5.authenticated
            r5.setWasAuthenticated(r1)
            r5.authenticated = r2
            r5.connected = r2
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.XMPPConnection.initConnection():void");
    }

    private void initReaderAndWriter() throws XMPPException {
        try {
            if (!this.usingCompression) {
                this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "UTF-8"));
                this.writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream(), "UTF-8"));
            } else {
                try {
                    Class<?> cls = Class.forName("com.jcraft.jzlib.ZOutputStream");
                    Object newInstance = cls.getConstructor(new Class[]{OutputStream.class, Integer.TYPE}).newInstance(new Object[]{this.socket.getOutputStream(), 9});
                    cls.getMethod("setFlushMode", new Class[]{Integer.TYPE}).invoke(newInstance, new Object[]{2});
                    this.writer = new BufferedWriter(new OutputStreamWriter((OutputStream) newInstance, "UTF-8"));
                    Class<?> cls2 = Class.forName("com.jcraft.jzlib.ZInputStream");
                    Object newInstance2 = cls2.getConstructor(new Class[]{InputStream.class}).newInstance(new Object[]{this.socket.getInputStream()});
                    cls2.getMethod("setFlushMode", new Class[]{Integer.TYPE}).invoke(newInstance2, new Object[]{2});
                    this.reader = new BufferedReader(new InputStreamReader((InputStream) newInstance2, "UTF-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                    this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "UTF-8"));
                    this.writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream(), "UTF-8"));
                }
            }
            initDebugger();
        } catch (IOException e2) {
            throw new XMPPException("XMPPError establishing connection with server.", new XMPPError(XMPPError.Condition.remote_server_error, "XMPPError establishing connection with server."), e2);
        }
    }

    public boolean isUsingTLS() {
        return this.usingTLS;
    }

    /* access modifiers changed from: package-private */
    public void startTLSReceived(boolean z) {
        if (z && this.config.getSecurityMode() == ConnectionConfiguration.SecurityMode.disabled) {
            this.packetReader.notifyConnectionError(new IllegalStateException("TLS required by server but not allowed by connection configuration"));
        } else if (this.config.getSecurityMode() != ConnectionConfiguration.SecurityMode.disabled) {
            try {
                this.writer.write("<starttls xmlns=\"urn:ietf:params:xml:ns:xmpp-tls\"/>");
                this.writer.flush();
            } catch (IOException e) {
                this.packetReader.notifyConnectionError(e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00e3 A[SYNTHETIC, Splitter:B:20:0x00e3] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00e7 A[Catch:{ NullPointerException -> 0x00f6 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void proceedTLSReceived() throws java.lang.Exception {
        /*
            r8 = this;
            org.jivesoftware.smack.ConnectionConfiguration r0 = r8.config
            javax.net.ssl.SSLContext r0 = r0.getCustomSSLContext()
            org.jivesoftware.smack.ConnectionConfiguration r1 = r8.config
            org.apache.harmony.javax.security.auth.callback.CallbackHandler r1 = r1.getCallbackHandler()
            r2 = 0
            r3 = 1
            r4 = 0
            if (r1 != 0) goto L_0x0013
            goto L_0x00f7
        L_0x0013:
            if (r0 != 0) goto L_0x00f7
            org.jivesoftware.smack.ConnectionConfiguration r1 = r8.config
            java.lang.String r1 = r1.getKeystoreType()
            java.lang.String r5 = "NONE"
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L_0x0027
        L_0x0023:
            r1 = r4
            r5 = r1
            goto L_0x00db
        L_0x0027:
            org.jivesoftware.smack.ConnectionConfiguration r1 = r8.config
            java.lang.String r1 = r1.getKeystoreType()
            java.lang.String r5 = "PKCS11"
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L_0x0092
            java.lang.String r1 = "sun.security.pkcs11.SunPKCS11"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ Exception -> 0x0023 }
            java.lang.Class[] r6 = new java.lang.Class[r3]     // Catch:{ Exception -> 0x0023 }
            java.lang.Class<java.io.InputStream> r7 = java.io.InputStream.class
            r6[r2] = r7     // Catch:{ Exception -> 0x0023 }
            java.lang.reflect.Constructor r1 = r1.getConstructor(r6)     // Catch:{ Exception -> 0x0023 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0023 }
            r6.<init>()     // Catch:{ Exception -> 0x0023 }
            java.lang.String r7 = "name = SmartCard\nlibrary = "
            r6.append(r7)     // Catch:{ Exception -> 0x0023 }
            org.jivesoftware.smack.ConnectionConfiguration r7 = r8.config     // Catch:{ Exception -> 0x0023 }
            java.lang.String r7 = r7.getPKCS11Library()     // Catch:{ Exception -> 0x0023 }
            r6.append(r7)     // Catch:{ Exception -> 0x0023 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0023 }
            java.io.ByteArrayInputStream r7 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x0023 }
            byte[] r6 = r6.getBytes()     // Catch:{ Exception -> 0x0023 }
            r7.<init>(r6)     // Catch:{ Exception -> 0x0023 }
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x0023 }
            r6[r2] = r7     // Catch:{ Exception -> 0x0023 }
            java.lang.Object r1 = r1.newInstance(r6)     // Catch:{ Exception -> 0x0023 }
            java.security.Provider r1 = (java.security.Provider) r1     // Catch:{ Exception -> 0x0023 }
            java.security.Security.addProvider(r1)     // Catch:{ Exception -> 0x0023 }
            java.security.KeyStore r1 = java.security.KeyStore.getInstance(r5, r1)     // Catch:{ Exception -> 0x0023 }
            org.apache.harmony.javax.security.auth.callback.PasswordCallback r5 = new org.apache.harmony.javax.security.auth.callback.PasswordCallback     // Catch:{ Exception -> 0x0023 }
            java.lang.String r6 = "PKCS11 Password: "
            r5.<init>(r6, r2)     // Catch:{ Exception -> 0x0023 }
            org.jivesoftware.smack.ConnectionConfiguration r6 = r8.config     // Catch:{ Exception -> 0x0023 }
            org.apache.harmony.javax.security.auth.callback.CallbackHandler r6 = r6.getCallbackHandler()     // Catch:{ Exception -> 0x0023 }
            org.apache.harmony.javax.security.auth.callback.Callback[] r7 = new org.apache.harmony.javax.security.auth.callback.Callback[r3]     // Catch:{ Exception -> 0x0023 }
            r7[r2] = r5     // Catch:{ Exception -> 0x0023 }
            r6.handle(r7)     // Catch:{ Exception -> 0x0023 }
            char[] r6 = r5.getPassword()     // Catch:{ Exception -> 0x0023 }
            r1.load(r4, r6)     // Catch:{ Exception -> 0x0023 }
            goto L_0x00db
        L_0x0092:
            org.jivesoftware.smack.ConnectionConfiguration r1 = r8.config
            java.lang.String r1 = r1.getKeystoreType()
            java.lang.String r5 = "Apple"
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L_0x00ab
            java.lang.String r1 = "KeychainStore"
            java.security.KeyStore r1 = java.security.KeyStore.getInstance(r1, r5)
            r1.load(r4, r4)
            r5 = r4
            goto L_0x00db
        L_0x00ab:
            org.jivesoftware.smack.ConnectionConfiguration r1 = r8.config
            java.lang.String r1 = r1.getKeystoreType()
            java.security.KeyStore r1 = java.security.KeyStore.getInstance(r1)
            org.apache.harmony.javax.security.auth.callback.PasswordCallback r5 = new org.apache.harmony.javax.security.auth.callback.PasswordCallback     // Catch:{ Exception -> 0x0023 }
            java.lang.String r6 = "Keystore Password: "
            r5.<init>(r6, r2)     // Catch:{ Exception -> 0x0023 }
            org.jivesoftware.smack.ConnectionConfiguration r6 = r8.config     // Catch:{ Exception -> 0x0023 }
            org.apache.harmony.javax.security.auth.callback.CallbackHandler r6 = r6.getCallbackHandler()     // Catch:{ Exception -> 0x0023 }
            org.apache.harmony.javax.security.auth.callback.Callback[] r7 = new org.apache.harmony.javax.security.auth.callback.Callback[r3]     // Catch:{ Exception -> 0x0023 }
            r7[r2] = r5     // Catch:{ Exception -> 0x0023 }
            r6.handle(r7)     // Catch:{ Exception -> 0x0023 }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0023 }
            org.jivesoftware.smack.ConnectionConfiguration r7 = r8.config     // Catch:{ Exception -> 0x0023 }
            java.lang.String r7 = r7.getKeystorePath()     // Catch:{ Exception -> 0x0023 }
            r6.<init>(r7)     // Catch:{ Exception -> 0x0023 }
            char[] r7 = r5.getPassword()     // Catch:{ Exception -> 0x0023 }
            r1.load(r6, r7)     // Catch:{ Exception -> 0x0023 }
        L_0x00db:
            java.lang.String r6 = "SunX509"
            javax.net.ssl.KeyManagerFactory r6 = javax.net.ssl.KeyManagerFactory.getInstance(r6)
            if (r5 != 0) goto L_0x00e7
            r6.init(r1, r4)     // Catch:{ NullPointerException -> 0x00f6 }
            goto L_0x00f1
        L_0x00e7:
            char[] r7 = r5.getPassword()     // Catch:{ NullPointerException -> 0x00f6 }
            r6.init(r1, r7)     // Catch:{ NullPointerException -> 0x00f6 }
            r5.clearPassword()     // Catch:{ NullPointerException -> 0x00f6 }
        L_0x00f1:
            javax.net.ssl.KeyManager[] r4 = r6.getKeyManagers()     // Catch:{ NullPointerException -> 0x00f6 }
            goto L_0x00f7
        L_0x00f6:
        L_0x00f7:
            if (r0 != 0) goto L_0x0116
            java.lang.String r0 = "TLS"
            javax.net.ssl.SSLContext r0 = javax.net.ssl.SSLContext.getInstance(r0)
            javax.net.ssl.TrustManager[] r1 = new javax.net.ssl.TrustManager[r3]
            org.jivesoftware.smack.ServerTrustManager r5 = new org.jivesoftware.smack.ServerTrustManager
            java.lang.String r6 = r8.getServiceName()
            org.jivesoftware.smack.ConnectionConfiguration r7 = r8.config
            r5.<init>(r6, r7)
            r1[r2] = r5
            java.security.SecureRandom r5 = new java.security.SecureRandom
            r5.<init>()
            r0.init(r4, r1, r5)
        L_0x0116:
            java.net.Socket r1 = r8.socket
            javax.net.ssl.SSLSocketFactory r0 = r0.getSocketFactory()
            java.net.InetAddress r4 = r1.getInetAddress()
            java.lang.String r4 = r4.getHostName()
            int r5 = r1.getPort()
            java.net.Socket r0 = r0.createSocket(r1, r4, r5, r3)
            r8.socket = r0
            r0.setSoTimeout(r2)
            java.net.Socket r0 = r8.socket
            r0.setKeepAlive(r3)
            r8.initReaderAndWriter()
            java.net.Socket r0 = r8.socket
            javax.net.ssl.SSLSocket r0 = (javax.net.ssl.SSLSocket) r0
            r0.startHandshake()
            r8.usingTLS = r3
            org.jivesoftware.smack.PacketWriter r0 = r8.packetWriter
            java.io.Writer r1 = r8.writer
            r0.setWriter(r1)
            org.jivesoftware.smack.PacketWriter r0 = r8.packetWriter
            r0.openStream()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.XMPPConnection.proceedTLSReceived():void");
    }

    /* access modifiers changed from: package-private */
    public void setAvailableCompressionMethods(Collection<String> collection) {
        this.compressionMethods = collection;
    }

    private boolean hasAvailableCompressionMethod(String str) {
        Collection<String> collection = this.compressionMethods;
        return collection != null && collection.contains(str);
    }

    public boolean isUsingCompression() {
        return this.usingCompression;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0022 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean useCompression() {
        /*
            r2 = this;
            boolean r0 = r2.authenticated
            if (r0 != 0) goto L_0x0032
            java.lang.String r0 = "com.jcraft.jzlib.ZOutputStream"
            java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x002a }
            java.lang.String r0 = "zlib"
            boolean r0 = r2.hasAvailableCompressionMethod(r0)
            if (r0 == 0) goto L_0x0028
            r2.requestStreamCompression()
            monitor-enter(r2)
            int r0 = org.jivesoftware.smack.SmackConfiguration.getPacketReplyTimeout()     // Catch:{ InterruptedException -> 0x0022 }
            int r0 = r0 * 5
            long r0 = (long) r0     // Catch:{ InterruptedException -> 0x0022 }
            r2.wait(r0)     // Catch:{ InterruptedException -> 0x0022 }
            goto L_0x0022
        L_0x0020:
            r0 = move-exception
            goto L_0x0026
        L_0x0022:
            monitor-exit(r2)     // Catch:{ all -> 0x0020 }
            boolean r0 = r2.usingCompression
            return r0
        L_0x0026:
            monitor-exit(r2)     // Catch:{ all -> 0x0020 }
            throw r0
        L_0x0028:
            r0 = 0
            return r0
        L_0x002a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Cannot use compression. Add smackx.jar to the classpath"
            r0.<init>(r1)
            throw r0
        L_0x0032:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Compression should be negotiated before authentication."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.XMPPConnection.useCompression():boolean");
    }

    private void requestStreamCompression() {
        try {
            this.writer.write("<compress xmlns='http://jabber.org/protocol/compress'>");
            this.writer.write("<method>zlib</method></compress>");
            this.writer.flush();
        } catch (IOException e) {
            this.packetReader.notifyConnectionError(e);
        }
    }

    /* access modifiers changed from: package-private */
    public void startStreamCompression() throws Exception {
        this.usingCompression = true;
        initReaderAndWriter();
        this.packetWriter.setWriter(this.writer);
        this.packetWriter.openStream();
        synchronized (this) {
            notify();
        }
    }

    /* access modifiers changed from: package-private */
    public void streamCompressionDenied() {
        synchronized (this) {
            notify();
        }
    }

    public void connect() throws XMPPException {
        connectUsingConfiguration(this.config);
        if (this.connected && this.wasAuthenticated) {
            try {
                if (isAnonymous()) {
                    loginAnonymously();
                } else {
                    login(this.config.getUsername(), this.config.getPassword(), this.config.getResource());
                }
                this.packetReader.notifyReconnection();
            } catch (XMPPException e) {
                e.printStackTrace();
            }
        }
    }

    private void setWasAuthenticated(boolean z) {
        if (!this.wasAuthenticated) {
            this.wasAuthenticated = z;
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
