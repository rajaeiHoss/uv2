package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.nearby.connection.PayloadCallback;

final class zzcpt extends zzcsr {
    private final zzci<PayloadCallback> zzjyb;

    zzcpt(zzci<PayloadCallback> zzci) {
        this.zzjyb = (zzci) zzbq.checkNotNull(zzci);
    }

    public final void zza(zzctp zzctp) {
        this.zzjyb.zza(new zzcpu(this, zzctp));
    }

    public final void zza(zzctr zzctr) {
        this.zzjyb.zza(new zzcpv(this, zzctr));
    }
}
