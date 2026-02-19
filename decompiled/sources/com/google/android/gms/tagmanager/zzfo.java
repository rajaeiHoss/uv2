package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.IntentFilter;

final class zzfo extends zzfn {
    /* access modifiers changed from: private */
    public static final Object zzkti = new Object();
    private static zzfo zzktu;
    /* access modifiers changed from: private */
    public boolean connected = true;
    /* access modifiers changed from: private */
    public Context zzktj;
    /* access modifiers changed from: private */
    public zzcc zzktk;
    private volatile zzbz zzktl;
    /* access modifiers changed from: private */
    public int zzktm = 1800000;
    private boolean zzktn = true;
    private boolean zzkto = false;
    private boolean zzktp = true;
    private zzcd zzktq = new zzfp(this);
    private zzfr zzktr;
    private zzdo zzkts;
    private boolean zzktt = false;

    private zzfo() {
    }

    /* access modifiers changed from: private */
    public final boolean isPowerSaveMode() {
        return this.zzktt || !this.connected || this.zzktm <= 0;
    }

    public static zzfo zzbhz() {
        if (zzktu == null) {
            zzktu = new zzfo();
        }
        return zzktu;
    }

    public final synchronized void dispatch() {
        if (!this.zzkto) {
            zzdj.v("Dispatch call queued. Dispatch will run once initialization is complete.");
            this.zzktn = true;
            return;
        }
        this.zzktl.zzm(new zzfq(this));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zza(android.content.Context r2, com.google.android.gms.tagmanager.zzbz r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            android.content.Context r0 = r1.zzktj     // Catch:{ all -> 0x0015 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r1)
            return
        L_0x0007:
            android.content.Context r2 = r2.getApplicationContext()     // Catch:{ all -> 0x0015 }
            r1.zzktj = r2     // Catch:{ all -> 0x0015 }
            com.google.android.gms.tagmanager.zzbz r2 = r1.zzktl     // Catch:{ all -> 0x0015 }
            if (r2 != 0) goto L_0x0013
            r1.zzktl = r3     // Catch:{ all -> 0x0015 }
        L_0x0013:
            monitor-exit(r1)
            return
        L_0x0015:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzfo.zza(android.content.Context, com.google.android.gms.tagmanager.zzbz):void");
    }

    public final synchronized void zzbhy() {
        if (!isPowerSaveMode()) {
            this.zzktr.zzbic();
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized zzcc zzbia() {
        if (this.zzktk == null) {
            if (this.zzktj != null) {
                this.zzktk = new zzec(this.zzktq, this.zzktj);
            } else {
                throw new IllegalStateException("Cant get a store unless we have a context");
            }
        }
        if (this.zzktr == null) {
            zzfs zzfs = new zzfs(this, (zzfp) null);
            this.zzktr = zzfs;
            int i = this.zzktm;
            if (i > 0) {
                zzfs.zzs((long) i);
            }
        }
        this.zzkto = true;
        if (this.zzktn) {
            dispatch();
            this.zzktn = false;
        }
        if (this.zzkts == null && this.zzktp) {
            zzdo zzdo = new zzdo(this);
            this.zzkts = zzdo;
            Context context = this.zzktj;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            context.registerReceiver(zzdo, intentFilter);
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("com.google.analytics.RADIO_POWERED");
            intentFilter2.addCategory(context.getPackageName());
            context.registerReceiver(zzdo, intentFilter2);
        }
        return this.zzktk;
    }

    public final synchronized void zzca(boolean z) {
        zzd(this.zzktt, z);
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzd(boolean z, boolean z2) {
        boolean isPowerSaveMode = isPowerSaveMode();
        this.zzktt = z;
        this.connected = z2;
        if (isPowerSaveMode() != isPowerSaveMode) {
            if (isPowerSaveMode()) {
                this.zzktr.cancel();
                zzdj.v("PowerSaveMode initiated.");
                return;
            }
            this.zzktr.zzs((long) this.zzktm);
            zzdj.v("PowerSaveMode terminated.");
        }
    }
}
