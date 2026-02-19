package com.google.android.gms.location.places;

import com.google.android.gms.common.api.zzb;

public class AutocompletePredictionBufferResponse extends zzb<AutocompletePrediction, AutocompletePredictionBuffer> {
    AutocompletePredictionBufferResponse() {
    }

    public String toString() {
        return ((AutocompletePredictionBuffer) getResult()).toString();
    }
}
