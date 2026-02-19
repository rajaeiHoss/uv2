package com.google.android.gms.internal;

final class zzhk implements Runnable {
    private /* synthetic */ zzhj zzazc;

    zzhk(zzhj zzhj) {
        this.zzazc = zzhj;
    }

    public final void run() {
        synchronized (this.zzazc.mLock) {
            if (!this.zzazc.zzayw || !this.zzazc.zzayx) {
                zzahw.zzby("App is still foreground");
            } else {
                boolean unused = this.zzazc.zzayw = false;
                zzahw.zzby("App went background");
                for (zzhl zzh : this.zzazc.zzayy) {
                    try {
                        zzh.zzh(false);
                    } catch (Exception e) {
                        zzahw.zzb("OnForegroundStateChangedListener threw exception.", e);
                    }
                }
            }
        }
    }
}
