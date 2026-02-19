package org.jivesoftware.smackx.bytestreams.socks5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPException;

public class Socks5Proxy {
    private static Socks5Proxy socks5Server;
    /* access modifiers changed from: private */
    public final List<String> allowedConnections = Collections.synchronizedList(new LinkedList());
    /* access modifiers changed from: private */
    public final Map<String, Socket> connectionMap = new ConcurrentHashMap();
    private final Set<String> localAddresses;
    private Socks5ServerProcess serverProcess;
    /* access modifiers changed from: private */
    public ServerSocket serverSocket;
    private Thread serverThread;

    private Socks5Proxy() {
        Set<String> synchronizedSet = Collections.synchronizedSet(new LinkedHashSet());
        this.localAddresses = synchronizedSet;
        this.serverProcess = new Socks5ServerProcess();
        try {
            synchronizedSet.add(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException unused) {
        }
    }

    public static synchronized Socks5Proxy getSocks5Proxy() {
        Socks5Proxy socks5Proxy;
        synchronized (Socks5Proxy.class) {
            if (socks5Server == null) {
                socks5Server = new Socks5Proxy();
            }
            if (SmackConfiguration.isLocalSocks5ProxyEnabled()) {
                socks5Server.start();
            }
            socks5Proxy = socks5Server;
        }
        return socks5Proxy;
    }

    public synchronized void start() {
        if (!isRunning()) {
            try {
                if (SmackConfiguration.getLocalSocks5ProxyPort() < 0) {
                    int abs = Math.abs(SmackConfiguration.getLocalSocks5ProxyPort());
                    int i = 0;
                    while (i < 65535 - abs) {
                        try {
                            this.serverSocket = new ServerSocket(abs + i);
                            break;
                        } catch (IOException unused) {
                            i++;
                        }
                    }
                } else {
                    this.serverSocket = new ServerSocket(SmackConfiguration.getLocalSocks5ProxyPort());
                }
                if (this.serverSocket != null) {
                    Thread thread = new Thread(this.serverProcess);
                    this.serverThread = thread;
                    thread.start();
                }
            } catch (IOException e) {
                PrintStream printStream = System.err;
                printStream.println("couldn't setup local SOCKS5 proxy on port " + SmackConfiguration.getLocalSocks5ProxyPort() + ": " + e.getMessage());
            }
        } else {
            return;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:6|7|8|9|(2:13|14)|15|17|18|19|20) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x000e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void stop() {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.isRunning()     // Catch:{ all -> 0x0029 }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r1)
            return
        L_0x0009:
            java.net.ServerSocket r0 = r1.serverSocket     // Catch:{ IOException -> 0x000e }
            r0.close()     // Catch:{ IOException -> 0x000e }
        L_0x000e:
            java.lang.Thread r0 = r1.serverThread     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0022
            boolean r0 = r0.isAlive()     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0022
            java.lang.Thread r0 = r1.serverThread     // Catch:{ InterruptedException -> 0x0022 }
            r0.interrupt()     // Catch:{ InterruptedException -> 0x0022 }
            java.lang.Thread r0 = r1.serverThread     // Catch:{ InterruptedException -> 0x0022 }
            r0.join()     // Catch:{ InterruptedException -> 0x0022 }
        L_0x0022:
            r0 = 0
            r1.serverThread = r0     // Catch:{ all -> 0x0029 }
            r1.serverSocket = r0     // Catch:{ all -> 0x0029 }
            monitor-exit(r1)
            return
        L_0x0029:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.bytestreams.socks5.Socks5Proxy.stop():void");
    }

    public void addLocalAddress(String str) {
        if (str != null) {
            this.localAddresses.add(str);
            return;
        }
        throw new IllegalArgumentException("address may not be null");
    }

    public void removeLocalAddress(String str) {
        this.localAddresses.remove(str);
    }

    public List<String> getLocalAddresses() {
        return Collections.unmodifiableList(new ArrayList(this.localAddresses));
    }

    public void replaceLocalAddresses(List<String> list) {
        if (list != null) {
            this.localAddresses.clear();
            this.localAddresses.addAll(list);
            return;
        }
        throw new IllegalArgumentException("list must not be null");
    }

    public int getPort() {
        if (!isRunning()) {
            return -1;
        }
        return this.serverSocket.getLocalPort();
    }

    /* access modifiers changed from: protected */
    public Socket getSocket(String str) {
        return this.connectionMap.get(str);
    }

    /* access modifiers changed from: protected */
    public void addTransfer(String str) {
        this.allowedConnections.add(str);
    }

    /* access modifiers changed from: protected */
    public void removeTransfer(String str) {
        this.allowedConnections.remove(str);
        this.connectionMap.remove(str);
    }

    public boolean isRunning() {
        return this.serverSocket != null;
    }

    private class Socks5ServerProcess implements Runnable {
        private Socks5ServerProcess() {
        }

        public void run() {
            while (true) {
                Socket socket = null;
                try {
                    if (Socks5Proxy.this.serverSocket.isClosed()) {
                        return;
                    }
                    if (!Thread.currentThread().isInterrupted()) {
                        socket = Socks5Proxy.this.serverSocket.accept();
                        establishConnection(socket);
                    } else {
                        return;
                    }
                } catch (Exception unused) {
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (IOException unused2) {
                        }
                    }
                }
            }
        }

        private void establishConnection(Socket socket) throws XMPPException, IOException {
            boolean z;
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            if (dataInputStream.read() == 5) {
                int read = dataInputStream.read();
                byte[] bArr = new byte[read];
                dataInputStream.readFully(bArr);
                byte[] bArr2 = new byte[2];
                bArr2[0] = 5;
                int i = 0;
                while (true) {
                    if (i >= read) {
                        z = false;
                        break;
                    } else if (bArr[i] == 0) {
                        z = true;
                        break;
                    } else {
                        i++;
                    }
                }
                if (z) {
                    bArr2[1] = 0;
                    dataOutputStream.write(bArr2);
                    dataOutputStream.flush();
                    byte[] receiveSocks5Message = Socks5Utils.receiveSocks5Message(dataInputStream);
                    String str = new String(receiveSocks5Message, 5, receiveSocks5Message[4]);
                    if (Socks5Proxy.this.allowedConnections.contains(str)) {
                        receiveSocks5Message[1] = 0;
                        dataOutputStream.write(receiveSocks5Message);
                        dataOutputStream.flush();
                        Socks5Proxy.this.connectionMap.put(str, socket);
                        return;
                    }
                    receiveSocks5Message[1] = 5;
                    dataOutputStream.write(receiveSocks5Message);
                    dataOutputStream.flush();
                    throw new XMPPException("Connection is not allowed");
                }
                bArr2[1] = -1;
                dataOutputStream.write(bArr2);
                dataOutputStream.flush();
                throw new XMPPException("Authentication method not supported");
            }
            throw new XMPPException("Only SOCKS5 supported");
        }
    }
}
