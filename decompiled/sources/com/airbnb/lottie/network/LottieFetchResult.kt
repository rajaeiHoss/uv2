package com.airbnb.lottie.network

import java.io.Closeable
import java.io.IOException
import java.io.InputStream

interface LottieFetchResult : Closeable {
    @Throws(IOException::class)
    fun bodyByteStream(): InputStream

    fun contentType(): String?

    fun error(): String?

    fun isSuccessful(): Boolean
}
