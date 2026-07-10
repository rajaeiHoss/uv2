package com.google.android.gms.tagmanager

interface CustomTagProvider {
    @JvmSuppressWildcards
    fun execute(parameters: Map<String, Any?>)
}
