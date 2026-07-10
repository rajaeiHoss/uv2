package com.google.android.gms.awareness

import com.google.android.gms.awareness.fence.FenceQueryRequest
import com.google.android.gms.awareness.fence.FenceQueryResult
import com.google.android.gms.awareness.fence.FenceUpdateRequest
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.common.api.Status

@java.lang.Deprecated
interface FenceApi {
    @java.lang.Deprecated
    fun queryFences(
        googleApiClient: GoogleApiClient,
        request: FenceQueryRequest,
    ): PendingResult<FenceQueryResult>

    @java.lang.Deprecated
    fun updateFences(
        googleApiClient: GoogleApiClient,
        request: FenceUpdateRequest,
    ): PendingResult<Status>
}
