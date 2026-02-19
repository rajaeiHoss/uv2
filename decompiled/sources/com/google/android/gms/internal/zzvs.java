package com.google.android.gms.internal;

@zzabh
public final class zzvs extends zzwm {
    private final Object mLock = new Object();
    private zzvx zzcin;
    private zzvr zzcio;

    public final void onAdClicked() {
        synchronized (this.mLock) {
            zzvr zzvr = this.zzcio;
            if (zzvr != null) {
                zzvr.zzci();
            }
        }
    }

    public final void onAdClosed() {
        synchronized (this.mLock) {
            zzvr zzvr = this.zzcio;
            if (zzvr != null) {
                zzvr.zzcj();
            }
        }
    }

    public final void onAdFailedToLoad(int i) {
        synchronized (this.mLock) {
            zzvx zzvx = this.zzcin;
            if (zzvx != null) {
                zzvx.zzv(i == 3 ? 1 : 2);
                this.zzcin = null;
            }
        }
    }

    public final void onAdImpression() {
        synchronized (this.mLock) {
            zzvr zzvr = this.zzcio;
            if (zzvr != null) {
                zzvr.zzcn();
            }
        }
    }

    public final void onAdLeftApplication() {
        synchronized (this.mLock) {
            zzvr zzvr = this.zzcio;
            if (zzvr != null) {
                zzvr.zzck();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onAdLoaded() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            com.google.android.gms.internal.zzvx r1 = r3.zzcin     // Catch:{ all -> 0x0019 }
            if (r1 == 0) goto L_0x0010
            r2 = 0
            r1.zzv(r2)     // Catch:{ all -> 0x0019 }
            r1 = 0
            r3.zzcin = r1     // Catch:{ all -> 0x0019 }
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            return
        L_0x0010:
            com.google.android.gms.internal.zzvr r1 = r3.zzcio     // Catch:{ all -> 0x0019 }
            if (r1 == 0) goto L_0x0017
            r1.zzcm()     // Catch:{ all -> 0x0019 }
        L_0x0017:
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            return
        L_0x0019:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzvs.onAdLoaded():void");
    }

    public final void onAdOpened() {
        synchronized (this.mLock) {
            zzvr zzvr = this.zzcio;
            if (zzvr != null) {
                zzvr.zzcl();
            }
        }
    }

    public final void onAppEvent(String str, String str2) {
        synchronized (this.mLock) {
            zzvr zzvr = this.zzcio;
            if (zzvr != null) {
                zzvr.zzc(str, str2);
            }
        }
    }

    public final void onVideoEnd() {
        synchronized (this.mLock) {
            zzvr zzvr = this.zzcio;
            if (zzvr != null) {
                zzvr.zzch();
            }
        }
    }

    public final void zza(zzvr zzvr) {
        synchronized (this.mLock) {
            this.zzcio = zzvr;
        }
    }

    public final void zza(zzvx zzvx) {
        synchronized (this.mLock) {
            this.zzcin = zzvx;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.zzwo r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            com.google.android.gms.internal.zzvx r1 = r3.zzcin     // Catch:{ all -> 0x0019 }
            if (r1 == 0) goto L_0x0010
            r2 = 0
            r1.zza(r2, r4)     // Catch:{ all -> 0x0019 }
            r4 = 0
            r3.zzcin = r4     // Catch:{ all -> 0x0019 }
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            return
        L_0x0010:
            com.google.android.gms.internal.zzvr r4 = r3.zzcio     // Catch:{ all -> 0x0019 }
            if (r4 == 0) goto L_0x0017
            r4.zzcm()     // Catch:{ all -> 0x0019 }
        L_0x0017:
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            return
        L_0x0019:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzvs.zza(com.google.android.gms.internal.zzwo):void");
    }

    public final void zzb(zzro zzro, String str) {
        synchronized (this.mLock) {
            zzvr zzvr = this.zzcio;
            if (zzvr != null) {
                zzvr.zza(zzro, str);
            }
        }
    }
}
