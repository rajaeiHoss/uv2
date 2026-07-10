package com.google.android.material.transition

import android.animation.Animator
import android.view.View
import android.view.ViewGroup

interface VisibilityAnimatorProvider {
    fun createAppear(sceneRoot: ViewGroup, view: View): Animator?

    fun createDisappear(sceneRoot: ViewGroup, view: View): Animator?
}
