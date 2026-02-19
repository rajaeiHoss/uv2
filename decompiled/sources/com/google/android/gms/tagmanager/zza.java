package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;

public final class zza {
    private static Object zzkmq = new Object();
    private static zza zzkmr;
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
    private zzd zzkmp;

    private zza(Context context) {
        this(context, (zzd) null, zzi.zzanq());
    }

    private zza(Context context, zzd zzd, zze zze) {
        this.zzkmk = 900000;
        this.zzkml = NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS;
        this.mClosed = false;
        this.zzkmo = new Object();
        this.zzkmp = new zzb(this);
        this.zzata = zze;
        this.mContext = context != null ? context.getApplicationContext() : context;
        this.zzkmm = zze.currentTimeMillis();
        this.zzdeq = new Thread(new zzc(this));
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0010 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzbfc() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.mClosed     // Catch:{ InterruptedException -> 0x0010 }
            if (r0 != 0) goto L_0x0010
            r2.zzbfd()     // Catch:{ InterruptedException -> 0x0010 }
            r0 = 500(0x1f4, double:2.47E-321)
            r2.wait(r0)     // Catch:{ InterruptedException -> 0x0010 }
            goto L_0x0010
        L_0x000e:
            r0 = move-exception
            goto L_0x0012
        L_0x0010:
            monitor-exit(r2)     // Catch:{ all -> 0x000e }
            return
        L_0x0012:
            monitor-exit(r2)     // Catch:{ all -> 0x000e }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zza.zzbfc():void");
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
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:225)
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
            boolean r0 = r4.mClosed
            if (r0 != 0) goto L_0x003d
            com.google.android.gms.tagmanager.zzd r0 = r4.zzkmp
            com.google.android.gms.ads.identifier.AdvertisingIdClient$Info r0 = r0.zzbfg()
            if (r0 == 0) goto L_0x0020
            r4.zzdyc = r0
            com.google.android.gms.common.util.zze r0 = r4.zzata
            long r0 = r0.currentTimeMillis()
            r4.zzkmn = r0
            java.lang.String r0 = "Obtained fresh AdvertisingId info from GmsCore."
            com.google.android.gms.tagmanager.zzdj.zzcy(r0)
        L_0x0020:
            monitor-enter(r4)
            r4.notifyAll()     // Catch:{ all -> 0x003a }
            monitor-exit(r4)     // Catch:{ all -> 0x003a }
            java.lang.Object r0 = r4.zzkmo     // Catch:{ InterruptedException -> 0x0034 }
            monitor-enter(r0)     // Catch:{ InterruptedException -> 0x0034 }
            java.lang.Object r1 = r4.zzkmo     // Catch:{ all -> 0x0031 }
            long r2 = r4.zzkmk     // Catch:{ all -> 0x0031 }
            r1.wait(r2)     // Catch:{ all -> 0x0031 }
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            goto L_0x0005
        L_0x0031:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            throw r1     // Catch:{ InterruptedException -> 0x0034 }
        L_0x0034:
            java.lang.String r0 = "sleep interrupted in AdvertiserDataPoller thread; continuing"
            com.google.android.gms.tagmanager.zzdj.zzcy(r0)
            goto L_0x0005
        L_0x003a:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x003a }
            throw r0
        L_0x003d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zza.zzbff():void");
    }

    public static zza zzeg(Context context) {
        if (zzkmr == null) {
            synchronized (zzkmq) {
                if (zzkmr == null) {
                    zza zza = new zza(context);
                    zzkmr = zza;
                    zza.zzdeq.start();
                }
            }
        }
        return zzkmr;
    }

    public final void close() {
        this.mClosed = true;
        this.zzdeq.interrupt();
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
