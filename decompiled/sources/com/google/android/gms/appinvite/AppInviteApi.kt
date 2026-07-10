package com.google.android.gms.appinvite

import android.app.Activity
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.common.api.Status

@java.lang.Deprecated
interface AppInviteApi {
    fun convertInvitation(
        googleApiClient: GoogleApiClient,
        invitationId: String,
    ): PendingResult<Status>

    @java.lang.Deprecated
    fun getInvitation(
        googleApiClient: GoogleApiClient,
        activity: Activity,
        autoLaunchDeepLink: Boolean,
    ): PendingResult<AppInviteInvitationResult>

    @java.lang.Deprecated
    fun updateInvitationOnInstall(
        googleApiClient: GoogleApiClient,
        invitationId: String,
    ): PendingResult<Status>
}
