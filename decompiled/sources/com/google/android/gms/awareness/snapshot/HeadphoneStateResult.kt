package com.google.android.gms.awareness.snapshot

import com.google.android.gms.awareness.state.HeadphoneState
import com.google.android.gms.common.api.Result

interface HeadphoneStateResult : Result {
    val headphoneState: HeadphoneState
}
