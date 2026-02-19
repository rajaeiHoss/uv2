package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzcl;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;

final class zzchd implements zzcl<LocationCallback> {
    private /* synthetic */ LocationAvailability zziui;

    zzchd(zzchb zzchb, LocationAvailability locationAvailability) {
        this.zziui = locationAvailability;
    }

    public final void zzajh() {
    }

    public final void zzu(LocationCallback locationCallback) {
        locationCallback.onLocationAvailability(this.zziui);
    }
}
