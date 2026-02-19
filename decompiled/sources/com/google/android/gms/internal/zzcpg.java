package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.nearby.connection.Connections;

@Deprecated
final class zzcpg extends zzcsh {
    private final zzci<Connections.ConnectionResponseCallback> zzjxu;

    public zzcpg(zzci<Connections.ConnectionResponseCallback> zzci) {
        this.zzjxu = (zzci) zzbq.checkNotNull(zzci);
    }

    public final void zza(zzctf zzctf) {
        this.zzjxu.zza(new zzcph(this, zzctf));
    }
}
