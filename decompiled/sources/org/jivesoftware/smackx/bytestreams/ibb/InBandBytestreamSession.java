package org.jivesoftware.smackx.bytestreams.ibb;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.bytestreams.BytestreamSession;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Close;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Data;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;
import org.jivesoftware.smackx.packet.SyncPacketSend;

public class InBandBytestreamSession implements BytestreamSession {
    /* access modifiers changed from: private */
    public final Open byteStreamRequest;
    private boolean closeBothStreamsEnabled = false;
    /* access modifiers changed from: private */
    public final Connection connection;
    private IBBInputStream inputStream;
    private boolean isClosed = false;
    private IBBOutputStream outputStream;
    /* access modifiers changed from: private */
    public String remoteJID;

    protected InBandBytestreamSession(Connection connection2, Open open, String str) {
        this.connection = connection2;
        this.byteStreamRequest = open;
        this.remoteJID = str;
        int i = AnonymousClass1.$SwitchMap$org$jivesoftware$smackx$bytestreams$ibb$InBandBytestreamManager$StanzaType[open.getStanza().ordinal()];
        if (i == 1) {
            this.inputStream = new IQIBBInputStream(this, (AnonymousClass1) null);
            this.outputStream = new IQIBBOutputStream(this, (AnonymousClass1) null);
        } else if (i == 2) {
            this.inputStream = new MessageIBBInputStream(this, (AnonymousClass1) null);
            this.outputStream = new MessageIBBOutputStream(this, (AnonymousClass1) null);
        }
    }

    /* renamed from: org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamSession$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jivesoftware$smackx$bytestreams$ibb$InBandBytestreamManager$StanzaType;

        static {
            int[] iArr = new int[org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager.StanzaType.values().length];
            $SwitchMap$org$jivesoftware$smackx$bytestreams$ibb$InBandBytestreamManager$StanzaType = iArr;
            try {
                iArr[org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager.StanzaType.IQ.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager.StanzaType.MESSAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    public int getReadTimeout() {
        return this.inputStream.readTimeout;
    }

    public void setReadTimeout(int i) {
        if (i >= 0) {
            int unused = this.inputStream.readTimeout = i;
            return;
        }
        throw new IllegalArgumentException("Timeout must be >= 0");
    }

    public boolean isCloseBothStreamsEnabled() {
        return this.closeBothStreamsEnabled;
    }

    public void setCloseBothStreamsEnabled(boolean z) {
        this.closeBothStreamsEnabled = z;
    }

    public void close() throws IOException {
        closeByLocal(true);
        closeByLocal(false);
    }

    /* access modifiers changed from: protected */
    public void closeByPeer(Close close) {
        this.inputStream.closeInternal();
        this.inputStream.cleanup();
        this.outputStream.closeInternal(false);
        this.connection.sendPacket(IQ.createResultIQ(close));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0079, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void closeByLocal(boolean r4) throws java.io.IOException {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.isClosed     // Catch:{ all -> 0x007a }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r3)
            return
        L_0x0007:
            boolean r0 = r3.closeBothStreamsEnabled     // Catch:{ all -> 0x007a }
            r1 = 1
            if (r0 == 0) goto L_0x0017
            org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamSession$IBBInputStream r4 = r3.inputStream     // Catch:{ all -> 0x007a }
            r4.closeInternal()     // Catch:{ all -> 0x007a }
            org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamSession$IBBOutputStream r4 = r3.outputStream     // Catch:{ all -> 0x007a }
            r4.closeInternal(r1)     // Catch:{ all -> 0x007a }
            goto L_0x0024
        L_0x0017:
            if (r4 == 0) goto L_0x001f
            org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamSession$IBBInputStream r4 = r3.inputStream     // Catch:{ all -> 0x007a }
            r4.closeInternal()     // Catch:{ all -> 0x007a }
            goto L_0x0024
        L_0x001f:
            org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamSession$IBBOutputStream r4 = r3.outputStream     // Catch:{ all -> 0x007a }
            r4.closeInternal(r1)     // Catch:{ all -> 0x007a }
        L_0x0024:
            org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamSession$IBBInputStream r4 = r3.inputStream     // Catch:{ all -> 0x007a }
            boolean r4 = r4.isClosed     // Catch:{ all -> 0x007a }
            if (r4 == 0) goto L_0x0078
            org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamSession$IBBOutputStream r4 = r3.outputStream     // Catch:{ all -> 0x007a }
            boolean r4 = r4.isClosed     // Catch:{ all -> 0x007a }
            if (r4 == 0) goto L_0x0078
            r3.isClosed = r1     // Catch:{ all -> 0x007a }
            org.jivesoftware.smackx.bytestreams.ibb.packet.Close r4 = new org.jivesoftware.smackx.bytestreams.ibb.packet.Close     // Catch:{ all -> 0x007a }
            org.jivesoftware.smackx.bytestreams.ibb.packet.Open r0 = r3.byteStreamRequest     // Catch:{ all -> 0x007a }
            java.lang.String r0 = r0.getSessionID()     // Catch:{ all -> 0x007a }
            r4.<init>(r0)     // Catch:{ all -> 0x007a }
            java.lang.String r0 = r3.remoteJID     // Catch:{ all -> 0x007a }
            r4.setTo(r0)     // Catch:{ all -> 0x007a }
            org.jivesoftware.smack.Connection r0 = r3.connection     // Catch:{ XMPPException -> 0x005c }
            org.jivesoftware.smackx.packet.SyncPacketSend.getReply(r0, r4)     // Catch:{ XMPPException -> 0x005c }
            org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamSession$IBBInputStream r4 = r3.inputStream     // Catch:{ all -> 0x007a }
            r4.cleanup()     // Catch:{ all -> 0x007a }
            org.jivesoftware.smack.Connection r4 = r3.connection     // Catch:{ all -> 0x007a }
            org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager r4 = org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager.getByteStreamManager(r4)     // Catch:{ all -> 0x007a }
            java.util.Map r4 = r4.getSessions()     // Catch:{ all -> 0x007a }
            r4.remove(r3)     // Catch:{ all -> 0x007a }
            goto L_0x0078
        L_0x005c:
            r4 = move-exception
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x007a }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x007a }
            r1.<init>()     // Catch:{ all -> 0x007a }
            java.lang.String r2 = "Error while closing stream: "
            r1.append(r2)     // Catch:{ all -> 0x007a }
            java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x007a }
            r1.append(r4)     // Catch:{ all -> 0x007a }
            java.lang.String r4 = r1.toString()     // Catch:{ all -> 0x007a }
            r0.<init>(r4)     // Catch:{ all -> 0x007a }
            throw r0     // Catch:{ all -> 0x007a }
        L_0x0078:
            monitor-exit(r3)
            return
        L_0x007a:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamSession.closeByLocal(boolean):void");
    }

    private abstract class IBBInputStream extends InputStream {
        private byte[] buffer;
        private int bufferPointer = -1;
        private boolean closeInvoked = false;
        private final PacketListener dataPacketListener;
        protected final BlockingQueue<DataPacketExtension> dataQueue = new LinkedBlockingQueue();
        /* access modifiers changed from: private */
        public boolean isClosed = false;
        /* access modifiers changed from: private */
        public int readTimeout = 0;
        private long seq = -1;

        /* access modifiers changed from: protected */
        public abstract PacketFilter getDataPacketFilter();

        /* access modifiers changed from: protected */
        public abstract PacketListener getDataPacketListener();

        public boolean markSupported() {
            return false;
        }

        public IBBInputStream() {
            PacketListener dataPacketListener2 = getDataPacketListener();
            this.dataPacketListener = dataPacketListener2;
            InBandBytestreamSession.this.connection.addPacketListener(dataPacketListener2, getDataPacketFilter());
        }

        public synchronized int read() throws IOException {
            checkClosed();
            int i = this.bufferPointer;
            if ((i == -1 || i >= this.buffer.length) && !loadBuffer()) {
                return -1;
            }
            byte[] bArr = this.buffer;
            int i2 = this.bufferPointer;
            this.bufferPointer = i2 + 1;
            return bArr[i2];
        }

        /* JADX WARNING: Unknown top exception splitter block from list: {B:23:0x002b=Splitter:B:23:0x002b, B:30:0x003e=Splitter:B:30:0x003e} */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized int read(byte[] r4, int r5, int r6) throws java.io.IOException {
            /*
                r3 = this;
                monitor-enter(r3)
                if (r4 == 0) goto L_0x0044
                if (r5 < 0) goto L_0x003e
                int r0 = r4.length     // Catch:{ all -> 0x004a }
                if (r5 > r0) goto L_0x003e
                if (r6 < 0) goto L_0x003e
                int r0 = r5 + r6
                int r1 = r4.length     // Catch:{ all -> 0x004a }
                if (r0 > r1) goto L_0x003e
                if (r0 < 0) goto L_0x003e
                if (r6 != 0) goto L_0x0016
                r4 = 0
                monitor-exit(r3)
                return r4
            L_0x0016:
                r3.checkClosed()     // Catch:{ all -> 0x004a }
                int r0 = r3.bufferPointer     // Catch:{ all -> 0x004a }
                r1 = -1
                if (r0 == r1) goto L_0x0023
                byte[] r2 = r3.buffer     // Catch:{ all -> 0x004a }
                int r2 = r2.length     // Catch:{ all -> 0x004a }
                if (r0 < r2) goto L_0x002b
            L_0x0023:
                boolean r0 = r3.loadBuffer()     // Catch:{ all -> 0x004a }
                if (r0 != 0) goto L_0x002b
                monitor-exit(r3)
                return r1
            L_0x002b:
                byte[] r0 = r3.buffer     // Catch:{ all -> 0x004a }
                int r1 = r0.length     // Catch:{ all -> 0x004a }
                int r2 = r3.bufferPointer     // Catch:{ all -> 0x004a }
                int r1 = r1 - r2
                if (r6 <= r1) goto L_0x0034
                r6 = r1
            L_0x0034:
                java.lang.System.arraycopy(r0, r2, r4, r5, r6)     // Catch:{ all -> 0x004a }
                int r4 = r3.bufferPointer     // Catch:{ all -> 0x004a }
                int r4 = r4 + r6
                r3.bufferPointer = r4     // Catch:{ all -> 0x004a }
                monitor-exit(r3)
                return r6
            L_0x003e:
                java.lang.IndexOutOfBoundsException r4 = new java.lang.IndexOutOfBoundsException     // Catch:{ all -> 0x004a }
                r4.<init>()     // Catch:{ all -> 0x004a }
                throw r4     // Catch:{ all -> 0x004a }
            L_0x0044:
                java.lang.NullPointerException r4 = new java.lang.NullPointerException     // Catch:{ all -> 0x004a }
                r4.<init>()     // Catch:{ all -> 0x004a }
                throw r4     // Catch:{ all -> 0x004a }
            L_0x004a:
                r4 = move-exception
                monitor-exit(r3)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamSession.IBBInputStream.read(byte[], int, int):int");
        }

        public synchronized int read(byte[] bArr) throws IOException {
            return read(bArr, 0, bArr.length);
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(4:34|35|36|37) */
        /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
            java.lang.Thread.currentThread().interrupt();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0076, code lost:
            return false;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x006e */
        /* JADX WARNING: Unknown top exception splitter block from list: {B:17:0x0031=Splitter:B:17:0x0031, B:34:0x006e=Splitter:B:34:0x006e} */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private synchronized boolean loadBuffer() throws java.io.IOException {
            /*
                r9 = this;
                monitor-enter(r9)
                r0 = 0
                int r1 = r9.readTimeout     // Catch:{ InterruptedException -> 0x006e }
                if (r1 != 0) goto L_0x0024
                r1 = 0
            L_0x0007:
                if (r1 != 0) goto L_0x0031
                boolean r1 = r9.isClosed     // Catch:{ InterruptedException -> 0x006e }
                if (r1 == 0) goto L_0x0017
                java.util.concurrent.BlockingQueue<org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension> r1 = r9.dataQueue     // Catch:{ InterruptedException -> 0x006e }
                boolean r1 = r1.isEmpty()     // Catch:{ InterruptedException -> 0x006e }
                if (r1 == 0) goto L_0x0017
                monitor-exit(r9)
                return r0
            L_0x0017:
                java.util.concurrent.BlockingQueue<org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension> r1 = r9.dataQueue     // Catch:{ InterruptedException -> 0x006e }
                r2 = 1000(0x3e8, double:4.94E-321)
                java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x006e }
                java.lang.Object r1 = r1.poll(r2, r4)     // Catch:{ InterruptedException -> 0x006e }
                org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension r1 = (org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension) r1     // Catch:{ InterruptedException -> 0x006e }
                goto L_0x0007
            L_0x0024:
                java.util.concurrent.BlockingQueue<org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension> r2 = r9.dataQueue     // Catch:{ InterruptedException -> 0x006e }
                long r3 = (long) r1     // Catch:{ InterruptedException -> 0x006e }
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x006e }
                java.lang.Object r1 = r2.poll(r3, r1)     // Catch:{ InterruptedException -> 0x006e }
                org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension r1 = (org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension) r1     // Catch:{ InterruptedException -> 0x006e }
                if (r1 == 0) goto L_0x0066
            L_0x0031:
                long r2 = r9.seq     // Catch:{ all -> 0x006c }
                r4 = 65535(0xffff, double:3.23786E-319)
                int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r6 != 0) goto L_0x003e
                r2 = -1
                r9.seq = r2     // Catch:{ all -> 0x006c }
            L_0x003e:
                long r2 = r1.getSeq()     // Catch:{ all -> 0x006c }
                r4 = 1
                long r4 = r2 - r4
                long r6 = r9.seq     // Catch:{ all -> 0x006c }
                int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r8 != 0) goto L_0x0059
                r9.seq = r2     // Catch:{ all -> 0x006c }
                byte[] r1 = r1.getDecodedData()     // Catch:{ all -> 0x006c }
                r9.buffer = r1     // Catch:{ all -> 0x006c }
                r9.bufferPointer = r0     // Catch:{ all -> 0x006c }
                r0 = 1
                monitor-exit(r9)
                return r0
            L_0x0059:
                org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamSession r0 = org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamSession.this     // Catch:{ all -> 0x006c }
                r0.close()     // Catch:{ all -> 0x006c }
                java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x006c }
                java.lang.String r1 = "Packets out of sequence"
                r0.<init>(r1)     // Catch:{ all -> 0x006c }
                throw r0     // Catch:{ all -> 0x006c }
            L_0x0066:
                java.net.SocketTimeoutException r1 = new java.net.SocketTimeoutException     // Catch:{ InterruptedException -> 0x006e }
                r1.<init>()     // Catch:{ InterruptedException -> 0x006e }
                throw r1     // Catch:{ InterruptedException -> 0x006e }
            L_0x006c:
                r0 = move-exception
                goto L_0x0077
            L_0x006e:
                java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x006c }
                r1.interrupt()     // Catch:{ all -> 0x006c }
                monitor-exit(r9)
                return r0
            L_0x0077:
                monitor-exit(r9)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamSession.IBBInputStream.loadBuffer():boolean");
        }

        private void checkClosed() throws IOException {
            if ((this.isClosed && this.dataQueue.isEmpty()) || this.closeInvoked) {
                this.dataQueue.clear();
                throw new IOException("Stream is closed");
            }
        }

        public void close() throws IOException {
            if (!this.isClosed) {
                this.closeInvoked = true;
                InBandBytestreamSession.this.closeByLocal(true);
            }
        }

        /* access modifiers changed from: private */
        public void closeInternal() {
            if (!this.isClosed) {
                this.isClosed = true;
            }
        }

        /* access modifiers changed from: private */
        public void cleanup() {
            InBandBytestreamSession.this.connection.removePacketListener(this.dataPacketListener);
        }
    }

    private class IQIBBInputStream extends IBBInputStream {
        private IQIBBInputStream() {
            super();
        }

        /* synthetic */ IQIBBInputStream(InBandBytestreamSession inBandBytestreamSession, AnonymousClass1 r2) {
            this();
        }

        /* access modifiers changed from: protected */
        public PacketListener getDataPacketListener() {
            return new PacketListener() {
                private long lastSequence = -1;

                public void processPacket(Packet packet) {
                    DataPacketExtension dataPacketExtension = (DataPacketExtension) packet.getExtension(DataPacketExtension.ELEMENT_NAME, InBandBytestreamManager.NAMESPACE);
                    if (dataPacketExtension.getSeq() <= this.lastSequence) {
                        InBandBytestreamSession.this.connection.sendPacket(IQ.createErrorResponse((IQ) packet, new XMPPError(XMPPError.Condition.unexpected_request)));
                    } else if (dataPacketExtension.getDecodedData() == null) {
                        InBandBytestreamSession.this.connection.sendPacket(IQ.createErrorResponse((IQ) packet, new XMPPError(XMPPError.Condition.bad_request)));
                    } else {
                        IQIBBInputStream.this.dataQueue.offer(dataPacketExtension);
                        InBandBytestreamSession.this.connection.sendPacket(IQ.createResultIQ((IQ) packet));
                        long seq = dataPacketExtension.getSeq();
                        this.lastSequence = seq;
                        if (seq == 65535) {
                            this.lastSequence = -1;
                        }
                    }
                }
            };
        }

        /* access modifiers changed from: protected */
        public PacketFilter getDataPacketFilter() {
            return new AndFilter(new PacketTypeFilter(Data.class), new IBBDataPacketFilter(InBandBytestreamSession.this, (AnonymousClass1) null));
        }
    }

    private class MessageIBBInputStream extends IBBInputStream {
        private MessageIBBInputStream() {
            super();
        }

        /* synthetic */ MessageIBBInputStream(InBandBytestreamSession inBandBytestreamSession, AnonymousClass1 r2) {
            this();
        }

        /* access modifiers changed from: protected */
        public PacketListener getDataPacketListener() {
            return new PacketListener() {
                public void processPacket(Packet packet) {
                    DataPacketExtension dataPacketExtension = (DataPacketExtension) packet.getExtension(DataPacketExtension.ELEMENT_NAME, InBandBytestreamManager.NAMESPACE);
                    if (dataPacketExtension.getDecodedData() != null) {
                        MessageIBBInputStream.this.dataQueue.offer(dataPacketExtension);
                    }
                }
            };
        }

        /* access modifiers changed from: protected */
        public PacketFilter getDataPacketFilter() {
            return new AndFilter(new PacketTypeFilter(Message.class), new IBBDataPacketFilter(InBandBytestreamSession.this, (AnonymousClass1) null));
        }
    }

    private class IBBDataPacketFilter implements PacketFilter {
        private IBBDataPacketFilter() {
        }

        /* synthetic */ IBBDataPacketFilter(InBandBytestreamSession inBandBytestreamSession, AnonymousClass1 r2) {
            this();
        }

        public boolean accept(Packet packet) {
            PacketExtension extension;
            if (packet.getFrom().equalsIgnoreCase(InBandBytestreamSession.this.remoteJID) && (extension = packet.getExtension(DataPacketExtension.ELEMENT_NAME, InBandBytestreamManager.NAMESPACE)) != null && (extension instanceof DataPacketExtension) && ((DataPacketExtension) extension).getSessionID().equals(InBandBytestreamSession.this.byteStreamRequest.getSessionID())) {
                return true;
            }
            return false;
        }
    }

    private abstract class IBBOutputStream extends OutputStream {
        protected final byte[] buffer;
        protected int bufferPointer = 0;
        protected boolean isClosed = false;
        protected long seq = 0;

        /* access modifiers changed from: protected */
        public abstract void writeToXML(DataPacketExtension dataPacketExtension) throws IOException;

        public IBBOutputStream() {
            this.buffer = new byte[InBandBytestreamSession.this.byteStreamRequest.getBlockSize()];
        }

        public synchronized void write(int i) throws IOException {
            if (!this.isClosed) {
                if (this.bufferPointer >= this.buffer.length) {
                    flushBuffer();
                }
                byte[] bArr = this.buffer;
                int i2 = this.bufferPointer;
                this.bufferPointer = i2 + 1;
                bArr[i2] = (byte) i;
            } else {
                throw new IOException("Stream is closed");
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0030, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized void write(byte[] r3, int r4, int r5) throws java.io.IOException {
            /*
                r2 = this;
                monitor-enter(r2)
                if (r3 == 0) goto L_0x003f
                if (r4 < 0) goto L_0x0039
                int r0 = r3.length     // Catch:{ all -> 0x0045 }
                if (r4 > r0) goto L_0x0039
                if (r5 < 0) goto L_0x0039
                int r0 = r4 + r5
                int r1 = r3.length     // Catch:{ all -> 0x0045 }
                if (r0 > r1) goto L_0x0039
                if (r0 < 0) goto L_0x0039
                if (r5 != 0) goto L_0x0015
                monitor-exit(r2)
                return
            L_0x0015:
                boolean r0 = r2.isClosed     // Catch:{ all -> 0x0045 }
                if (r0 != 0) goto L_0x0031
                byte[] r0 = r2.buffer     // Catch:{ all -> 0x0045 }
                int r1 = r0.length     // Catch:{ all -> 0x0045 }
                if (r5 < r1) goto L_0x002c
                int r0 = r0.length     // Catch:{ all -> 0x0045 }
                r2.writeOut(r3, r4, r0)     // Catch:{ all -> 0x0045 }
                byte[] r0 = r2.buffer     // Catch:{ all -> 0x0045 }
                int r1 = r0.length     // Catch:{ all -> 0x0045 }
                int r4 = r4 + r1
                int r0 = r0.length     // Catch:{ all -> 0x0045 }
                int r5 = r5 - r0
                r2.write(r3, r4, r5)     // Catch:{ all -> 0x0045 }
                goto L_0x002f
            L_0x002c:
                r2.writeOut(r3, r4, r5)     // Catch:{ all -> 0x0045 }
            L_0x002f:
                monitor-exit(r2)
                return
            L_0x0031:
                java.io.IOException r3 = new java.io.IOException     // Catch:{ all -> 0x0045 }
                java.lang.String r4 = "Stream is closed"
                r3.<init>(r4)     // Catch:{ all -> 0x0045 }
                throw r3     // Catch:{ all -> 0x0045 }
            L_0x0039:
                java.lang.IndexOutOfBoundsException r3 = new java.lang.IndexOutOfBoundsException     // Catch:{ all -> 0x0045 }
                r3.<init>()     // Catch:{ all -> 0x0045 }
                throw r3     // Catch:{ all -> 0x0045 }
            L_0x003f:
                java.lang.NullPointerException r3 = new java.lang.NullPointerException     // Catch:{ all -> 0x0045 }
                r3.<init>()     // Catch:{ all -> 0x0045 }
                throw r3     // Catch:{ all -> 0x0045 }
            L_0x0045:
                r3 = move-exception
                monitor-exit(r2)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamSession.IBBOutputStream.write(byte[], int, int):void");
        }

        public synchronized void write(byte[] bArr) throws IOException {
            write(bArr, 0, bArr.length);
        }

        private synchronized void writeOut(byte[] bArr, int i, int i2) throws IOException {
            if (!this.isClosed) {
                int i3 = 0;
                byte[] bArr2 = this.buffer;
                int length = bArr2.length;
                int i4 = this.bufferPointer;
                if (i2 > length - i4) {
                    i3 = bArr2.length - i4;
                    System.arraycopy(bArr, i, bArr2, i4, i3);
                    this.bufferPointer += i3;
                    flushBuffer();
                }
                int i5 = i2 - i3;
                System.arraycopy(bArr, i + i3, this.buffer, this.bufferPointer, i5);
                this.bufferPointer += i5;
            } else {
                throw new IOException("Stream is closed");
            }
        }

        public synchronized void flush() throws IOException {
            if (!this.isClosed) {
                flushBuffer();
            } else {
                throw new IOException("Stream is closed");
            }
        }

        private synchronized void flushBuffer() throws IOException {
            int i = this.bufferPointer;
            if (i != 0) {
                writeToXML(new DataPacketExtension(InBandBytestreamSession.this.byteStreamRequest.getSessionID(), this.seq, StringUtils.encodeBase64(this.buffer, 0, i, false)));
                this.bufferPointer = 0;
                long j = this.seq;
                this.seq = j + 1 == 65535 ? 0 : j + 1;
            }
        }

        public void close() throws IOException {
            if (!this.isClosed) {
                InBandBytestreamSession.this.closeByLocal(false);
            }
        }

        /* access modifiers changed from: protected */
        public void closeInternal(boolean z) {
            if (!this.isClosed) {
                this.isClosed = true;
                if (z) {
                    try {
                        flushBuffer();
                    } catch (IOException unused) {
                    }
                }
            }
        }
    }

    private class IQIBBOutputStream extends IBBOutputStream {
        private IQIBBOutputStream() {
            super();
        }

        /* synthetic */ IQIBBOutputStream(InBandBytestreamSession inBandBytestreamSession, AnonymousClass1 r2) {
            this();
        }

        /* access modifiers changed from: protected */
        public synchronized void writeToXML(DataPacketExtension dataPacketExtension) throws IOException {
            Data data = new Data(dataPacketExtension);
            data.setTo(InBandBytestreamSession.this.remoteJID);
            try {
                SyncPacketSend.getReply(InBandBytestreamSession.this.connection, data);
            } catch (XMPPException e) {
                if (!this.isClosed) {
                    InBandBytestreamSession.this.close();
                    throw new IOException("Error while sending Data: " + e.getMessage());
                }
            }
        }
    }

    private class MessageIBBOutputStream extends IBBOutputStream {
        private MessageIBBOutputStream() {
            super();
        }

        /* synthetic */ MessageIBBOutputStream(InBandBytestreamSession inBandBytestreamSession, AnonymousClass1 r2) {
            this();
        }

        /* access modifiers changed from: protected */
        public synchronized void writeToXML(DataPacketExtension dataPacketExtension) {
            Message message = new Message(InBandBytestreamSession.this.remoteJID);
            message.addExtension(dataPacketExtension);
            InBandBytestreamSession.this.connection.sendPacket(message);
        }
    }
}
