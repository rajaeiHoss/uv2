package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzcxf;
import com.google.android.gms.safetynet.zza;

final class zzcxn extends zzcwz {
    private /* synthetic */ zzcxf.zzb zzkks;

    zzcxn(zzcxf.zzb zzb) {
        this.zzkks = zzb;
    }

    public final void zza(Status status, zza zza) {
        this.zzkks.setResult(new zzcxf.zza(status, zza));
    }
}
