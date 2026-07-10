package com.dvr.net

import com.mdvr.BlackBox.BlackBoxFrame

interface IRegisterIOTCListener {
    fun receiveBlackBoxFrame(channel: Int, frames: Array<BlackBoxFrame?>?): Int

    fun receiveEventStatusInfo(ssrc: Int, progress: Int, errorCode: Int): Int

    fun receiveFrameData(channel: Int, width: Int, height: Int, frameTimestamp: Long, dataLength: Int, frameId: Long): Int

    fun receiveHearbeatInfo(state: Int): Int

    fun receiveParameter(operation: String?, parameterJson: String?): Int

    fun receivePlaybackInfo(operation: String?, errorCode: Int, errorCause: String?): Int

    fun receiveSessionInfo(ssrc: Int, errorCode: Int, errorCause: String?): Int

    fun receiveTransData(data: ByteArray?, length: Int): Int
}
