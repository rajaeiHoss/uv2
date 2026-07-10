package com.google.android.gms.ads.mediation

import android.content.Context
import android.os.Bundle

interface MediationNativeAdapter : MediationAdapter {
    fun requestNativeAd(
        context: Context,
        mediationNativeListener: MediationNativeListener,
        serverParameters: Bundle,
        mediationAdRequest: NativeMediationAdRequest,
        mediationExtras: Bundle,
    )
}
