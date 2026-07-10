package com.google.android.gms.awareness.snapshot

import com.google.android.gms.awareness.state.BeaconState
import com.google.android.gms.common.api.Result

interface BeaconStateResult : Result {
    val beaconState: BeaconState
}
