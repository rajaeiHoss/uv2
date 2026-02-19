package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzcxf;

final class zzcxo extends zzcwz {
    private /* synthetic */ zzcxf.zzc zzkkt;

    zzcxo(zzcxf.zzc zzc) {
        this.zzkkt = zzc;
    }

    public final void zzb(Status status, boolean z) {
        this.zzkkt.setResult(new zzcxf.zzj(status, z));
    }
}
