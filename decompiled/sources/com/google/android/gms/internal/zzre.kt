package com.google.android.gms.internal

import android.os.IBinder
import android.os.IInterface
import android.os.RemoteException
import com.google.android.gms.dynamic.IObjectWrapper

interface zzre : IInterface {
    @Throws(RemoteException::class)
    fun zza(a: IObjectWrapper, b: IObjectWrapper, c: IObjectWrapper): IBinder
}
