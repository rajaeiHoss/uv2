package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzcl;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

final class zzchc implements zzcl<LocationCallback> {
    private /* synthetic */ LocationResult zziuh;

    zzchc(zzchb zzchb, LocationResult locationResult) {
        this.zziuh = locationResult;
    }

    public final void zzajh() {
    }

    public final void zzu(LocationCallback locationCallback) {
        locationCallback.onLocationResult(this.zziuh);
    }
}
