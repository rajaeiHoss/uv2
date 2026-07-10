package com.google.android.gms.wallet

import android.content.Intent

interface AutoResolvableResult {
    fun putIntoIntent(intent: Intent)
}
