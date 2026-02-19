package com.google.android.gms.location.places;

import com.google.android.gms.common.api.zzb;

public class PlaceBufferResponse extends zzb<Place, PlaceBuffer> {
    PlaceBufferResponse() {
    }

    public CharSequence getAttributions() {
        return ((PlaceBuffer) getResult()).getAttributions();
    }
}
