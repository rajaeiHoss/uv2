package com.google.android.gms.common.data

interface Freezable<T> {
    fun freeze(): T

    val isDataValid: Boolean
}
