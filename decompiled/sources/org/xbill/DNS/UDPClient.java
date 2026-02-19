package org.xbill.DNS;

import java.io.EOFException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.security.SecureRandom;

final class UDPClient extends Client {
    private static final int EPHEMERAL_RANGE = 64511;
    private static final int EPHEMERAL_START = 1024;
    private static final int EPHEMERAL_STOP = 65535;
    /* access modifiers changed from: private */
    public static SecureRandom prng = new SecureRandom();
    /* access modifiers changed from: private */
    public static volatile boolean prng_initializing = true;
    private boolean bound = false;

    static {
        new Thread(new Runnable() {
            public void run() {
                UDPClient.prng.nextInt();
                boolean unused = UDPClient.prng_initializing = false;
            }
        }).start();
    }

    public UDPClient(long j) throws IOException {
        super(DatagramChannel.open(), j);
    }

    private void bind_random(InetSocketAddress inetSocketAddress) throws IOException {
        InetSocketAddress inetSocketAddress2;
        if (prng_initializing) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException unused) {
            }
            if (prng_initializing) {
                return;
            }
        }
        DatagramChannel datagramChannel = (DatagramChannel) this.key.channel();
        int i = 0;
        while (i < 1024) {
            try {
                int nextInt = prng.nextInt(EPHEMERAL_RANGE) + 1024;
                if (inetSocketAddress != null) {
                    inetSocketAddress2 = new InetSocketAddress(inetSocketAddress.getAddress(), nextInt);
                } else {
                    inetSocketAddress2 = new InetSocketAddress(nextInt);
                }
                datagramChannel.socket().bind(inetSocketAddress2);
                this.bound = true;
                return;
            } catch (SocketException unused2) {
                i++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void bind(SocketAddress socketAddress) throws IOException {
        if (socketAddress == null || ((socketAddress instanceof InetSocketAddress) && ((InetSocketAddress) socketAddress).getPort() == 0)) {
            bind_random((InetSocketAddress) socketAddress);
            if (this.bound) {
                return;
            }
        }
        if (socketAddress != null) {
            ((DatagramChannel) this.key.channel()).socket().bind(socketAddress);
            this.bound = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void connect(SocketAddress socketAddress) throws IOException {
        if (!this.bound) {
            bind((SocketAddress) null);
        }
        ((DatagramChannel) this.key.channel()).connect(socketAddress);
    }

    /* access modifiers changed from: package-private */
    public void send(byte[] bArr) throws IOException {
        verboseLog("UDP write", bArr);
        ((DatagramChannel) this.key.channel()).write(ByteBuffer.wrap(bArr));
    }

    /* access modifiers changed from: package-private */
    public byte[] recv(int i) throws IOException {
        DatagramChannel datagramChannel = (DatagramChannel) this.key.channel();
        byte[] bArr = new byte[i];
        this.key.interestOps(1);
        while (!this.key.isReadable()) {
            try {
                blockUntil(this.key, this.endTime);
            } finally {
                if (this.key.isValid()) {
                    this.key.interestOps(0);
                }
            }
        }
        long read = (long) datagramChannel.read(ByteBuffer.wrap(bArr));
        if (read > 0) {
            int i2 = (int) read;
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, 0, bArr2, 0, i2);
            verboseLog("UDP read", bArr2);
            return bArr2;
        }
        throw new EOFException();
    }

    static byte[] sendrecv(SocketAddress socketAddress, SocketAddress socketAddress2, byte[] bArr, int i, long j) throws IOException {
        UDPClient uDPClient = new UDPClient(j);
        try {
            uDPClient.bind(socketAddress);
            uDPClient.connect(socketAddress2);
            uDPClient.send(bArr);
            return uDPClient.recv(i);
        } finally {
            uDPClient.cleanup();
        }
    }

    static byte[] sendrecv(SocketAddress socketAddress, byte[] bArr, int i, long j) throws IOException {
        return sendrecv((SocketAddress) null, socketAddress, bArr, i, j);
    }
}
