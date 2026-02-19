package com.google.android.gms.location.places;

import com.google.android.gms.common.api.zzb;

public class PlaceLikelihoodBufferResponse extends zzb<PlaceLikelihood, PlaceLikelihoodBuffer> {
    PlaceLikelihoodBufferResponse() {
    }

    public CharSequence getAttributions() {
        return ((PlaceLikelihoodBuffer) getResult()).getAttributions();
    }

    public String toString() {
        return ((PlaceLikelihoodBuffer) getResult()).toString();
    }
}
