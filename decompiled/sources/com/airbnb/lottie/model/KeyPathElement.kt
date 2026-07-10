package com.airbnb.lottie.model

import com.airbnb.lottie.value.LottieValueCallback

interface KeyPathElement {
    fun <T> addValueCallback(property: T, callback: LottieValueCallback<T>?)

    fun resolveKeyPath(
        keyPath: KeyPath,
        depth: Int,
        accumulator: MutableList<KeyPath>,
        currentPartialKeyPath: KeyPath,
    )
}
