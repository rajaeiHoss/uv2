package com.google.android.gms.auth.account

import android.accounts.Account
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.common.api.Result

@java.lang.Deprecated
interface WorkAccountApi {
    @java.lang.Deprecated
    interface AddAccountResult : Result {
        val account: Account
    }

    @java.lang.Deprecated
    fun addWorkAccount(
        googleApiClient: GoogleApiClient,
        accountName: String,
    ): PendingResult<AddAccountResult>

    @java.lang.Deprecated
    fun removeWorkAccount(
        googleApiClient: GoogleApiClient,
        account: Account,
    ): PendingResult<Result>

    @java.lang.Deprecated
    fun setWorkAuthenticatorEnabled(
        googleApiClient: GoogleApiClient,
        enabled: Boolean,
    )

    @java.lang.Deprecated
    fun setWorkAuthenticatorEnabledWithResult(
        googleApiClient: GoogleApiClient,
        enabled: Boolean,
    ): PendingResult<Result>
}
