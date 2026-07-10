package com.google.android.gms.internal

import android.os.IInterface
import android.os.RemoteException

interface zzme : IInterface {
    @Throws(RemoteException::class)
    fun getValue(): Long
}
