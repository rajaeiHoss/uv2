package com.google.android.gms.internal

import org.apache.http.HttpResponse
import java.io.IOException

@Deprecated(message = "Use the newer HTTP stack")
interface zzar {
    @Throws(IOException::class, zza::class)
    fun zzb(request: zzr<*>, headers: Map<String, String>): HttpResponse
}
