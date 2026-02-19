package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.zzcl;
import com.google.android.gms.fitness.data.DataPoint;

final class zzam implements zzcl<OnDataPointListener> {
    private /* synthetic */ DataPoint zzhow;

    zzam(zzal zzal, DataPoint dataPoint) {
        this.zzhow = dataPoint;
    }

    public final void zzajh() {
    }

    public final void zzu(OnDataPointListener onDataPointListener) {
        onDataPointListener.onDataPoint(this.zzhow);
    }
}
