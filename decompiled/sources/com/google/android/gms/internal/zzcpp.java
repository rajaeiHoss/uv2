package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.nearby.connection.Connections;

@Deprecated
final class zzcpp extends zzcsb {
    private final zzci<Connections.MessageListener> zzjxz;

    zzcpp(zzci<Connections.MessageListener> zzci) {
        this.zzjxz = (zzci) zzbq.checkNotNull(zzci);
    }

    public final void zza(zzctj zzctj) {
        this.zzjxz.zza(new zzcpr(this, zzctj));
    }

    public final void zza(zzctp zzctp) {
        this.zzjxz.zza(new zzcpq(this, zzctp));
    }

    public final void zza(zzctr zzctr) {
    }
}
