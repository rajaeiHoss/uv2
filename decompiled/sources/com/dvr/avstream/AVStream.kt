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

    external fun AVCaptureImage(nativeHandle: Long, outputPath: ByteArray?): Int
    external fun AVCloseDecoder(nativeHandle: Long): Int
    external fun AVCloseFileDecoder(nativeHandle: Long): Int
    external fun AVFilePause(nativeHandle: Long, paused: Int): Int
    external fun AVFileSeekPos(nativeHandle: Long, position: Int): Int
    external fun AVFileSetMute(nativeHandle: Long, muted: Int): Int
    external fun AVFileSetSpeed(nativeHandle: Long, speed: Int): Int
    external fun AVInputFrame(nativeHandle: Long, frameTimestamp: Long, dataLength: Int, width: Int, height: Int, frameId: Long): Int
    external fun AVInputStream(nativeHandle: Long, frameTimestamp: Long, dataLength: Int): Int
    external fun AVMultiplayCloseDecoder(nativeHandle: Long): Int
    external fun AVMultiplayDecoder(): Long
    external fun AVMultiplayInputStream(nativeHandle: Long, frameType: Int, width: Int, height: Int, frameTimestamp: Long): Int
    external fun AVMultiplayStartPlay(nativeHandle: Long): Int
    external fun AVMultiplayStopPlay(nativeHandle: Long): Int
    external fun AVOpenDecoder(): Long
    external fun AVOpenFileDecoder(filePath: ByteArray?): Long
    external fun AVSetMute(nativeHandle: Long, muted: Int): Int
    external fun AVSetPauseDecoder(nativeHandle: Long, paused: Int): Int
    external fun AVStartFilePlay(nativeHandle: Long): Int
    external fun AVStartPlay(nativeHandle: Long): Int
    external fun AVStartRecord(nativeHandle: Long, filePath: ByteArray?): Int
    external fun AVStopFilePlay(nativeHandle: Long): Int
    external fun AVStopPlay(nativeHandle: Long): Int
    external fun AVStopRecord(nativeHandle: Long): Int

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
        val decoderHandle = handle
        if (decoderHandle == 0L) {
            return 0
        }
        AVCloseDecoder(decoderHandle)
        handle = 0
        return 0
    }

    fun StartPlay(): Int {
        Log.v(TAG, "StartPlay")
        val decoderHandle = handle
        if (decoderHandle == 0L) {
            return 0
        }
        return AVStartPlay(decoderHandle)
    }

    fun SetMute(muted: Boolean): Int {
        val decoderHandle = handle
        if (decoderHandle == 0L) {
            return 0
        }
        return AVSetMute(decoderHandle, if (muted) 1 else 0)
    }

    fun SetStreamDecodeState(paused: Boolean): Int {
        val decoderHandle = handle
        if (decoderHandle == 0L) {
            return 0
        }
        return AVSetPauseDecoder(decoderHandle, if (paused) 1 else 0)
    }

    fun SetFileSetMute(channel: Int, muted: Boolean): Int {
        val decoderHandle = handle
        if (decoderHandle == 0L) {
            return 0
        }
        return AVFileSetMute(decoderHandle, if (muted) 1 else 0)
    }

    fun Capture(filePath: String): Int {
        val decoderHandle = handle
        if (decoderHandle == 0L) {
            return 0
        }
        return AVCaptureImage(decoderHandle, File(filePath).absolutePath.toByteArray())
    }

    fun InputFrame(frameTimestamp: Long, dataLength: Int, width: Int, height: Int, frameId: Long): Int {
        val decoderHandle = handle
        if (decoderHandle == 0L) {
            return 0
        }
        return AVInputFrame(decoderHandle, frameTimestamp, dataLength, width, height, frameId)
    }

    fun StartRecord(filePath: String): Int {
        val decoderHandle = handle
        if (decoderHandle == 0L) {
            return 0
        }
        return AVStartRecord(decoderHandle, filePath.toByteArray())
    }

    fun StopRecord(): Int {
        val decoderHandle = handle
        if (decoderHandle == 0L) {
            return 0
        }
        return AVStopRecord(decoderHandle)
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
        val multiplayHandle = hMultiplayHandle
        if (multiplayHandle == 0L) {
            return 0
        }
        return AVMultiplayCloseDecoder(multiplayHandle)
    }

    fun MultiplayInputStream(frameType: Int, width: Int, height: Int, frameTimestamp: Long): Int {
        val multiplayHandle = hMultiplayHandle
        if (multiplayHandle == 0L) {
            return 0
        }
        return AVMultiplayInputStream(multiplayHandle, frameType, width, height, frameTimestamp)
    }

    fun StartMultiplay(): Int {
        val multiplayHandle = hMultiplayHandle
        if (multiplayHandle == 0L) {
            return 0
        }
        return AVMultiplayStartPlay(multiplayHandle)
    }

    fun StopMultiplay(): Int {
        val multiplayHandle = hMultiplayHandle
        if (multiplayHandle == 0L) {
            return 0
        }
        return AVMultiplayStopPlay(multiplayHandle)
    }

    companion object {
        private const val TAG = "AVStream"

        init {
            System.loadLibrary("ijkffmpeg")
            System.loadLibrary("ijkstream")
        }
    }
}
