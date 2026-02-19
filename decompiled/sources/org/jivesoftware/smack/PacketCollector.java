package org.jivesoftware.smack;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Packet;

public class PacketCollector {
    private static final int MAX_PACKETS = 65536;
    private boolean cancelled = false;
    private Connection conection;
    private PacketFilter packetFilter;
    private LinkedBlockingQueue<Packet> resultQueue;

    protected PacketCollector(Connection connection, PacketFilter packetFilter2) {
        this.conection = connection;
        this.packetFilter = packetFilter2;
        this.resultQueue = new LinkedBlockingQueue<>(65536);
    }

    public void cancel() {
        if (!this.cancelled) {
            this.conection.removePacketCollector(this);
            this.cancelled = true;
        }
    }

    public PacketFilter getPacketFilter() {
        return this.packetFilter;
    }

    public Packet pollResult() {
        return this.resultQueue.poll();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:0|1|3|2) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:0:0x0000 */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:1:?, LOOP_START, MTH_ENTER_BLOCK, SYNTHETIC, Splitter:B:0:0x0000] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.jivesoftware.smack.packet.Packet nextResult() {
        /*
            r1 = this;
        L_0x0000:
            java.util.concurrent.LinkedBlockingQueue<org.jivesoftware.smack.packet.Packet> r0 = r1.resultQueue     // Catch:{ InterruptedException -> 0x0000 }
            java.lang.Object r0 = r0.take()     // Catch:{ InterruptedException -> 0x0000 }
            org.jivesoftware.smack.packet.Packet r0 = (org.jivesoftware.smack.packet.Packet) r0     // Catch:{ InterruptedException -> 0x0000 }
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.PacketCollector.nextResult():org.jivesoftware.smack.packet.Packet");
    }

    public Packet nextResult(long j) {
        do {
            try {
                return this.resultQueue.poll(j, TimeUnit.MILLISECONDS);
            } catch (InterruptedException unused) {
                if (System.currentTimeMillis() >= System.currentTimeMillis() + j) {
                    return null;
                }
            }
        } while (System.currentTimeMillis() >= System.currentTimeMillis() + j);
        return null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void processPacket(org.jivesoftware.smack.packet.Packet r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            if (r2 != 0) goto L_0x0005
            monitor-exit(r1)
            return
        L_0x0005:
            org.jivesoftware.smack.filter.PacketFilter r0 = r1.packetFilter     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x000f
            boolean r0 = r0.accept(r2)     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x001d
        L_0x000f:
            java.util.concurrent.LinkedBlockingQueue<org.jivesoftware.smack.packet.Packet> r0 = r1.resultQueue     // Catch:{ all -> 0x001f }
            boolean r0 = r0.offer(r2)     // Catch:{ all -> 0x001f }
            if (r0 != 0) goto L_0x001d
            java.util.concurrent.LinkedBlockingQueue<org.jivesoftware.smack.packet.Packet> r0 = r1.resultQueue     // Catch:{ all -> 0x001f }
            r0.poll()     // Catch:{ all -> 0x001f }
            goto L_0x000f
        L_0x001d:
            monitor-exit(r1)
            return
        L_0x001f:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.PacketCollector.processPacket(org.jivesoftware.smack.packet.Packet):void");
    }
}
