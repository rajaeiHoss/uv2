package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.internal.zzbq;

public final class zzci<L> {
    private final zzcj zzgas;
    private volatile L zzgat;
    private final zzck<L> zzgau;

    zzci(Looper looper, L l, String str) {
        this.zzgas = new zzcj(this, looper);
        this.zzgat = zzbq.checkNotNull(l, "Listener must not be null");
        this.zzgau = new zzck<>(l, zzbq.zzgv(str));
    }

    public final void clear() {
        this.zzgat = null;
    }

    public final void zza(zzcl<? super L> zzcl) {
        zzbq.checkNotNull(zzcl, "Notifier must not be null");
        this.zzgas.sendMessage(this.zzgas.obtainMessage(1, zzcl));
    }

    public final boolean zzafr() {
        return this.zzgat != null;
    }

    public final zzck<L> zzakx() {
        return this.zzgau;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzcl<? super L> zzcl) {
        L l = this.zzgat;
        if (l == null) {
            zzcl.zzajh();
            return;
        }
        try {
            zzcl.zzu(l);
        } catch (RuntimeException e) {
            zzcl.zzajh();
            throw e;
        }
    }
}
