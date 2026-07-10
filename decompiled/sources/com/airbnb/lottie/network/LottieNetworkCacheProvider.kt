package com.airbnb.lottie.network

import java.io.File

interface LottieNetworkCacheProvider {
    fun getCacheDir(): File
}
