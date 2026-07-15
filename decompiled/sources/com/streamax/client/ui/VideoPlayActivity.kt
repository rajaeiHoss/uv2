package com.streamax.client.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.streamax.client.widget.PlayerView
import com.zycs.UView.R
import java.io.File

open class VideoPlayActivity : Activity(), LifecycleOwner, PlayerView.OnPlayListener {
    companion object {
        const val INTENT_KEY_PARAMETERS = "parameters"
    }

    class Landscape : VideoPlayActivity()
    class Portrait : VideoPlayActivity()

    private val mLifecycle = LifecycleRegistry(this)
    private lateinit var mBuilder: Builder
    private lateinit var mPlayerView: PlayerView

    override val lifecycle: Lifecycle
        get() = mLifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        setContentView(R.layout.video_play_activity)
        initView()
        mBuilder = savedInstanceState?.getParcelable(INTENT_KEY_PARAMETERS)
            ?: intent?.extras?.getParcelable(INTENT_KEY_PARAMETERS)
            ?: throw IllegalArgumentException("Missing video playback parameters")
        initData()
        ImmersionBar.with(this).hideBar(BarHide.FLAG_HIDE_BAR).init()
    }

    override fun onStart() {
        super.onStart()
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    override fun onResume() {
        super.onResume()
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun onPause() {
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        super.onPause()
    }

    override fun onStop() {
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        super.onStop()
    }

    override fun onDestroy() {
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        super.onDestroy()
    }

    override fun onClickLock(playerView: PlayerView) =
        PlayerView.OnPlayListener.CC.`$default$onClickLock`(this, playerView)

    override fun onClickPlay(playerView: PlayerView) =
        PlayerView.OnPlayListener.CC.`$default$onClickPlay`(this, playerView)

    private fun initView() {
        mPlayerView = findViewById(R.id.pv_video_play_view)
        mPlayerView.setLifecycleOwner(this)
        mPlayerView.setOnPlayListener(this)
    }

    private fun initData() {
        mPlayerView.setVideoTitle(mBuilder.videoTitle)
        mPlayerView.setVideoSource(mBuilder.videoSource)
        mPlayerView.setGestureEnabled(mBuilder.gestureEnabled)
        if (mBuilder.autoPlay) mPlayerView.start()
    }

    override fun onClickBack(playerView: PlayerView) {
        onBackPressed()
    }

    override fun onPlayStart(playerView: PlayerView) {
        if (mBuilder.playProgress > 0) mPlayerView.setProgress(mBuilder.playProgress)
    }

    override fun onPlayProgress(playerView: PlayerView) {
        mBuilder.setPlayProgress(playerView.getProgress())
    }

    override fun onPlayEnd(playerView: PlayerView) {
        if (mBuilder.loopPlay) {
            mPlayerView.setProgress(0)
            mPlayerView.start()
        } else if (mBuilder.autoOver) {
            finish()
        }
    }

    override fun onSaveInstanceState(state: Bundle) {
        super.onSaveInstanceState(state)
        if (::mBuilder.isInitialized) {
            state.putParcelable(INTENT_KEY_PARAMETERS, mBuilder)
        }
    }

    class Builder() : Parcelable {
        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<Builder> = object : Parcelable.Creator<Builder> {
                override fun createFromParcel(parcel: Parcel) = Builder(parcel)
                override fun newArray(size: Int): Array<Builder?> = arrayOfNulls(size)
            }
        }

        private var activityOrientation = -1
        internal var autoOver = true
        internal var autoPlay = true
        internal var gestureEnabled = true
        internal var loopPlay = false
        internal var playProgress = 0
        internal var videoSource: String? = null
        internal var videoTitle: String? = null

        private constructor(parcel: Parcel) : this() {
            videoSource = parcel.readString()
            videoTitle = parcel.readString()
            activityOrientation = parcel.readInt()
            playProgress = parcel.readInt()
            gestureEnabled = parcel.readByte().toInt() != 0
            loopPlay = parcel.readByte().toInt() != 0
            autoPlay = parcel.readByte().toInt() != 0
            autoOver = parcel.readByte().toInt() != 0
        }

        fun setVideoSource(file: File): Builder {
            videoSource = file.path
            if (videoTitle == null) videoTitle = file.name
            return this
        }

        fun setVideoSource(source: String?): Builder {
            videoSource = source
            return this
        }

        fun setVideoTitle(title: String?): Builder {
            videoTitle = title
            return this
        }

        fun setPlayProgress(progress: Int): Builder {
            playProgress = progress
            return this
        }

        fun setGestureEnabled(enabled: Boolean): Builder {
            gestureEnabled = enabled
            return this
        }

        fun setLoopPlay(enabled: Boolean): Builder {
            loopPlay = enabled
            return this
        }

        fun setAutoPlay(enabled: Boolean): Builder {
            autoPlay = enabled
            return this
        }

        fun isAutoPlay(): Boolean = autoPlay

        fun setAutoOver(enabled: Boolean): Builder {
            autoOver = enabled
            return this
        }

        fun setActivityOrientation(orientation: Int): Builder {
            activityOrientation = orientation
            return this
        }

        fun start(context: Context) {
            val target = when (activityOrientation) {
                0 -> Landscape::class.java
                1 -> Portrait::class.java
                else -> VideoPlayActivity::class.java
            }
            val intent = Intent(context, target).putExtra(INTENT_KEY_PARAMETERS, this)
            if (context !is Activity) intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

        override fun describeContents() = 0

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(videoSource)
            parcel.writeString(videoTitle)
            parcel.writeInt(activityOrientation)
            parcel.writeInt(playProgress)
            parcel.writeByte(if (gestureEnabled) 1.toByte() else 0.toByte())
            parcel.writeByte(if (loopPlay) 1.toByte() else 0.toByte())
            parcel.writeByte(if (autoPlay) 1.toByte() else 0.toByte())
            parcel.writeByte(if (autoOver) 1.toByte() else 0.toByte())
        }
    }
}
