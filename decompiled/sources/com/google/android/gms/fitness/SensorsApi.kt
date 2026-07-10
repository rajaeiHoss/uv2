package com.google.android.gms.fitness

import android.app.PendingIntent
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.common.api.Status
import com.google.android.gms.fitness.request.DataSourcesRequest
import com.google.android.gms.fitness.request.OnDataPointListener
import com.google.android.gms.fitness.request.SensorRequest
import com.google.android.gms.fitness.result.DataSourcesResult

interface SensorsApi {
    fun add(
        googleApiClient: GoogleApiClient,
        request: SensorRequest,
        pendingIntent: PendingIntent,
    ): PendingResult<Status>

    fun add(
        googleApiClient: GoogleApiClient,
        request: SensorRequest,
        listener: OnDataPointListener,
    ): PendingResult<Status>

    fun findDataSources(
        googleApiClient: GoogleApiClient,
        request: DataSourcesRequest,
    ): PendingResult<DataSourcesResult>

    fun remove(
        googleApiClient: GoogleApiClient,
        pendingIntent: PendingIntent,
    ): PendingResult<Status>

    fun remove(
        googleApiClient: GoogleApiClient,
        listener: OnDataPointListener,
    ): PendingResult<Status>
}
