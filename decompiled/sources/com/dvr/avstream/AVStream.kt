package com.dvr.avstream

import android.util.Log
import com.dvr.net.MultiplaybackInterface
import java.io.File

class AVStream {
    private val FRAMETYPE_A = 4
    private val FRAMETYPE_I = 1
    private val FRAMETYPE_NONE = 0
    private val FRAMETYPE_P = 2
    private val FRAMETYPE_VIDEO = 3

    @JvmField
    var ac: AudioTrackInterface? = null

    @JvmField
    var fc: FilePlaybackInterface? = null

    private var hMultiplayHandle: Long = 0
    private var handle: Long = 0

    @JvmField
    var mAudio: ByteArray? = null

    private var mChannel: Int = 0

    @JvmField
    var mInputBufferDataLen: Int = 0

    @JvmField
    var mInputBufferSize: Int = 0

    @JvmField
    var mOutputBufferDataLen: Int = 0

    @JvmField
    var mOutputBufferSize: Int = 0

    @JvmField
    var mPixels: ByteArray? = null

    private var mbRecord = false

    @JvmField
    var mc: MultiplaybackInterface? = null

    @JvmField
    var rc: MyCallInterface? = null

    external fun AVCaptureImage(j: Long, bArr: ByteArray?): Int
    external fun AVCloseDecoder(j: Long): Int
    external fun AVCloseFileDecoder(j: Long): Int
    external fun AVFilePause(j: Long, i: Int): Int
    external fun AVFileSeekPos(j: Long, i: Int): Int
    external fun AVFileSetMute(j: Long, i: Int): Int
    external fun AVFileSetSpeed(j: Long, i: Int): Int
    external fun AVInputFrame(j: Long, j2: Long, i: Int, i2: Int, i3: Int, j3: Long): Int
    external fun AVInputStream(j: Long, j2: Long, i: Int): Int
    external fun AVMultiplayCloseDecoder(j: Long): Int
    external fun AVMultiplayDecoder(): Long
    external fun AVMultiplayInputStream(j: Long, i: Int, i2: Int, i3: Int, j2: Long): Int
    external fun AVMultiplayStartPlay(j: Long): Int
    external fun AVMultiplayStopPlay(j: Long): Int
    external fun AVOpenDecoder(): Long
    external fun AVOpenFileDecoder(bArr: ByteArray?): Long
    external fun AVSetMute(j: Long, i: Int): Int
    external fun AVSetPauseDecoder(j: Long, i: Int): Int
    external fun AVStartFilePlay(j: Long): Int
    external fun AVStartPlay(j: Long): Int
    external fun AVStartRecord(j: Long, bArr: ByteArray?): Int
    external fun AVStopFilePlay(j: Long): Int
    external fun AVStopPlay(j: Long): Int
    external fun AVStopRecord(j: Long): Int

    fun DisplayOneFrame(nativeHandle: Long, frameType: Int, frameData: ByteArray?, dataLength: Int, width: Int, height: Int) {
        synchronized(this) {
            if (frameType == 1 || frameType == 2) {
                rc?.fuc(mChannel, 0, frameData, dataLength, width, height)
            } else if (frameType == 4) {
                ac?.InputAudioData(mChannel, frameData, dataLength)
            } else {
                // No-op.
            }
        }
    }

    fun FileDisplayOneFrame(nativeHandle: Long, streamIndex: Int, frameType: Int, timestamp: Long, dataLength: Int, width: Int, height: Int, playbackState: Int, playbackSecond: Int) {
        if (frameType == 1 || frameType == 2 || frameType == 0) {
            fc?.FilePlaybackCallback(mChannel, streamIndex, mPixels, dataLength, width, height, playbackState, playbackSecond)
        } else if (frameType == 4) {
            ac?.InputAudioData(mChannel, mAudio, dataLength)
        }
    }

    fun GetHandle(channel: Int): Long {
        val openDecoder = AVOpenDecoder()
        handle = openDecoder
        mChannel = channel
        return openDecoder
    }

    fun CloseHandle(): Int {
        Log.v(TAG, "CloseHandle")
        val j = handle
        if (j == 0L) {
            return 0
        }
        AVCloseDecoder(j)
        handle = 0
        return 0
    }

    fun StartPlay(): Int {
        Log.v(TAG, "StartPlay")
        val j = handle
        if (j == 0L) {
            return 0
        }
        return AVStartPlay(j)
    }

    fun SetMute(muted: Boolean): Int {
        val j = handle
        if (j == 0L) {
            return 0
        }
        return AVSetMute(j, if (muted) 1 else 0)
    }

    fun SetStreamDecodeState(paused: Boolean): Int {
        val j = handle
        if (j == 0L) {
            return 0
        }
        return AVSetPauseDecoder(j, if (paused) 1 else 0)
    }

    fun SetFileSetMute(channel: Int, muted: Boolean): Int {
        val j = handle
        if (j == 0L) {
            return 0
        }
        return AVFileSetMute(j, if (muted) 1 else 0)
    }

    fun Capture(filePath: String): Int {
        val j = handle
        if (j == 0L) {
            return 0
        }
        return AVCaptureImage(j, File(filePath).absolutePath.toByteArray())
    }

    fun InputFrame(frameTimestamp: Long, dataLength: Int, width: Int, height: Int, frameId: Long): Int {
        val decoderHandle = handle
        if (decoderHandle == 0L) {
            return 0
        }
        return AVInputFrame(decoderHandle, frameTimestamp, dataLength, width, height, frameId)
    }

    fun StartRecord(filePath: String): Int {
        val j = handle
        if (j == 0L) {
            return 0
        }
        return AVStartRecord(j, filePath.toByteArray())
    }

    fun StopRecord(): Int {
        val j = handle
        if (j == 0L) {
            return 0
        }
        return AVStopRecord(j)
    }

    fun StopPlay(): Int {
        if (handle == 0L) {
            return 0
        }
        StopRecord()
        return AVStopPlay(handle)
    }

    fun SetVideoInterface(myCallInterface: MyCallInterface?) {
        synchronized(this) {
            rc = myCallInterface
        }
    }

    fun SetAudioInterface(audioTrackInterface: AudioTrackInterface?) {
        synchronized(this) {
            ac = audioTrackInterface
        }
    }

    fun SetFilePlaybackInterface(filePlaybackInterface: FilePlaybackInterface?) {
        synchronized(this) {
            fc = filePlaybackInterface
        }
    }

    fun SetMultiplayInterface(multiplaybackInterface: MultiplaybackInterface?) {
        synchronized(this) {
            mc = multiplaybackInterface
        }
    }

    fun GetMultiplayHandle(channel: Int): Long {
        val multiplayDecoder = AVMultiplayDecoder()
        hMultiplayHandle = multiplayDecoder
        mChannel = channel
        return multiplayDecoder
    }

    fun CloseMultiplayHandle(): Int {
        val j = hMultiplayHandle
        if (j == 0L) {
            return 0
        }
        return AVMultiplayCloseDecoder(j)
    }

    fun MultiplayInputStream(frameType: Int, width: Int, height: Int, frameTimestamp: Long): Int {
        val multiplayHandle = hMultiplayHandle
        if (multiplayHandle == 0L) {
            return 0
        }
        return AVMultiplayInputStream(multiplayHandle, frameType, width, height, frameTimestamp)
    }

    fun StartMultiplay(): Int {
        val j = hMultiplayHandle
        if (j == 0L) {
            return 0
        }
        return AVMultiplayStartPlay(j)
    }

    fun StopMultiplay(): Int {
        val j = hMultiplayHandle
        if (j == 0L) {
            return 0
        }
        return AVMultiplayStopPlay(j)
    }

    companion object {
        private const val TAG = "AVStream"

        init {
            System.loadLibrary("ijkffmpeg")
            System.loadLibrary("ijkstream")
        }
    }
}
