package com.dvr.avstream

import android.media.AudioRecord
import android.util.Log

class AudioRecorder {
    @JvmField
    var mAudioRecorder: AudioRecord? = null

    private var mDataBuffer: ByteArray? = null

    @JvmField
    var mDataCallback: TalkbackDataCallback? = null

    private var mbExit = false

    fun Init() {
        var i: Int
        Log.v(TAG, "[Init]")
        if (mAudioRecorder == null) {
            i = AudioRecord.getMinBufferSize(8000, 16, 2)
            mAudioRecorder = AudioRecord(1, 8000, 16, 2, i * 4)
        } else {
            i = 0
        }
        mDataBuffer = ByteArray(i)
    }

    fun Record() {
        Log.v(TAG, "[Record]")
        if (mAudioRecorder == null) {
            Init()
        }
        val audioRecord = mAudioRecorder ?: return
        if (3 != audioRecord.recordingState) {
            audioRecord.startRecording()
            mbExit = false
            Thread {
                while (!mbExit) {
                    val recorder = mAudioRecorder ?: break
                    val buffer = mDataBuffer ?: break
                    val read = recorder.read(buffer, 0, BUFFER_SIZE)
                    if (read < 0 || read == -3 || read == -2) {
                        break
                    }
                    mDataCallback?.SendPCMData(buffer, read)
                }
            }.start()
        }
    }

    fun Stop() {
        val audioRecord = mAudioRecorder
        if (audioRecord != null) {
            mbExit = true
            if (3 == audioRecord.recordingState) {
                audioRecord.stop()
            }
        }
    }

    fun Release() {
        mAudioRecorder?.release()
    }

    fun SetDataCallback(talkbackDataCallback: TalkbackDataCallback?) {
        mDataCallback = talkbackDataCallback
    }

    companion object {
        private const val BUFFER_SIZE = 640
        private const val TAG = "AudioRecorder"
    }
}
