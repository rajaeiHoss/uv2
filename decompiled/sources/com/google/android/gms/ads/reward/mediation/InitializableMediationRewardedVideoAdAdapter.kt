package com.google.android.gms.ads.reward.mediation

import android.content.Context
import android.os.Bundle

interface InitializableMediationRewardedVideoAdAdapter : MediationRewardedVideoAdAdapter {
    fun initialize(
        context: Context,
        listener: MediationRewardedVideoAdListener,
        serverParameters: List<Bundle>,
    )
}
