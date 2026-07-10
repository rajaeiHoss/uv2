package com.airbnb.lottie

import android.graphics.Bitmap

interface ImageAssetDelegate {
    fun fetchBitmap(asset: LottieImageAsset): Bitmap?
}
