package org.jivesoftware.smackx.muc;

import java.util.LinkedList;
import org.jivesoftware.smack.packet.Packet;

class ConnectionDetachedPacketCollector {
    private static final int MAX_PACKETS = 65536;
    private LinkedList<Packet> resultQueue = new LinkedList<>();

    public synchronized Packet pollResult() {
        if (this.resultQueue.isEmpty()) {
            return null;
        }
        return this.resultQueue.removeLast();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:1:0x0001 */
    /* JADX WARNING: Removed duplicated region for block: B:1:0x0001 A[LOOP:0: B:1:0x0001->B:13:0x0001, LOOP_START, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized org.jivesoftware.smack.packet.Packet nextResult() {
        /*
            r1 = this;
            monitor-enter(r1)
        L_0x0001:
            java.util.LinkedList<org.jivesoftware.smack.packet.Packet> r0 = r1.resultQueue     // Catch:{ all -> 0x0017 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x000d
            r1.wait()     // Catch:{ InterruptedException -> 0x0001 }
            goto L_0x0001
        L_0x000d:
            java.util.LinkedList<org.jivesoftware.smack.packet.Packet> r0 = r1.resultQueue     // Catch:{ all -> 0x0017 }
            java.lang.Object r0 = r0.removeLast()     // Catch:{ all -> 0x0017 }
            org.jivesoftware.smack.packet.Packet r0 = (org.jivesoftware.smack.packet.Packet) r0     // Catch:{ all -> 0x0017 }
            monitor-exit(r1)
            return r0
        L_0x0017:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.muc.ConnectionDetachedPacketCollector.nextResult():org.jivesoftware.smack.packet.Packet");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:1|2|(2:4|5)|6|7|(3:9|10|11)(4:12|13|14|15)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x000c */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0017 A[SYNTHETIC, Splitter:B:12:0x0017] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0014  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized org.jivesoftware.smack.packet.Packet nextResult(long r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            java.util.LinkedList<org.jivesoftware.smack.packet.Packet> r0 = r1.resultQueue     // Catch:{ all -> 0x0021 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x000c
            r1.wait(r2)     // Catch:{ InterruptedException -> 0x000c }
        L_0x000c:
            java.util.LinkedList<org.jivesoftware.smack.packet.Packet> r2 = r1.resultQueue     // Catch:{ all -> 0x0021 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x0021 }
            if (r2 == 0) goto L_0x0017
            r2 = 0
            monitor-exit(r1)
            return r2
        L_0x0017:
            java.util.LinkedList<org.jivesoftware.smack.packet.Packet> r2 = r1.resultQueue     // Catch:{ all -> 0x0021 }
            java.lang.Object r2 = r2.removeLast()     // Catch:{ all -> 0x0021 }
            org.jivesoftware.smack.packet.Packet r2 = (org.jivesoftware.smack.packet.Packet) r2     // Catch:{ all -> 0x0021 }
            monitor-exit(r1)
            return r2
        L_0x0021:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.muc.ConnectionDetachedPacketCollector.nextResult(long):org.jivesoftware.smack.packet.Packet");
    }

    /* access modifiers changed from: protected */
    public synchronized void processPacket(Packet packet) {
        if (packet != null) {
            if (this.resultQueue.size() == 65536) {
                this.resultQueue.removeLast();
            }
            this.resultQueue.addFirst(packet);
            notifyAll();
        }
    }
}
