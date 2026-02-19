package org.jivesoftware.smack;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.jivesoftware.smack.packet.Packet;

class PacketWriter {
    private XMPPConnection connection;
    /* access modifiers changed from: private */
    public boolean done;
    /* access modifiers changed from: private */
    public Thread keepAliveThread;
    /* access modifiers changed from: private */
    public long lastActive = System.currentTimeMillis();
    private final BlockingQueue<Packet> queue = new ArrayBlockingQueue(500, true);
    /* access modifiers changed from: private */
    public Writer writer;
    private Thread writerThread;

    protected PacketWriter(XMPPConnection xMPPConnection) {
        this.connection = xMPPConnection;
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.writer = this.connection.writer;
        this.done = false;
        Thread writer = new Thread() {
            public void run() {
                PacketWriter.this.writePackets(this);
            }
        };
        this.writerThread = writer;
        writer.setName("Smack Packet Writer (" + this.connection.connectionCounterValue + ")");
        this.writerThread.setDaemon(true);
    }

    public void sendPacket(Packet packet) {
        if (!this.done) {
            this.connection.firePacketInterceptors(packet);
            try {
                this.queue.put(packet);
                synchronized (this.queue) {
                    this.queue.notifyAll();
                }
                this.connection.firePacketSendingListeners(packet);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startup() {
        this.writerThread.start();
    }

    /* access modifiers changed from: package-private */
    public void startKeepAliveProcess() {
        int keepAliveInterval = SmackConfiguration.getKeepAliveInterval();
        if (keepAliveInterval > 0) {
            KeepAliveTask keepAliveTask = new KeepAliveTask(keepAliveInterval);
            Thread thread = new Thread(keepAliveTask);
            this.keepAliveThread = thread;
            keepAliveTask.setThread(thread);
            this.keepAliveThread.setDaemon(true);
            Thread thread2 = this.keepAliveThread;
            thread2.setName("Smack Keep Alive (" + this.connection.connectionCounterValue + ")");
            this.keepAliveThread.start();
        }
    }

    /* access modifiers changed from: package-private */
    public void setWriter(Writer writer2) {
        this.writer = writer2;
    }

    public void shutdown() {
        this.done = true;
        synchronized (this.queue) {
            this.queue.notifyAll();
        }
    }

    /* access modifiers changed from: package-private */
    public void cleanup() {
        this.connection.interceptors.clear();
        this.connection.sendListeners.clear();
    }

    private Packet nextPacket() {
        Packet packet = null;
        while (!this.done && (packet = (Packet) this.queue.poll()) == null) {
            try {
                synchronized (this.queue) {
                    this.queue.wait();
                }
            } catch (InterruptedException unused) {
            }
        }
        return packet;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:44:0x0075 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:46:0x0076 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writePackets(java.lang.Thread r5) {
        /*
            r4 = this;
            r4.openStream()     // Catch:{ IOException -> 0x0079 }
        L_0x0003:
            boolean r0 = r4.done     // Catch:{ IOException -> 0x0079 }
            if (r0 != 0) goto L_0x002d
            java.lang.Thread r0 = r4.writerThread     // Catch:{ IOException -> 0x0079 }
            if (r0 != r5) goto L_0x002d
            org.jivesoftware.smack.packet.Packet r0 = r4.nextPacket()     // Catch:{ IOException -> 0x0079 }
            if (r0 == 0) goto L_0x0003
            java.io.Writer r1 = r4.writer     // Catch:{ IOException -> 0x0079 }
            monitor-enter(r1)     // Catch:{ IOException -> 0x0079 }
            java.io.Writer r2 = r4.writer     // Catch:{ all -> 0x002a }
            java.lang.String r0 = r0.toXML()     // Catch:{ all -> 0x002a }
            r2.write(r0)     // Catch:{ all -> 0x002a }
            java.io.Writer r0 = r4.writer     // Catch:{ all -> 0x002a }
            r0.flush()     // Catch:{ all -> 0x002a }
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x002a }
            r4.lastActive = r2     // Catch:{ all -> 0x002a }
            monitor-exit(r1)     // Catch:{ all -> 0x002a }
            goto L_0x0003
        L_0x002a:
            r5 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x002a }
            throw r5     // Catch:{ IOException -> 0x0079 }
        L_0x002d:
            java.io.Writer r5 = r4.writer     // Catch:{ Exception -> 0x0054 }
            monitor-enter(r5)     // Catch:{ Exception -> 0x0054 }
        L_0x0030:
            java.util.concurrent.BlockingQueue<org.jivesoftware.smack.packet.Packet> r0 = r4.queue     // Catch:{ all -> 0x0051 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0051 }
            if (r0 != 0) goto L_0x004a
            java.util.concurrent.BlockingQueue<org.jivesoftware.smack.packet.Packet> r0 = r4.queue     // Catch:{ all -> 0x0051 }
            java.lang.Object r0 = r0.remove()     // Catch:{ all -> 0x0051 }
            org.jivesoftware.smack.packet.Packet r0 = (org.jivesoftware.smack.packet.Packet) r0     // Catch:{ all -> 0x0051 }
            java.io.Writer r1 = r4.writer     // Catch:{ all -> 0x0051 }
            java.lang.String r0 = r0.toXML()     // Catch:{ all -> 0x0051 }
            r1.write(r0)     // Catch:{ all -> 0x0051 }
            goto L_0x0030
        L_0x004a:
            java.io.Writer r0 = r4.writer     // Catch:{ all -> 0x0051 }
            r0.flush()     // Catch:{ all -> 0x0051 }
            monitor-exit(r5)     // Catch:{ all -> 0x0051 }
            goto L_0x0058
        L_0x0051:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0051 }
            throw r0     // Catch:{ Exception -> 0x0054 }
        L_0x0054:
            r5 = move-exception
            r5.printStackTrace()     // Catch:{ IOException -> 0x0079 }
        L_0x0058:
            java.util.concurrent.BlockingQueue<org.jivesoftware.smack.packet.Packet> r5 = r4.queue     // Catch:{ IOException -> 0x0079 }
            r5.clear()     // Catch:{ IOException -> 0x0079 }
            java.io.Writer r5 = r4.writer     // Catch:{ Exception -> 0x0076, all -> 0x006f }
            java.lang.String r0 = "</stream:stream>"
            r5.write(r0)     // Catch:{ Exception -> 0x0076, all -> 0x006f }
            java.io.Writer r5 = r4.writer     // Catch:{ Exception -> 0x0076, all -> 0x006f }
            r5.flush()     // Catch:{ Exception -> 0x0076, all -> 0x006f }
            java.io.Writer r5 = r4.writer     // Catch:{ Exception -> 0x0088 }
        L_0x006b:
            r5.close()     // Catch:{ Exception -> 0x0088 }
            goto L_0x0088
        L_0x006f:
            r5 = move-exception
            java.io.Writer r0 = r4.writer     // Catch:{ Exception -> 0x0075 }
            r0.close()     // Catch:{ Exception -> 0x0075 }
        L_0x0075:
            throw r5     // Catch:{ IOException -> 0x0079 }
        L_0x0076:
            java.io.Writer r5 = r4.writer     // Catch:{ Exception -> 0x0088 }
            goto L_0x006b
        L_0x0079:
            r5 = move-exception
            boolean r0 = r4.done
            if (r0 != 0) goto L_0x0088
            r0 = 1
            r4.done = r0
            org.jivesoftware.smack.XMPPConnection r0 = r4.connection
            org.jivesoftware.smack.PacketReader r0 = r0.packetReader
            r0.notifyConnectionError(r5)
        L_0x0088:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.PacketWriter.writePackets(java.lang.Thread):void");
    }

    /* access modifiers changed from: package-private */
    public void openStream() throws IOException {
        this.writer.write("<stream:stream" + " to=\"" + this.connection.getServiceName() + "\"" + " xmlns=\"jabber:client\"" + " xmlns:stream=\"http://etherx.jabber.org/streams\"" + " version=\"1.0\">");
        this.writer.flush();
    }

    private class KeepAliveTask implements Runnable {
        private int delay;
        private Thread thread;

        public KeepAliveTask(int i) {
            this.delay = i;
        }

        /* access modifiers changed from: protected */
        public void setThread(Thread thread2) {
            this.thread = thread2;
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(6:10|11|12|(2:14|15)|16|17) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0046 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r6 = this;
                r0 = 15000(0x3a98, double:7.411E-320)
                java.lang.Thread.sleep(r0)     // Catch:{ InterruptedException -> 0x0006 }
                goto L_0x0007
            L_0x0006:
            L_0x0007:
                org.jivesoftware.smack.PacketWriter r0 = org.jivesoftware.smack.PacketWriter.this
                boolean r0 = r0.done
                if (r0 != 0) goto L_0x0051
                org.jivesoftware.smack.PacketWriter r0 = org.jivesoftware.smack.PacketWriter.this
                java.lang.Thread r0 = r0.keepAliveThread
                java.lang.Thread r1 = r6.thread
                if (r0 != r1) goto L_0x0051
                org.jivesoftware.smack.PacketWriter r0 = org.jivesoftware.smack.PacketWriter.this
                java.io.Writer r0 = r0.writer
                monitor-enter(r0)
                long r1 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x004e }
                org.jivesoftware.smack.PacketWriter r3 = org.jivesoftware.smack.PacketWriter.this     // Catch:{ all -> 0x004e }
                long r3 = r3.lastActive     // Catch:{ all -> 0x004e }
                long r1 = r1 - r3
                int r3 = r6.delay     // Catch:{ all -> 0x004e }
                long r3 = (long) r3
                int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
                if (r5 < 0) goto L_0x0046
                org.jivesoftware.smack.PacketWriter r1 = org.jivesoftware.smack.PacketWriter.this     // Catch:{ Exception -> 0x0046 }
                java.io.Writer r1 = r1.writer     // Catch:{ Exception -> 0x0046 }
                java.lang.String r2 = " "
                r1.write(r2)     // Catch:{ Exception -> 0x0046 }
                org.jivesoftware.smack.PacketWriter r1 = org.jivesoftware.smack.PacketWriter.this     // Catch:{ Exception -> 0x0046 }
                java.io.Writer r1 = r1.writer     // Catch:{ Exception -> 0x0046 }
                r1.flush()     // Catch:{ Exception -> 0x0046 }
            L_0x0046:
                monitor-exit(r0)     // Catch:{ all -> 0x004e }
                int r0 = r6.delay     // Catch:{ InterruptedException -> 0x0006 }
                long r0 = (long) r0     // Catch:{ InterruptedException -> 0x0006 }
                java.lang.Thread.sleep(r0)     // Catch:{ InterruptedException -> 0x0006 }
                goto L_0x0007
            L_0x004e:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x004e }
                throw r1
            L_0x0051:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.PacketWriter.KeepAliveTask.run():void");
        }
    }
}
