package com.dvr.avstream

interface AudioTrackInterface {
    fun InputAudioData(channel: Int, audioData: ByteArray?, length: Int)
}
