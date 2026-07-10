package com.google.android.material.animation

import android.view.View

interface TransformationCallback<T : View> {
    fun onScaleChanged(view: T)

    fun onTranslationChanged(view: T)
}
