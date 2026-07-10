package com.google.android.gms.fitness

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.common.api.Status
import com.google.android.gms.fitness.request.DataTypeCreateRequest
import com.google.android.gms.fitness.result.DataTypeResult

interface ConfigApi {
    fun createCustomDataType(
        googleApiClient: GoogleApiClient,
        request: DataTypeCreateRequest,
    ): PendingResult<DataTypeResult>

    fun disableFit(googleApiClient: GoogleApiClient): PendingResult<Status>

    fun readDataType(
        googleApiClient: GoogleApiClient,
        dataTypeName: String,
    ): PendingResult<DataTypeResult>
}
