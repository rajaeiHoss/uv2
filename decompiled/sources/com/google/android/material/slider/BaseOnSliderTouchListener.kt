package com.google.android.material.slider

interface BaseOnSliderTouchListener<S> {
    fun onStartTrackingTouch(slider: S)

    fun onStopTrackingTouch(slider: S)
}
