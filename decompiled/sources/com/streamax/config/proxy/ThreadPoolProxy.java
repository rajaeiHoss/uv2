package com.streamax.config.proxy;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolProxy {
    private int mCorePoolSize;
    private ThreadPoolExecutor mExecutor;
    private long mKeepAliveTime;
    private int mMaximumPoolSize;

    public ThreadPoolProxy(int i, int i2, long j) {
        this.mCorePoolSize = i;
        this.mMaximumPoolSize = i2;
        this.mKeepAliveTime = j;
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public synchronized void initThreadPoolExecutor() {
        /*
            r10 = this;
            monitor-enter(r10)
            java.util.concurrent.ThreadPoolExecutor r0 = r10.mExecutor     // Catch:{ all -> 0x004a }
            if (r0 == 0) goto L_0x0013
            boolean r0 = r0.isShutdown()     // Catch:{ all -> 0x004a }
            if (r0 != 0) goto L_0x0013
            java.util.concurrent.ThreadPoolExecutor r0 = r10.mExecutor     // Catch:{ all -> 0x004a }
            boolean r0 = r0.isTerminated()     // Catch:{ all -> 0x004a }
            if (r0 == 0) goto L_0x0045
        L_0x0013:
            monitor-enter(r10)     // Catch:{ all -> 0x004a }
            java.util.concurrent.ThreadPoolExecutor r0 = r10.mExecutor     // Catch:{ all -> 0x0047 }
            if (r0 == 0) goto L_0x0026
            boolean r0 = r0.isShutdown()     // Catch:{ all -> 0x0047 }
            if (r0 != 0) goto L_0x0026
            java.util.concurrent.ThreadPoolExecutor r0 = r10.mExecutor     // Catch:{ all -> 0x0047 }
            boolean r0 = r0.isTerminated()     // Catch:{ all -> 0x0047 }
            if (r0 == 0) goto L_0x0044
        L_0x0026:
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x0047 }
            java.util.concurrent.LinkedBlockingQueue r7 = new java.util.concurrent.LinkedBlockingQueue     // Catch:{ all -> 0x0047 }
            r7.<init>()     // Catch:{ all -> 0x0047 }
            java.util.concurrent.ThreadFactory r8 = java.util.concurrent.Executors.defaultThreadFactory()     // Catch:{ all -> 0x0047 }
            java.util.concurrent.ThreadPoolExecutor$DiscardPolicy r9 = new java.util.concurrent.ThreadPoolExecutor$DiscardPolicy     // Catch:{ all -> 0x0047 }
            r9.<init>()     // Catch:{ all -> 0x0047 }
            java.util.concurrent.ThreadPoolExecutor r0 = new java.util.concurrent.ThreadPoolExecutor     // Catch:{ all -> 0x0047 }
            int r2 = r10.mCorePoolSize     // Catch:{ all -> 0x0047 }
            int r3 = r10.mMaximumPoolSize     // Catch:{ all -> 0x0047 }
            long r4 = r10.mKeepAliveTime     // Catch:{ all -> 0x0047 }
            r1 = r0
            r1.<init>(r2, r3, r4, r6, r7, r8, r9)     // Catch:{ all -> 0x0047 }
            r10.mExecutor = r0     // Catch:{ all -> 0x0047 }
        L_0x0044:
            monitor-exit(r10)     // Catch:{ all -> 0x0047 }
        L_0x0045:
            monitor-exit(r10)
            return
        L_0x0047:
            r0 = move-exception
            monitor-exit(r10)     // Catch:{ all -> 0x0047 }
            throw r0     // Catch:{ all -> 0x004a }
        L_0x004a:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamax.config.proxy.ThreadPoolProxy.initThreadPoolExecutor():void");
    }

    public Future<?> submit(Runnable runnable) {
        initThreadPoolExecutor();
        return this.mExecutor.submit(runnable);
    }

    public void execute(Runnable runnable) {
        initThreadPoolExecutor();
        this.mExecutor.execute(runnable);
    }

    public void remove(Runnable runnable) {
        initThreadPoolExecutor();
        this.mExecutor.remove(runnable);
    }
}
