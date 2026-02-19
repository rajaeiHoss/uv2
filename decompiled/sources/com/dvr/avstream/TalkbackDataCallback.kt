package com.dvr.avstream

interface TalkbackDataCallback {
    fun SendPCMData(bArr: ByteArray?, i: Int)
}
