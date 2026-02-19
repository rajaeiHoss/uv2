package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;

public final class zzczb {
    private static Object zzkmq = new Object();
    private static zzczb zzkvl;
    private volatile boolean mClosed;
    /* access modifiers changed from: private */
    public final Context mContext;
    private final zze zzata;
    private final Thread zzdeq;
    private volatile AdvertisingIdClient.Info zzdyc;
    private volatile long zzkmk;
    private volatile long zzkml;
    private volatile long zzkmm;
    private volatile long zzkmn;
    private final Object zzkmo;
    /* access modifiers changed from: private */
    public volatile boolean zzkvj;
    private zzcze zzkvk;

    private zzczb(Context context) {
        this(context, (zzcze) null, zzi.zzanq());
    }

    private zzczb(Context context, zzcze zzcze, zze zze) {
        this.zzkmk = 900000;
        this.zzkml = NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS;
        this.zzkvj = true;
        this.mClosed = false;
        this.zzkmo = new Object();
        this.zzkvk = new zzczc(this);
        this.zzata = zze;
        this.mContext = context != null ? context.getApplicationContext() : context;
        this.zzkmm = zze.currentTimeMillis();
        this.zzdeq = new Thread(new zzczd(this));
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x000c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzbfc() {
        /*
            r2 = this;
            monitor-enter(r2)
            r2.zzbfd()     // Catch:{ InterruptedException -> 0x000c }
            r0 = 500(0x1f4, double:2.47E-321)
            r2.wait(r0)     // Catch:{ InterruptedException -> 0x000c }
            goto L_0x000c
        L_0x000a:
            r0 = move-exception
            goto L_0x000e
        L_0x000c:
            monitor-exit(r2)     // Catch:{ all -> 0x000a }
            return
        L_0x000e:
            monitor-exit(r2)     // Catch:{ all -> 0x000a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzczb.zzbfc():void");
    }

    private final void zzbfd() {
        if (this.zzata.currentTimeMillis() - this.zzkmm > this.zzkml) {
            synchronized (this.zzkmo) {
                this.zzkmo.notify();
            }
            this.zzkmm = this.zzata.currentTimeMillis();
        }
    }

    private final void zzbfe() {
        if (this.zzata.currentTimeMillis() - this.zzkmn > 3600000) {
            this.zzdyc = null;
        }
    }

    /* access modifiers changed from: private */
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
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:368)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:172)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public final void zzbff() {
        /*
            r4 = this;
            r0 = 10
            android.os.Process.setThreadPriority(r0)
        L_0x0005:
            r0 = 0
            boolean r1 = r4.zzkvj
            if (r1 == 0) goto L_0x0010
            com.google.android.gms.internal.zzcze r0 = r4.zzkvk
            com.google.android.gms.ads.identifier.AdvertisingIdClient$Info r0 = r0.zzbfg()
        L_0x0010:
            if (r0 == 0) goto L_0x0021
            r4.zzdyc = r0
            com.google.android.gms.common.util.zze r0 = r4.zzata
            long r0 = r0.currentTimeMillis()
            r4.zzkmn = r0
            java.lang.String r0 = "Obtained fresh AdvertisingId info from GmsCore."
            com.google.android.gms.internal.zzdal.zzcy(r0)
        L_0x0021:
            monitor-enter(r4)
            r4.notifyAll()     // Catch:{ all -> 0x003b }
            monitor-exit(r4)     // Catch:{ all -> 0x003b }
            java.lang.Object r0 = r4.zzkmo     // Catch:{ InterruptedException -> 0x0035 }
            monitor-enter(r0)     // Catch:{ InterruptedException -> 0x0035 }
            java.lang.Object r1 = r4.zzkmo     // Catch:{ all -> 0x0032 }
            long r2 = r4.zzkmk     // Catch:{ all -> 0x0032 }
            r1.wait(r2)     // Catch:{ all -> 0x0032 }
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            goto L_0x0005
        L_0x0032:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            throw r1     // Catch:{ InterruptedException -> 0x0035 }
        L_0x0035:
            java.lang.String r0 = "sleep interrupted in AdvertiserDataPoller thread; continuing"
            com.google.android.gms.internal.zzdal.zzcy(r0)
            goto L_0x0005
        L_0x003b:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x003b }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzczb.zzbff():void");
    }

    public static zzczb zzep(Context context) {
        if (zzkvl == null) {
            synchronized (zzkmq) {
                if (zzkvl == null) {
                    zzczb zzczb = new zzczb(context);
                    zzkvl = zzczb;
                    zzczb.zzdeq.start();
                }
            }
        }
        return zzkvl;
    }

    public final boolean isLimitAdTrackingEnabled() {
        if (this.zzdyc == null) {
            zzbfc();
        } else {
            zzbfd();
        }
        zzbfe();
        if (this.zzdyc == null) {
            return true;
        }
        return this.zzdyc.isLimitAdTrackingEnabled();
    }

    public final String zzbfb() {
        if (this.zzdyc == null) {
            zzbfc();
        } else {
            zzbfd();
        }
        zzbfe();
        if (this.zzdyc == null) {
            return null;
        }
        return this.zzdyc.getId();
    }
}
