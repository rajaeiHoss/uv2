package com.google.android.gms.cast.framework

import android.content.Context

interface OptionsProvider {
    fun getAdditionalSessionProviders(context: Context): List<SessionProvider>

    fun getCastOptions(context: Context): CastOptions
}
