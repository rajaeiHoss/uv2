package com.airbnb.lottie

interface LottieListener<T> {
    fun onResult(value: T)
}
