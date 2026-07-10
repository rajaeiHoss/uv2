package com.google.android.gms.awareness.state

interface TimeIntervals {
    val timeIntervals: IntArray

    fun hasTimeInterval(timeInterval: Int): Boolean
}
