package com.streamax.client

interface FilePlaybackInterface {
    fun FilePlaybackCallback(channel: Int, frameData: ByteArray, dataLength: Int, width: Int, height: Int, playbackSecond: Int)
}
