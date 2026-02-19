package com.dvr.net

import com.mdvr.BlackBox.BlackBoxFrame

interface IRegisterIOTCListener {
    fun receiveBlackBoxFrame(i: Int, blackBoxFrameArr: Array<BlackBoxFrame?>?): Int

    fun receiveEventStatusInfo(i: Int, i2: Int, i3: Int): Int

    fun receiveFrameData(i: Int, i2: Int, i3: Int, j: Long, i4: Int, j2: Long): Int

    fun receiveHearbeatInfo(i: Int): Int

    fun receiveParameter(str: String?, str2: String?): Int

    fun receivePlaybackInfo(str: String?, i: Int, str2: String?): Int

    fun receiveSessionInfo(i: Int, i2: Int, str: String?): Int

    fun receiveTransData(bArr: ByteArray?, i: Int): Int
}
