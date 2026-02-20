package com.google.android.material.slider

interface BaseOnChangeListener<S> {
    fun onValueChange(s: S, f: Float, z: Boolean)
}
