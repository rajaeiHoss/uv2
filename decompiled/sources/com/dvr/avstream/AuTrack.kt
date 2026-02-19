package com.dvr.avstream

import android.media.AudioTrack
import android.util.Log

class AuTrack : AudioTrackInterface {
    @JvmField
    var mChannel: Int = 1

    @JvmField
    var mPlayer: PCMAudioTrack = PCMAudioTrack()

    private var mbMute = false

    inner class PCMAudioTrack {
        private val audioEncoding = 2
        private val channelConfig = 4
        private val frequence = 8000

        @JvmField
        var mAudioTrack: AudioTrack? = null

        fun play() {
            val audioTrack = mAudioTrack
            if (audioTrack != null) {
                Log.v(TAG, "[PCMAudioTrack][play]----start")
                if (audioTrack.playState != 3) {
                    Log.v(TAG, "[PCMAudioTrack][play]----end")
                    audioTrack.play()
                    audioTrack.setStereoVolume(1.0f, 1.0f)
                }
                return
            }
            val minBufferSize = AudioTrack.getMinBufferSize(8000, channelConfig, audioEncoding)
            if (minBufferSize != -2) {
                val created = AudioTrack(3, frequence, channelConfig, audioEncoding, minBufferSize * 2, 1)
                mAudioTrack = created
                created.setStereoVolume(1.0f, 1.0f)
                created.flush()
                created.play()
            }
        }

        fun Stop() {
            val audioTrack = mAudioTrack
            if (audioTrack != null) {
                Log.v(TAG, "[SwitchChannels][Stop]----check start")
                if (audioTrack.playState == 1) {
                    Log.v(TAG, "[SwitchChannels][Stop]----check end")
                    return
                }
                Log.v(TAG, "[SwitchChannels][Stop]----check end")
                audioTrack.stop()
                audioTrack.release()
                mAudioTrack = null
                Log.v(TAG, "AudioTrack stop success")
            }
        }

        fun Pause() {
            val audioTrack = mAudioTrack
            if (audioTrack != null) {
                Log.v(TAG, "[SwitchChannels][Pause]----check start")
                if (audioTrack.playState != 3) {
                    Log.v(TAG, "mAudioTrack.getPlayState() =${audioTrack.playState}")
                    return
                }
                Log.v(TAG, "[SwitchChannels][Pause]----check end")
                audioTrack.pause()
            }
        }
    }

    fun SetMute(z: Boolean) {
        Log.v(TAG, "[SetMute]bMute =$z")
        if (mPlayer.mAudioTrack == null) {
            Log.v(TAG, "mPlayer.mAudioTrack == null")
        } else if (z) {
            mPlayer.Pause()
        } else {
            mPlayer.play()
        }
        mbMute = z
    }

    fun SwitchChannels(i: Int) {
        if (mChannel != i) {
            mChannel = i
            val player = mPlayer
            if (player.mAudioTrack != null) {
                Log.v(TAG, "[SwitchChannels][$i]----check start")
                val audioTrack = player.mAudioTrack
                if (audioTrack == null || audioTrack.playState != 3) {
                    Log.v(TAG, "[SwitchChannels][$i]----check end")
                    return
                }
                Log.v(TAG, "[SwitchChannels][$i]----check end")
                audioTrack.pause()
                player.play()
            }
        }
    }

    override fun InputAudioData(i: Int, bArr: ByteArray?, i2: Int) {
        val player = mPlayer
        val audioTrack = player.mAudioTrack
        if (audioTrack != null && !mbMute && i == mChannel && audioTrack.playState == 3 && bArr != null) {
            audioTrack.write(bArr, 0, i2)
        }
    }

    companion object {
        private const val TAG = "AuTrack"
    }
}
