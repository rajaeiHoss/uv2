package com.google.android.gms.internal;

import android.content.Context;
import android.content.IntentFilter;

final class zzdbe extends zzdbd {
    /* access modifiers changed from: private */
    public static final Object zzkti = new Object();
    private static zzdbe zzkyf;
    /* access modifiers changed from: private */
    public boolean connected = true;
    /* access modifiers changed from: private */
    public Context zzktj;
    /* access modifiers changed from: private */
    public int zzktm = 1800000;
    private boolean zzktn = true;
    private boolean zzkto = false;
    private boolean zzktp = true;
    private boolean zzktt = false;
    /* access modifiers changed from: private */
    public zzdac zzkxz;
    private volatile zzczz zzkya;
    /* access modifiers changed from: private */
    public boolean zzkyb = false;
    private zzdad zzkyc = new zzdbf(this);
    private zzdbh zzkyd;
    private zzdan zzkye;

    private zzdbe() {
    }

    /* access modifiers changed from: private */
    public final boolean isPowerSaveMode() {
        return this.zzktt || !this.connected || this.zzktm <= 0;
    }

    public static zzdbe zzbje() {
        if (zzkyf == null) {
            zzkyf = new zzdbe();
        }
        return zzkyf;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void dispatch() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.zzkto     // Catch:{ all -> 0x0021 }
            r1 = 1
            if (r0 != 0) goto L_0x000f
            java.lang.String r0 = "Dispatch call queued. Dispatch will run once initialization is complete."
            com.google.android.gms.internal.zzdal.v(r0)     // Catch:{ all -> 0x0021 }
            r2.zzktn = r1     // Catch:{ all -> 0x0021 }
            monitor-exit(r2)
            return
        L_0x000f:
            boolean r0 = r2.zzkyb     // Catch:{ all -> 0x0021 }
            if (r0 != 0) goto L_0x001f
            r2.zzkyb = r1     // Catch:{ all -> 0x0021 }
            com.google.android.gms.internal.zzczz r0 = r2.zzkya     // Catch:{ all -> 0x0021 }
            com.google.android.gms.internal.zzdbg r1 = new com.google.android.gms.internal.zzdbg     // Catch:{ all -> 0x0021 }
            r1.<init>(r2)     // Catch:{ all -> 0x0021 }
            r0.zzm(r1)     // Catch:{ all -> 0x0021 }
        L_0x001f:
            monitor-exit(r2)
            return
        L_0x0021:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdbe.dispatch():void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zza(android.content.Context r2, com.google.android.gms.internal.zzczz r3) {
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
            com.google.android.gms.internal.zzczz r2 = r1.zzkya     // Catch:{ all -> 0x0015 }
            if (r2 != 0) goto L_0x0013
            r1.zzkya = r3     // Catch:{ all -> 0x0015 }
        L_0x0013:
            monitor-exit(r1)
            return
        L_0x0015:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdbe.zza(android.content.Context, com.google.android.gms.internal.zzczz):void");
    }

    public final synchronized void zzbhy() {
        if (!isPowerSaveMode()) {
            this.zzkyd.zzbic();
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized zzdac zzbjf() {
        if (this.zzkxz == null) {
            if (this.zzktj != null) {
                this.zzkxz = new zzdao(this.zzkyc, this.zzktj);
            } else {
                throw new IllegalStateException("Cant get a store unless we have a context");
            }
        }
        if (this.zzkyd == null) {
            zzdbi zzdbi = new zzdbi(this, (zzdbf) null);
            this.zzkyd = zzdbi;
            int i = this.zzktm;
            if (i > 0) {
                zzdbi.zzs((long) i);
            }
        }
        this.zzkto = true;
        if (this.zzktn) {
            dispatch();
            this.zzktn = false;
        }
        if (this.zzkye == null && this.zzktp) {
            zzdan zzdan = new zzdan(this);
            this.zzkye = zzdan;
            Context context = this.zzktj;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            context.registerReceiver(zzdan, intentFilter);
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("com.google.analytics.RADIO_POWERED");
            intentFilter2.addCategory(context.getPackageName());
            context.registerReceiver(zzdan, intentFilter2);
        }
        return this.zzkxz;
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
                this.zzkyd.cancel();
                zzdal.v("PowerSaveMode initiated.");
                return;
            }
            this.zzkyd.zzs((long) this.zzktm);
            zzdal.v("PowerSaveMode terminated.");
        }
    }
}
