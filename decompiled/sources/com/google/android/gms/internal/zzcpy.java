package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.nearby.connection.Connections;

final class zzcpy extends zzcsx {
    private final zzn<Connections.StartAdvertisingResult> zzhmf;

    zzcpy(zzn<Connections.StartAdvertisingResult> zzn) {
        this.zzhmf = (zzn) zzbq.checkNotNull(zzn);
    }

    public final void zza(zzctt zzctt) {
        Status zzeq = zzcov.zzcm(zzctt.getStatusCode());
        if (zzeq.isSuccess()) {
            this.zzhmf.setResult(new zzcpx(zzeq, zzctt.getLocalEndpointName()));
        } else {
            this.zzhmf.zzu(zzeq);
        }
    }
}
