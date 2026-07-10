package com.google.android.gms.fido.u2f

import android.app.Activity
import android.content.IntentSender

interface U2fPendingIntent {
    fun hasPendingIntent(): Boolean

    @Throws(IntentSender.SendIntentException::class)
    fun launchPendingIntent(activity: Activity, requestCode: Int)
}
