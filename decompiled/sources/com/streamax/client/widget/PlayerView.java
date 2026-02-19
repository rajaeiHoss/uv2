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

    public PlayerView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public PlayerView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
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

    public void setVideoSource(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mVideoView.setVideoURI(Uri.parse(str));
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

    public void setProgress(int i) {
        if (i > this.mVideoView.getDuration()) {
            i = this.mVideoView.getDuration();
        }
        if (Math.abs(i - this.mVideoView.getCurrentPosition()) > 1000) {
            this.mVideoView.seekTo(i);
            this.mProgressView.setProgress(i);
        }
    }

    public int getProgress() {
        return this.mVideoView.getCurrentPosition();
    }

    public int getDuration() {
        return this.mVideoView.getDuration();
    }

    public void setGestureEnabled(boolean z) {
        this.mGestureEnabled = z;
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
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            this.mVideoView.seekTo(this.mCurrentProgress);
            this.mProgressView.setProgress(this.mCurrentProgress);
        }
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            this.mPlayTime.setText(conversionTime(i));
        } else if (i != 0) {
            this.mCurrentProgress = i;
        } else if (this.mVideoView.getDuration() > 0) {
            this.mCurrentProgress = i;
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
        int i = this.mVideoWidth;
        int i2 = i * height;
        int i3 = this.mVideoHeight;
        if (i2 < width * i3) {
            width = (i * height) / i3;
        } else if (i * height > width * i3) {
            height = (i3 * width) / i;
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

    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        if (i == 701) {
            this.mLottieView.setAnimation((int) R.raw.progress);
            this.mLottieView.playAnimation();
            this.mMessageView.setText(R.string.common_loading);
            post(this.mShowMessageRunnable);
            return true;
        } else if (i != 702) {
            return false;
        } else {
            this.mLottieView.cancelAnimation();
            this.mMessageView.setText(R.string.common_loading);
            postDelayed(this.mHideMessageRunnable, 500);
            return true;
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String str;
        Activity activity = getActivity();
        if (activity == null) {
            return false;
        }
        if (i == 200) {
            str = activity.getString(R.string.common_video_error_not_support);
        } else {
            str = activity.getString(R.string.common_video_error_unknown);
        }
        final MediaPlayer player = mediaPlayer;
        ((MessageDialog.Builder) ((MessageDialog.Builder) ((MessageDialog.Builder) new MessageDialog.Builder(getActivity()).setMessage((CharSequence) str + "\n" + String.format(activity.getString(R.string.common_video_error_supplement), new Object[]{Integer.valueOf(i), Integer.valueOf(i2)})).setConfirm((int) R.string.common_confirm)).setCancel((CharSequence) null)).setCancelable(false)).setListener(new MessageDialog.OnListener() {
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

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        if (r0 != 3) goto L_0x024d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r11) {
        /*
            r10 = this;
            boolean r0 = r10.mGestureEnabled
            if (r0 == 0) goto L_0x024e
            boolean r0 = r10.mLockMode
            if (r0 != 0) goto L_0x024e
            com.airbnb.lottie.LottieAnimationView r0 = r10.mLottieView
            boolean r0 = r0.isAnimating()
            if (r0 == 0) goto L_0x0012
            goto L_0x024e
        L_0x0012:
            int r0 = r11.getAction()
            r1 = 0
            r2 = 3
            r3 = 1
            if (r0 == 0) goto L_0x01f7
            r4 = -1
            r5 = 0
            if (r0 == r3) goto L_0x0185
            r6 = 2
            if (r0 == r6) goto L_0x0026
            if (r0 == r2) goto L_0x01cc
            goto L_0x024d
        L_0x0026:
            float r0 = r10.mViewDownX
            float r7 = r11.getX()
            float r0 = r0 - r7
            float r7 = r10.mViewDownY
            float r8 = r11.getY()
            float r7 = r7 - r8
            float r8 = java.lang.Math.abs(r7)
            android.content.Context r9 = r10.getContext()
            android.view.ViewConfiguration r9 = android.view.ViewConfiguration.get(r9)
            int r9 = r9.getScaledTouchSlop()
            float r9 = (float) r9
            int r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r8 >= 0) goto L_0x004b
            goto L_0x024d
        L_0x004b:
            int r8 = r10.mTouchOrientation
            if (r8 != r4) goto L_0x006c
            float r4 = java.lang.Math.abs(r7)
            float r8 = java.lang.Math.abs(r0)
            int r4 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r4 <= 0) goto L_0x005e
            r10.mTouchOrientation = r3
            goto L_0x006c
        L_0x005e:
            float r4 = java.lang.Math.abs(r7)
            float r8 = java.lang.Math.abs(r0)
            int r4 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r4 >= 0) goto L_0x006c
            r10.mTouchOrientation = r5
        L_0x006c:
            int r4 = r10.mTouchOrientation
            if (r4 != 0) goto L_0x00bb
            int r11 = r10.getWidth()
            float r11 = (float) r11
            float r0 = r0 / r11
            r11 = 1114636288(0x42700000, float:60.0)
            float r0 = r0 * r11
            int r11 = (int) r0
            int r11 = -r11
            int r0 = r10.getProgress()
            int r1 = r11 * 1000
            int r0 = r0 + r1
            if (r0 < 0) goto L_0x024d
            int r1 = r10.getDuration()
            if (r0 > r1) goto L_0x024d
            r10.mAdjustSecond = r11
            com.airbnb.lottie.LottieAnimationView r0 = r10.mLottieView
            if (r11 >= 0) goto L_0x0095
            r11 = 2131231413(0x7f0802b5, float:1.8078906E38)
            goto L_0x0098
        L_0x0095:
            r11 = 2131231412(0x7f0802b4, float:1.8078904E38)
        L_0x0098:
            r0.setImageResource(r11)
            android.widget.TextView r11 = r10.mMessageView
            java.lang.Object[] r0 = new java.lang.Object[r3]
            int r1 = r10.mAdjustSecond
            int r1 = java.lang.Math.abs(r1)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r0[r5] = r1
            java.lang.String r1 = "%s s"
            java.lang.String r0 = java.lang.String.format(r1, r0)
            r11.setText(r0)
            java.lang.Runnable r11 = r10.mShowMessageRunnable
            r10.post(r11)
            goto L_0x024d
        L_0x00bb:
            if (r4 != r3) goto L_0x024d
            float r11 = r11.getX()
            int r11 = (int) r11
            int r0 = r10.getWidth()
            int r0 = r0 / r6
            java.lang.String r4 = "%s %%"
            r6 = 33
            r8 = 66
            if (r11 >= r0) goto L_0x0128
            int r11 = r10.getHeight()
            float r11 = (float) r11
            float r7 = r7 / r11
            r11 = 1065353216(0x3f800000, float:1.0)
            float r7 = r7 * r11
            int r0 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x00df
            goto L_0x024d
        L_0x00df:
            float r0 = r10.mCurrentBrightness
            float r0 = r0 + r7
            float r0 = java.lang.Math.max(r0, r1)
            float r11 = java.lang.Math.min(r0, r11)
            android.view.Window r0 = r10.mWindow
            android.view.WindowManager$LayoutParams r0 = r0.getAttributes()
            r0.screenBrightness = r11
            android.view.Window r1 = r10.mWindow
            r1.setAttributes(r0)
            r0 = 1120403456(0x42c80000, float:100.0)
            float r11 = r11 * r0
            int r11 = (int) r11
            if (r11 <= r8) goto L_0x0102
            r0 = 2131231405(0x7f0802ad, float:1.807889E38)
            goto L_0x010b
        L_0x0102:
            if (r11 <= r6) goto L_0x0108
            r0 = 2131231407(0x7f0802af, float:1.8078894E38)
            goto L_0x010b
        L_0x0108:
            r0 = 2131231406(0x7f0802ae, float:1.8078892E38)
        L_0x010b:
            com.airbnb.lottie.LottieAnimationView r1 = r10.mLottieView
            r1.setImageResource(r0)
            android.widget.TextView r0 = r10.mMessageView
            java.lang.Object[] r1 = new java.lang.Object[r3]
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r1[r5] = r11
            java.lang.String r11 = java.lang.String.format(r4, r1)
            r0.setText(r11)
            java.lang.Runnable r11 = r10.mShowMessageRunnable
            r10.post(r11)
            goto L_0x024d
        L_0x0128:
            int r11 = r10.getHeight()
            float r11 = (float) r11
            float r7 = r7 / r11
            int r11 = r10.mMaxVoice
            float r11 = (float) r11
            float r7 = r7 * r11
            int r11 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r11 != 0) goto L_0x0139
            goto L_0x024d
        L_0x0139:
            int r11 = r10.mCurrentVolume
            float r11 = (float) r11
            float r11 = r11 + r7
            float r11 = java.lang.Math.max(r11, r1)
            int r0 = r10.mMaxVoice
            float r0 = (float) r0
            float r11 = java.lang.Math.min(r11, r0)
            int r11 = (int) r11
            android.media.AudioManager r0 = r10.mAudioManager
            r0.setStreamVolume(r2, r11, r5)
            int r11 = r11 * 100
            int r0 = r10.mMaxVoice
            int r11 = r11 / r0
            if (r11 <= r8) goto L_0x0159
            r0 = 2131231414(0x7f0802b6, float:1.8078908E38)
            goto L_0x0168
        L_0x0159:
            if (r11 <= r6) goto L_0x015f
            r0 = 2131231416(0x7f0802b8, float:1.8078912E38)
            goto L_0x0168
        L_0x015f:
            if (r11 == 0) goto L_0x0165
            r0 = 2131231415(0x7f0802b7, float:1.807891E38)
            goto L_0x0168
        L_0x0165:
            r0 = 2131231417(0x7f0802b9, float:1.8078914E38)
        L_0x0168:
            com.airbnb.lottie.LottieAnimationView r1 = r10.mLottieView
            r1.setImageResource(r0)
            android.widget.TextView r0 = r10.mMessageView
            java.lang.Object[] r1 = new java.lang.Object[r3]
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r1[r5] = r11
            java.lang.String r11 = java.lang.String.format(r4, r1)
            r0.setText(r11)
            java.lang.Runnable r11 = r10.mShowMessageRunnable
            r10.post(r11)
            goto L_0x024d
        L_0x0185:
            float r0 = r10.mViewDownX
            float r1 = r11.getX()
            float r0 = r0 - r1
            float r0 = java.lang.Math.abs(r0)
            android.content.Context r1 = r10.getContext()
            android.view.ViewConfiguration r1 = android.view.ViewConfiguration.get(r1)
            int r1 = r1.getScaledTouchSlop()
            float r1 = (float) r1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 > 0) goto L_0x01cc
            float r0 = r10.mViewDownY
            float r11 = r11.getY()
            float r0 = r0 - r11
            float r11 = java.lang.Math.abs(r0)
            android.content.Context r0 = r10.getContext()
            android.view.ViewConfiguration r0 = android.view.ViewConfiguration.get(r0)
            int r0 = r0.getScaledTouchSlop()
            float r0 = (float) r0
            int r11 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r11 > 0) goto L_0x01cc
            boolean r11 = r10.isEnabled()
            if (r11 == 0) goto L_0x01cc
            boolean r11 = r10.isClickable()
            if (r11 == 0) goto L_0x01cc
            r10.performClick()
        L_0x01cc:
            r10.mTouchOrientation = r4
            android.media.AudioManager r11 = r10.mAudioManager
            int r11 = r11.getStreamVolume(r2)
            r10.mCurrentVolume = r11
            int r11 = r10.mAdjustSecond
            if (r11 == 0) goto L_0x01e8
            int r11 = r10.getProgress()
            int r0 = r10.mAdjustSecond
            int r0 = r0 * 1000
            int r11 = r11 + r0
            r10.setProgress(r11)
            r10.mAdjustSecond = r5
        L_0x01e8:
            java.lang.Runnable r11 = r10.mHideControllerRunnable
            r0 = 3000(0xbb8, double:1.482E-320)
            r10.postDelayed(r11, r0)
            java.lang.Runnable r11 = r10.mHideMessageRunnable
            r0 = 500(0x1f4, double:2.47E-321)
            r10.postDelayed(r11, r0)
            goto L_0x024d
        L_0x01f7:
            android.media.AudioManager r0 = r10.mAudioManager
            int r0 = r0.getStreamMaxVolume(r2)
            r10.mMaxVoice = r0
            android.media.AudioManager r0 = r10.mAudioManager
            int r0 = r0.getStreamVolume(r2)
            r10.mCurrentVolume = r0
            android.app.Activity r0 = r10.getActivity()
            android.view.Window r0 = r0.getWindow()
            r10.mWindow = r0
            android.view.WindowManager$LayoutParams r0 = r0.getAttributes()
            float r0 = r0.screenBrightness
            r10.mCurrentBrightness = r0
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L_0x023c
            android.content.Context r0 = r10.getContext()     // Catch:{ SettingNotFoundException -> 0x023a }
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch:{ SettingNotFoundException -> 0x023a }
            java.lang.String r2 = "screen_brightness"
            int r0 = android.provider.Settings.System.getInt(r0, r2)     // Catch:{ SettingNotFoundException -> 0x023a }
            r2 = 255(0xff, float:3.57E-43)
            int r0 = java.lang.Math.min(r0, r2)     // Catch:{ SettingNotFoundException -> 0x023a }
            float r0 = (float) r0     // Catch:{ SettingNotFoundException -> 0x023a }
            r2 = 1132396544(0x437f0000, float:255.0)
            float r0 = r0 / r2
            r10.mCurrentBrightness = r0     // Catch:{ SettingNotFoundException -> 0x023a }
            goto L_0x023c
        L_0x023a:
            r10.mCurrentBrightness = r1
        L_0x023c:
            float r0 = r11.getX()
            r10.mViewDownX = r0
            float r11 = r11.getY()
            r10.mViewDownY = r11
            java.lang.Runnable r11 = r10.mHideControllerRunnable
            r10.removeCallbacks(r11)
        L_0x024d:
            return r3
        L_0x024e:
            boolean r11 = super.onTouchEvent(r11)
            return r11
        */
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

    public static String conversionTime(int i) {
        Formatter formatter = new Formatter(Locale.getDefault());
        int i2 = i / 1000;
        int i3 = i2 / 3600;
        int i4 = (i2 / 60) % 60;
        int i5 = i2 % 60;
        if (i3 > 0) {
            return formatter.format("%d:%02d:%02d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)}).toString();
        }
        return formatter.format("%02d:%02d", new Object[]{Integer.valueOf(i4), Integer.valueOf(i5)}).toString();
    }
}
