package com.streamax.client

interface AudioTrackInterface {
    fun InputAudioData(channel: Int, audioData: ByteArray, length: Int)
}
