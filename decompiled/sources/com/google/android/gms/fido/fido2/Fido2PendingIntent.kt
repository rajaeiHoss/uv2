package com.google.android.gms.fido.fido2

import android.app.Activity
import android.content.IntentSender

interface Fido2PendingIntent {
    fun hasPendingIntent(): Boolean

    @Throws(IntentSender.SendIntentException::class)
    fun launchPendingIntent(activity: Activity, requestCode: Int)
}
