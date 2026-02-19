package com.google.android.gms.awareness.snapshot;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.location.places.PlaceLikelihood;
import java.util.List;

public class PlacesResponse extends Response<PlacesResult> {
    public List<PlaceLikelihood> getPlaceLikelihoods() {
        return ((PlacesResult) getResult()).getPlaceLikelihoods();
    }
}
