package com.google.android.gms.appinvite

import android.content.Intent
import com.google.android.gms.common.api.Result
import com.google.android.gms.common.api.Status

@java.lang.Deprecated
interface AppInviteInvitationResult : Result {
    val invitationIntent: Intent

    override fun getStatus(): Status
}
