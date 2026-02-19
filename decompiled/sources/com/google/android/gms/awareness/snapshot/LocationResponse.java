package com.google.android.gms.awareness.snapshot;

import android.location.Location;
import com.google.android.gms.common.api.Response;

public class LocationResponse extends Response<LocationResult> {
    public Location getLocation() {
        return ((LocationResult) getResult()).getLocation();
    }
}
