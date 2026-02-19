package com.google.android.gms.internal;

import com.google.android.gms.awareness.snapshot.WeatherResult;
import com.google.android.gms.awareness.state.Weather;
import com.google.android.gms.common.api.Status;

final class zzazs implements WeatherResult {
    private /* synthetic */ zzazy zzert;

    zzazs(zzazr zzazr, zzazy zzazy) {
        this.zzert = zzazy;
    }

    public final Status getStatus() {
        return this.zzert.getStatus();
    }

    public final Weather getWeather() {
        if (this.zzert.zzadl() == null) {
            return null;
        }
        return this.zzert.zzadl().zzadj();
    }
}
