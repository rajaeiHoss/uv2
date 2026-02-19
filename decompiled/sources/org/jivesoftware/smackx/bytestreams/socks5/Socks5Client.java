package org.jivesoftware.smackx.bytestreams.socks5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;

class Socks5Client {
    protected String digest;
    protected Bytestream.StreamHost streamHost;

    public Socks5Client(Bytestream.StreamHost streamHost2, String str) {
        this.streamHost = streamHost2;
        this.digest = str;
    }

    public Socket getSocket(int i) throws IOException, XMPPException, InterruptedException, TimeoutException {
        FutureTask futureTask = new FutureTask(new Callable<Socket>() {
            public Socket call() throws Exception {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(Socks5Client.this.streamHost.getAddress(), Socks5Client.this.streamHost.getPort()));
                if (Socks5Client.this.establish(socket)) {
                    return socket;
                }
                socket.close();
                throw new XMPPException("establishing connection to SOCKS5 proxy failed");
            }
        });
        new Thread(futureTask).start();
        try {
            return (Socket) futureTask.get((long) i, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause != null) {
                if (cause instanceof IOException) {
                    throw ((IOException) cause);
                } else if (cause instanceof XMPPException) {
                    throw ((XMPPException) cause);
                }
            }
            throw new IOException("Error while connection to SOCKS5 proxy");
        }
    }

    /* access modifiers changed from: protected */
    public boolean establish(Socket socket) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.write(new byte[]{5, 1, 0});
        dataOutputStream.flush();
        byte[] bArr = new byte[2];
        dataInputStream.readFully(bArr);
        if (bArr[0] == 5 && bArr[1] == 0) {
            byte[] createSocks5ConnectRequest = createSocks5ConnectRequest();
            dataOutputStream.write(createSocks5ConnectRequest);
            dataOutputStream.flush();
            try {
                byte[] receiveSocks5Message = Socks5Utils.receiveSocks5Message(dataInputStream);
                createSocks5ConnectRequest[1] = 0;
                return Arrays.equals(createSocks5ConnectRequest, receiveSocks5Message);
            } catch (XMPPException unused) {
            }
        }
        return false;
    }

    private byte[] createSocks5ConnectRequest() {
        byte[] bytes = this.digest.getBytes();
        int length = bytes.length + 7;
        byte[] bArr = new byte[length];
        bArr[0] = 5;
        bArr[1] = 1;
        bArr[2] = 0;
        bArr[3] = 3;
        bArr[4] = (byte) bytes.length;
        System.arraycopy(bytes, 0, bArr, 5, bytes.length);
        bArr[length - 2] = 0;
        bArr[length - 1] = 0;
        return bArr;
    }
}
