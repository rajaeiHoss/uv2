package com.google.android.gms.vision.text

import android.graphics.Point
import android.graphics.Rect
import kotlin.jvm.JvmWildcard

interface Text {
    val boundingBox: Rect

    val components: List<@JvmWildcard Text>

    val cornerPoints: Array<Point>

    val value: String
}
