package com.google.android.material.snackbar

interface ContentViewCallback {
    fun animateContentIn(delay: Int, duration: Int)

    fun animateContentOut(delay: Int, duration: Int)
}
