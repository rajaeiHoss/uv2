package com.google.android.gms.drive.query

import com.google.android.gms.drive.query.internal.zzj
import com.google.android.gms.internal.zzbgp

interface Filter : zzbgp {
    fun <T> zza(visitor: zzj<T>): T
}
