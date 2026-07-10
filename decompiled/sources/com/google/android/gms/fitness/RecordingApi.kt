package com.google.android.gms.fitness

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.common.api.Status
import com.google.android.gms.fitness.data.DataSource
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Subscription
import com.google.android.gms.fitness.result.ListSubscriptionsResult

interface RecordingApi {
    fun listSubscriptions(googleApiClient: GoogleApiClient): PendingResult<ListSubscriptionsResult>

    fun listSubscriptions(
        googleApiClient: GoogleApiClient,
        dataType: DataType,
    ): PendingResult<ListSubscriptionsResult>

    fun subscribe(
        googleApiClient: GoogleApiClient,
        dataSource: DataSource,
    ): PendingResult<Status>

    fun subscribe(
        googleApiClient: GoogleApiClient,
        dataType: DataType,
    ): PendingResult<Status>

    fun unsubscribe(
        googleApiClient: GoogleApiClient,
        dataSource: DataSource,
    ): PendingResult<Status>

    fun unsubscribe(
        googleApiClient: GoogleApiClient,
        dataType: DataType,
    ): PendingResult<Status>

    fun unsubscribe(
        googleApiClient: GoogleApiClient,
        subscription: Subscription,
    ): PendingResult<Status>
}
