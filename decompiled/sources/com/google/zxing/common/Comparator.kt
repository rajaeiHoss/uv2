package com.google.zxing.common

interface Comparator {
    fun compare(obj: Any?, obj2: Any?): Int
}
