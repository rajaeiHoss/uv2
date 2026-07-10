package com.google.android.gms.ads.mediation.customevent

import android.content.Context
import android.os.Bundle
import com.google.android.gms.ads.mediation.NativeMediationAdRequest

interface CustomEventNative : CustomEvent {
    fun requestNativeAd(
        context: Context,
        listener: CustomEventNativeListener,
        serverParameter: String,
        mediationAdRequest: NativeMediationAdRequest,
        customEventExtras: Bundle,
    )
}
