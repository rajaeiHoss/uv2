package com.google.android.gms.awareness.snapshot

import com.google.android.gms.awareness.state.TimeIntervals
import com.google.android.gms.common.api.Result

interface TimeIntervalsResult : Result {
    val timeIntervals: TimeIntervals
}
