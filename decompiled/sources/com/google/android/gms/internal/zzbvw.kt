package com.google.android.gms.internal

import android.app.PendingIntent
import android.os.IInterface
import android.os.RemoteException
import com.google.android.gms.common.api.Status

interface zzbvw : IInterface {
    @Throws(RemoteException::class)
    fun zza(status: Status, pendingIntent: PendingIntent)
}
