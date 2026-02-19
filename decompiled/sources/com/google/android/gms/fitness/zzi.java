package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.result.DailyTotalResult;

final /* synthetic */ class zzi implements zzbo {
    static final zzbo zzgui = new zzi();

    private zzi() {
    }

    public final Object zzb(Result result) {
        return ((DailyTotalResult) result).getTotal();
    }
}
