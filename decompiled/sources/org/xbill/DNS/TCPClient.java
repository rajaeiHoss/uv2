package org.xbill.DNS;

import java.io.EOFException;
import java.io.IOException;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import kotlin.UByte;

final class TCPClient extends Client {
    public TCPClient(long j) throws IOException {
        super(SocketChannel.open(), j);
    }

    /* access modifiers changed from: package-private */
    public void bind(SocketAddress socketAddress) throws IOException {
        ((SocketChannel) this.key.channel()).socket().bind(socketAddress);
    }

    /* access modifiers changed from: package-private */
    public void connect(SocketAddress socketAddress) throws IOException {
        SocketChannel socketChannel = (SocketChannel) this.key.channel();
        if (!socketChannel.connect(socketAddress)) {
            this.key.interestOps(8);
            while (!socketChannel.finishConnect()) {
                try {
                    if (!this.key.isConnectable()) {
                        blockUntil(this.key, this.endTime);
                    }
                } finally {
                    if (this.key.isValid()) {
                        this.key.interestOps(0);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void send(byte[] bArr) throws IOException {
        SocketChannel socketChannel = (SocketChannel) this.key.channel();
        verboseLog("TCP write", bArr);
        ByteBuffer[] byteBufferArr = {ByteBuffer.wrap(new byte[]{(byte) (bArr.length >>> 8), (byte) (bArr.length & 255)}), ByteBuffer.wrap(bArr)};
        this.key.interestOps(4);
        int i = 0;
        while (i < bArr.length + 2) {
            try {
                if (this.key.isWritable()) {
                    long write = socketChannel.write(byteBufferArr);
                    if (write >= 0) {
                        i += (int) write;
                        if (i >= bArr.length + 2) {
                            continue;
                        } else if (System.currentTimeMillis() > this.endTime) {
                            throw new SocketTimeoutException();
                        }
                    } else {
                        throw new EOFException();
                    }
                } else {
                    blockUntil(this.key, this.endTime);
                }
            } finally {
                if (this.key.isValid()) {
                    this.key.interestOps(0);
                }
            }
        }
    }

    private byte[] _recv(int i) throws IOException {
        SocketChannel socketChannel = (SocketChannel) this.key.channel();
        byte[] bArr = new byte[i];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        this.key.interestOps(1);
        int i2 = 0;
        while (i2 < i) {
            try {
                if (this.key.isReadable()) {
                    long read = (long) socketChannel.read(wrap);
                    if (read >= 0) {
                        i2 += (int) read;
                        if (i2 >= i) {
                            continue;
                        } else if (System.currentTimeMillis() > this.endTime) {
                            throw new SocketTimeoutException();
                        }
                    } else {
                        throw new EOFException();
                    }
                } else {
                    blockUntil(this.key, this.endTime);
                }
            } catch (Throwable th) {
                if (this.key.isValid()) {
                    this.key.interestOps(0);
                }
                throw th;
            }
        }
        if (this.key.isValid()) {
            this.key.interestOps(0);
        }
        return bArr;
    }

    /* access modifiers changed from: package-private */
    public byte[] recv() throws IOException {
        byte[] _recv = _recv(2);
        byte[] _recv2 = _recv(((_recv[0] & UByte.MAX_VALUE) << 8) + (_recv[1] & UByte.MAX_VALUE));
        verboseLog("TCP read", _recv2);
        return _recv2;
    }

    static byte[] sendrecv(SocketAddress socketAddress, SocketAddress socketAddress2, byte[] bArr, long j) throws IOException {
        TCPClient tCPClient = new TCPClient(j);
        if (socketAddress != null) {
            try {
                tCPClient.bind(socketAddress);
            } catch (Throwable th) {
                tCPClient.cleanup();
                throw th;
            }
        }
        tCPClient.connect(socketAddress2);
        tCPClient.send(bArr);
        byte[] recv = tCPClient.recv();
        tCPClient.cleanup();
        return recv;
    }

    static byte[] sendrecv(SocketAddress socketAddress, byte[] bArr, long j) throws IOException {
        return sendrecv((SocketAddress) null, socketAddress, bArr, j);
    }
}
