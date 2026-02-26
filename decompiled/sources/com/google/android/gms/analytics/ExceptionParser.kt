package com.google.android.gms.analytics

interface ExceptionParser {
    fun getDescription(str: String, th: Throwable): String
}
