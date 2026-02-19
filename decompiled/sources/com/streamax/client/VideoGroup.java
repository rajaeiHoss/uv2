package com.streamax.client;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.dvr.net.DvrNet;
import com.streamax.client.ui.devlist.LvVideoFrame;
import com.zycs.UView.R;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.List;

// Grid container for preview tiles, gestures, and PTZ controls.
public class VideoGroup extends RelativeLayout {
    private static final int DRAG = 1;
    private static final int FLING_TO_LEFT = 0;
    private static final int FLING_TO_RIGHT = 1;
    private static final int NONE = 0;
    private static final int PTZ_DOWN = 2;
    private static final int PTZ_LEFT = 3;
    private static final int PTZ_LINE_START = 16;
    private static final int PTZ_LINE_STOP = 19;
    private static final int PTZ_RIGHT = 4;
    private static final int PTZ_STOP = 20;
    private static final int PTZ_UP = 1;
    private static final int PTZ_ZOOM_IN = 7;
    private static final int PTZ_ZOOM_OUT = 8;
    private static final int ZOOM = 2;
    private final int DISTANCE_MIN_SIZE = 100;
    /* access modifiers changed from: private */
    public boolean bCurise = false;
    public boolean flagY = false;
    public boolean flag_LeftX = false;
    public boolean flag_RightX = false;
    private int mBaseIndex = 0;
    private Context mContext;
    /* access modifiers changed from: private */
    public int mCurViewCount = 0;
    public int mDestinationIndex;
    public RelativeLayout.LayoutParams mDestinationParams;
    private float mDignitalDistance;
    private float mDistance;
    /* access modifiers changed from: private */
    public DvrNet mDvrNet = null;
    private int mFlingStatus;
    /* access modifiers changed from: private */
    public int mFocusIndex = 0;
    GestureDetector mGestureDetector;
    public int mHeight = 0;
    /* access modifiers changed from: private */
    public List<Integer> mIndexList = new ArrayList();
    /* access modifiers changed from: private */
    public int mInitViewCount = 0;
    private int mLastArrayMode = 0;
    public LiveViewUi mLiveViewUi;
    private int mMax = 32;
    public View.OnClickListener mOnclickListener;
    private int mPtzState = 0;
    public int mSourceIndex;
    public RelativeLayout.LayoutParams mSourceParams;
    /* access modifiers changed from: private */
    public int mTouchMode;
    /* access modifiers changed from: private */
    public LvVideoFrame[] mVideoFrame;
    public VideoGroup mVideoGroup;
    public int mWidth = 0;
    private Matrix matrix = new Matrix();
    public boolean mbFull = false;
    /* access modifiers changed from: private */
    public boolean mbLongPress = false;
    /* access modifiers changed from: private */
    public boolean mbPtz = false;
    private Matrix savedMatrix = new Matrix();
    private PointF start = new PointF();
    public int videoFrame_Left_zero = -1;
    public int videoFrame_Right_zero = -1;

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            postDelayed(new Runnable() {
                public void run() {
                    VideoGroup videoGroup = VideoGroup.this;
                    videoGroup.ArrayViews(videoGroup.mCurViewCount, true);
                }
            }, 100);
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    public VideoGroup(Context context) {
        super(context);
        this.mContext = context;
        if (this.mGestureDetector == null) {
            this.mGestureDetector = new GestureDetector(this.mContext, new FlingGestureDetector());
        }
        this.mVideoGroup = this;
        LoadViews();
    }

    public VideoGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        if (this.mGestureDetector == null) {
            this.mGestureDetector = new GestureDetector(this.mContext, new FlingGestureDetector());
        }
        this.mVideoGroup = this;
        LoadViews();
    }

    public VideoGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        if (this.mGestureDetector == null) {
            this.mGestureDetector = new GestureDetector(this.mContext, new FlingGestureDetector());
        }
        this.mVideoGroup = this;
        LoadViews();
    }

    public void setCanFull(boolean z) {
        this.mbFull = z;
    }

    public void SetInitMode(int i) {
        this.mLastArrayMode = i;
        this.mCurViewCount = i;
        this.mInitViewCount = i;
        this.mIndexList.clear();
        for (int i2 = 0; i2 < this.mMax; i2++) {
            this.mIndexList.add(i2, Integer.valueOf(i2));
        }
        this.mBaseIndex = 0;
        this.mFocusIndex = 0;
        for (int i3 = 0; i3 < this.mInitViewCount; i3++) {
            if (i3 == this.mFocusIndex) {
                this.mVideoFrame[this.mIndexList.get(i3).intValue()].SetFocusState(true);
            } else {
                this.mVideoFrame[this.mIndexList.get(i3).intValue()].SetFocusState(false);
            }
        }
        if (this.mCurViewCount == 32) {
            this.mCurViewCount = 4;
        }
        ArrayViews(this.mCurViewCount, false);
    }

    public void setAdas(int i, boolean z) {
        this.mVideoFrame[i].mVideoView.setAdas(z);
    }

    public void setVideoInfo(int i, int i2, int i3) {
        this.mVideoFrame[i].mVideoView.setVideoInfo(i2, i3);
    }

    public void setAdasHorizon(int i, int i2) {
        this.mVideoFrame[i].mVideoView.setAdasHorizon(i2);
    }

    public void setAdasVertical(int i, int i2) {
        this.mVideoFrame[i].mVideoView.setAdasVertical(i2);
    }

    public void setFocusState(int i, boolean z) {
        this.mVideoFrame[i].mVideoView.setFocusState(z);
        this.mVideoFrame[i].mCusImageView.setFocusState(z);
    }

    public void setOsdState(int i, boolean z) {
        this.mVideoFrame[i].mVideoView.setOsdState(z);
        this.mVideoFrame[i].mCusImageView.setOsdState(z);
    }

    public int GetLayoutMode() {
        return this.mInitViewCount;
    }

    public int GetCurArrayMode() {
        return this.mCurViewCount;
    }

    public List<Integer> GetChannelList() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mInitViewCount; i++) {
            LvVideoFrame[] lvVideoFrameArr = this.mVideoFrame;
            if (lvVideoFrameArr[i] != null && lvVideoFrameArr[i].getVisibility() == 0) {
                arrayList.add(Integer.valueOf(this.mIndexList.get(i).intValue()));
            }
        }
        return arrayList;
    }

    public void SetActivity(LiveViewUi liveViewUi) {
        this.mLiveViewUi = liveViewUi;
    }

    public void LoadViews() {
        this.mVideoFrame = new LvVideoFrame[this.mMax];
        this.mIndexList.clear();
        setVerticalGravity(17);
        setHorizontalGravity(17);
        int i = 0;
        while (i < this.mMax) {
            int i2 = i + 1;
            this.mVideoFrame[i] = new LvVideoFrame(this.mContext, i2, this.mbFull);
            this.mVideoFrame[i].setId(i);
            this.mVideoFrame[i].setVisibility(8);
            this.mVideoFrame[i].setClickable(false);
            this.mVideoFrame[i].setFocusable(false);
            this.mVideoFrame[i].setFocusableInTouchMode(false);
            this.mVideoFrame[i].setHapticFeedbackEnabled(false);
            addView(this.mVideoFrame[i]);
            this.mIndexList.add(i, Integer.valueOf(i));
            i = i2;
        }
        this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].SetFocusState(true);
        this.mOnclickListener = new View.OnClickListener() {
            public void onClick(View view) {
                final int i;
                switch (view.getId()) {
                    case R.id.ptz_control_down /*2131362923*/:
                        i = 2;
                        break;
                    case R.id.ptz_control_left /*2131362924*/:
                        i = 3;
                        break;
                    case R.id.ptz_control_right /*2131362926*/:
                        i = 4;
                        break;
                    case R.id.ptz_control_up /*2131362927*/:
                        i = 1;
                        break;
                    default:
                        i = 0;
                        break;
                }
                if (VideoGroup.this.mDvrNet != null && VideoGroup.this.mCurViewCount == 1) {
                    new Thread(new Runnable() {
                        public void run() {
                            VideoGroup.this.mDvrNet.PTZControl(((Integer) VideoGroup.this.mIndexList.get(VideoGroup.this.mFocusIndex)).intValue(), i, VideoGroup.this.mLiveViewUi.mApp.mPtzSpeed);
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            VideoGroup.this.mDvrNet.PTZControl(((Integer) VideoGroup.this.mIndexList.get(VideoGroup.this.mFocusIndex)).intValue(), 20, VideoGroup.this.mLiveViewUi.mApp.mPtzSpeed);
                        }
                    }).start();
                }
            }
        };
        int[] iArr = {R.id.ptz_control_left, R.id.ptz_control_right, R.id.ptz_control_up, R.id.ptz_control_down};
        for (int i3 = 0; i3 < 4; i3++) {
            ImageView imageView = (ImageView) findViewById(iArr[i3]);
            if (imageView != null) {
                imageView.setOnClickListener(this.mOnclickListener);
            }
        }
        final ImageView imageView2 = (ImageView) findViewById(R.id.ptz_control_line);
        if (imageView2 != null) {
            imageView2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!VideoGroup.this.bCurise) {
                        imageView2.setImageResource(R.drawable.direction_line_stop);
                        VideoGroup.this.mDvrNet.PTZControl(((Integer) VideoGroup.this.mIndexList.get(VideoGroup.this.mFocusIndex)).intValue(), 16, VideoGroup.this.mLiveViewUi.mApp.mPtzSpeed);
                        boolean unused = VideoGroup.this.bCurise = true;
                        return;
                    }
                    VideoGroup.this.mDvrNet.PTZControl(((Integer) VideoGroup.this.mIndexList.get(VideoGroup.this.mFocusIndex)).intValue(), 19, VideoGroup.this.mLiveViewUi.mApp.mPtzSpeed);
                    imageView2.setImageResource(R.drawable.direction_line_start);
                    boolean unused2 = VideoGroup.this.bCurise = false;
                }
            });
        }
    }

    public void showFull(boolean z) {
        for (int i = 0; i < this.mMax; i++) {
            this.mVideoFrame[i].showFull(z);
        }
    }

    public void SetPlayState(int i, boolean z) {
        this.mVideoFrame[i].SetPlayState(z);
    }

    public void SetBusyState(int i, boolean z) {
        this.mVideoFrame[i].SetBusyState(z);
    }

    public void SetRecState(int i, boolean z) {
        this.mVideoFrame[i].SetRecState(z);
    }

    public void ClearViews() {
        for (int i = 0; i < this.mInitViewCount; i++) {
            this.mVideoFrame[i].ClearViews();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:443:0x0ce1  */
    /* JADX WARNING: Removed duplicated region for block: B:485:0x0e44  */
    /* JADX WARNING: Removed duplicated region for block: B:505:0x0efd  */
    /* JADX WARNING: Removed duplicated region for block: B:521:0x0f2b  */
    /* JADX WARNING: Removed duplicated region for block: B:563:0x108e  */
    /* JADX WARNING: Removed duplicated region for block: B:583:0x1147  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void ArrayViews(int r17, boolean r18) {
        int i;
        if (r17 <= 0 || this.mVideoFrame == null) {
            return;
        }
        this.mWidth = getWidth();
        this.mHeight = getHeight();
        if (this.mWidth <= 0 || this.mHeight <= 0) {
            this.mWidth = getMeasuredWidth();
            this.mHeight = getMeasuredHeight();
        }
        if (this.mWidth <= 0 || this.mHeight <= 0) {
            return;
        }
        if (!r18) {
            this.mLastArrayMode = this.mCurViewCount;
        }
        int i2 = this.mInitViewCount > 0 ? this.mInitViewCount : this.mIndexList.size();
        int i3 = this.mBaseIndex;
        if (i3 < 0) {
            i3 = 0;
        }
        if (i3 + r17 > i2) {
            i3 = 0;
        }
        this.mBaseIndex = i3;
        this.mCurViewCount = r17;
        int i4 = r17 == 1 ? 1 : r17 == 4 ? 2 : r17 == 9 ? 3 : r17 == 16 ? 4 : (int) Math.ceil(Math.sqrt((double) r17));
        int i5 = (int) Math.ceil(((double) r17) / ((double) i4));
        int i6 = this.mWidth / i4;
        int i7 = this.mHeight / i5;
        for (int i8 = 0; i8 < i2; i8++) {
            int intValue = this.mIndexList.get(i8).intValue();
            if (i8 < this.mBaseIndex || i8 >= this.mBaseIndex + r17) {
                LvVideoFrame lvVideoFrame = this.mVideoFrame[intValue];
                if (lvVideoFrame != null) {
                    lvVideoFrame.setVisibility(8);
                }
                LiveViewUi liveViewUi = this.mLiveViewUi;
                if (liveViewUi != null) {
                    liveViewUi.SetStreamDecodeState(intValue, true);
                }
            }
        }
        int i9 = 0;
        for (int i10 = 0; i10 < i5; i10++) {
            for (int i11 = 0; i11 < i4; i11++) {
                if (i9 >= r17 || (i = this.mBaseIndex + i9) >= i2) {
                    break;
                }
                int intValue2 = this.mIndexList.get(i).intValue();
                LvVideoFrame lvVideoFrame2 = this.mVideoFrame[intValue2];
                if (lvVideoFrame2 != null) {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) lvVideoFrame2.getLayoutParams();
                    if (layoutParams == null) {
                        layoutParams = new RelativeLayout.LayoutParams(i6, i7);
                    }
                    layoutParams.width = i6;
                    layoutParams.height = i7;
                    layoutParams.leftMargin = i6 * i11;
                    layoutParams.topMargin = i7 * i10;
                    lvVideoFrame2.setLayoutParams(layoutParams);
                    lvVideoFrame2.setVisibility(0);
                    lvVideoFrame2.SetMax(r17 == 1);
                }
                LiveViewUi liveViewUi2 = this.mLiveViewUi;
                if (liveViewUi2 != null) {
                    liveViewUi2.SetStreamDecodeState(intValue2, false);
                }
                i9++;
            }
        }
        if (this.mLiveViewUi != null) {
            new Thread(new Runnable() {
                public void run() {
                    VideoGroup.this.mLiveViewUi.SwitchPlay();
                }
            }).start();
        }
    }

    public void SetPtzControlState(int i) {
        LiveViewUi liveViewUi;
        ImageView imageView;
        int[] iArr = {R.id.ptz_control_left, R.id.ptz_control_right, R.id.ptz_control_up, R.id.ptz_control_down, R.id.ptz_control_line};
        for (int i2 = 0; i2 < 5; i2++) {
            if ((iArr[i2] != R.id.ptz_control_line || this.mLiveViewUi.mApp.mbCurise) && (imageView = (ImageView) findViewById(iArr[i2])) != null) {
                if (imageView.getVisibility() != i) {
                    imageView.setVisibility(i);
                }
                imageView.bringToFront();
            }
        }
        if (i == 8 || i == 4) {
            LiveViewUi liveViewUi2 = this.mLiveViewUi;
            if (liveViewUi2 != null) {
                liveViewUi2.SetPtzState(false);
            }
        } else if (i == 0 && (liveViewUi = this.mLiveViewUi) != null) {
            liveViewUi.SetPtzState(true);
        }
    }

    public int GetMaxChannel() {
        if (this.mCurViewCount != 1) {
            return -1;
        }
        return this.mIndexList.get(this.mFocusIndex).intValue();
    }

    public int GetFocusChannel() {
        return this.mIndexList.get(this.mFocusIndex).intValue();
    }

    public VideoView getVideoView(int i) {
        return this.mVideoFrame[i].GetVideoView();
    }

    public int GetCurrentMode() {
        return this.mCurViewCount;
    }

    public void FlingRight(float f) {
        if ((this.mCurViewCount != 1 || this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].getDigitalScales() == 1.0f) && this.mInitViewCount != this.mCurViewCount) {
            applyRotation(-1, 0.0f, 90.0f);
        }
    }

    public void FlingLeft(float f) {
        if ((this.mCurViewCount != 1 || this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].getDigitalScales() == 1.0f) && this.mInitViewCount != this.mCurViewCount) {
            applyRotation(1, 0.0f, -90.0f);
        }
    }

    private void applyRotation(int i, float f, float f2) {
        Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(f, f2, ((float) this.mVideoGroup.getWidth()) / 2.0f, ((float) this.mVideoGroup.getHeight()) / 2.0f, 250.0f, true);
        rotate3dAnimation.setDuration(500);
        rotate3dAnimation.setFillAfter(true);
        rotate3dAnimation.setInterpolator(new AccelerateInterpolator());
        rotate3dAnimation.setAnimationListener(new DisplayNextView(i));
        startAnimation(rotate3dAnimation);
    }

    private final class DisplayNextView implements Animation.AnimationListener {
        private final int mPosition;

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }

        private DisplayNextView(int i) {
            this.mPosition = i;
        }

        public void onAnimationEnd(Animation animation) {
            VideoGroup.this.mVideoGroup.post(new SwapViews(this.mPosition));
        }
    }

    private final class SwapViews implements Runnable {
        private final int mPosition;

        public SwapViews(int i) {
            this.mPosition = i;
        }

        public void run() {
            Rotate3dAnimation rotate3dAnimation;
            float width = ((float) VideoGroup.this.getWidth()) / 2.0f;
            float height = ((float) VideoGroup.this.getHeight()) / 2.0f;
            if (this.mPosition > -1) {
                VideoGroup.this.TurnNextView();
                rotate3dAnimation = new Rotate3dAnimation(90.0f, 0.0f, width, height, 250.0f, false);
            } else {
                VideoGroup.this.TurnLastView();
                rotate3dAnimation = new Rotate3dAnimation(-90.0f, 0.0f, width, height, 250.0f, false);
            }
            rotate3dAnimation.setDuration(300);
            rotate3dAnimation.setFillAfter(true);
            rotate3dAnimation.setAnimationListener(new DisplaySwitchChannel());
            VideoGroup.this.mVideoGroup.startAnimation(rotate3dAnimation);
        }
    }

    public final class DisplaySwitchChannel implements Animation.AnimationListener {
        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }

        public DisplaySwitchChannel() {
        }

        public void onAnimationEnd(Animation animation) {
            if (VideoGroup.this.mLiveViewUi != null) {
                new Thread(new Runnable() {
                    public void run() {
                        VideoGroup.this.mLiveViewUi.SwitchPlay();
                    }
                }).start();
            }
        }
    }

    public void zoomIn() {
        int i = this.mCurViewCount;
        if (i != 1 || !this.mbPtz) {
            int i2 = this.mInitViewCount;
            if (i2 == 32) {
                if (i == 4) {
                    ArrayViews(1, false);
                } else if (i == 9) {
                    ArrayViews(4, false);
                } else if (i == 16) {
                    ArrayViews(9, false);
                }
            } else if (i2 == 16) {
                if (i == 4) {
                    ArrayViews(1, false);
                } else if (i == 9) {
                    ArrayViews(4, false);
                } else if (i == 16) {
                    ArrayViews(9, false);
                }
            } else if (i2 == 9) {
                if (i == 4) {
                    ArrayViews(1, false);
                } else if (i == 9) {
                    ArrayViews(4, false);
                }
            } else if (i2 == 4 && i == 4) {
                ArrayViews(1, false);
            }
            if (this.mbPtz && this.mCurViewCount == 1 && (this.mPtzState & (1 << this.mIndexList.get(this.mFocusIndex).intValue())) > 0) {
                SetPtzControlState(0);
            }
        } else if (this.mDvrNet != null && (this.mPtzState & (1 << this.mIndexList.get(this.mFocusIndex).intValue())) > 0) {
            this.mDvrNet.PTZControl(this.mIndexList.get(this.mFocusIndex).intValue(), 7, this.mLiveViewUi.mApp.mPtzSpeed);
            this.mLiveViewUi.setPtz(this.mIndexList.get(this.mFocusIndex).intValue(), 7);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.mDvrNet.PTZControl(this.mIndexList.get(this.mFocusIndex).intValue(), 20, this.mLiveViewUi.mApp.mPtzSpeed);
            this.mLiveViewUi.setPtz(this.mIndexList.get(this.mFocusIndex).intValue(), 20);
            SetPtzControlState(0);
        }
    }

    public void zoomOut() {
        if (this.mCurViewCount == 1 && this.mbPtz) {
            if (this.mDvrNet != null) {
                if ((this.mPtzState & (1 << this.mIndexList.get(this.mFocusIndex).intValue())) > 0) {
                    this.mDvrNet.PTZControl(this.mIndexList.get(this.mFocusIndex).intValue(), 8, this.mLiveViewUi.mApp.mPtzSpeed);
                    this.mLiveViewUi.setPtz(this.mIndexList.get(this.mFocusIndex).intValue(), 8);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.mDvrNet.PTZControl(this.mIndexList.get(this.mFocusIndex).intValue(), 20, this.mLiveViewUi.mApp.mPtzSpeed);
                    this.mLiveViewUi.setPtz(this.mIndexList.get(this.mFocusIndex).intValue(), 20);
                    SetPtzControlState(0);
                    return;
                }
            } else {
                return;
            }
        }
        int i = this.mInitViewCount;
        if (i == 32) {
            int i2 = this.mCurViewCount;
            if (i2 == 1) {
                ArrayViews(4, false);
            } else if (i2 == 4) {
                ArrayViews(9, false);
            } else if (i2 == 9) {
                ArrayViews(16, false);
            }
        } else if (i == 16) {
            int i3 = this.mCurViewCount;
            if (i3 == 1) {
                ArrayViews(4, false);
            } else if (i3 == 4) {
                ArrayViews(9, false);
            } else if (i3 == 9) {
                ArrayViews(16, false);
            }
        } else if (i == 9) {
            int i4 = this.mCurViewCount;
            if (i4 == 1) {
                ArrayViews(4, false);
            } else if (i4 == 4) {
                ArrayViews(9, false);
            }
        } else if (i == 4 && this.mCurViewCount == 1) {
            ArrayViews(4, false);
        }
        LiveViewUi liveViewUi = this.mLiveViewUi;
        if (liveViewUi != null) {
            liveViewUi.SetPtzState(false);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mGestureDetector.onTouchEvent(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    if (this.mbLongPress) {
                        dragging(motionEvent);
                    }
                    int i = this.mTouchMode;
                    if (i == 2) {
                        float distance = getDistance(motionEvent);
                        if (this.mCurViewCount == 1 && ((!this.mbPtz || (this.mPtzState & (1 << this.mIndexList.get(this.mFocusIndex).intValue())) <= 0) && distance > 0.0f)) {
                            this.matrix.set(this.savedMatrix);
                            float f = distance / this.mDignitalDistance;
                            this.mDignitalDistance = distance;
                            Log.e("getTop()", "" + getTop());
                            Log.e("getHeight()", "" + getHeight());
                            this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].setFatherW_H(getLeft(), getTop(), getRight(), getBottom(), getWidth(), getHeight());
                            this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].SetDigitalZoomIn(f);
                            Log.e("VideoGruop", "left:" + getLeft() + ",top:" + getTop() + ",right:" + getRight() + ",bottom:" + getBottom() + ",width:" + getWidth() + ",height:" + getHeight());
                        }
                    } else if (i == 1 && this.mCurViewCount == 1 && this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].getDigitalScales() != 1.0f) {
                        this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].SetDigitalTranslate(this.start.x - motionEvent.getX(), this.start.y - motionEvent.getY());
                        this.start.set(motionEvent.getX(), motionEvent.getY());
                        this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].onTouchMove(motionEvent);
                    }
                } else if (action == 5) {
                    this.mTouchMode = 2;
                    this.mDistance = getDistance(motionEvent);
                    this.mDignitalDistance = getDistance(motionEvent);
                    if (this.mCurViewCount == 1) {
                        this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].onPointerDown(motionEvent);
                    }
                } else if (action == 6) {
                    this.mTouchMode = 0;
                    if (this.mCurViewCount == 1) {
                        if (!this.mbPtz || (this.mPtzState & (1 << this.mIndexList.get(this.mFocusIndex).intValue())) <= 0) {
                            if (this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].getDigitalScales() <= 1.0f) {
                                this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].resetDigitalScales();
                            }
                        } else if (getDistance(motionEvent) - this.mDistance > 0.0f) {
                            zoomIn();
                        } else {
                            zoomOut();
                        }
                    }
                    if (getDistance(motionEvent) - this.mDistance > 0.0f) {
                        zoomIn();
                    } else {
                        zoomOut();
                    }
                }
            } else if (this.mbLongPress) {
                endDragging(motionEvent);
                this.mbLongPress = false;
            }
        } else if (!(this.mCurViewCount == 1 && this.mbPtz && (this.mPtzState & (1 << this.mIndexList.get(this.mFocusIndex).intValue())) > 0) && this.mCurViewCount == 1 && this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].getDigitalScales() > 1.0f) {
            this.mTouchMode = 1;
            this.start.set(motionEvent.getX(), motionEvent.getY());
            this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].onTouchDown(motionEvent);
        }
        return true;
    }

    public int getFocusView(PointF pointF) {
        boolean z = false;
        for (int i = 0; i < this.mInitViewCount; i++) {
            LvVideoFrame[] lvVideoFrameArr = this.mVideoFrame;
            if (lvVideoFrameArr[i] != null) {
                if (lvVideoFrameArr[i].getVisibility() != 0) {
                    this.mVideoFrame[i].SetFocusState(false);
                } else {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(i).intValue()].getLayoutParams();
                    if (new Rect(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + layoutParams.width, layoutParams.topMargin + layoutParams.height).contains((int) pointF.x, (int) pointF.y)) {
                        this.mFocusIndex = i;
                        this.mVideoFrame[this.mIndexList.get(i).intValue()].SetFocusState(true);
                        LiveViewUi liveViewUi = this.mLiveViewUi;
                        if (liveViewUi != null) {
                            liveViewUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                        }
                        z = true;
                    } else {
                        this.mVideoFrame[this.mIndexList.get(i).intValue()].SetFocusState(false);
                    }
                }
            }
        }
        if (!z) {
            this.mFocusIndex = 0;
        }
        return this.mFocusIndex;
    }

    public int getFirstIndex() {
        return this.mBaseIndex;
    }

    public int getInitMode() {
        return this.mInitViewCount;
    }

    public void SetFocusViewMax() {
        if (this.mCurViewCount == 1) {
            this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].resetDigitalScales();
            ArrayViews(this.mLastArrayMode, false);
        } else {
            ArrayViews(1, false);
        }
        if (this.mbPtz && this.mCurViewCount == 1 && (this.mPtzState & (1 << this.mIndexList.get(this.mFocusIndex).intValue())) > 0) {
            SetPtzControlState(0);
        }
    }

    public void TurnNextView() {
        this.mWidth = getWidth();
        this.mHeight = getHeight();
        int i = this.mInitViewCount;
        if (i == 32) {
            int i2 = this.mCurViewCount;
            if (i2 < i) {
                if (i2 == 16) {
                    int i3 = this.mBaseIndex;
                    if (i3 + 16 >= 32) {
                        this.mBaseIndex = 0;
                    } else {
                        this.mBaseIndex = i3 + 16;
                    }
                    for (int i4 = 0; i4 < this.mInitViewCount; i4++) {
                        int i5 = this.mBaseIndex;
                        if (i4 < i5 || i4 >= i5 + 16) {
                            this.mVideoFrame[this.mIndexList.get(i4).intValue()].setVisibility(8);
                            LiveViewUi liveViewUi = this.mLiveViewUi;
                            if (liveViewUi != null) {
                                liveViewUi.SetStreamDecodeState(this.mIndexList.get(i4).intValue(), true);
                            }
                        }
                    }
                    int i6 = this.mWidth / 4;
                    int i7 = this.mHeight / 4;
                    for (int i8 = 0; i8 < 4; i8++) {
                        for (int i9 = 0; i9 < 4; i9++) {
                            int i10 = (i8 * 4) + i9;
                            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i10).intValue()].getLayoutParams();
                            if (layoutParams == null) {
                                layoutParams = new RelativeLayout.LayoutParams(i6, i7);
                            }
                            layoutParams.width = i6;
                            layoutParams.height = i7;
                            layoutParams.leftMargin = i6 * i9;
                            layoutParams.topMargin = i7 * i8;
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i10).intValue()].setVisibility(0);
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i10).intValue()].setLayoutParams(layoutParams);
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i10).intValue()].SetMax(false);
                            LiveViewUi liveViewUi2 = this.mLiveViewUi;
                            if (liveViewUi2 != null) {
                                liveViewUi2.SetStreamDecodeState(this.mIndexList.get(i10 + this.mBaseIndex).intValue(), false);
                            }
                        }
                    }
                } else if (i2 == 9) {
                    this.mFlingStatus = 0;
                    ArrayViews(9, true);
                } else if (i2 == 4) {
                    int i11 = this.mBaseIndex;
                    if (i11 + 4 >= 32) {
                        this.mBaseIndex = 0;
                    } else {
                        this.mBaseIndex = i11 + 4;
                    }
                    for (int i12 = 0; i12 < this.mInitViewCount; i12++) {
                        int i13 = this.mBaseIndex;
                        if (i12 < i13 || i12 >= i13 + 4) {
                            this.mVideoFrame[this.mIndexList.get(i12).intValue()].setVisibility(8);
                            LiveViewUi liveViewUi3 = this.mLiveViewUi;
                            if (liveViewUi3 != null) {
                                liveViewUi3.SetStreamDecodeState(this.mIndexList.get(i12).intValue(), true);
                            }
                        }
                    }
                    int i14 = this.mWidth / 2;
                    int i15 = this.mHeight / 2;
                    for (int i16 = 0; i16 < 2; i16++) {
                        for (int i17 = 0; i17 < 2; i17++) {
                            int i18 = (i16 * 2) + i17;
                            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i18).intValue()].getLayoutParams();
                            if (layoutParams2 == null) {
                                layoutParams2 = new RelativeLayout.LayoutParams(i14, i15);
                            }
                            layoutParams2.width = i14;
                            layoutParams2.height = i15;
                            layoutParams2.leftMargin = i14 * i17;
                            layoutParams2.topMargin = i15 * i16;
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i18).intValue()].setVisibility(0);
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i18).intValue()].setLayoutParams(layoutParams2);
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i18).intValue()].SetMax(false);
                            LiveViewUi liveViewUi4 = this.mLiveViewUi;
                            if (liveViewUi4 != null) {
                                liveViewUi4.SetStreamDecodeState(this.mIndexList.get(i18 + this.mBaseIndex).intValue(), false);
                            }
                        }
                    }
                } else if (i2 == 1) {
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].SetFocusState(false);
                    this.mBaseIndex = (this.mBaseIndex + 1) % 32;
                    for (int i19 = 0; i19 < this.mInitViewCount; i19++) {
                        int i20 = this.mBaseIndex;
                        if (i19 < i20 || i19 >= i20 + this.mCurViewCount) {
                            this.mVideoFrame[this.mIndexList.get(i19).intValue()].setVisibility(8);
                            this.mVideoFrame[this.mIndexList.get(i19).intValue()].SetFocusState(false);
                            LiveViewUi liveViewUi5 = this.mLiveViewUi;
                            if (liveViewUi5 != null) {
                                liveViewUi5.SetStreamDecodeState(this.mIndexList.get(i19).intValue(), true);
                            }
                        } else {
                            this.mVideoFrame[this.mIndexList.get(i19).intValue()].SetFocusState(false);
                        }
                    }
                    int i21 = this.mWidth;
                    int i22 = this.mHeight;
                    RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].getLayoutParams();
                    if (layoutParams3 == null) {
                        layoutParams3 = new RelativeLayout.LayoutParams(i21, i22);
                    }
                    layoutParams3.width = i21;
                    layoutParams3.height = i22;
                    layoutParams3.leftMargin = 0;
                    layoutParams3.topMargin = 0;
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setVisibility(0);
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setLayoutParams(layoutParams3);
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].SetMax(true);
                    LiveViewUi liveViewUi6 = this.mLiveViewUi;
                    if (liveViewUi6 != null) {
                        liveViewUi6.SetStreamDecodeState(this.mIndexList.get(this.mBaseIndex).intValue(), false);
                    }
                }
                this.mFocusIndex = this.mBaseIndex;
                for (int i23 = 0; i23 < this.mInitViewCount; i23++) {
                    if (i23 == this.mFocusIndex) {
                        this.mVideoFrame[this.mIndexList.get(i23).intValue()].SetFocusState(true);
                        this.mLiveViewUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                    } else {
                        this.mVideoFrame[this.mIndexList.get(i23).intValue()].SetFocusState(false);
                    }
                }
            } else {
                return;
            }
        } else if (i == 16) {
            int i24 = this.mCurViewCount;
            if (i24 < i) {
                if (i24 == 9) {
                    this.mFlingStatus = 0;
                    ArrayViews(9, true);
                } else if (i24 == 4) {
                    int i25 = this.mBaseIndex;
                    if (i25 + 4 >= 16) {
                        this.mBaseIndex = 0;
                    } else {
                        this.mBaseIndex = i25 + 4;
                    }
                    for (int i26 = 0; i26 < this.mInitViewCount; i26++) {
                        int i27 = this.mBaseIndex;
                        if (i26 < i27 || i26 >= i27 + 4) {
                            this.mVideoFrame[this.mIndexList.get(i26).intValue()].setVisibility(8);
                            LiveViewUi liveViewUi7 = this.mLiveViewUi;
                            if (liveViewUi7 != null) {
                                liveViewUi7.SetStreamDecodeState(this.mIndexList.get(i26).intValue(), true);
                            }
                        }
                    }
                    int i28 = this.mWidth / 2;
                    int i29 = this.mHeight / 2;
                    for (int i30 = 0; i30 < 2; i30++) {
                        for (int i31 = 0; i31 < 2; i31++) {
                            int i32 = (i30 * 2) + i31;
                            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i32).intValue()].getLayoutParams();
                            if (layoutParams4 == null) {
                                layoutParams4 = new RelativeLayout.LayoutParams(i28, i29);
                            }
                            layoutParams4.width = i28;
                            layoutParams4.height = i29;
                            layoutParams4.leftMargin = i28 * i31;
                            layoutParams4.topMargin = i29 * i30;
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i32).intValue()].setVisibility(0);
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i32).intValue()].setLayoutParams(layoutParams4);
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i32).intValue()].SetMax(false);
                            LiveViewUi liveViewUi8 = this.mLiveViewUi;
                            if (liveViewUi8 != null) {
                                liveViewUi8.SetStreamDecodeState(this.mIndexList.get(i32 + this.mBaseIndex).intValue(), false);
                            }
                        }
                    }
                } else if (i24 == 1) {
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].SetFocusState(false);
                    this.mBaseIndex = (this.mBaseIndex + 1) % 16;
                    for (int i33 = 0; i33 < this.mInitViewCount; i33++) {
                        int i34 = this.mBaseIndex;
                        if (i33 < i34 || i33 >= i34 + this.mCurViewCount) {
                            this.mVideoFrame[this.mIndexList.get(i33).intValue()].setVisibility(8);
                            this.mVideoFrame[this.mIndexList.get(i33).intValue()].SetFocusState(false);
                            LiveViewUi liveViewUi9 = this.mLiveViewUi;
                            if (liveViewUi9 != null) {
                                liveViewUi9.SetStreamDecodeState(this.mIndexList.get(i33).intValue(), true);
                            }
                        } else {
                            this.mVideoFrame[this.mIndexList.get(i33).intValue()].SetFocusState(false);
                        }
                    }
                    int i35 = this.mWidth;
                    int i36 = this.mHeight;
                    RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].getLayoutParams();
                    if (layoutParams5 == null) {
                        layoutParams5 = new RelativeLayout.LayoutParams(i35, i36);
                    }
                    layoutParams5.width = i35;
                    layoutParams5.height = i36;
                    layoutParams5.leftMargin = 0;
                    layoutParams5.topMargin = 0;
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setVisibility(0);
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setLayoutParams(layoutParams5);
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].SetMax(true);
                    LiveViewUi liveViewUi10 = this.mLiveViewUi;
                    if (liveViewUi10 != null) {
                        liveViewUi10.SetStreamDecodeState(this.mIndexList.get(this.mBaseIndex).intValue(), false);
                    }
                }
                this.mFocusIndex = this.mBaseIndex;
                for (int i37 = 0; i37 < this.mInitViewCount; i37++) {
                    int i38 = this.mFocusIndex;
                    if (i37 == i38) {
                        this.mVideoFrame[this.mIndexList.get(i38).intValue()].SetFocusState(true);
                        this.mLiveViewUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                    } else {
                        this.mVideoFrame[this.mIndexList.get(i37).intValue()].SetFocusState(false);
                    }
                }
            } else {
                return;
            }
        } else if (i == 9) {
            int i39 = this.mCurViewCount;
            if (i39 < i) {
                if (i39 == 4) {
                    ArrayViews(4, true);
                } else if (i39 == 1) {
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].SetFocusState(false);
                    this.mBaseIndex = (this.mBaseIndex + 1) % 8;
                    for (int i40 = 0; i40 < this.mInitViewCount; i40++) {
                        int i41 = this.mBaseIndex;
                        if (i40 < i41 || i40 >= i41 + this.mCurViewCount) {
                            this.mVideoFrame[this.mIndexList.get(i40).intValue()].setVisibility(8);
                            this.mVideoFrame[this.mIndexList.get(i40).intValue()].SetFocusState(false);
                            LiveViewUi liveViewUi11 = this.mLiveViewUi;
                            if (liveViewUi11 != null) {
                                liveViewUi11.SetStreamDecodeState(this.mIndexList.get(i40).intValue(), true);
                            }
                        } else {
                            this.mVideoFrame[this.mIndexList.get(i40).intValue()].SetFocusState(false);
                        }
                    }
                    int i42 = this.mWidth;
                    int i43 = this.mHeight;
                    RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].getLayoutParams();
                    if (layoutParams6 == null) {
                        layoutParams6 = new RelativeLayout.LayoutParams(i42, i43);
                    }
                    layoutParams6.width = i42;
                    layoutParams6.height = i43;
                    layoutParams6.leftMargin = 0;
                    layoutParams6.topMargin = 0;
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setVisibility(0);
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setLayoutParams(layoutParams6);
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].SetMax(true);
                    LiveViewUi liveViewUi12 = this.mLiveViewUi;
                    if (liveViewUi12 != null) {
                        liveViewUi12.SetStreamDecodeState(this.mIndexList.get(this.mBaseIndex).intValue(), false);
                    }
                }
                this.mFocusIndex = this.mBaseIndex;
                for (int i44 = 0; i44 < this.mInitViewCount; i44++) {
                    int i45 = this.mFocusIndex;
                    if (i44 == i45) {
                        this.mVideoFrame[this.mIndexList.get(i45).intValue()].SetFocusState(true);
                        this.mLiveViewUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                    } else {
                        this.mVideoFrame[this.mIndexList.get(i44).intValue()].SetFocusState(false);
                    }
                }
            } else {
                return;
            }
        } else if (i == 4) {
            int i46 = this.mCurViewCount;
            if (i46 < i) {
                if (i46 == 1) {
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].SetFocusState(false);
                    this.mBaseIndex = (this.mBaseIndex + 1) % 4;
                    for (int i47 = 0; i47 < this.mInitViewCount; i47++) {
                        this.mVideoFrame[this.mIndexList.get(i47).intValue()].SetFocusState(false);
                        if (i47 != this.mBaseIndex) {
                            this.mVideoFrame[this.mIndexList.get(i47).intValue()].setVisibility(8);
                            LiveViewUi liveViewUi13 = this.mLiveViewUi;
                            if (liveViewUi13 != null) {
                                liveViewUi13.SetStreamDecodeState(this.mIndexList.get(i47).intValue(), true);
                            }
                        }
                    }
                    int i48 = this.mWidth;
                    int i49 = this.mHeight;
                    RelativeLayout.LayoutParams layoutParams7 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].getLayoutParams();
                    if (layoutParams7 == null) {
                        layoutParams7 = new RelativeLayout.LayoutParams(i48, i49);
                    }
                    layoutParams7.width = i48;
                    layoutParams7.height = i49;
                    layoutParams7.leftMargin = 0;
                    layoutParams7.topMargin = 0;
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setVisibility(0);
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setLayoutParams(layoutParams7);
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].SetMax(true);
                    LiveViewUi liveViewUi14 = this.mLiveViewUi;
                    if (liveViewUi14 != null) {
                        liveViewUi14.SetStreamDecodeState(this.mIndexList.get(this.mBaseIndex).intValue(), false);
                    }
                }
                this.mFocusIndex = this.mBaseIndex;
                for (int i50 = 0; i50 < this.mInitViewCount; i50++) {
                    int i51 = this.mFocusIndex;
                    if (i50 == i51) {
                        this.mVideoFrame[this.mIndexList.get(i51).intValue()].SetFocusState(true);
                        this.mLiveViewUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                    } else {
                        this.mVideoFrame[this.mIndexList.get(i50).intValue()].SetFocusState(false);
                    }
                }
            } else {
                return;
            }
        } else if (i == 1) {
            if (this.mCurViewCount < i) {
                for (int i52 = 0; i52 < this.mInitViewCount; i52++) {
                    this.mVideoFrame[this.mIndexList.get(i52).intValue()].SetFocusState(false);
                    int i53 = this.mBaseIndex;
                    if (i52 < i53 || i52 >= i53 + this.mCurViewCount) {
                        this.mVideoFrame[this.mIndexList.get(i52).intValue()].setVisibility(8);
                        LiveViewUi liveViewUi15 = this.mLiveViewUi;
                        if (liveViewUi15 != null) {
                            liveViewUi15.SetStreamDecodeState(this.mIndexList.get(i52).intValue(), true);
                        }
                    }
                }
            } else {
                return;
            }
        }
        if (this.mCurViewCount != 1 || !this.mbPtz) {
            SetPtzControlState(8);
        } else if ((this.mPtzState & (1 << this.mIndexList.get(this.mFocusIndex).intValue())) > 0) {
            SetPtzControlState(0);
        } else {
            SetPtzControlState(8);
        }
    }

    public void TurnLastView() {
        int i = this.mInitViewCount;
        if (i != 1) {
            if (i == 32) {
                int i2 = this.mCurViewCount;
                if (i2 < i) {
                    if (i2 == 16) {
                        int i3 = this.mBaseIndex;
                        if (i3 - 16 < 0) {
                            this.mBaseIndex = 16;
                        } else {
                            this.mBaseIndex = i3 - 16;
                        }
                        for (int i4 = 0; i4 < this.mInitViewCount; i4++) {
                            int i5 = this.mBaseIndex;
                            if (i4 < i5 || i4 >= i5 - 16) {
                                this.mVideoFrame[this.mIndexList.get(i4).intValue()].setVisibility(8);
                                LiveViewUi liveViewUi = this.mLiveViewUi;
                                if (liveViewUi != null) {
                                    liveViewUi.SetStreamDecodeState(this.mIndexList.get(i4).intValue(), true);
                                }
                            }
                        }
                        int i6 = this.mWidth / 4;
                        int i7 = this.mHeight / 4;
                        for (int i8 = 0; i8 < 4; i8++) {
                            for (int i9 = 0; i9 < 4; i9++) {
                                int i10 = (i8 * 4) + i9;
                                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i10).intValue()].getLayoutParams();
                                if (layoutParams == null) {
                                    layoutParams = new RelativeLayout.LayoutParams(i6, i7);
                                }
                                layoutParams.width = i6;
                                layoutParams.height = i7;
                                layoutParams.leftMargin = i6 * i9;
                                layoutParams.topMargin = i7 * i8;
                                this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i10).intValue()].setVisibility(0);
                                this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i10).intValue()].setLayoutParams(layoutParams);
                                this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i10).intValue()].SetMax(false);
                                LiveViewUi liveViewUi2 = this.mLiveViewUi;
                                if (liveViewUi2 != null) {
                                    liveViewUi2.SetStreamDecodeState(this.mIndexList.get(i10 + this.mBaseIndex).intValue(), false);
                                }
                            }
                        }
                    } else if (i2 == 9) {
                        this.mFlingStatus = 1;
                        ArrayViews(9, true);
                    } else if (i2 == 4) {
                        int i11 = this.mBaseIndex;
                        if (i11 - 4 < 0) {
                            this.mBaseIndex = 28;
                        } else {
                            this.mBaseIndex = i11 - 4;
                        }
                        for (int i12 = 0; i12 < this.mInitViewCount; i12++) {
                            int i13 = this.mBaseIndex;
                            if (i12 < i13 || i12 >= i13 + 4) {
                                this.mVideoFrame[this.mIndexList.get(i12).intValue()].setVisibility(8);
                                LiveViewUi liveViewUi3 = this.mLiveViewUi;
                                if (liveViewUi3 != null) {
                                    liveViewUi3.SetStreamDecodeState(this.mIndexList.get(i12).intValue(), true);
                                }
                            }
                        }
                        int i14 = this.mWidth / 2;
                        int i15 = this.mHeight / 2;
                        for (int i16 = 0; i16 < 2; i16++) {
                            for (int i17 = 0; i17 < 2; i17++) {
                                int i18 = (i16 * 2) + i17;
                                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i18).intValue()].getLayoutParams();
                                if (layoutParams2 == null) {
                                    layoutParams2 = new RelativeLayout.LayoutParams(i14, i15);
                                }
                                layoutParams2.width = i14;
                                layoutParams2.height = i15;
                                layoutParams2.leftMargin = i14 * i17;
                                layoutParams2.topMargin = i15 * i16;
                                this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i18).intValue()].setVisibility(0);
                                this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i18).intValue()].setLayoutParams(layoutParams2);
                                this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i18).intValue()].SetMax(false);
                                LiveViewUi liveViewUi4 = this.mLiveViewUi;
                                if (liveViewUi4 != null) {
                                    liveViewUi4.SetStreamDecodeState(this.mIndexList.get(i18 + this.mBaseIndex).intValue(), false);
                                }
                            }
                        }
                    } else if (i2 == 1) {
                        int i19 = this.mBaseIndex;
                        if (i19 - 1 < 0) {
                            this.mBaseIndex = 31;
                        } else {
                            this.mBaseIndex = (i19 - 1) % 32;
                        }
                        for (int i20 = 0; i20 < this.mInitViewCount; i20++) {
                            int i21 = this.mBaseIndex;
                            if (i20 < i21 || i20 >= i21 + this.mCurViewCount) {
                                this.mVideoFrame[this.mIndexList.get(i20).intValue()].setVisibility(8);
                                LiveViewUi liveViewUi5 = this.mLiveViewUi;
                                if (liveViewUi5 != null) {
                                    liveViewUi5.SetStreamDecodeState(this.mIndexList.get(i20).intValue(), true);
                                }
                            } else {
                                this.mVideoFrame[this.mIndexList.get(i20).intValue()].SetFocusState(false);
                            }
                        }
                        int i22 = this.mWidth;
                        int i23 = this.mHeight;
                        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].getLayoutParams();
                        if (layoutParams3 == null) {
                            layoutParams3 = new RelativeLayout.LayoutParams(i22, i23);
                        }
                        layoutParams3.width = i22;
                        layoutParams3.height = i23;
                        layoutParams3.leftMargin = 0;
                        layoutParams3.topMargin = 0;
                        this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setVisibility(0);
                        this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setLayoutParams(layoutParams3);
                        this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].SetMax(true);
                        LiveViewUi liveViewUi6 = this.mLiveViewUi;
                        if (liveViewUi6 != null) {
                            liveViewUi6.SetStreamDecodeState(this.mIndexList.get(this.mBaseIndex).intValue(), false);
                        }
                    }
                    this.mFocusIndex = this.mBaseIndex;
                    for (int i24 = 0; i24 < this.mInitViewCount; i24++) {
                        int i25 = this.mFocusIndex;
                        if (i24 == i25) {
                            this.mVideoFrame[this.mIndexList.get(i25).intValue()].SetFocusState(true);
                            this.mLiveViewUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                        } else {
                            this.mVideoFrame[this.mIndexList.get(i24).intValue()].SetFocusState(false);
                        }
                    }
                } else {
                    return;
                }
            } else if (i == 16) {
                int i26 = this.mCurViewCount;
                if (i26 < i) {
                    if (i26 == 9) {
                        this.mFlingStatus = 1;
                        ArrayViews(9, true);
                    } else if (i26 == 4) {
                        int i27 = this.mBaseIndex;
                        if (i27 - 4 < 0) {
                            this.mBaseIndex = 12;
                        } else {
                            this.mBaseIndex = i27 - 4;
                        }
                        for (int i28 = 0; i28 < this.mInitViewCount; i28++) {
                            int i29 = this.mBaseIndex;
                            if (i28 < i29 || i28 >= i29 + 4) {
                                this.mVideoFrame[this.mIndexList.get(i28).intValue()].setVisibility(8);
                                LiveViewUi liveViewUi7 = this.mLiveViewUi;
                                if (liveViewUi7 != null) {
                                    liveViewUi7.SetStreamDecodeState(this.mIndexList.get(i28).intValue(), true);
                                }
                            }
                        }
                        int i30 = this.mWidth / 2;
                        int i31 = this.mHeight / 2;
                        for (int i32 = 0; i32 < 2; i32++) {
                            for (int i33 = 0; i33 < 2; i33++) {
                                int i34 = (i32 * 2) + i33;
                                RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i34).intValue()].getLayoutParams();
                                if (layoutParams4 == null) {
                                    layoutParams4 = new RelativeLayout.LayoutParams(i30, i31);
                                }
                                layoutParams4.width = i30;
                                layoutParams4.height = i31;
                                layoutParams4.leftMargin = i30 * i33;
                                layoutParams4.topMargin = i31 * i32;
                                this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i34).intValue()].setVisibility(0);
                                this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i34).intValue()].setLayoutParams(layoutParams4);
                                this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i34).intValue()].SetMax(false);
                                LiveViewUi liveViewUi8 = this.mLiveViewUi;
                                if (liveViewUi8 != null) {
                                    liveViewUi8.SetStreamDecodeState(this.mIndexList.get(i34 + this.mBaseIndex).intValue(), false);
                                }
                            }
                        }
                    } else if (i26 == 1) {
                        int i35 = this.mBaseIndex;
                        if (i35 - 1 < 0) {
                            this.mBaseIndex = 15;
                        } else {
                            this.mBaseIndex = (i35 - 1) % 16;
                        }
                        for (int i36 = 0; i36 < this.mInitViewCount; i36++) {
                            int i37 = this.mBaseIndex;
                            if (i36 < i37 || i36 >= i37 + this.mCurViewCount) {
                                this.mVideoFrame[this.mIndexList.get(i36).intValue()].setVisibility(8);
                                LiveViewUi liveViewUi9 = this.mLiveViewUi;
                                if (liveViewUi9 != null) {
                                    liveViewUi9.SetStreamDecodeState(this.mIndexList.get(i36).intValue(), true);
                                }
                            } else {
                                this.mVideoFrame[this.mIndexList.get(i36).intValue()].SetFocusState(false);
                            }
                        }
                        int i38 = this.mWidth;
                        int i39 = this.mHeight;
                        RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].getLayoutParams();
                        if (layoutParams5 == null) {
                            layoutParams5 = new RelativeLayout.LayoutParams(i38, i39);
                        }
                        layoutParams5.width = i38;
                        layoutParams5.height = i39;
                        layoutParams5.leftMargin = 0;
                        layoutParams5.topMargin = 0;
                        this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setVisibility(0);
                        this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setLayoutParams(layoutParams5);
                        this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].SetMax(true);
                        LiveViewUi liveViewUi10 = this.mLiveViewUi;
                        if (liveViewUi10 != null) {
                            liveViewUi10.SetStreamDecodeState(this.mIndexList.get(this.mBaseIndex).intValue(), false);
                        }
                    }
                    this.mFocusIndex = this.mBaseIndex;
                    for (int i40 = 0; i40 < this.mInitViewCount; i40++) {
                        int i41 = this.mFocusIndex;
                        if (i40 == i41) {
                            this.mVideoFrame[this.mIndexList.get(i41).intValue()].SetFocusState(true);
                            this.mLiveViewUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                        } else {
                            this.mVideoFrame[this.mIndexList.get(i40).intValue()].SetFocusState(false);
                        }
                    }
                } else {
                    return;
                }
            } else if (i == 9) {
                int i42 = this.mCurViewCount;
                if (i42 < i) {
                    if (i42 == 4) {
                        ArrayViews(4, true);
                    } else if (i42 == 1) {
                        int i43 = this.mBaseIndex;
                        if (i43 - 1 < 0) {
                            this.mBaseIndex = 7;
                        } else {
                            this.mBaseIndex = (i43 - 1) % 8;
                        }
                        for (int i44 = 0; i44 < this.mInitViewCount; i44++) {
                            int i45 = this.mBaseIndex;
                            if (i44 < i45 || i44 >= i45 + this.mCurViewCount) {
                                this.mVideoFrame[this.mIndexList.get(i44).intValue()].setVisibility(8);
                                LiveViewUi liveViewUi11 = this.mLiveViewUi;
                                if (liveViewUi11 != null) {
                                    liveViewUi11.SetStreamDecodeState(this.mIndexList.get(i44).intValue(), true);
                                }
                            } else {
                                this.mVideoFrame[this.mIndexList.get(i44).intValue()].SetFocusState(false);
                            }
                        }
                        int i46 = this.mWidth;
                        int i47 = this.mHeight;
                        RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].getLayoutParams();
                        if (layoutParams6 == null) {
                            layoutParams6 = new RelativeLayout.LayoutParams(i46, i47);
                        }
                        layoutParams6.width = i46;
                        layoutParams6.height = i47;
                        layoutParams6.leftMargin = 0;
                        layoutParams6.topMargin = 0;
                        this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setVisibility(0);
                        this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setLayoutParams(layoutParams6);
                        this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].SetMax(true);
                        LiveViewUi liveViewUi12 = this.mLiveViewUi;
                        if (liveViewUi12 != null) {
                            liveViewUi12.SetStreamDecodeState(this.mIndexList.get(this.mBaseIndex).intValue(), false);
                        }
                    }
                    this.mFocusIndex = this.mBaseIndex;
                    for (int i48 = 0; i48 < this.mInitViewCount; i48++) {
                        int i49 = this.mFocusIndex;
                        if (i48 == i49) {
                            this.mVideoFrame[this.mIndexList.get(i49).intValue()].SetFocusState(true);
                            this.mLiveViewUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                        } else {
                            this.mVideoFrame[this.mIndexList.get(i48).intValue()].SetFocusState(false);
                        }
                    }
                } else {
                    return;
                }
            } else if (i == 4) {
                int i50 = this.mCurViewCount;
                if (i50 < i) {
                    if (i50 == 1) {
                        int i51 = this.mBaseIndex;
                        if (i51 - 1 < 0) {
                            this.mBaseIndex = 3;
                        } else {
                            this.mBaseIndex = i51 - 1;
                        }
                        for (int i52 = 0; i52 < this.mInitViewCount; i52++) {
                            this.mVideoFrame[this.mIndexList.get(i52).intValue()].SetFocusState(false);
                            if (i52 != this.mBaseIndex) {
                                this.mVideoFrame[this.mIndexList.get(i52).intValue()].setVisibility(8);
                                LiveViewUi liveViewUi13 = this.mLiveViewUi;
                                if (liveViewUi13 != null) {
                                    liveViewUi13.SetStreamDecodeState(this.mIndexList.get(i52).intValue(), true);
                                }
                            }
                        }
                        int i53 = this.mWidth;
                        int i54 = this.mHeight;
                        RelativeLayout.LayoutParams layoutParams7 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].getLayoutParams();
                        if (layoutParams7 == null) {
                            layoutParams7 = new RelativeLayout.LayoutParams(i53, i54);
                        }
                        layoutParams7.width = i53;
                        layoutParams7.height = i54;
                        layoutParams7.leftMargin = 0;
                        layoutParams7.topMargin = 0;
                        this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setVisibility(0);
                        this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setLayoutParams(layoutParams7);
                        this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].SetMax(true);
                        LiveViewUi liveViewUi14 = this.mLiveViewUi;
                        if (liveViewUi14 != null) {
                            liveViewUi14.SetStreamDecodeState(this.mIndexList.get(this.mBaseIndex).intValue(), false);
                        }
                    }
                    this.mFocusIndex = this.mBaseIndex;
                    for (int i55 = 0; i55 < this.mInitViewCount; i55++) {
                        int i56 = this.mFocusIndex;
                        if (i55 == i56) {
                            this.mVideoFrame[this.mIndexList.get(i56).intValue()].SetFocusState(true);
                            this.mLiveViewUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                        } else {
                            this.mVideoFrame[this.mIndexList.get(i55).intValue()].SetFocusState(false);
                        }
                    }
                } else {
                    return;
                }
            } else if (i == 1 && this.mCurViewCount >= i) {
                return;
            }
            if (this.mCurViewCount == 1 && this.mbPtz) {
                if ((this.mPtzState & (1 << this.mIndexList.get(this.mFocusIndex).intValue())) > 0) {
                    SetPtzControlState(0);
                } else {
                    SetPtzControlState(8);
                }
            }
        }
    }

    public void startDragging(MotionEvent motionEvent) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.mSourceParams.width, this.mSourceParams.height);
        layoutParams.width += 30;
        layoutParams.height += 30;
        layoutParams.leftMargin = (int) (motionEvent.getX() - ((float) (layoutParams.width / 2)));
        layoutParams.topMargin = (int) (motionEvent.getY() - ((float) (layoutParams.height / 2)));
        this.mVideoFrame[this.mIndexList.get(this.mSourceIndex).intValue()].setLayoutParams(layoutParams);
        this.mVideoGroup.bringChildToFront(this.mVideoFrame[this.mIndexList.get(this.mSourceIndex).intValue()]);
        this.mVideoFrame[this.mIndexList.get(this.mSourceIndex).intValue()].requestFocus();
        this.mVideoFrame[this.mIndexList.get(this.mSourceIndex).intValue()].bringToFront();
        findDestination(motionEvent);
    }

    public void dragging(MotionEvent motionEvent) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mSourceIndex).intValue()].getLayoutParams();
        layoutParams.leftMargin = (int) (motionEvent.getX() - ((float) (layoutParams.width / 2)));
        layoutParams.topMargin = (int) (motionEvent.getY() - ((float) (layoutParams.height / 2)));
        this.mVideoFrame[this.mIndexList.get(this.mSourceIndex).intValue()].setLayoutParams(layoutParams);
        findDestination(motionEvent);
    }

    public void endDragging(MotionEvent motionEvent) {
        int i = this.mSourceIndex;
        if (i != this.mDestinationIndex) {
            this.mVideoFrame[this.mIndexList.get(i).intValue()].setLayoutParams(this.mDestinationParams);
            this.mVideoFrame[this.mIndexList.get(this.mDestinationIndex).intValue()].setLayoutParams(this.mSourceParams);
            int intValue = this.mIndexList.get(this.mSourceIndex).intValue();
            this.mIndexList.set(this.mSourceIndex, Integer.valueOf(this.mIndexList.get(this.mDestinationIndex).intValue()));
            this.mIndexList.set(this.mDestinationIndex, Integer.valueOf(intValue));
        } else {
            this.mVideoFrame[this.mIndexList.get(i).intValue()].setLayoutParams(this.mSourceParams);
        }
        for (int i2 = this.mBaseIndex; i2 < this.mBaseIndex + this.mCurViewCount; i2++) {
            this.mVideoFrame[this.mIndexList.get(i2).intValue()].setDragState(false);
        }
    }

    public void findDestination(MotionEvent motionEvent) {
        boolean z = false;
        for (int i = this.mBaseIndex; i < this.mCurViewCount + this.mBaseIndex; i++) {
            if (i != this.mSourceIndex) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(i).intValue()].getLayoutParams();
                if (new Rect(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + layoutParams.width, layoutParams.topMargin + layoutParams.height).contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                    this.mDestinationIndex = i;
                    this.mDestinationParams = layoutParams;
                    this.mVideoFrame[this.mIndexList.get(i).intValue()].setDragState(true);
                    z = true;
                } else {
                    this.mVideoFrame[this.mIndexList.get(i).intValue()].setDragState(false);
                }
            }
        }
        if (!z) {
            this.mDestinationIndex = this.mSourceIndex;
            this.mDestinationParams = this.mSourceParams;
        }
    }

    public boolean SetPtzMode(boolean z, int i, DvrNet dvrNet) {
        if (!z || this.mCurViewCount == 1) {
            this.mbPtz = z;
            this.mPtzState = i;
            this.mDvrNet = dvrNet;
            if (!z) {
                SetPtzControlState(8);
                this.mLiveViewUi.SetPtzState(false);
            } else if (((1 << this.mIndexList.get(this.mFocusIndex).intValue()) & i) > 0) {
                SetPtzControlState(0);
                this.mLiveViewUi.SetPtzState(true);
            }
            return true;
        }
        this.mLiveViewUi.SetPtzState(false);
        return false;
    }

    public float getDistance(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }

    public float getDistance(float f, float f2) {
        return (float) Math.sqrt((double) ((f * f) + (f2 * f2)));
    }

    private class FlingGestureDetector extends GestureDetector.SimpleOnGestureListener {
        public boolean mbMultiTouch;

        private FlingGestureDetector() {
            this.mbMultiTouch = false;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (motionEvent2.getPointerCount() > 1) {
                this.mbMultiTouch = true;
            }
            return super.onScroll(motionEvent, motionEvent2, f, f2);
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (VideoGroup.this.mTouchMode != 0) {
                boolean unused = VideoGroup.this.mbLongPress = false;
            } else if (VideoGroup.this.mCurViewCount == 1) {
                boolean unused2 = VideoGroup.this.mbLongPress = false;
            } else {
                boolean unused3 = VideoGroup.this.mbLongPress = true;
                VideoGroup videoGroup = VideoGroup.this;
                int access$302 = videoGroup.mFocusIndex = videoGroup.getFocusView(new PointF(motionEvent.getX(), motionEvent.getY()));
                videoGroup.mDestinationIndex = access$302;
                videoGroup.mSourceIndex = access$302;
                VideoGroup.this.mSourceParams = (RelativeLayout.LayoutParams) VideoGroup.this.mVideoFrame[((Integer) VideoGroup.this.mIndexList.get(VideoGroup.this.mSourceIndex)).intValue()].getLayoutParams();
                VideoGroup.this.startDragging(motionEvent);
                super.onLongPress(motionEvent);
            }
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (this.mbMultiTouch) {
                this.mbMultiTouch = false;
                return super.onSingleTapConfirmed(motionEvent);
            }
            EventBus.getDefault().post(new ClickGroupEvent());
            VideoGroup.this.getFocusView(new PointF(motionEvent.getX(), motionEvent.getY()));
            return super.onSingleTapConfirmed(motionEvent);
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (this.mbMultiTouch) {
                this.mbMultiTouch = false;
            } else if (motionEvent.getX() - motionEvent2.getX() > 100.0f) {
                VideoGroup.this.FlingLeft(motionEvent.getX() - motionEvent2.getX());
            } else if (motionEvent.getX() - motionEvent2.getX() < -100.0f) {
                VideoGroup.this.FlingRight(motionEvent.getX() - motionEvent2.getX());
            }
            return super.onFling(motionEvent, motionEvent2, f, f2);
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            VideoGroup.this.getFocusView(new PointF(motionEvent.getX(), motionEvent.getY()));
            if (VideoGroup.this.mCurViewCount == 1 && VideoGroup.this.mbPtz) {
                VideoGroup.this.SetPtzControlState(8);
            }
            if (VideoGroup.this.mInitViewCount == 1) {
                return super.onDoubleTap(motionEvent);
            }
            Log.e("onDoubleTap", "onDoubleTap");
            VideoGroup.this.SetFocusViewMax();
            return super.onDoubleTap(motionEvent);
        }
    }

    public int getFocusView() {
        return this.mIndexList.get(this.mFocusIndex).intValue();
    }

    public int getInitViewCount() {
        return this.mInitViewCount;
    }

    public int getCurViewCount() {
        return this.mCurViewCount;
    }

    public int getVideoFrameVisibility(int i) {
        return this.mVideoFrame[this.mIndexList.get(i).intValue()].getVisibility();
    }
}
