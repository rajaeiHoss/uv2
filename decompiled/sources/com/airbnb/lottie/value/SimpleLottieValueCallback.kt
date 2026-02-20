package com.airbnb.lottie.value

interface SimpleLottieValueCallback<T> {
    fun getValue(lottieFrameInfo: LottieFrameInfo<T>): T
}
