package com.google.android.gms.internal;

import java.util.concurrent.Callable;

public final class zzef implements Callable {
    private final zzdm zzagq;
    private final zzba zzakm;

    public zzef(zzdm zzdm, zzba zzba) {
        this.zzagq = zzdm;
        this.zzakm = zzba;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzay */
    public final Void call() throws Exception {
        if (this.zzagq.zzan() != null) {
            this.zzagq.zzan().get();
        }
        zzba zzam = this.zzagq.zzam();
        if (zzam == null) {
            return null;
        }
        try {
            synchronized (this.zzakm) {
                zzfls.zza(this.zzakm, zzfls.zzc(zzam));
            }
            return null;
        } catch (zzflr unused) {
            return null;
        }
    }
}
