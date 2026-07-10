package com.google.android.gms.awareness.fence

interface FenceStateMap {
    val fenceKeys: Set<String>

    fun getFenceState(fenceKey: String): FenceState
}
