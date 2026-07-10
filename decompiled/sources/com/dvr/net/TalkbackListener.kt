package com.dvr.net

interface TalkbackListener {
    fun receiveTalkbackPCMData(audioData: ByteArray?, length: Int): Int
}
