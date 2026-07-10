package com.google.android.gms.cast

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult

@java.lang.Deprecated
interface CastRemoteDisplayApi {
    fun startRemoteDisplay(
        googleApiClient: GoogleApiClient,
        applicationId: String,
    ): PendingResult<CastRemoteDisplay.CastRemoteDisplaySessionResult>

    fun stopRemoteDisplay(
        googleApiClient: GoogleApiClient,
    ): PendingResult<CastRemoteDisplay.CastRemoteDisplaySessionResult>
}
