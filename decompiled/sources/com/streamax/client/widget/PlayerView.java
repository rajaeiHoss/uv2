package com.streamax.client.widget;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import com.airbnb.lottie.LottieAnimationView;
import com.hjq.base.BaseDialog;
import com.hjq.base.action.ActivityAction;
import com.hjq.widget.layout.SimpleLayout;
import com.hjq.widget.view.PlayButton;
import com.streamax.client.ui.dialog.MessageDialog;
import com.zycs.UView.R;
import java.io.File;
import java.util.Formatter;
import java.util.Locale;

// Full-screen video player widget with controls and lifecycle hooks.
public final class PlayerView extends SimpleLayout implements LifecycleEventObserver, SeekBar.OnSeekBarChangeListener, View.OnClickListener, ActivityAction, MediaPlayer.OnPreparedListener, MediaPlayer.OnInfoListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
    private static final int ANIM_TIME = 500;
    private static final int CONTROLLER_TIME = 3000;
    private static final int DIALOG_TIME = 500;
    private static final int REFRESH_TIME = 1000;
    private int mAdjustSecond;
    private final AudioManager mAudioManager;
    /* access modifiers changed from: private */
    public final ViewGroup mBottomLayout;
    private final PlayButton mControlView;
    private boolean mControllerShow;
    private float mCurrentBrightness;
    private int mCurrentProgress;
    private int mCurrentVolume;
    private boolean mGestureEnabled;
    private final Runnable mHideControllerRunnable;
    private final Runnable mHideMessageRunnable;
    private final View mLeftView;
    /* access modifiers changed from: private */
    public OnPlayListener mListener;
    /* access modifiers changed from: private */
    public boolean mLockMode;
    private final ImageView mLockView;
    private final LottieAnimationView mLottieView;
    private int mMaxVoice;
    private ViewGroup mMessageLayout;
    private final TextView mMessageView;
    /* access modifiers changed from: private */
    public final TextView mPlayTime;
    /* access modifiers changed from: private */
    public final SeekBar mProgressView;
    private final Runnable mRefreshRunnable;
    private final Runnable mShowControllerRunnable;
    private final Runnable mShowMessageRunnable;
    private final TextView mTitleView;
    private final ViewGroup mTopLayout;
    private final TextView mTotalTime;
    private int mTouchOrientation;
    private int mVideoHeight;
    /* access modifiers changed from: private */
    public final VideoView mVideoView;
    private int mVideoWidth;
    private float mViewDownX;
    private float mViewDownY;
    private Window mWindow;

    public interface OnPlayListener {

        /* renamed from: com.streamax.client.widget.PlayerView$OnPlayListener$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onClickBack(OnPlayListener onPlayListener, PlayerView playerView) {
            }

            public static void $default$onClickLock(OnPlayListener onPlayListener, PlayerView playerView) {
            }

            public static void $default$onClickPlay(OnPlayListener onPlayListener, PlayerView playerView) {
            }

            public static void $default$onPlayEnd(OnPlayListener onPlayListener, PlayerView playerView) {
            }

            public static void $default$onPlayProgress(OnPlayListener onPlayListener, PlayerView playerView) {
            }

            public static void $default$onPlayStart(OnPlayListener onPlayListener, PlayerView playerView) {
            }
        }

        void onClickBack(PlayerView playerView);

        void onClickLock(PlayerView playerView);

        void onClickPlay(PlayerView playerView);

        void onPlayEnd(PlayerView playerView);

        void onPlayProgress(PlayerView playerView);

        void onPlayStart(PlayerView playerView);
    }

    public /* synthetic */ Activity getActivity() {
        return ActivityAction.CC.$default$getActivity(this);
    }

    public /* synthetic */ void startActivity(Intent intent) {
        ActivityAction.CC.$default$startActivity((ActivityAction) this, intent);
    }

    public /* synthetic */ void startActivity(Class cls) {
        ActivityAction.CC.$default$startActivity((ActivityAction) this, cls);
    }

    public PlayerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PlayerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PlayerView(Context context, AttributeSet attributeSet, int defStyleAttr) {
        this(context, attributeSet, defStyleAttr, 0);
    }

    public PlayerView(Context context, AttributeSet attributeSet, int defStyleAttr, int defStyleRes) {
        super(context, attributeSet, defStyleAttr, defStyleRes);
        this.mControllerShow = false;
        this.mTouchOrientation = -1;
        this.mRefreshRunnable = new Runnable() {
            public void run() {
                int currentPosition = PlayerView.this.mVideoView.getCurrentPosition();
                if (currentPosition + 1000 < PlayerView.this.mVideoView.getDuration()) {
                    currentPosition = Math.round(((float) currentPosition) / 1000.0f) * 1000;
                }
                PlayerView.this.mPlayTime.setText(PlayerView.conversionTime(currentPosition));
                PlayerView.this.mProgressView.setProgress(currentPosition);
                PlayerView.this.mProgressView.setSecondaryProgress((int) ((((float) PlayerView.this.mVideoView.getBufferPercentage()) / 100.0f) * ((float) PlayerView.this.mVideoView.getDuration())));
                if (PlayerView.this.mVideoView.isPlaying()) {
                    if (!PlayerView.this.mLockMode && PlayerView.this.mBottomLayout.getVisibility() == 8) {
                        PlayerView.this.mBottomLayout.setVisibility(0);
                    }
                } else if (PlayerView.this.mBottomLayout.getVisibility() == 0) {
                    PlayerView.this.mBottomLayout.setVisibility(8);
                }
                PlayerView.this.postDelayed(this, 1000);
                if (PlayerView.this.mListener != null) {
                    PlayerView.this.mListener.onPlayProgress(PlayerView.this);
                }
            }
        };
        this.mShowControllerRunnable = new Runnable() {
            public final void run() {
                PlayerView.this.lambda$new$7$PlayerView();
            }
        };
        this.mHideControllerRunnable = new Runnable() {
            public final void run() {
                PlayerView.this.lambda$new$8$PlayerView();
            }
        };
        this.mShowMessageRunnable = new Runnable() {
            public final void run() {
                PlayerView.this.lambda$new$9$PlayerView();
            }
        };
        this.mHideMessageRunnable = new Runnable() {
            public final void run() {
                PlayerView.this.lambda$new$10$PlayerView();
            }
        };
        LayoutInflater.from(getContext()).inflate(R.layout.widget_player_view, this, true);
        this.mTopLayout = (ViewGroup) findViewById(R.id.ll_player_view_top);
        View findViewById = findViewById(R.id.iv_player_view_left);
        this.mLeftView = findViewById;
        this.mTitleView = (TextView) findViewById(R.id.tv_player_view_title);
        this.mBottomLayout = (ViewGroup) findViewById(R.id.ll_player_view_bottom);
        this.mPlayTime = (TextView) findViewById(R.id.tv_player_view_play_time);
        this.mTotalTime = (TextView) findViewById(R.id.tv_player_view_total_time);
        SeekBar seekBar = (SeekBar) findViewById(R.id.sb_player_view_progress);
        this.mProgressView = seekBar;
        VideoView videoView = (VideoView) findViewById(R.id.vv_player_view_video);
        this.mVideoView = videoView;
        ImageView imageView = (ImageView) findViewById(R.id.iv_player_view_lock);
        this.mLockView = imageView;
        PlayButton playButton = (PlayButton) findViewById(R.id.iv_player_view_control);
        this.mControlView = playButton;
        this.mMessageLayout = (ViewGroup) findViewById(R.id.cv_player_view_message);
        this.mLottieView = (LottieAnimationView) findViewById(R.id.lav_player_view_lottie);
        this.mMessageView = (TextView) findViewById(R.id.tv_player_view_message);
        findViewById.setOnClickListener(this);
        playButton.setOnClickListener(this);
        imageView.setOnClickListener(this);
        setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);
        videoView.setOnPreparedListener(this);
        videoView.setOnCompletionListener(this);
        videoView.setOnInfoListener(this);
        videoView.setOnErrorListener(this);
        this.mAudioManager = (AudioManager) ContextCompat.getSystemService(getContext(), AudioManager.class);
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        lifecycleOwner.getLifecycle().addObserver(this);
    }

    /* renamed from: com.streamax.client.widget.PlayerView$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$androidx$lifecycle$Lifecycle$Event;

        static {
            int[] iArr = new int[androidx.lifecycle.Lifecycle.Event.values().length];
            $SwitchMap$androidx$lifecycle$Lifecycle$Event = iArr;
            try {
                iArr[androidx.lifecycle.Lifecycle.Event.ON_RESUME.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[androidx.lifecycle.Lifecycle.Event.ON_PAUSE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[androidx.lifecycle.Lifecycle.Event.ON_DESTROY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        int i = AnonymousClass2.$SwitchMap$androidx$lifecycle$Lifecycle$Event[event.ordinal()];
        if (i == 1) {
            onResume();
        } else if (i == 2) {
            onPause();
        } else if (i == 3) {
            onDestroy();
        }
    }

    public void setVideoTitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            this.mTitleView.setText(charSequence);
        }
    }

    public void setVideoSource(File file) {
        if (file != null && file.isFile()) {
            this.mVideoView.setVideoPath(file.getPath());
        }
    }

    public void setVideoSource(String source) {
        if (!TextUtils.isEmpty(source)) {
            this.mVideoView.setVideoURI(Uri.parse(source));
        }
    }

    public void start() {
        this.mVideoView.start();
        this.mControlView.play();
        removeCallbacks(this.mHideControllerRunnable);
        postDelayed(this.mHideControllerRunnable, 3000);
    }

    public void pause() {
        this.mVideoView.pause();
        this.mControlView.pause();
        removeCallbacks(this.mHideControllerRunnable);
        postDelayed(this.mHideControllerRunnable, 3000);
    }

    public void lock() {
        this.mLockMode = true;
        this.mLockView.setImageResource(R.drawable.video_lock_close_ic);
        this.mTopLayout.setVisibility(8);
        this.mBottomLayout.setVisibility(8);
        this.mControlView.setVisibility(8);
        removeCallbacks(this.mHideControllerRunnable);
        postDelayed(this.mHideControllerRunnable, 3000);
    }

    public void unlock() {
        this.mLockMode = false;
        this.mLockView.setImageResource(R.drawable.video_lock_open_ic);
        this.mTopLayout.setVisibility(0);
        if (this.mVideoView.isPlaying()) {
            this.mBottomLayout.setVisibility(0);
        }
        this.mControlView.setVisibility(0);
        removeCallbacks(this.mHideControllerRunnable);
        postDelayed(this.mHideControllerRunnable, 3000);
    }

    public boolean isPlaying() {
        return this.mVideoView.isPlaying();
    }

    public void setProgress(int progress) {
        if (progress > this.mVideoView.getDuration()) {
            progress = this.mVideoView.getDuration();
        }
        if (Math.abs(progress - this.mVideoView.getCurrentPosition()) > 1000) {
            this.mVideoView.seekTo(progress);
            this.mProgressView.setProgress(progress);
        }
    }

    public int getProgress() {
        return this.mVideoView.getCurrentPosition();
    }

    public int getDuration() {
        return this.mVideoView.getDuration();
    }

    public void setGestureEnabled(boolean enabled) {
        this.mGestureEnabled = enabled;
    }

    public void setOnPlayListener(OnPlayListener onPlayListener) {
        this.mListener = onPlayListener;
        this.mLeftView.setVisibility(onPlayListener != null ? 0 : 4);
    }

    public void showController() {
        if (!this.mControllerShow) {
            this.mControllerShow = true;
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{-this.mTopLayout.getHeight(), 0});
            ofInt.setDuration(500);
            ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    PlayerView.this.lambda$showController$0$PlayerView(valueAnimator);
                }
            });
            ofInt.start();
            ValueAnimator ofInt2 = ValueAnimator.ofInt(new int[]{this.mBottomLayout.getHeight(), 0});
            ofInt2.setDuration(500);
            ofInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    PlayerView.this.lambda$showController$1$PlayerView(valueAnimator);
                }
            });
            ofInt2.start();
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            ofFloat.setDuration(500);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    PlayerView.this.lambda$showController$2$PlayerView(valueAnimator);
                }
            });
            ofFloat.start();
        }
    }

    public /* synthetic */ void lambda$showController$0$PlayerView(ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.mTopLayout.setTranslationY((float) intValue);
        if (intValue == (-this.mTopLayout.getHeight()) && this.mTopLayout.getVisibility() == 4) {
            this.mTopLayout.setVisibility(0);
        }
    }

    public /* synthetic */ void lambda$showController$1$PlayerView(ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.mBottomLayout.setTranslationY((float) intValue);
        if (intValue == this.mBottomLayout.getHeight() && this.mBottomLayout.getVisibility() == 4) {
            this.mBottomLayout.setVisibility(0);
        }
    }

    public /* synthetic */ void lambda$showController$2$PlayerView(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.mLockView.setAlpha(floatValue);
        this.mControlView.setAlpha(floatValue);
        if (floatValue == 0.0f && this.mControlView.getVisibility() == 4) {
            this.mControlView.setVisibility(0);
        }
    }

    public void hideController() {
        if (this.mControllerShow) {
            this.mControllerShow = false;
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, -this.mTopLayout.getHeight()});
            ofInt.setDuration(500);
            ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    PlayerView.this.lambda$hideController$3$PlayerView(valueAnimator);
                }
            });
            ofInt.start();
            ValueAnimator ofInt2 = ValueAnimator.ofInt(new int[]{0, this.mBottomLayout.getHeight()});
            ofInt2.setDuration(500);
            ofInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    PlayerView.this.lambda$hideController$4$PlayerView(valueAnimator);
                }
            });
            ofInt2.start();
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
            ofFloat.setDuration(500);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    PlayerView.this.lambda$hideController$5$PlayerView(valueAnimator);
                }
            });
            ofFloat.start();
        }
    }

    public /* synthetic */ void lambda$hideController$3$PlayerView(ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.mTopLayout.setTranslationY((float) intValue);
        if (intValue == (-this.mTopLayout.getHeight()) && this.mTopLayout.getVisibility() == 0) {
            this.mTopLayout.setVisibility(4);
        }
    }

    public /* synthetic */ void lambda$hideController$4$PlayerView(ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.mBottomLayout.setTranslationY((float) intValue);
        if (intValue == this.mBottomLayout.getHeight() && this.mBottomLayout.getVisibility() == 0) {
            this.mBottomLayout.setVisibility(4);
        }
    }

    public /* synthetic */ void lambda$hideController$5$PlayerView(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.mLockView.setAlpha(floatValue);
        this.mControlView.setAlpha(floatValue);
        if (floatValue == 0.0f && this.mControlView.getVisibility() == 0) {
            this.mControlView.setVisibility(4);
        }
    }

    public void onResume() {
        this.mVideoView.resume();
    }

    public void onPause() {
        this.mVideoView.suspend();
        pause();
    }

    public void onDestroy() {
        this.mVideoView.stopPlayback();
        removeCallbacks(this.mRefreshRunnable);
        removeCallbacks(this.mShowControllerRunnable);
        removeCallbacks(this.mHideControllerRunnable);
        removeCallbacks(this.mShowMessageRunnable);
        removeCallbacks(this.mHideMessageRunnable);
        removeAllViews();
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == 0) {
            this.mVideoView.seekTo(this.mCurrentProgress);
            this.mProgressView.setProgress(this.mCurrentProgress);
        }
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            this.mPlayTime.setText(conversionTime(progress));
        } else if (progress != 0) {
            this.mCurrentProgress = progress;
        } else if (this.mVideoView.getDuration() > 0) {
            this.mCurrentProgress = progress;
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        removeCallbacks(this.mRefreshRunnable);
        removeCallbacks(this.mHideControllerRunnable);
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        postDelayed(this.mRefreshRunnable, 1000);
        postDelayed(this.mHideControllerRunnable, 3000);
        setProgress(seekBar.getProgress());
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.mPlayTime.setText(conversionTime(0));
        this.mTotalTime.setText(conversionTime(mediaPlayer.getDuration()));
        this.mProgressView.setMax(this.mVideoView.getDuration());
        this.mVideoWidth = mediaPlayer.getVideoWidth();
        this.mVideoHeight = mediaPlayer.getVideoHeight();
        int width = getWidth();
        int height = getHeight();
        int videoWidth = this.mVideoWidth;
        int scaledVideoWidth = videoWidth * height;
        int videoHeight = this.mVideoHeight;
        if (scaledVideoWidth < width * videoHeight) {
            width = (videoWidth * height) / videoHeight;
        } else if (videoWidth * height > width * videoHeight) {
            height = (videoHeight * width) / videoWidth;
        }
        ViewGroup.LayoutParams layoutParams = this.mVideoView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        this.mVideoView.setLayoutParams(layoutParams);
        post(this.mShowControllerRunnable);
        postDelayed(this.mRefreshRunnable, 500);
        OnPlayListener onPlayListener = this.mListener;
        if (onPlayListener != null) {
            onPlayListener.onPlayStart(this);
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        pause();
        OnPlayListener onPlayListener = this.mListener;
        if (onPlayListener != null) {
            onPlayListener.onPlayEnd(this);
        }
    }

    public boolean onInfo(MediaPlayer mediaPlayer, int what, int extra) {
        if (what == 701) {
            this.mLottieView.setAnimation((int) R.raw.progress);
            this.mLottieView.playAnimation();
            this.mMessageView.setText(R.string.common_loading);
            post(this.mShowMessageRunnable);
            return true;
        } else if (what != 702) {
            return false;
        } else {
            this.mLottieView.cancelAnimation();
            this.mMessageView.setText(R.string.common_loading);
            postDelayed(this.mHideMessageRunnable, 500);
            return true;
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
        String errorMessage;
        Activity activity = getActivity();
        if (activity == null) {
            return false;
        }
        if (what == 200) {
            errorMessage = activity.getString(R.string.common_video_error_not_support);
        } else {
            errorMessage = activity.getString(R.string.common_video_error_unknown);
        }
        final MediaPlayer player = mediaPlayer;
        ((MessageDialog.Builder) ((MessageDialog.Builder) ((MessageDialog.Builder) new MessageDialog.Builder(getActivity()).setMessage((CharSequence) errorMessage + "\n" + String.format(activity.getString(R.string.common_video_error_supplement), new Object[]{Integer.valueOf(what), Integer.valueOf(extra)})).setConfirm((int) R.string.common_confirm)).setCancel((CharSequence) null)).setCancelable(false)).setListener(new MessageDialog.OnListener() {
            public /* synthetic */ void onCancel(BaseDialog baseDialog) {
                MessageDialog.OnListener.CC.$default$onCancel(this, baseDialog);
            }

            public final void onConfirm(BaseDialog baseDialog) {
                PlayerView.this.lambda$onError$6$PlayerView(player, baseDialog);
            }
        }).show();
        return true;
    }

    public /* synthetic */ void lambda$onError$6$PlayerView(MediaPlayer mediaPlayer, BaseDialog baseDialog) {
        onCompletion(mediaPlayer);
    }

    public void onClick(View view) {
        if (view == this) {
            removeCallbacks(this.mShowControllerRunnable);
            removeCallbacks(this.mHideControllerRunnable);
            if (this.mControllerShow) {
                post(this.mHideControllerRunnable);
                return;
            }
            post(this.mShowControllerRunnable);
            postDelayed(this.mHideControllerRunnable, 3000);
        } else if (view == this.mLeftView) {
            OnPlayListener onPlayListener = this.mListener;
            if (onPlayListener != null) {
                onPlayListener.onClickBack(this);
            }
        } else {
            PlayButton playButton = this.mControlView;
            if (view == playButton) {
                if (playButton.getVisibility() == 0) {
                    if (isPlaying()) {
                        pause();
                    } else {
                        start();
                    }
                    removeCallbacks(this.mShowControllerRunnable);
                    removeCallbacks(this.mHideControllerRunnable);
                    if (!this.mControllerShow) {
                        post(this.mShowControllerRunnable);
                    }
                    postDelayed(this.mHideControllerRunnable, 3000);
                    OnPlayListener onPlayListener2 = this.mListener;
                    if (onPlayListener2 != null) {
                        onPlayListener2.onClickPlay(this);
                    }
                }
            } else if (view == this.mLockView) {
                if (this.mLockMode) {
                    unlock();
                } else {
                    lock();
                }
                OnPlayListener onPlayListener3 = this.mListener;
                if (onPlayListener3 != null) {
                    onPlayListener3.onClickLock(this);
                }
            }
        }
    }

    public int getVideoWidth() {
        return this.mVideoWidth;
    }

    public int getVideoHeight() {
        return this.mVideoHeight;
    }

    public boolean onTouchEvent(android.view.MotionEvent motionEvent) {
        throw new UnsupportedOperationException("Method not decompiled: com.streamax.client.widget.PlayerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public /* synthetic */ void lambda$new$7$PlayerView() {
        if (!this.mControllerShow) {
            showController();
        }
    }

    public /* synthetic */ void lambda$new$8$PlayerView() {
        if (this.mControllerShow) {
            hideController();
        }
    }

    public /* synthetic */ void lambda$new$9$PlayerView() {
        hideController();
        this.mMessageLayout.setVisibility(0);
    }

    public /* synthetic */ void lambda$new$10$PlayerView() {
        this.mMessageLayout.setVisibility(8);
    }

    public static String conversionTime(int milliseconds) {
        Formatter formatter = new Formatter(Locale.getDefault());
        int totalSeconds = milliseconds / 1000;
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds / 60) % 60;
        int seconds = totalSeconds % 60;
        if (hours > 0) {
            return formatter.format("%d:%02d:%02d", new Object[]{Integer.valueOf(hours), Integer.valueOf(minutes), Integer.valueOf(seconds)}).toString();
        }
        return formatter.format("%02d:%02d", new Object[]{Integer.valueOf(minutes), Integer.valueOf(seconds)}).toString();
    }
}
