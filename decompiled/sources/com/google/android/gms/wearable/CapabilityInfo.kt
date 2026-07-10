package com.google.android.gms.wearable

interface CapabilityInfo {
    val name: String

    val nodes: Set<Node>
}
