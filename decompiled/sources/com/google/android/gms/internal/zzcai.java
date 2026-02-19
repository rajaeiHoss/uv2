package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.fitness.result.DataTypeResult;

final class zzcai extends zzbyr {
    private final zzn<DataTypeResult> zzhmf;

    private zzcai(zzn<DataTypeResult> zzn) {
        this.zzhmf = zzn;
    }

    /* synthetic */ zzcai(zzn zzn, zzcaf zzcaf) {
        this(zzn);
    }

    public final void zza(DataTypeResult dataTypeResult) {
        this.zzhmf.setResult(dataTypeResult);
    }
}
