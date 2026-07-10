package com.dvr.avstream

interface TalkbackDataCallback {
    fun SendPCMData(audioData: ByteArray?, length: Int)
}
