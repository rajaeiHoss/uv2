package com.google.android.gms.location

import android.app.PendingIntent
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.common.api.Status

@java.lang.Deprecated
interface ActivityRecognitionApi {
    fun removeActivityUpdates(
        googleApiClient: GoogleApiClient,
        pendingIntent: PendingIntent,
    ): PendingResult<Status>

    fun requestActivityUpdates(
        googleApiClient: GoogleApiClient,
        detectionIntervalMillis: Long,
        pendingIntent: PendingIntent,
    ): PendingResult<Status>

    fun zza(
        googleApiClient: GoogleApiClient,
        pendingIntent: PendingIntent,
    ): PendingResult<Status>

    fun zza(
        googleApiClient: GoogleApiClient,
        request: ActivityTransitionRequest,
        pendingIntent: PendingIntent,
    ): PendingResult<Status>
}
