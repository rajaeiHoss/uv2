package com.dvr.avstream

interface MyCallInterface {
    fun fuc(channel: Int, streamType: Int, frameData: ByteArray?, dataLength: Int, width: Int, height: Int)
}
