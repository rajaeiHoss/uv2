package com.google.android.gms.location

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult

@java.lang.Deprecated
interface SettingsApi {
    fun checkLocationSettings(
        googleApiClient: GoogleApiClient,
        request: LocationSettingsRequest,
    ): PendingResult<LocationSettingsResult>
}
