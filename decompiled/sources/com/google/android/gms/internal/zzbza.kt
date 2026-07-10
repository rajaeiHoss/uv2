package com.google.android.gms.internal

import android.os.IInterface
import android.os.RemoteException
import com.google.android.gms.fitness.request.GoalsReadRequest

interface zzbza : IInterface {
    @Throws(RemoteException::class)
    fun zza(request: GoalsReadRequest)
}
