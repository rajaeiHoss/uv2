package freemarker.debug.impl;

import freemarker.debug.Debugger;
import freemarker.log.Logger;
import freemarker.template.utility.SecurityUtilities;
import freemarker.template.utility.UndeclaredThrowableException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

class DebuggerServer {
    /* access modifiers changed from: private */
    public static final Random R = new SecureRandom();
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger("freemarker.debug.server");
    /* access modifiers changed from: private */
    public final Serializable debuggerStub;
    /* access modifiers changed from: private */
    public final byte[] password;
    private final int port = SecurityUtilities.getSystemProperty("freemarker.debug.port", (int) Debugger.DEFAULT_PORT).intValue();
    private ServerSocket serverSocket;
    private boolean stop = false;

    public DebuggerServer(Serializable serializable) {
        try {
            this.password = SecurityUtilities.getSystemProperty("freemarker.debug.password", "").getBytes("UTF-8");
            this.debuggerStub = serializable;
        } catch (UnsupportedEncodingException e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    public void start() {
        new Thread(new Runnable() {
            public void run() {
                DebuggerServer.this.startInternal();
            }
        }, "FreeMarker Debugger Server Acceptor").start();
    }

    /* access modifiers changed from: private */
    public void startInternal() {
        try {
            this.serverSocket = new ServerSocket(this.port);
            while (!this.stop) {
                new Thread(new DebuggerAuthProtocol(this.serverSocket.accept())).start();
            }
        } catch (IOException e) {
            logger.error("Debugger server shut down.", e);
        }
    }

    private class DebuggerAuthProtocol implements Runnable {
        private final Socket s;

        DebuggerAuthProtocol(Socket socket) {
            this.s = socket;
        }

        public void run() {
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(this.s.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(this.s.getInputStream());
                byte[] bArr = new byte[512];
                DebuggerServer.R.nextBytes(bArr);
                objectOutputStream.writeInt(220);
                objectOutputStream.writeObject(bArr);
                MessageDigest instance = MessageDigest.getInstance("SHA");
                instance.update(DebuggerServer.this.password);
                instance.update(bArr);
                if (Arrays.equals((byte[]) objectInputStream.readObject(), instance.digest())) {
                    objectOutputStream.writeObject(DebuggerServer.this.debuggerStub);
                } else {
                    objectOutputStream.writeObject((Object) null);
                }
            } catch (Exception e) {
                Logger access$400 = DebuggerServer.logger;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Connection to ");
                stringBuffer.append(this.s.getInetAddress().getHostAddress());
                stringBuffer.append(" abruply broke");
                access$400.warn(stringBuffer.toString(), e);
            }
        }
    }

    public void stop() {
        this.stop = true;
        ServerSocket serverSocket2 = this.serverSocket;
        if (serverSocket2 != null) {
            try {
                serverSocket2.close();
            } catch (IOException e) {
                logger.error("Unable to close server socket.", e);
            }
        }
    }
}
