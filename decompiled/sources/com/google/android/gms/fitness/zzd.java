package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.result.DataTypeResult;

final /* synthetic */ class zzd implements zzbo {
    static final zzbo zzgui = new zzd();

    private zzd() {
    }

    public final Object zzb(Result result) {
        return ((DataTypeResult) result).getDataType();
    }
}
