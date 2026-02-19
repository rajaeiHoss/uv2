package com.google.android.gms.internal;

import android.location.Location;
import com.google.android.gms.awareness.snapshot.LocationResult;
import com.google.android.gms.common.api.Status;

final class zzazo implements LocationResult {
    private /* synthetic */ zzazy zzert;

    zzazo(zzazn zzazn, zzazy zzazy) {
        this.zzert = zzazy;
    }

    public final Location getLocation() {
        if (this.zzert.zzadl() == null) {
            return null;
        }
        return this.zzert.zzadl().getLocation();
    }

    public final Status getStatus() {
        return this.zzert.getStatus();
    }
}
