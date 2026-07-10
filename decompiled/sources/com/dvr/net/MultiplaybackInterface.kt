package com.dvr.net

interface MultiplaybackInterface {
    fun MultiplayCallback(nativeHandle: Long, channel: Int, codecType: Int, frameType: Int, frameData: ByteArray?, width: Int, height: Int, playbackSecond: Int)
}
