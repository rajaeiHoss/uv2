package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.result.DataSourcesResult;

final /* synthetic */ class zzm implements zzbo {
    static final zzbo zzgui = new zzm();

    private zzm() {
    }

    public final Object zzb(Result result) {
        return ((DataSourcesResult) result).getDataSources();
    }
}
