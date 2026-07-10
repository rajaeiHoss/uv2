package com.dvr.avstream

interface FilePlaybackInterface {
    fun FilePlaybackCallback(channel: Int, streamIndex: Int, frameData: ByteArray?, dataLength: Int, width: Int, height: Int, playbackState: Int, playbackSecond: Int)
}
