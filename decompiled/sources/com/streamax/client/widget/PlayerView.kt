package com.streamax.client.widget

import android.animation.ValueAnimator
import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.VideoView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.airbnb.lottie.LottieAnimationView
import com.hjq.base.BaseDialog
import com.hjq.base.action.ActivityAction
import com.hjq.widget.layout.SimpleLayout
import com.hjq.widget.view.PlayButton
import com.streamax.client.ui.dialog.MessageDialog
import com.zycs.UView.R
import java.io.File
import java.util.Formatter
import java.util.Locale
import kotlin.math.abs
import kotlin.math.roundToInt

// Full-screen video player widget with controls and lifecycle hooks.
class PlayerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : SimpleLayout(context, attrs, defStyleAttr, defStyleRes), LifecycleEventObserver,
    SeekBar.OnSeekBarChangeListener, View.OnClickListener, ActivityAction,
    MediaPlayer.OnPreparedListener, MediaPlayer.OnInfoListener,
    MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {

    interface OnPlayListener {
        fun onClickBack(playerView: PlayerView)
        fun onClickLock(playerView: PlayerView)
        fun onClickPlay(playerView: PlayerView)
        fun onPlayEnd(playerView: PlayerView)
        fun onPlayProgress(playerView: PlayerView)
        fun onPlayStart(playerView: PlayerView)

        object CC {
            @JvmStatic fun `$default$onClickBack`(listener: OnPlayListener, playerView: PlayerView) {}
            @JvmStatic fun `$default$onClickLock`(listener: OnPlayListener, playerView: PlayerView) {}
            @JvmStatic fun `$default$onClickPlay`(listener: OnPlayListener, playerView: PlayerView) {}
            @JvmStatic fun `$default$onPlayEnd`(listener: OnPlayListener, playerView: PlayerView) {}
            @JvmStatic fun `$default$onPlayProgress`(listener: OnPlayListener, playerView: PlayerView) {}
            @JvmStatic fun `$default$onPlayStart`(listener: OnPlayListener, playerView: PlayerView) {}
        }
    }

    private var mAdjustSecond = 0
    private val mAudioManager: AudioManager? = ContextCompat.getSystemService(context, AudioManager::class.java)
    private lateinit var mBottomLayout: ViewGroup
    private lateinit var mControlView: PlayButton
    private var mControllerShow = false
    private var mCurrentBrightness = 0f
    private var mCurrentProgress = 0
    private var mCurrentVolume = 0
    private var mGestureEnabled = false
    private lateinit var mLeftView: View
    private var mListener: OnPlayListener? = null
    private var mLockMode = false
    private lateinit var mLockView: ImageView
    private lateinit var mLottieView: LottieAnimationView
    private var mMaxVoice = 0
    private lateinit var mMessageLayout: ViewGroup
    private lateinit var mMessageView: TextView
    private lateinit var mPlayTime: TextView
    private lateinit var mProgressView: SeekBar
    private lateinit var mTitleView: TextView
    private lateinit var mTopLayout: ViewGroup
    private lateinit var mTotalTime: TextView
    private var mTouchOrientation = -1
    private var mVideoHeight = 0
    private lateinit var mVideoView: VideoView
    private var mVideoWidth = 0
    private var mViewDownX = 0f
    private var mViewDownY = 0f
    private var mWindow: android.view.Window? = null

    private val mRefreshRunnable = object : Runnable {
        override fun run() {
            var position = mVideoView.currentPosition
            if (position + REFRESH_TIME < mVideoView.duration) position = (position / 1000f).roundToInt() * 1000
            mPlayTime.text = conversionTime(position)
            mProgressView.progress = position
            mProgressView.secondaryProgress = (mVideoView.bufferPercentage / 100f * mVideoView.duration).toInt()
            if (mVideoView.isPlaying) {
                if (!mLockMode && mBottomLayout.visibility == View.GONE) mBottomLayout.visibility = View.VISIBLE
            } else if (mBottomLayout.visibility == View.VISIBLE) {
                mBottomLayout.visibility = View.GONE
            }
            postDelayed(this, REFRESH_TIME.toLong())
            mListener?.onPlayProgress(this@PlayerView)
        }
    }
    private val mShowControllerRunnable = Runnable { if (!mControllerShow) showController() }
    private val mHideControllerRunnable = Runnable { if (mControllerShow) hideController() }
    private val mShowMessageRunnable = Runnable { hideController(); mMessageLayout.visibility = View.VISIBLE }
    private val mHideMessageRunnable = Runnable { mMessageLayout.visibility = View.GONE }

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_player_view, this, true)
        mTopLayout = findViewById(R.id.ll_player_view_top)
        mLeftView = findViewById(R.id.iv_player_view_left)
        mTitleView = findViewById(R.id.tv_player_view_title)
        mBottomLayout = findViewById(R.id.ll_player_view_bottom)
        mPlayTime = findViewById(R.id.tv_player_view_play_time)
        mTotalTime = findViewById(R.id.tv_player_view_total_time)
        mProgressView = findViewById(R.id.sb_player_view_progress)
        mVideoView = findViewById(R.id.vv_player_view_video)
        mLockView = findViewById(R.id.iv_player_view_lock)
        mControlView = findViewById(R.id.iv_player_view_control)
        mMessageLayout = findViewById(R.id.cv_player_view_message)
        mLottieView = findViewById(R.id.lav_player_view_lottie)
        mMessageView = findViewById(R.id.tv_player_view_message)
        mLeftView.setOnClickListener(this)
        mControlView.setOnClickListener(this)
        mLockView.setOnClickListener(this)
        setOnClickListener(this)
        mProgressView.setOnSeekBarChangeListener(this)
        mVideoView.setOnPreparedListener(this)
        mVideoView.setOnCompletionListener(this)
        mVideoView.setOnInfoListener(this)
        mVideoView.setOnErrorListener(this)
    }

    fun setLifecycleOwner(owner: LifecycleOwner) = owner.lifecycle.addObserver(this)

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_RESUME -> onResume()
            Lifecycle.Event.ON_PAUSE -> onPause()
            Lifecycle.Event.ON_DESTROY -> onDestroy()
            else -> {}
        }
    }

    fun setVideoTitle(title: CharSequence?) {
        if (!title.isNullOrEmpty()) mTitleView.text = title
    }

    fun setVideoSource(file: File?) {
        if (file?.isFile == true) mVideoView.setVideoPath(file.path)
    }

    fun setVideoSource(source: String?) {
        if (!source.isNullOrEmpty()) mVideoView.setVideoURI(Uri.parse(source))
    }

    fun start() {
        mVideoView.start()
        mControlView.play()
        scheduleControllerHide()
    }

    fun pause() {
        mVideoView.pause()
        mControlView.pause()
        scheduleControllerHide()
    }

    fun lock() {
        mLockMode = true
        mLockView.setImageResource(R.drawable.video_lock_close_ic)
        mTopLayout.visibility = View.GONE
        mBottomLayout.visibility = View.GONE
        mControlView.visibility = View.GONE
        scheduleControllerHide()
    }

    fun unlock() {
        mLockMode = false
        mLockView.setImageResource(R.drawable.video_lock_open_ic)
        mTopLayout.visibility = View.VISIBLE
        if (mVideoView.isPlaying) mBottomLayout.visibility = View.VISIBLE
        mControlView.visibility = View.VISIBLE
        scheduleControllerHide()
    }

    fun isPlaying(): Boolean = mVideoView.isPlaying

    fun setProgress(requestedProgress: Int) {
        val progress = requestedProgress.coerceAtMost(mVideoView.duration)
        if (abs(progress - mVideoView.currentPosition) > 1000) {
            mVideoView.seekTo(progress)
            mProgressView.progress = progress
        }
    }

    fun getProgress(): Int = mVideoView.currentPosition
    fun getDuration(): Int = mVideoView.duration
    fun setGestureEnabled(enabled: Boolean) { mGestureEnabled = enabled }

    fun setOnPlayListener(listener: OnPlayListener?) {
        mListener = listener
        mLeftView.visibility = if (listener != null) View.VISIBLE else View.INVISIBLE
    }

    fun showController() {
        if (mControllerShow) return
        mControllerShow = true
        animateInt(-mTopLayout.height, 0) { value ->
            mTopLayout.translationY = value.toFloat()
            if (value == -mTopLayout.height && mTopLayout.visibility == View.INVISIBLE) mTopLayout.visibility = View.VISIBLE
        }
        animateInt(mBottomLayout.height, 0) { value ->
            mBottomLayout.translationY = value.toFloat()
            if (value == mBottomLayout.height && mBottomLayout.visibility == View.INVISIBLE) mBottomLayout.visibility = View.VISIBLE
        }
        animateFloat(0f, 1f) { value ->
            mLockView.alpha = value
            mControlView.alpha = value
            if (value == 0f && mControlView.visibility == View.INVISIBLE) mControlView.visibility = View.VISIBLE
        }
    }

    fun hideController() {
        if (!mControllerShow) return
        mControllerShow = false
        animateInt(0, -mTopLayout.height) { value ->
            mTopLayout.translationY = value.toFloat()
            if (value == -mTopLayout.height && mTopLayout.visibility == View.VISIBLE) mTopLayout.visibility = View.INVISIBLE
        }
        animateInt(0, mBottomLayout.height) { value ->
            mBottomLayout.translationY = value.toFloat()
            if (value == mBottomLayout.height && mBottomLayout.visibility == View.VISIBLE) mBottomLayout.visibility = View.INVISIBLE
        }
        animateFloat(1f, 0f) { value ->
            mLockView.alpha = value
            mControlView.alpha = value
            if (value == 0f && mControlView.visibility == View.VISIBLE) mControlView.visibility = View.INVISIBLE
        }
    }

    private fun animateInt(from: Int, to: Int, update: (Int) -> kotlin.Unit) {
        ValueAnimator.ofInt(from, to).apply {
            duration = ANIM_TIME.toLong()
            addUpdateListener { update(it.animatedValue as Int) }
            start()
        }
    }

    private fun animateFloat(from: Float, to: Float, update: (Float) -> kotlin.Unit) {
        ValueAnimator.ofFloat(from, to).apply {
            duration = ANIM_TIME.toLong()
            addUpdateListener { update(it.animatedValue as Float) }
            start()
        }
    }

    private fun scheduleControllerHide() {
        removeCallbacks(mHideControllerRunnable)
        postDelayed(mHideControllerRunnable, CONTROLLER_TIME.toLong())
    }

    fun onResume() = mVideoView.resume()

    fun onPause() {
        mVideoView.suspend()
        pause()
    }

    fun onDestroy() {
        mVideoView.stopPlayback()
        removeCallbacks(mRefreshRunnable)
        removeCallbacks(mShowControllerRunnable)
        removeCallbacks(mHideControllerRunnable)
        removeCallbacks(mShowMessageRunnable)
        removeCallbacks(mHideMessageRunnable)
        removeAllViews()
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        if (visibility == View.VISIBLE) {
            mVideoView.seekTo(mCurrentProgress)
            mProgressView.progress = mCurrentProgress
        }
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        if (fromUser) mPlayTime.text = conversionTime(progress)
        else if (progress != 0 || mVideoView.duration > 0) mCurrentProgress = progress
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {
        removeCallbacks(mRefreshRunnable)
        removeCallbacks(mHideControllerRunnable)
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        postDelayed(mRefreshRunnable, REFRESH_TIME.toLong())
        postDelayed(mHideControllerRunnable, CONTROLLER_TIME.toLong())
        setProgress(seekBar.progress)
    }

    override fun onPrepared(player: MediaPlayer) {
        mPlayTime.text = conversionTime(0)
        mTotalTime.text = conversionTime(player.duration)
        mProgressView.max = mVideoView.duration
        mVideoWidth = player.videoWidth
        mVideoHeight = player.videoHeight
        var targetWidth = width
        var targetHeight = height
        if (mVideoWidth * targetHeight < targetWidth * mVideoHeight) targetWidth = mVideoWidth * targetHeight / mVideoHeight
        else if (mVideoWidth * targetHeight > targetWidth * mVideoHeight) targetHeight = mVideoHeight * targetWidth / mVideoWidth
        mVideoView.layoutParams = mVideoView.layoutParams.apply { width = targetWidth; height = targetHeight }
        post(mShowControllerRunnable)
        postDelayed(mRefreshRunnable, DIALOG_TIME.toLong())
        mListener?.onPlayStart(this)
    }

    override fun onCompletion(player: MediaPlayer) {
        pause()
        mListener?.onPlayEnd(this)
    }

    override fun onInfo(player: MediaPlayer, what: Int, extra: Int): Boolean = when (what) {
        MediaPlayer.MEDIA_INFO_BUFFERING_START -> {
            mLottieView.setAnimation(R.raw.progress)
            mLottieView.playAnimation()
            mMessageView.setText(R.string.common_loading)
            post(mShowMessageRunnable)
            true
        }
        MediaPlayer.MEDIA_INFO_BUFFERING_END -> {
            mLottieView.cancelAnimation()
            mMessageView.setText(R.string.common_loading)
            postDelayed(mHideMessageRunnable, DIALOG_TIME.toLong())
            true
        }
        else -> false
    }

    override fun onError(player: MediaPlayer, what: Int, extra: Int): Boolean {
        val activity = getActivity() ?: return false
        val error = activity.getString(if (what == MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK) R.string.common_video_error_not_support else R.string.common_video_error_unknown)
        MessageDialog.Builder(activity)
            .setMessage(error + "\n" + String.format(activity.getString(R.string.common_video_error_supplement), what, extra))
            .setConfirm(R.string.common_confirm)
            .setCancel(null)
            .setCancelable(false)
            .setListener(object : MessageDialog.OnListener {
                override fun onCancel(baseDialog: BaseDialog) {}
                override fun onConfirm(baseDialog: BaseDialog) { onCompletion(player) }
            }).show()
        return true
    }

    override fun onClick(view: View) {
        when (view) {
            this -> {
                removeCallbacks(mShowControllerRunnable)
                removeCallbacks(mHideControllerRunnable)
                if (mControllerShow) post(mHideControllerRunnable)
                else { post(mShowControllerRunnable); postDelayed(mHideControllerRunnable, CONTROLLER_TIME.toLong()) }
            }
            mLeftView -> mListener?.onClickBack(this)
            mControlView -> if (mControlView.visibility == View.VISIBLE) {
                if (isPlaying()) pause() else start()
                removeCallbacks(mShowControllerRunnable)
                removeCallbacks(mHideControllerRunnable)
                if (!mControllerShow) post(mShowControllerRunnable)
                postDelayed(mHideControllerRunnable, CONTROLLER_TIME.toLong())
                mListener?.onClickPlay(this)
            }
            mLockView -> {
                if (mLockMode) unlock() else lock()
                mListener?.onClickLock(this)
            }
        }
    }

    fun getVideoWidth(): Int = mVideoWidth
    fun getVideoHeight(): Int = mVideoHeight

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return super.onTouchEvent(event)
    }

    companion object {
        private const val ANIM_TIME = 500
        private const val CONTROLLER_TIME = 3000
        private const val DIALOG_TIME = 500
        private const val REFRESH_TIME = 1000

        @JvmStatic fun conversionTime(milliseconds: Int): String {
            val formatter = Formatter(Locale.getDefault())
            val totalSeconds = milliseconds / 1000
            val hours = totalSeconds / 3600
            val minutes = totalSeconds / 60 % 60
            val seconds = totalSeconds % 60
            return if (hours > 0) formatter.format("%d:%02d:%02d", hours, minutes, seconds).toString()
            else formatter.format("%02d:%02d", minutes, seconds).toString()
        }
    }
}
