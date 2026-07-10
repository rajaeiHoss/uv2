package com.google.android.gms.location

import android.app.PendingIntent
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.common.api.Status

@java.lang.Deprecated
interface GeofencingApi {
    fun addGeofences(
        googleApiClient: GoogleApiClient,
        request: GeofencingRequest,
        pendingIntent: PendingIntent,
    ): PendingResult<Status>

    @java.lang.Deprecated
    fun addGeofences(
        googleApiClient: GoogleApiClient,
        geofences: List<@JvmSuppressWildcards Geofence>,
        pendingIntent: PendingIntent,
    ): PendingResult<Status>

    fun removeGeofences(
        googleApiClient: GoogleApiClient,
        pendingIntent: PendingIntent,
    ): PendingResult<Status>

    fun removeGeofences(
        googleApiClient: GoogleApiClient,
        requestIds: List<String>,
    ): PendingResult<Status>
}
