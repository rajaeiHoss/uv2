package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.location.places.PlaceDetectionApi;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.Places;

public final class zzz implements PlaceDetectionApi {
    public final PendingResult<PlaceLikelihoodBuffer> getCurrentPlace(GoogleApiClient googleApiClient, PlaceFilter placeFilter) {
        return googleApiClient.zzd(new zzaa(this, Places.PLACE_DETECTION_API, googleApiClient, placeFilter));
    }

    public final PendingResult<Status> reportDeviceAtPlace(GoogleApiClient googleApiClient, PlaceReport placeReport) {
        zzbq.checkNotNull(placeReport, "report == null");
        return googleApiClient.zze(new zzab(this, Places.PLACE_DETECTION_API, googleApiClient, placeReport));
    }
}
