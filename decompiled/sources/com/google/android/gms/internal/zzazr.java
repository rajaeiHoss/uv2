package com.google.android.gms.internal;

import com.google.android.gms.awareness.snapshot.WeatherResult;
import com.google.android.gms.common.api.PendingResult;

final class zzazr extends zzff<WeatherResult, zzazy> {
    zzazr(zzaze zzaze, PendingResult pendingResult) {
        super(pendingResult);
    }

    /* access modifiers changed from: protected */
    public final WeatherResult zza(zzazy zzazy) {
        return new zzazs(this, zzazy);
    }
}
