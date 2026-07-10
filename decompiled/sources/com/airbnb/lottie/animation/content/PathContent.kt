package com.airbnb.lottie.animation.content

import android.graphics.Path

internal interface PathContent : Content {
    fun getPath(): Path
}
