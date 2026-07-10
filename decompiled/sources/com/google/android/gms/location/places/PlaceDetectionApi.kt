package com.google.android.gms.location.places

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.common.api.Status

interface PlaceDetectionApi {
    fun getCurrentPlace(
        googleApiClient: GoogleApiClient,
        placeFilter: PlaceFilter,
    ): PendingResult<PlaceLikelihoodBuffer>

    fun reportDeviceAtPlace(
        googleApiClient: GoogleApiClient,
        placeReport: PlaceReport,
    ): PendingResult<Status>
}
