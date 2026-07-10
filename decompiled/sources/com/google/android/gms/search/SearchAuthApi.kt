package com.google.android.gms.search

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.common.api.Result
import com.google.android.gms.common.api.Status

interface SearchAuthApi {
    interface GoogleNowAuthResult : Result {
        val googleNowAuthState: GoogleNowAuthState
    }

    fun clearToken(
        googleApiClient: GoogleApiClient,
        token: String,
    ): PendingResult<Status>

    fun getGoogleNowAuth(
        googleApiClient: GoogleApiClient,
        webAppClientId: String,
    ): PendingResult<GoogleNowAuthResult>
}
