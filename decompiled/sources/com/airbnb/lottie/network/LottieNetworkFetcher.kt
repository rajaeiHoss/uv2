package com.airbnb.lottie.network

import java.io.IOException

interface LottieNetworkFetcher {
    @Throws(IOException::class)
    fun fetchSync(url: String): LottieFetchResult
}
