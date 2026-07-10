package com.google.android.gms.fitness

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.fitness.request.GoalsReadRequest
import com.google.android.gms.fitness.result.GoalsResult

interface GoalsApi {
    fun readCurrentGoals(
        googleApiClient: GoogleApiClient,
        request: GoalsReadRequest,
    ): PendingResult<GoalsResult>
}
