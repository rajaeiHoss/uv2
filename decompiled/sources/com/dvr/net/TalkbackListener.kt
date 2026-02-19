package com.dvr.net

interface TalkbackListener {
    fun receiveTalkbackPCMData(bArr: ByteArray?, i: Int): Int
}
