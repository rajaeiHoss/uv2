package com.google.android.gms.ads.mediation

import com.google.android.gms.ads.formats.NativeAdOptions

interface NativeMediationAdRequest : MediationAdRequest {
    val adVolume: Float

    val nativeAdOptions: NativeAdOptions

    val isAdMuted: Boolean

    val isAppInstallAdRequested: Boolean

    val isContentAdRequested: Boolean

    fun zznb(): Boolean

    fun zznc(): Boolean

    fun zznd(): Map<String, Boolean>
}
