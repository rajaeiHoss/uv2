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
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed) {
            postDelayed(new Runnable() {
                public void run() {
                    VideoGroup videoGroup = VideoGroup.this;
                    videoGroup.ArrayViews(videoGroup.mCurViewCount, true);
                }
            }, 100);
        }
        super.onLayout(changed, left, top, right, bottom);
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

    public VideoGroup(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        this.mContext = context;
        if (this.mGestureDetector == null) {
            this.mGestureDetector = new GestureDetector(this.mContext, new FlingGestureDetector());
        }
        this.mVideoGroup = this;
        LoadViews();
    }

    public void setCanFull(boolean fullscreen) {
        this.mbFull = fullscreen;
    }

    public void SetInitMode(int viewCount) {
        this.mLastArrayMode = viewCount;
        this.mCurViewCount = viewCount;
        this.mInitViewCount = viewCount;
        this.mIndexList.clear();
        for (int viewIndex = 0; viewIndex < this.mMax; viewIndex++) {
            this.mIndexList.add(viewIndex, Integer.valueOf(viewIndex));
        }
        this.mBaseIndex = 0;
        this.mFocusIndex = 0;
        for (int focusCandidateIndex = 0; focusCandidateIndex < this.mInitViewCount; focusCandidateIndex++) {
            if (focusCandidateIndex == this.mFocusIndex) {
                this.mVideoFrame[this.mIndexList.get(focusCandidateIndex).intValue()].SetFocusState(true);
            } else {
                this.mVideoFrame[this.mIndexList.get(focusCandidateIndex).intValue()].SetFocusState(false);
            }
        }
        if (this.mCurViewCount == 32) {
            this.mCurViewCount = 4;
        }
        ArrayViews(this.mCurViewCount, false);
    }

    public void setAdas(int viewIndex, boolean enabled) {
        this.mVideoFrame[viewIndex].mVideoView.setAdas(enabled);
    }

    public void setVideoInfo(int viewIndex, int width, int height) {
        this.mVideoFrame[viewIndex].mVideoView.setVideoInfo(width, height);
    }

    public void setAdasHorizon(int viewIndex, int horizon) {
        this.mVideoFrame[viewIndex].mVideoView.setAdasHorizon(horizon);
    }

    public void setAdasVertical(int viewIndex, int vertical) {
        this.mVideoFrame[viewIndex].mVideoView.setAdasVertical(vertical);
    }

    public void setFocusState(int viewIndex, boolean focused) {
        this.mVideoFrame[viewIndex].mVideoView.setFocusState(focused);
        this.mVideoFrame[viewIndex].mCusImageView.setFocusState(focused);
    }

    public void setOsdState(int viewIndex, boolean enabled) {
        this.mVideoFrame[viewIndex].mVideoView.setOsdState(enabled);
        this.mVideoFrame[viewIndex].mCusImageView.setOsdState(enabled);
    }

    public int GetLayoutMode() {
        return this.mInitViewCount;
    }

    public int GetCurArrayMode() {
        return this.mCurViewCount;
    }

    public List<Integer> GetChannelList() {
        ArrayList arrayList = new ArrayList();
        for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
            LvVideoFrame[] lvVideoFrameArr = this.mVideoFrame;
            if (lvVideoFrameArr[viewIndex] != null && lvVideoFrameArr[viewIndex].getVisibility() == 0) {
                arrayList.add(Integer.valueOf(this.mIndexList.get(viewIndex).intValue()));
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
        int viewIndex = 0;
        while (viewIndex < this.mMax) {
            int displayIndex = viewIndex + 1;
            this.mVideoFrame[viewIndex] = new LvVideoFrame(this.mContext, displayIndex, this.mbFull);
            this.mVideoFrame[viewIndex].setId(viewIndex);
            this.mVideoFrame[viewIndex].setVisibility(8);
            this.mVideoFrame[viewIndex].setClickable(false);
            this.mVideoFrame[viewIndex].setFocusable(false);
            this.mVideoFrame[viewIndex].setFocusableInTouchMode(false);
            this.mVideoFrame[viewIndex].setHapticFeedbackEnabled(false);
            addView(this.mVideoFrame[viewIndex]);
            this.mIndexList.add(viewIndex, Integer.valueOf(viewIndex));
            viewIndex = displayIndex;
        }
        this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].SetFocusState(true);
        this.mOnclickListener = new View.OnClickListener() {
            public void onClick(View view) {
                final int ptzCommand;
                switch (view.getId()) {
                    case R.id.ptz_control_down /*2131362923*/:
                        ptzCommand = 2;
                        break;
                    case R.id.ptz_control_left /*2131362924*/:
                        ptzCommand = 3;
                        break;
                    case R.id.ptz_control_right /*2131362926*/:
                        ptzCommand = 4;
                        break;
                    case R.id.ptz_control_up /*2131362927*/:
                        ptzCommand = 1;
                        break;
                    default:
                        ptzCommand = 0;
                        break;
                }
                if (VideoGroup.this.mDvrNet != null && VideoGroup.this.mCurViewCount == 1) {
                    new Thread(new Runnable() {
                        public void run() {
                            VideoGroup.this.mDvrNet.PTZControl(((Integer) VideoGroup.this.mIndexList.get(VideoGroup.this.mFocusIndex)).intValue(), ptzCommand, VideoGroup.this.mLiveViewUi.mApp.mPtzSpeed);
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
        int[] ptzControlIds = {R.id.ptz_control_left, R.id.ptz_control_right, R.id.ptz_control_up, R.id.ptz_control_down};
        for (int controlIndex = 0; controlIndex < 4; controlIndex++) {
            ImageView imageView = (ImageView) findViewById(ptzControlIds[controlIndex]);
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

    public void showFull(boolean fullscreen) {
        for (int viewIndex = 0; viewIndex < this.mMax; viewIndex++) {
            this.mVideoFrame[viewIndex].showFull(fullscreen);
        }
    }

    public void SetPlayState(int viewIndex, boolean playing) {
        this.mVideoFrame[viewIndex].SetPlayState(playing);
    }

    public void SetBusyState(int viewIndex, boolean busy) {
        this.mVideoFrame[viewIndex].SetBusyState(busy);
    }

    public void SetRecState(int viewIndex, boolean recording) {
        this.mVideoFrame[viewIndex].SetRecState(recording);
    }

    public void ClearViews() {
        for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
            this.mVideoFrame[viewIndex].ClearViews();
        }
    }

    public void ArrayViews(int targetViewCount, boolean preserveLastArrayMode) {
        int layoutIndex;
        if (targetViewCount <= 0 || this.mVideoFrame == null) {
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
        if (!preserveLastArrayMode) {
            this.mLastArrayMode = this.mCurViewCount;
        }
        int visibleItemCount = this.mInitViewCount > 0 ? this.mInitViewCount : this.mIndexList.size();
        int baseIndex = this.mBaseIndex;
        if (baseIndex < 0) {
            baseIndex = 0;
        }
        if (baseIndex + targetViewCount > visibleItemCount) {
            baseIndex = 0;
        }
        this.mBaseIndex = baseIndex;
        this.mCurViewCount = targetViewCount;
        int columns = targetViewCount == 1 ? 1 : targetViewCount == 4 ? 2 : targetViewCount == 9 ? 3 : targetViewCount == 16 ? 4 : (int) Math.ceil(Math.sqrt((double) targetViewCount));
        int rows = (int) Math.ceil(((double) targetViewCount) / ((double) columns));
        int cellWidth = this.mWidth / columns;
        int cellHeight = this.mHeight / rows;
        for (int listIndex = 0; listIndex < visibleItemCount; listIndex++) {
            int frameIndex = this.mIndexList.get(listIndex).intValue();
            if (listIndex < this.mBaseIndex || listIndex >= this.mBaseIndex + targetViewCount) {
                LvVideoFrame lvVideoFrame = this.mVideoFrame[frameIndex];
                if (lvVideoFrame != null) {
                    lvVideoFrame.setVisibility(8);
                }
                LiveViewUi liveViewUi = this.mLiveViewUi;
                if (liveViewUi != null) {
                    liveViewUi.SetStreamDecodeState(frameIndex, true);
                }
            }
        }
        int visibleIndex = 0;
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
                if (visibleIndex >= targetViewCount || (layoutIndex = this.mBaseIndex + visibleIndex) >= visibleItemCount) {
                    break;
                }
                int frameIndex = this.mIndexList.get(layoutIndex).intValue();
                LvVideoFrame lvVideoFrame2 = this.mVideoFrame[frameIndex];
                if (lvVideoFrame2 != null) {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) lvVideoFrame2.getLayoutParams();
                    if (layoutParams == null) {
                        layoutParams = new RelativeLayout.LayoutParams(cellWidth, cellHeight);
                    }
                    layoutParams.width = cellWidth;
                    layoutParams.height = cellHeight;
                    layoutParams.leftMargin = cellWidth * columnIndex;
                    layoutParams.topMargin = cellHeight * rowIndex;
                    lvVideoFrame2.setLayoutParams(layoutParams);
                    lvVideoFrame2.setVisibility(0);
                    lvVideoFrame2.SetMax(targetViewCount == 1);
                }
                LiveViewUi liveViewUi2 = this.mLiveViewUi;
                if (liveViewUi2 != null) {
                    liveViewUi2.SetStreamDecodeState(frameIndex, false);
                }
                visibleIndex++;
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

    public void SetPtzControlState(int visibility) {
        LiveViewUi liveViewUi;
        ImageView imageView;
        int[] ptzControlIds = {R.id.ptz_control_left, R.id.ptz_control_right, R.id.ptz_control_up, R.id.ptz_control_down, R.id.ptz_control_line};
        for (int controlIndex = 0; controlIndex < 5; controlIndex++) {
            if ((ptzControlIds[controlIndex] != R.id.ptz_control_line || this.mLiveViewUi.mApp.mbCurise) && (imageView = (ImageView) findViewById(ptzControlIds[controlIndex])) != null) {
                if (imageView.getVisibility() != visibility) {
                    imageView.setVisibility(visibility);
                }
                imageView.bringToFront();
            }
        }
        if (visibility == 8 || visibility == 4) {
            LiveViewUi liveViewUi2 = this.mLiveViewUi;
            if (liveViewUi2 != null) {
                liveViewUi2.SetPtzState(false);
            }
        } else if (visibility == 0 && (liveViewUi = this.mLiveViewUi) != null) {
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

    public VideoView getVideoView(int viewIndex) {
        return this.mVideoFrame[viewIndex].GetVideoView();
    }

    public int GetCurrentMode() {
        return this.mCurViewCount;
    }

    public void FlingRight(float distance) {
        if ((this.mCurViewCount != 1 || this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].getDigitalScales() == 1.0f) && this.mInitViewCount != this.mCurViewCount) {
            applyRotation(-1, 0.0f, 90.0f);
        }
    }

    public void FlingLeft(float distance) {
        if ((this.mCurViewCount != 1 || this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].getDigitalScales() == 1.0f) && this.mInitViewCount != this.mCurViewCount) {
            applyRotation(1, 0.0f, -90.0f);
        }
    }

    private void applyRotation(int position, float startDegrees, float endDegrees) {
        Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(startDegrees, endDegrees, ((float) this.mVideoGroup.getWidth()) / 2.0f, ((float) this.mVideoGroup.getHeight()) / 2.0f, 250.0f, true);
        rotate3dAnimation.setDuration(500);
        rotate3dAnimation.setFillAfter(true);
        rotate3dAnimation.setInterpolator(new AccelerateInterpolator());
        rotate3dAnimation.setAnimationListener(new DisplayNextView(position));
        startAnimation(rotate3dAnimation);
    }

    private final class DisplayNextView implements Animation.AnimationListener {
        private final int mPosition;

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }

        private DisplayNextView(int position) {
            this.mPosition = position;
        }

        public void onAnimationEnd(Animation animation) {
            VideoGroup.this.mVideoGroup.post(new SwapViews(this.mPosition));
        }
    }

    private final class SwapViews implements Runnable {
        private final int mPosition;

        public SwapViews(int position) {
            this.mPosition = position;
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
        int currentViewCount = this.mCurViewCount;
        if (currentViewCount != 1 || !this.mbPtz) {
            int initViewCount = this.mInitViewCount;
            if (initViewCount == 32) {
                if (currentViewCount == 4) {
                    ArrayViews(1, false);
                } else if (currentViewCount == 9) {
                    ArrayViews(4, false);
                } else if (currentViewCount == 16) {
                    ArrayViews(9, false);
                }
            } else if (initViewCount == 16) {
                if (currentViewCount == 4) {
                    ArrayViews(1, false);
                } else if (currentViewCount == 9) {
                    ArrayViews(4, false);
                } else if (currentViewCount == 16) {
                    ArrayViews(9, false);
                }
            } else if (initViewCount == 9) {
                if (currentViewCount == 4) {
                    ArrayViews(1, false);
                } else if (currentViewCount == 9) {
                    ArrayViews(4, false);
                }
            } else if (initViewCount == 4 && currentViewCount == 4) {
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
        int initViewCount = this.mInitViewCount;
        if (initViewCount == 32) {
            int currentViewCount = this.mCurViewCount;
            if (currentViewCount == 1) {
                ArrayViews(4, false);
            } else if (currentViewCount == 4) {
                ArrayViews(9, false);
            } else if (currentViewCount == 9) {
                ArrayViews(16, false);
            }
        } else if (initViewCount == 16) {
            int currentViewCount = this.mCurViewCount;
            if (currentViewCount == 1) {
                ArrayViews(4, false);
            } else if (currentViewCount == 4) {
                ArrayViews(9, false);
            } else if (currentViewCount == 9) {
                ArrayViews(16, false);
            }
        } else if (initViewCount == 9) {
            int currentViewCount = this.mCurViewCount;
            if (currentViewCount == 1) {
                ArrayViews(4, false);
            } else if (currentViewCount == 4) {
                ArrayViews(9, false);
            }
        } else if (initViewCount == 4 && this.mCurViewCount == 1) {
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
        boolean focusFound = false;
        for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
            LvVideoFrame[] lvVideoFrameArr = this.mVideoFrame;
            if (lvVideoFrameArr[viewIndex] != null) {
                if (lvVideoFrameArr[viewIndex].getVisibility() != 0) {
                    this.mVideoFrame[viewIndex].SetFocusState(false);
                } else {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].getLayoutParams();
                    if (new Rect(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + layoutParams.width, layoutParams.topMargin + layoutParams.height).contains((int) pointF.x, (int) pointF.y)) {
                        this.mFocusIndex = viewIndex;
                        this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].SetFocusState(true);
                        LiveViewUi liveViewUi = this.mLiveViewUi;
                        if (liveViewUi != null) {
                            liveViewUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                        }
                        focusFound = true;
                    } else {
                        this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].SetFocusState(false);
                    }
                }
            }
        }
        if (!focusFound) {
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
        int initViewCount = this.mInitViewCount;
        if (initViewCount == 32) {
            int currentViewCount = this.mCurViewCount;
            if (currentViewCount < initViewCount) {
                if (currentViewCount == 16) {
                    int previousBaseIndex = this.mBaseIndex;
                    if (previousBaseIndex + 16 >= 32) {
                        this.mBaseIndex = 0;
                    } else {
                        this.mBaseIndex = previousBaseIndex + 16;
                    }
                    for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                        int baseIndex = this.mBaseIndex;
                        if (viewIndex < baseIndex || viewIndex >= baseIndex + 16) {
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setVisibility(8);
                            LiveViewUi liveViewUi = this.mLiveViewUi;
                            if (liveViewUi != null) {
                                liveViewUi.SetStreamDecodeState(this.mIndexList.get(viewIndex).intValue(), true);
                            }
                        }
                    }
                    int cellWidth = this.mWidth / 4;
                    int cellHeight = this.mHeight / 4;
                    for (int rowIndex = 0; rowIndex < 4; rowIndex++) {
                        for (int columnIndex = 0; columnIndex < 4; columnIndex++) {
                            int cellIndex = (rowIndex * 4) + columnIndex;
                            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex).intValue()].getLayoutParams();
                            if (layoutParams == null) {
                                layoutParams = new RelativeLayout.LayoutParams(cellWidth, cellHeight);
                            }
                            layoutParams.width = cellWidth;
                            layoutParams.height = cellHeight;
                            layoutParams.leftMargin = cellWidth * columnIndex;
                            layoutParams.topMargin = cellHeight * rowIndex;
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex).intValue()].setVisibility(0);
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex).intValue()].setLayoutParams(layoutParams);
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex).intValue()].SetMax(false);
                            LiveViewUi liveViewUi2 = this.mLiveViewUi;
                            if (liveViewUi2 != null) {
                                liveViewUi2.SetStreamDecodeState(this.mIndexList.get(cellIndex + this.mBaseIndex).intValue(), false);
                            }
                        }
                    }
                } else if (currentViewCount == 9) {
                    this.mFlingStatus = 0;
                    ArrayViews(9, true);
                } else if (currentViewCount == 4) {
                    int previousBaseIndex = this.mBaseIndex;
                    if (previousBaseIndex + 4 >= 32) {
                        this.mBaseIndex = 0;
                    } else {
                        this.mBaseIndex = previousBaseIndex + 4;
                    }
                    for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                        int baseIndex = this.mBaseIndex;
                        if (viewIndex < baseIndex || viewIndex >= baseIndex + 4) {
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setVisibility(8);
                            LiveViewUi liveViewUi3 = this.mLiveViewUi;
                            if (liveViewUi3 != null) {
                                liveViewUi3.SetStreamDecodeState(this.mIndexList.get(viewIndex).intValue(), true);
                            }
                        }
                    }
                    int cellWidth = this.mWidth / 2;
                    int cellHeight = this.mHeight / 2;
                    for (int rowIndex = 0; rowIndex < 2; rowIndex++) {
                        for (int columnIndex = 0; columnIndex < 2; columnIndex++) {
                            int cellIndex = (rowIndex * 2) + columnIndex;
                            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex).intValue()].getLayoutParams();
                            if (layoutParams2 == null) {
                                layoutParams2 = new RelativeLayout.LayoutParams(cellWidth, cellHeight);
                            }
                            layoutParams2.width = cellWidth;
                            layoutParams2.height = cellHeight;
                            layoutParams2.leftMargin = cellWidth * columnIndex;
                            layoutParams2.topMargin = cellHeight * rowIndex;
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex).intValue()].setVisibility(0);
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex).intValue()].setLayoutParams(layoutParams2);
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex).intValue()].SetMax(false);
                            LiveViewUi liveViewUi4 = this.mLiveViewUi;
                            if (liveViewUi4 != null) {
                                liveViewUi4.SetStreamDecodeState(this.mIndexList.get(cellIndex + this.mBaseIndex).intValue(), false);
                            }
                        }
                    }
                } else if (currentViewCount == 1) {
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].SetFocusState(false);
                    this.mBaseIndex = (this.mBaseIndex + 1) % 32;
                    for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                        int baseIndex = this.mBaseIndex;
                        if (viewIndex < baseIndex || viewIndex >= baseIndex + this.mCurViewCount) {
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setVisibility(8);
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].SetFocusState(false);
                            LiveViewUi liveViewUi5 = this.mLiveViewUi;
                            if (liveViewUi5 != null) {
                                liveViewUi5.SetStreamDecodeState(this.mIndexList.get(viewIndex).intValue(), true);
                            }
                        } else {
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].SetFocusState(false);
                        }
                    }
                    int cellWidth = this.mWidth;
                    int cellHeight = this.mHeight;
                    RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].getLayoutParams();
                    if (layoutParams3 == null) {
                        layoutParams3 = new RelativeLayout.LayoutParams(cellWidth, cellHeight);
                    }
                    layoutParams3.width = cellWidth;
                    layoutParams3.height = cellHeight;
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
                for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                    if (viewIndex == this.mFocusIndex) {
                        this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].SetFocusState(true);
                        this.mLiveViewUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                    } else {
                        this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].SetFocusState(false);
                    }
                }
            } else {
                return;
            }
        } else if (initViewCount == 16) {
            int currentViewCount = this.mCurViewCount;
            if (currentViewCount < initViewCount) {
                if (currentViewCount == 9) {
                    this.mFlingStatus = 0;
                    ArrayViews(9, true);
                } else if (currentViewCount == 4) {
                    int previousBaseIndex = this.mBaseIndex;
                    if (previousBaseIndex + 4 >= 16) {
                        this.mBaseIndex = 0;
                    } else {
                        this.mBaseIndex = previousBaseIndex + 4;
                    }
                    for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                        int baseIndex = this.mBaseIndex;
                        if (viewIndex < baseIndex || viewIndex >= baseIndex + 4) {
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setVisibility(8);
                            LiveViewUi liveViewUi7 = this.mLiveViewUi;
                            if (liveViewUi7 != null) {
                                liveViewUi7.SetStreamDecodeState(this.mIndexList.get(viewIndex).intValue(), true);
                            }
                        }
                    }
                    int cellWidth = this.mWidth / 2;
                    int cellHeight = this.mHeight / 2;
                    for (int rowIndex = 0; rowIndex < 2; rowIndex++) {
                        for (int columnIndex = 0; columnIndex < 2; columnIndex++) {
                            int cellIndex = (rowIndex * 2) + columnIndex;
                            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex).intValue()].getLayoutParams();
                            if (layoutParams4 == null) {
                                layoutParams4 = new RelativeLayout.LayoutParams(cellWidth, cellHeight);
                            }
                            layoutParams4.width = cellWidth;
                            layoutParams4.height = cellHeight;
                            layoutParams4.leftMargin = cellWidth * columnIndex;
                            layoutParams4.topMargin = cellHeight * rowIndex;
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex).intValue()].setVisibility(0);
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex).intValue()].setLayoutParams(layoutParams4);
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex).intValue()].SetMax(false);
                            LiveViewUi liveViewUi8 = this.mLiveViewUi;
                            if (liveViewUi8 != null) {
                                liveViewUi8.SetStreamDecodeState(this.mIndexList.get(cellIndex + this.mBaseIndex).intValue(), false);
                            }
                        }
                    }
                } else if (currentViewCount == 1) {
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].SetFocusState(false);
                    this.mBaseIndex = (this.mBaseIndex + 1) % 16;
                    for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                        int baseIndex = this.mBaseIndex;
                        if (viewIndex < baseIndex || viewIndex >= baseIndex + this.mCurViewCount) {
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setVisibility(8);
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].SetFocusState(false);
                            LiveViewUi liveViewUi9 = this.mLiveViewUi;
                            if (liveViewUi9 != null) {
                                liveViewUi9.SetStreamDecodeState(this.mIndexList.get(viewIndex).intValue(), true);
                            }
                        } else {
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].SetFocusState(false);
                        }
                    }
                    int cellWidth = this.mWidth;
                    int cellHeight = this.mHeight;
                    RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].getLayoutParams();
                    if (layoutParams5 == null) {
                        layoutParams5 = new RelativeLayout.LayoutParams(cellWidth, cellHeight);
                    }
                    layoutParams5.width = cellWidth;
                    layoutParams5.height = cellHeight;
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
                for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                    int focusIndex = this.mFocusIndex;
                    if (viewIndex == focusIndex) {
                        this.mVideoFrame[this.mIndexList.get(focusIndex).intValue()].SetFocusState(true);
                        this.mLiveViewUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                    } else {
                        this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].SetFocusState(false);
                    }
                }
            } else {
                return;
            }
        } else if (initViewCount == 9) {
            int currentViewCount = this.mCurViewCount;
            if (currentViewCount < initViewCount) {
                if (currentViewCount == 4) {
                    ArrayViews(4, true);
                } else if (currentViewCount == 1) {
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].SetFocusState(false);
                    this.mBaseIndex = (this.mBaseIndex + 1) % 8;
                    for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                        int baseIndex = this.mBaseIndex;
                        if (viewIndex < baseIndex || viewIndex >= baseIndex + this.mCurViewCount) {
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setVisibility(8);
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].SetFocusState(false);
                            LiveViewUi liveViewUi11 = this.mLiveViewUi;
                            if (liveViewUi11 != null) {
                                liveViewUi11.SetStreamDecodeState(this.mIndexList.get(viewIndex).intValue(), true);
                            }
                        } else {
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].SetFocusState(false);
                        }
                    }
                    int cellWidth = this.mWidth;
                    int cellHeight = this.mHeight;
                    RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].getLayoutParams();
                    if (layoutParams6 == null) {
                        layoutParams6 = new RelativeLayout.LayoutParams(cellWidth, cellHeight);
                    }
                    layoutParams6.width = cellWidth;
                    layoutParams6.height = cellHeight;
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
                for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                    int focusIndex = this.mFocusIndex;
                    if (viewIndex == focusIndex) {
                        this.mVideoFrame[this.mIndexList.get(focusIndex).intValue()].SetFocusState(true);
                        this.mLiveViewUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                    } else {
                        this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].SetFocusState(false);
                    }
                }
            } else {
                return;
            }
        } else if (initViewCount == 4) {
            int currentViewCount = this.mCurViewCount;
            if (currentViewCount < initViewCount) {
                if (currentViewCount == 1) {
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].SetFocusState(false);
                    this.mBaseIndex = (this.mBaseIndex + 1) % 4;
                    for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                        this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].SetFocusState(false);
                        if (viewIndex != this.mBaseIndex) {
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setVisibility(8);
                            LiveViewUi liveViewUi13 = this.mLiveViewUi;
                            if (liveViewUi13 != null) {
                                liveViewUi13.SetStreamDecodeState(this.mIndexList.get(viewIndex).intValue(), true);
                            }
                        }
                    }
                    int cellWidth = this.mWidth;
                    int cellHeight = this.mHeight;
                    RelativeLayout.LayoutParams layoutParams7 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].getLayoutParams();
                    if (layoutParams7 == null) {
                        layoutParams7 = new RelativeLayout.LayoutParams(cellWidth, cellHeight);
                    }
                    layoutParams7.width = cellWidth;
                    layoutParams7.height = cellHeight;
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
                for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                    int focusIndex = this.mFocusIndex;
                    if (viewIndex == focusIndex) {
                        this.mVideoFrame[this.mIndexList.get(focusIndex).intValue()].SetFocusState(true);
                        this.mLiveViewUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                    } else {
                        this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].SetFocusState(false);
                    }
                }
            } else {
                return;
            }
        } else if (initViewCount == 1) {
            if (this.mCurViewCount < initViewCount) {
                for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                    this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].SetFocusState(false);
                    int baseIndex = this.mBaseIndex;
                    if (viewIndex < baseIndex || viewIndex >= baseIndex + this.mCurViewCount) {
                        this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setVisibility(8);
                        LiveViewUi liveViewUi15 = this.mLiveViewUi;
                        if (liveViewUi15 != null) {
                            liveViewUi15.SetStreamDecodeState(this.mIndexList.get(viewIndex).intValue(), true);
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
        int initViewCount = this.mInitViewCount;
        if (initViewCount != 1) {
            if (initViewCount == 32) {
                int currentViewCount32 = this.mCurViewCount;
                if (currentViewCount32 < initViewCount) {
                    if (currentViewCount32 == 16) {
                        int previousBaseIndex32For16 = this.mBaseIndex;
                        if (previousBaseIndex32For16 - 16 < 0) {
                            this.mBaseIndex = 16;
                        } else {
                            this.mBaseIndex = previousBaseIndex32For16 - 16;
                        }
                        for (int viewIndex32For16 = 0; viewIndex32For16 < this.mInitViewCount; viewIndex32For16++) {
                            int baseIndex32For16 = this.mBaseIndex;
                            if (viewIndex32For16 < baseIndex32For16 || viewIndex32For16 >= baseIndex32For16 - 16) {
                                this.mVideoFrame[this.mIndexList.get(viewIndex32For16).intValue()].setVisibility(8);
                                LiveViewUi liveViewUi = this.mLiveViewUi;
                                if (liveViewUi != null) {
                                    liveViewUi.SetStreamDecodeState(this.mIndexList.get(viewIndex32For16).intValue(), true);
                                }
                            }
                        }
                        int cellWidth32For16 = this.mWidth / 4;
                        int cellHeight32For16 = this.mHeight / 4;
                        for (int rowIndex32For16 = 0; rowIndex32For16 < 4; rowIndex32For16++) {
                            for (int columnIndex32For16 = 0; columnIndex32For16 < 4; columnIndex32For16++) {
                                int cellIndex32For16 = (rowIndex32For16 * 4) + columnIndex32For16;
                                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex32For16).intValue()].getLayoutParams();
                                if (layoutParams == null) {
                                    layoutParams = new RelativeLayout.LayoutParams(cellWidth32For16, cellHeight32For16);
                                }
                                layoutParams.width = cellWidth32For16;
                                layoutParams.height = cellHeight32For16;
                                layoutParams.leftMargin = cellWidth32For16 * columnIndex32For16;
                                layoutParams.topMargin = cellHeight32For16 * rowIndex32For16;
                                this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex32For16).intValue()].setVisibility(0);
                                this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex32For16).intValue()].setLayoutParams(layoutParams);
                                this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex32For16).intValue()].SetMax(false);
                                LiveViewUi liveViewUi2 = this.mLiveViewUi;
                                if (liveViewUi2 != null) {
                                    liveViewUi2.SetStreamDecodeState(this.mIndexList.get(cellIndex32For16 + this.mBaseIndex).intValue(), false);
                                }
                            }
                        }
                    } else if (currentViewCount32 == 9) {
                        this.mFlingStatus = 1;
                        ArrayViews(9, true);
                    } else if (currentViewCount32 == 4) {
                        int previousBaseIndex32For4 = this.mBaseIndex;
                        if (previousBaseIndex32For4 - 4 < 0) {
                            this.mBaseIndex = 28;
                        } else {
                            this.mBaseIndex = previousBaseIndex32For4 - 4;
                        }
                        for (int viewIndex32For4 = 0; viewIndex32For4 < this.mInitViewCount; viewIndex32For4++) {
                            int baseIndex32For4 = this.mBaseIndex;
                            if (viewIndex32For4 < baseIndex32For4 || viewIndex32For4 >= baseIndex32For4 + 4) {
                                this.mVideoFrame[this.mIndexList.get(viewIndex32For4).intValue()].setVisibility(8);
                                LiveViewUi liveViewUi3 = this.mLiveViewUi;
                                if (liveViewUi3 != null) {
                                    liveViewUi3.SetStreamDecodeState(this.mIndexList.get(viewIndex32For4).intValue(), true);
                                }
                            }
                        }
                        int cellWidth32For4 = this.mWidth / 2;
                        int cellHeight32For4 = this.mHeight / 2;
                        for (int rowIndex32For4 = 0; rowIndex32For4 < 2; rowIndex32For4++) {
                            for (int columnIndex32For4 = 0; columnIndex32For4 < 2; columnIndex32For4++) {
                                int cellIndex32For4 = (rowIndex32For4 * 2) + columnIndex32For4;
                                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex32For4).intValue()].getLayoutParams();
                                if (layoutParams2 == null) {
                                    layoutParams2 = new RelativeLayout.LayoutParams(cellWidth32For4, cellHeight32For4);
                                }
                                layoutParams2.width = cellWidth32For4;
                                layoutParams2.height = cellHeight32For4;
                                layoutParams2.leftMargin = cellWidth32For4 * columnIndex32For4;
                                layoutParams2.topMargin = cellHeight32For4 * rowIndex32For4;
                                this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex32For4).intValue()].setVisibility(0);
                                this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex32For4).intValue()].setLayoutParams(layoutParams2);
                                this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex32For4).intValue()].SetMax(false);
                                LiveViewUi liveViewUi4 = this.mLiveViewUi;
                                if (liveViewUi4 != null) {
                                    liveViewUi4.SetStreamDecodeState(this.mIndexList.get(cellIndex32For4 + this.mBaseIndex).intValue(), false);
                                }
                            }
                        }
                    } else if (currentViewCount32 == 1) {
                        int previousBaseIndex32For1 = this.mBaseIndex;
                        if (previousBaseIndex32For1 - 1 < 0) {
                            this.mBaseIndex = 31;
                        } else {
                            this.mBaseIndex = (previousBaseIndex32For1 - 1) % 32;
                        }
                        for (int viewIndex32For1 = 0; viewIndex32For1 < this.mInitViewCount; viewIndex32For1++) {
                            int baseIndex32For1 = this.mBaseIndex;
                            if (viewIndex32For1 < baseIndex32For1 || viewIndex32For1 >= baseIndex32For1 + this.mCurViewCount) {
                                this.mVideoFrame[this.mIndexList.get(viewIndex32For1).intValue()].setVisibility(8);
                                LiveViewUi liveViewUi5 = this.mLiveViewUi;
                                if (liveViewUi5 != null) {
                                    liveViewUi5.SetStreamDecodeState(this.mIndexList.get(viewIndex32For1).intValue(), true);
                                }
                            } else {
                                this.mVideoFrame[this.mIndexList.get(viewIndex32For1).intValue()].SetFocusState(false);
                            }
                        }
                        int cellWidth32For1 = this.mWidth;
                        int cellHeight32For1 = this.mHeight;
                        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].getLayoutParams();
                        if (layoutParams3 == null) {
                            layoutParams3 = new RelativeLayout.LayoutParams(cellWidth32For1, cellHeight32For1);
                        }
                        layoutParams3.width = cellWidth32For1;
                        layoutParams3.height = cellHeight32For1;
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
                    for (int focusViewIndex32 = 0; focusViewIndex32 < this.mInitViewCount; focusViewIndex32++) {
                        int focusIndex32 = this.mFocusIndex;
                        if (focusViewIndex32 == focusIndex32) {
                            this.mVideoFrame[this.mIndexList.get(focusIndex32).intValue()].SetFocusState(true);
                            this.mLiveViewUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                        } else {
                            this.mVideoFrame[this.mIndexList.get(focusViewIndex32).intValue()].SetFocusState(false);
                        }
                    }
                } else {
                    return;
                }
            } else if (initViewCount == 16) {
                int currentViewCount16 = this.mCurViewCount;
                if (currentViewCount16 < initViewCount) {
                    if (currentViewCount16 == 9) {
                        this.mFlingStatus = 1;
                        ArrayViews(9, true);
                    } else if (currentViewCount16 == 4) {
                        int previousBaseIndex16For4 = this.mBaseIndex;
                        if (previousBaseIndex16For4 - 4 < 0) {
                            this.mBaseIndex = 12;
                        } else {
                            this.mBaseIndex = previousBaseIndex16For4 - 4;
                        }
                        for (int viewIndex16For4 = 0; viewIndex16For4 < this.mInitViewCount; viewIndex16For4++) {
                            int baseIndex16For4 = this.mBaseIndex;
                            if (viewIndex16For4 < baseIndex16For4 || viewIndex16For4 >= baseIndex16For4 + 4) {
                                this.mVideoFrame[this.mIndexList.get(viewIndex16For4).intValue()].setVisibility(8);
                                LiveViewUi liveViewUi7 = this.mLiveViewUi;
                                if (liveViewUi7 != null) {
                                    liveViewUi7.SetStreamDecodeState(this.mIndexList.get(viewIndex16For4).intValue(), true);
                                }
                            }
                        }
                        int cellWidth16For4 = this.mWidth / 2;
                        int cellHeight16For4 = this.mHeight / 2;
                        for (int rowIndex16For4 = 0; rowIndex16For4 < 2; rowIndex16For4++) {
                            for (int columnIndex16For4 = 0; columnIndex16For4 < 2; columnIndex16For4++) {
                                int cellIndex16For4 = (rowIndex16For4 * 2) + columnIndex16For4;
                                RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex16For4).intValue()].getLayoutParams();
                                if (layoutParams4 == null) {
                                    layoutParams4 = new RelativeLayout.LayoutParams(cellWidth16For4, cellHeight16For4);
                                }
                                layoutParams4.width = cellWidth16For4;
                                layoutParams4.height = cellHeight16For4;
                                layoutParams4.leftMargin = cellWidth16For4 * columnIndex16For4;
                                layoutParams4.topMargin = cellHeight16For4 * rowIndex16For4;
                                this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex16For4).intValue()].setVisibility(0);
                                this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex16For4).intValue()].setLayoutParams(layoutParams4);
                                this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + cellIndex16For4).intValue()].SetMax(false);
                                LiveViewUi liveViewUi8 = this.mLiveViewUi;
                                if (liveViewUi8 != null) {
                                    liveViewUi8.SetStreamDecodeState(this.mIndexList.get(cellIndex16For4 + this.mBaseIndex).intValue(), false);
                                }
                            }
                        }
                    } else if (currentViewCount16 == 1) {
                        int previousBaseIndex16For1 = this.mBaseIndex;
                        if (previousBaseIndex16For1 - 1 < 0) {
                            this.mBaseIndex = 15;
                        } else {
                            this.mBaseIndex = (previousBaseIndex16For1 - 1) % 16;
                        }
                        for (int viewIndex16For1 = 0; viewIndex16For1 < this.mInitViewCount; viewIndex16For1++) {
                            int baseIndex16For1 = this.mBaseIndex;
                            if (viewIndex16For1 < baseIndex16For1 || viewIndex16For1 >= baseIndex16For1 + this.mCurViewCount) {
                                this.mVideoFrame[this.mIndexList.get(viewIndex16For1).intValue()].setVisibility(8);
                                LiveViewUi liveViewUi9 = this.mLiveViewUi;
                                if (liveViewUi9 != null) {
                                    liveViewUi9.SetStreamDecodeState(this.mIndexList.get(viewIndex16For1).intValue(), true);
                                }
                            } else {
                                this.mVideoFrame[this.mIndexList.get(viewIndex16For1).intValue()].SetFocusState(false);
                            }
                        }
                        int cellWidth16For1 = this.mWidth;
                        int cellHeight16For1 = this.mHeight;
                        RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].getLayoutParams();
                        if (layoutParams5 == null) {
                            layoutParams5 = new RelativeLayout.LayoutParams(cellWidth16For1, cellHeight16For1);
                        }
                        layoutParams5.width = cellWidth16For1;
                        layoutParams5.height = cellHeight16For1;
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
                    for (int focusViewIndex16 = 0; focusViewIndex16 < this.mInitViewCount; focusViewIndex16++) {
                        int focusIndex16 = this.mFocusIndex;
                        if (focusViewIndex16 == focusIndex16) {
                            this.mVideoFrame[this.mIndexList.get(focusIndex16).intValue()].SetFocusState(true);
                            this.mLiveViewUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                        } else {
                            this.mVideoFrame[this.mIndexList.get(focusViewIndex16).intValue()].SetFocusState(false);
                        }
                    }
                } else {
                    return;
                }
            } else if (initViewCount == 9) {
                int currentViewCount9 = this.mCurViewCount;
                if (currentViewCount9 < initViewCount) {
                    if (currentViewCount9 == 4) {
                        ArrayViews(4, true);
                    } else if (currentViewCount9 == 1) {
                        int previousBaseIndex9For1 = this.mBaseIndex;
                        if (previousBaseIndex9For1 - 1 < 0) {
                            this.mBaseIndex = 7;
                        } else {
                            this.mBaseIndex = (previousBaseIndex9For1 - 1) % 8;
                        }
                        for (int viewIndex9For1 = 0; viewIndex9For1 < this.mInitViewCount; viewIndex9For1++) {
                            int baseIndex9For1 = this.mBaseIndex;
                            if (viewIndex9For1 < baseIndex9For1 || viewIndex9For1 >= baseIndex9For1 + this.mCurViewCount) {
                                this.mVideoFrame[this.mIndexList.get(viewIndex9For1).intValue()].setVisibility(8);
                                LiveViewUi liveViewUi11 = this.mLiveViewUi;
                                if (liveViewUi11 != null) {
                                    liveViewUi11.SetStreamDecodeState(this.mIndexList.get(viewIndex9For1).intValue(), true);
                                }
                            } else {
                                this.mVideoFrame[this.mIndexList.get(viewIndex9For1).intValue()].SetFocusState(false);
                            }
                        }
                        int cellWidth9For1 = this.mWidth;
                        int cellHeight9For1 = this.mHeight;
                        RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].getLayoutParams();
                        if (layoutParams6 == null) {
                            layoutParams6 = new RelativeLayout.LayoutParams(cellWidth9For1, cellHeight9For1);
                        }
                        layoutParams6.width = cellWidth9For1;
                        layoutParams6.height = cellHeight9For1;
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
                    for (int focusViewIndex9 = 0; focusViewIndex9 < this.mInitViewCount; focusViewIndex9++) {
                        int focusIndex9 = this.mFocusIndex;
                        if (focusViewIndex9 == focusIndex9) {
                            this.mVideoFrame[this.mIndexList.get(focusIndex9).intValue()].SetFocusState(true);
                            this.mLiveViewUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                        } else {
                            this.mVideoFrame[this.mIndexList.get(focusViewIndex9).intValue()].SetFocusState(false);
                        }
                    }
                } else {
                    return;
                }
            } else if (initViewCount == 4) {
                int currentViewCount4 = this.mCurViewCount;
                if (currentViewCount4 < initViewCount) {
                    if (currentViewCount4 == 1) {
                        int previousBaseIndex4For1 = this.mBaseIndex;
                        if (previousBaseIndex4For1 - 1 < 0) {
                            this.mBaseIndex = 3;
                        } else {
                            this.mBaseIndex = previousBaseIndex4For1 - 1;
                        }
                        for (int viewIndex4For1 = 0; viewIndex4For1 < this.mInitViewCount; viewIndex4For1++) {
                            this.mVideoFrame[this.mIndexList.get(viewIndex4For1).intValue()].SetFocusState(false);
                            if (viewIndex4For1 != this.mBaseIndex) {
                                this.mVideoFrame[this.mIndexList.get(viewIndex4For1).intValue()].setVisibility(8);
                                LiveViewUi liveViewUi13 = this.mLiveViewUi;
                                if (liveViewUi13 != null) {
                                    liveViewUi13.SetStreamDecodeState(this.mIndexList.get(viewIndex4For1).intValue(), true);
                                }
                            }
                        }
                        int cellWidth4For1 = this.mWidth;
                        int cellHeight4For1 = this.mHeight;
                        RelativeLayout.LayoutParams layoutParams7 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].getLayoutParams();
                        if (layoutParams7 == null) {
                            layoutParams7 = new RelativeLayout.LayoutParams(cellWidth4For1, cellHeight4For1);
                        }
                        layoutParams7.width = cellWidth4For1;
                        layoutParams7.height = cellHeight4For1;
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
                    for (int focusViewIndex4 = 0; focusViewIndex4 < this.mInitViewCount; focusViewIndex4++) {
                        int focusIndex4 = this.mFocusIndex;
                        if (focusViewIndex4 == focusIndex4) {
                            this.mVideoFrame[this.mIndexList.get(focusIndex4).intValue()].SetFocusState(true);
                            this.mLiveViewUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                        } else {
                            this.mVideoFrame[this.mIndexList.get(focusViewIndex4).intValue()].SetFocusState(false);
                        }
                    }
                } else {
                    return;
                }
            } else if (initViewCount == 1 && this.mCurViewCount >= initViewCount) {
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
        int sourceIndex = this.mSourceIndex;
        if (sourceIndex != this.mDestinationIndex) {
            this.mVideoFrame[this.mIndexList.get(sourceIndex).intValue()].setLayoutParams(this.mDestinationParams);
            this.mVideoFrame[this.mIndexList.get(this.mDestinationIndex).intValue()].setLayoutParams(this.mSourceParams);
            int intValue = this.mIndexList.get(this.mSourceIndex).intValue();
            this.mIndexList.set(this.mSourceIndex, Integer.valueOf(this.mIndexList.get(this.mDestinationIndex).intValue()));
            this.mIndexList.set(this.mDestinationIndex, Integer.valueOf(intValue));
        } else {
            this.mVideoFrame[this.mIndexList.get(sourceIndex).intValue()].setLayoutParams(this.mSourceParams);
        }
        for (int viewIndex = this.mBaseIndex; viewIndex < this.mBaseIndex + this.mCurViewCount; viewIndex++) {
            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setDragState(false);
        }
    }

    public void findDestination(MotionEvent motionEvent) {
        boolean destinationFound = false;
        for (int viewIndex = this.mBaseIndex; viewIndex < this.mCurViewCount + this.mBaseIndex; viewIndex++) {
            if (viewIndex != this.mSourceIndex) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].getLayoutParams();
                if (new Rect(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + layoutParams.width, layoutParams.topMargin + layoutParams.height).contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                    this.mDestinationIndex = viewIndex;
                    this.mDestinationParams = layoutParams;
                    this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setDragState(true);
                    destinationFound = true;
                } else {
                    this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setDragState(false);
                }
            }
        }
        if (!destinationFound) {
            this.mDestinationIndex = this.mSourceIndex;
            this.mDestinationParams = this.mSourceParams;
        }
    }

    public boolean SetPtzMode(boolean enabled, int ptzState, DvrNet dvrNet) {
        if (!enabled || this.mCurViewCount == 1) {
            this.mbPtz = enabled;
            this.mPtzState = ptzState;
            this.mDvrNet = dvrNet;
            if (!enabled) {
                SetPtzControlState(8);
                this.mLiveViewUi.SetPtzState(false);
            } else if (((1 << this.mIndexList.get(this.mFocusIndex).intValue()) & ptzState) > 0) {
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
