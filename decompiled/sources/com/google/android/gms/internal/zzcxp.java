package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzcxf;
import com.google.android.gms.safetynet.zzd;

final class zzcxp extends zzcwz {
    private /* synthetic */ zzcxf.zzd zzkku;

    zzcxp(zzcxf.zzd zzd) {
        this.zzkku = zzd;
    }

    public final void zza(Status status, zzd zzd) {
        this.zzkku.setResult(new zzcxf.zzg(status, zzd));
    }
}
