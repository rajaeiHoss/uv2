package com.google.android.gms.tagmanager

interface CustomVariableProvider {
    @JvmSuppressWildcards
    fun getValue(parameters: Map<String, Any?>): String?
}
