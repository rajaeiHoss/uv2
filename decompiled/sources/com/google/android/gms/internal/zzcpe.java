package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.nearby.connection.Connections;

@Deprecated
final class zzcpe extends zzcry {
    private final zzci<Connections.ConnectionRequestListener> zzhov;

    zzcpe(zzci<Connections.ConnectionRequestListener> zzci) {
        this.zzhov = (zzci) zzbq.checkNotNull(zzci);
    }

    public final void zza(zzctd zzctd) {
        this.zzhov.zza(new zzcpf(this, zzctd));
    }

    public final void zza(zzctv zzctv) {
    }
}
