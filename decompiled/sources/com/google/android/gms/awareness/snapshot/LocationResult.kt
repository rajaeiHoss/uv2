package com.google.android.gms.awareness.snapshot

import android.location.Location
import com.google.android.gms.common.api.Result

interface LocationResult : Result {
    val location: Location
}
