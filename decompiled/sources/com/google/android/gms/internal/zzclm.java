package com.google.android.gms.internal;

import com.google.android.gms.measurement.AppMeasurement;

final class zzclm implements Runnable {
    private /* synthetic */ zzclk zzjpy;
    private /* synthetic */ AppMeasurement.ConditionalUserProperty zzjpz;

    zzclm(zzclk zzclk, AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        this.zzjpy = zzclk;
        this.zzjpz = conditionalUserProperty;
    }

    public final void run() {
        this.zzjpy.zzb(this.zzjpz);
    }
}
