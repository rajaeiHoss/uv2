package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.fitness.result.DataSourcesResult;

public final class zzbwr extends zzbyo {
    private final zzn<DataSourcesResult> zzhmf;

    public zzbwr(zzn<DataSourcesResult> zzn) {
        this.zzhmf = zzn;
    }

    public final void zza(DataSourcesResult dataSourcesResult) {
        this.zzhmf.setResult(dataSourcesResult);
    }
}
