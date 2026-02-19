package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzcxf;
import com.google.android.gms.safetynet.zzf;

final class zzcxq extends zzcwz {
    private /* synthetic */ zzcxf.zze zzkkv;

    zzcxq(zzcxf.zze zze) {
        this.zzkkv = zze;
    }

    public final void zza(Status status, zzf zzf) {
        this.zzkkv.setResult(new zzcxf.zzh(status, zzf));
    }
}
