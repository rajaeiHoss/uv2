package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.nearby.connection.Connections;

final class zzcpm extends zzcsm {
    private final zzci<Connections.EndpointDiscoveryListener> zzhov;

    zzcpm(zzci<Connections.EndpointDiscoveryListener> zzci) {
        this.zzhov = (zzci) zzbq.checkNotNull(zzci);
    }

    public final void zza(zzctl zzctl) {
        this.zzhov.zza(new zzcpn(this, zzctl));
    }

    public final void zza(zzctn zzctn) {
        this.zzhov.zza(new zzcpo(this, zzctn));
    }

    public final void zza(zzctx zzctx) {
    }
}
