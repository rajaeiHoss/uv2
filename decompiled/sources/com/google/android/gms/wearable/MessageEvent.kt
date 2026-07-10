package com.google.android.gms.wearable

interface MessageEvent {
    val data: ByteArray

    val path: String

    val requestId: Int

    val sourceNodeId: String
}
