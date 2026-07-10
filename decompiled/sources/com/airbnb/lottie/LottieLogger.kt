package com.airbnb.lottie

interface LottieLogger {
    fun debug(message: String)

    fun debug(message: String, throwable: Throwable?)

    fun error(message: String, throwable: Throwable?)

    fun warning(message: String)

    fun warning(message: String, throwable: Throwable?)
}
