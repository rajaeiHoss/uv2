package com.google.android.gms.internal;

import java.util.concurrent.Callable;

final class zzwa implements Callable<zzvw> {
    private /* synthetic */ zzvt zzcjr;
    private /* synthetic */ zzvz zzcjs;

    zzwa(zzvz zzvz, zzvt zzvt) {
        this.zzcjs = zzvz;
        this.zzcjr = zzvt;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzmo */
    public final zzvw call() throws Exception {
        synchronized (this.zzcjs.mLock) {
            if (this.zzcjs.zzcjn) {
                return null;
            }
            return this.zzcjr.zza(this.zzcjs.mStartTime, this.zzcjs.zzcjl);
        }
    }
}
