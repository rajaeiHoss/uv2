package com.google.android.gms.awareness.fence

import com.google.android.gms.common.api.Result

interface FenceQueryResult : Result {
    val fenceStateMap: FenceStateMap
}
