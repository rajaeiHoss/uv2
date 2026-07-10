package com.streamax.client;

import android.content.Context;
import android.content.Intent;
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
import com.zycs.UView.R;
import java.util.ArrayList;
import java.util.List;

// Live preview grid container with gesture navigation.
public class VideoContainer extends RelativeLayout {
    private static final int DRAG = 1;
    private static final int FLING_TO_LEFT = 0;
    private static final int FLING_TO_RIGHT = 1;
    private static final int NONE = 0;
    private static final int PTZ_DOWN = 2;
    private static final int PTZ_LEFT = 3;
    private static final int PTZ_RIGHT = 4;
    private static final int PTZ_STOP = 20;
    private static final int PTZ_UP = 1;
    private static final int PTZ_ZOOM_IN = 7;
    private static final int PTZ_ZOOM_OUT = 8;
    private static final int ZOOM = 2;
    private final int DISTANCE_MIN_SIZE = 100;
    private int mBaseIndex = 0;
    public VideoContainer mContainer;
    private Context mContext;
    /* access modifiers changed from: private */
    public int mCurViewCount;
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
    public int mInitViewCount;
    private int mLastArrayMode;
    /* access modifiers changed from: private */
    public boolean mLongPress = false;
    private int mMax = 32;
    public View.OnClickListener mOnclickListener;
    private int mPtzState = 0;
    public RealPlayActivity mRealPlayUi;
    public int mSourceIndex;
    public RelativeLayout.LayoutParams mSourceParams;
    /* access modifiers changed from: private */
    public int mTouchMode;
    /* access modifiers changed from: private */
    public VideoFrame[] mVideoFrame;
    public int mWidth = 0;
    private Matrix matrix = new Matrix();
    /* access modifiers changed from: private */
    public boolean mbPtz = false;
    private Matrix savedMatrix = new Matrix();
    private PointF start = new PointF();

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed) {
            postDelayed(new Runnable() {
                public void run() {
                    VideoContainer videoContainer = VideoContainer.this;
                    videoContainer.ArrayViews(videoContainer.mCurViewCount, true);
                }
            }, 100);
        }
        super.onLayout(changed, left, top, right, bottom);
    }

    public VideoContainer(Context context) {
        super(context);
        this.mContext = context;
        if (this.mGestureDetector == null) {
            this.mGestureDetector = new GestureDetector(this.mContext, new FlingGestureDetector());
        }
        this.mContainer = this;
        LoadViews();
    }

    public VideoContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        if (this.mGestureDetector == null) {
            this.mGestureDetector = new GestureDetector(this.mContext, new FlingGestureDetector());
        }
        this.mContainer = this;
        LoadViews();
    }

    public VideoContainer(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        this.mContext = context;
        if (this.mGestureDetector == null) {
            this.mGestureDetector = new GestureDetector(this.mContext, new FlingGestureDetector());
        }
        this.mContainer = this;
        LoadViews();
    }

    public void SetInitMode(int layoutMode) {
        this.mLastArrayMode = layoutMode;
        this.mCurViewCount = layoutMode;
        this.mInitViewCount = layoutMode;
        this.mIndexList.clear();
        for (int index = 0; index < this.mMax; index++) {
            this.mIndexList.add(index, Integer.valueOf(index));
        }
        this.mFocusIndex = 0;
        for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
            if (viewIndex == this.mFocusIndex) {
                this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setFocusState(true);
            } else {
                this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setFocusState(false);
            }
        }
        if (this.mCurViewCount == 32) {
            this.mCurViewCount = 4;
        }
        ArrayViews(this.mCurViewCount, false);
    }

    public int GetLayoutMode() {
        return this.mInitViewCount;
    }

    public int GetCurArrayMode() {
        return this.mCurViewCount;
    }

    public List<Integer> GetChannelList() {
        ArrayList<Integer> channelList = new ArrayList();
        for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
            VideoFrame[] videoFrameArr = this.mVideoFrame;
            if (videoFrameArr[viewIndex] != null && videoFrameArr[viewIndex].getVisibility() == 0) {
                channelList.add(Integer.valueOf(this.mIndexList.get(viewIndex).intValue()));
            }
        }
        return channelList;
    }

    public void SetActivity(RealPlayActivity realPlayActivity) {
        this.mRealPlayUi = realPlayActivity;
    }

    public void LoadViews() {
        this.mVideoFrame = new VideoFrame[this.mMax];
        this.mIndexList.clear();
        setVerticalGravity(17);
        setHorizontalGravity(17);
        int viewIndex = 0;
        while (viewIndex < this.mMax) {
            int channelNumber = viewIndex + 1;
            this.mVideoFrame[viewIndex] = new VideoFrame(this.mContext, channelNumber);
            this.mVideoFrame[viewIndex].setId(viewIndex);
            this.mVideoFrame[viewIndex].setVisibility(8);
            this.mVideoFrame[viewIndex].setClickable(false);
            this.mVideoFrame[viewIndex].setFocusable(false);
            this.mVideoFrame[viewIndex].setFocusableInTouchMode(false);
            this.mVideoFrame[viewIndex].setHapticFeedbackEnabled(false);
            addView(this.mVideoFrame[viewIndex]);
            this.mIndexList.add(viewIndex, Integer.valueOf(viewIndex));
            viewIndex = channelNumber;
        }
        for (int frameIndex = 0; frameIndex < this.mMax; frameIndex++) {
            final int index = frameIndex;
            this.mVideoFrame[frameIndex].mImgAdd.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(VideoContainer.this.mRealPlayUi, DevSelectUi.class);
                    intent.putExtra("currentPosition", index);
                    VideoContainer.this.mRealPlayUi.startActivityForResult(intent, 0);
                }
            });
        }
        this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].setFocusState(true);
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
                if (VideoContainer.this.mDvrNet != null && VideoContainer.this.mCurViewCount == 1) {
                    new Thread(new Runnable() {
                        public void run() {
                            VideoContainer.this.mDvrNet.PTZControl(((Integer) VideoContainer.this.mIndexList.get(VideoContainer.this.mFocusIndex)).intValue(), ptzCommand, VideoContainer.this.mRealPlayUi.mApp.mPtzSpeed);
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            VideoContainer.this.mDvrNet.PTZControl(((Integer) VideoContainer.this.mIndexList.get(VideoContainer.this.mFocusIndex)).intValue(), 20, VideoContainer.this.mRealPlayUi.mApp.mPtzSpeed);
                        }
                    }).start();
                }
            }
        };
        int[] ptzButtonIds = {R.id.ptz_control_left, R.id.ptz_control_right, R.id.ptz_control_up, R.id.ptz_control_down};
        for (int buttonIndex = 0; buttonIndex < 4; buttonIndex++) {
            ImageView imageView = (ImageView) findViewById(ptzButtonIds[buttonIndex]);
            if (imageView != null) {
                imageView.setOnClickListener(this.mOnclickListener);
            }
        }
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
    }

    public void SetPlayState(int channel, boolean playing) {
        this.mVideoFrame[channel].setPlayState(playing);
    }

    public void SetBusyState(int channel, boolean busy) {
        this.mVideoFrame[channel].SetBusyState(busy);
    }

    public void SetRecState(int channel, boolean recording) {
        this.mVideoFrame[channel].SetRecState(recording);
    }

    public void ClearViews() {
        for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
            this.mVideoFrame[viewIndex].ClearViews();
        }
    }

    public void ArrayViews(int viewCount, boolean fromFling) {
        // Decompiler could not reconstruct this method; keep the stub explicit instead of preserving the
        // multi-thousand-line register dump comment that obscures the surrounding real Java code.
        throw new UnsupportedOperationException("Method not decompiled: com.streamax.client.VideoContainer.ArrayViews(int, boolean):void");
    }

    public void SetPtzControlState(int visibility) {
        RealPlayActivity realPlayActivity;
        int[] ptzButtonIds = {R.id.ptz_control_left, R.id.ptz_control_right, R.id.ptz_control_up, R.id.ptz_control_down};
        for (int buttonIndex = 0; buttonIndex < 4; buttonIndex++) {
            ImageView imageView = (ImageView) findViewById(ptzButtonIds[buttonIndex]);
            if (imageView != null) {
                if (imageView.getVisibility() != visibility) {
                    imageView.setVisibility(visibility);
                }
                imageView.bringToFront();
            }
        }
        if (visibility == 8 || visibility == 4) {
            RealPlayActivity realPlayActivity2 = this.mRealPlayUi;
            if (realPlayActivity2 != null) {
                realPlayActivity2.SetPtzState(false);
            }
        } else if (visibility == 0 && (realPlayActivity = this.mRealPlayUi) != null) {
            realPlayActivity.SetPtzState(true);
        }
    }

    public int GetMaxChannel() {
        if (this.mCurViewCount != 1) {
            return -1;
        }
        return this.mIndexList.get(this.mFocusIndex).intValue();
    }

    public int GetCurrentMode() {
        return this.mCurViewCount;
    }

    public void FlingRight(float distance) {
        if ((this.mCurViewCount != 1 || this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].GetDigitalScales() == 1.0f) && this.mInitViewCount != this.mCurViewCount) {
            applyRotation(-1, 0.0f, 90.0f);
        }
    }

    public void FlingLeft(float distance) {
        if ((this.mCurViewCount != 1 || this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].GetDigitalScales() == 1.0f) && this.mInitViewCount != this.mCurViewCount) {
            applyRotation(1, 0.0f, -90.0f);
        }
    }

    private void applyRotation(int direction, float startDegrees, float endDegrees) {
        Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(startDegrees, endDegrees, ((float) this.mContainer.getWidth()) / 2.0f, ((float) this.mContainer.getHeight()) / 2.0f, 250.0f, true);
        rotate3dAnimation.setDuration(500);
        rotate3dAnimation.setFillAfter(true);
        rotate3dAnimation.setInterpolator(new AccelerateInterpolator());
        rotate3dAnimation.setAnimationListener(new DisplayNextView(direction));
        this.mContainer.startAnimation(rotate3dAnimation);
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
            VideoContainer.this.mContainer.post(new SwapViews(this.mPosition));
        }
    }

    private final class SwapViews implements Runnable {
        private final int mPosition;

        public SwapViews(int position) {
            this.mPosition = position;
        }

        public void run() {
            Rotate3dAnimation rotate3dAnimation;
            float width = ((float) VideoContainer.this.getWidth()) / 2.0f;
            float height = ((float) VideoContainer.this.getHeight()) / 2.0f;
            if (this.mPosition > -1) {
                VideoContainer.this.mContainer.TurnNextView();
                rotate3dAnimation = new Rotate3dAnimation(90.0f, 0.0f, width, height, 250.0f, false);
            } else {
                VideoContainer.this.mContainer.TurnLastView();
                rotate3dAnimation = new Rotate3dAnimation(-90.0f, 0.0f, width, height, 250.0f, false);
            }
            rotate3dAnimation.setDuration(300);
            rotate3dAnimation.setFillAfter(true);
            rotate3dAnimation.setAnimationListener(new DisplaySwitchChannel());
            VideoContainer.this.mContainer.startAnimation(rotate3dAnimation);
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
            if (VideoContainer.this.mRealPlayUi != null) {
                new Thread(new Runnable() {
                    public void run() {
                        VideoContainer.this.mRealPlayUi.SwitchPlay();
                    }
                }).start();
            }
        }
    }

    public void ZoomIn() {
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
            this.mDvrNet.PTZControl(this.mIndexList.get(this.mFocusIndex).intValue(), 7, this.mRealPlayUi.mApp.mPtzSpeed);
            this.mRealPlayUi.setPtz(this.mIndexList.get(this.mFocusIndex).intValue(), 7);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.mDvrNet.PTZControl(this.mIndexList.get(this.mFocusIndex).intValue(), 20, this.mRealPlayUi.mApp.mPtzSpeed);
            this.mRealPlayUi.setPtz(this.mIndexList.get(this.mFocusIndex).intValue(), 20);
            SetPtzControlState(0);
        }
    }

    public void ZoomOut() {
        if (this.mCurViewCount == 1 && this.mbPtz) {
            if (this.mDvrNet != null) {
                if ((this.mPtzState & (1 << this.mIndexList.get(this.mFocusIndex).intValue())) > 0) {
                    this.mDvrNet.PTZControl(this.mIndexList.get(this.mFocusIndex).intValue(), 8, this.mRealPlayUi.mApp.mPtzSpeed);
                    this.mRealPlayUi.setPtz(this.mIndexList.get(this.mFocusIndex).intValue(), 8);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.mDvrNet.PTZControl(this.mIndexList.get(this.mFocusIndex).intValue(), 20, this.mRealPlayUi.mApp.mPtzSpeed);
                    this.mRealPlayUi.setPtz(this.mIndexList.get(this.mFocusIndex).intValue(), 20);
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
        RealPlayActivity realPlayActivity = this.mRealPlayUi;
        if (realPlayActivity != null) {
            realPlayActivity.SetPtzState(false);
        }
    }

    public int getFirstIndex() {
        return this.mBaseIndex;
    }

    public int getInitMode() {
        return this.mInitViewCount;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mGestureDetector.onTouchEvent(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    if (this.mLongPress) {
                        Dragging(motionEvent);
                    }
                    int touchMode = this.mTouchMode;
                    if (touchMode == 2) {
                        float currentDistance = GetDistance(motionEvent);
                        if (this.mCurViewCount == 1 && ((!this.mbPtz || (this.mPtzState & (1 << this.mIndexList.get(this.mFocusIndex).intValue())) <= 0) && currentDistance > 0.0f)) {
                            this.matrix.set(this.savedMatrix);
                            float scale = currentDistance / this.mDignitalDistance;
                            this.mDignitalDistance = currentDistance;
                            this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].setFatherW_H(getLeft(), getTop(), getRight(), getBottom(), getWidth(), getHeight());
                            this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].SetDigitalZoomIn(scale);
                        }
                    } else if (touchMode == 1 && this.mCurViewCount == 1 && this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].GetDigitalScales() != 1.0f) {
                        this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].SetDigitalTranslate(this.start.x - motionEvent.getX(), this.start.y - motionEvent.getY());
                        this.start.set(motionEvent.getX(), motionEvent.getY());
                        this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].onTouchMove(motionEvent);
                    }
                } else if (action == 5) {
                    this.mTouchMode = 2;
                    this.mDistance = GetDistance(motionEvent);
                    this.mDignitalDistance = GetDistance(motionEvent);
                    if (this.mCurViewCount == 1) {
                        this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].onPointerDown(motionEvent);
                    }
                } else if (action == 6) {
                    this.mTouchMode = 0;
                    if (this.mCurViewCount == 1) {
                        if (!this.mbPtz || (this.mPtzState & (1 << this.mIndexList.get(this.mFocusIndex).intValue())) <= 0) {
                            if (this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].GetDigitalScales() <= 1.0f) {
                                this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].ResetDigitalScales();
                            }
                        } else if (GetDistance(motionEvent) - this.mDistance > 0.0f) {
                            ZoomIn();
                        } else {
                            ZoomOut();
                        }
                    }
                    if (GetDistance(motionEvent) - this.mDistance > 0.0f) {
                        ZoomIn();
                    } else {
                        ZoomOut();
                    }
                }
            } else if (this.mLongPress) {
                endDragging(motionEvent);
                this.mLongPress = false;
            }
        } else if (!(this.mCurViewCount == 1 && this.mbPtz && (this.mPtzState & (1 << this.mIndexList.get(this.mFocusIndex).intValue())) > 0) && this.mCurViewCount == 1 && this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].GetDigitalScales() > 1.0f) {
            this.mTouchMode = 1;
            this.start.set(motionEvent.getX(), motionEvent.getY());
            this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].onTouchDown(motionEvent);
        }
        return true;
    }

    public int getFocusView(PointF point) {
        boolean foundFocus = false;
        for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
            VideoFrame[] videoFrameArr = this.mVideoFrame;
            if (videoFrameArr[viewIndex] != null) {
                if (videoFrameArr[viewIndex].getVisibility() != 0) {
                    this.mVideoFrame[viewIndex].setFocusState(false);
                } else {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].getLayoutParams();
                    if (new Rect(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + layoutParams.width, layoutParams.topMargin + layoutParams.height).contains((int) point.x, (int) point.y)) {
                        this.mFocusIndex = viewIndex;
                        this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setFocusState(true);
                        RealPlayActivity realPlayActivity = this.mRealPlayUi;
                        if (realPlayActivity != null) {
                            realPlayActivity.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                        }
                        foundFocus = true;
                    } else {
                        this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setFocusState(false);
                    }
                }
            }
        }
        if (!foundFocus) {
            this.mFocusIndex = 0;
        }
        return this.mFocusIndex;
    }

    public void SetFocusViewMax() {
        if (this.mCurViewCount == 1) {
            this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].ResetDigitalScales();
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
                            RealPlayActivity realPlayActivity = this.mRealPlayUi;
                            if (realPlayActivity != null) {
                                realPlayActivity.SetStreamDecodeState(this.mIndexList.get(viewIndex).intValue(), true);
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
                            RealPlayActivity realPlayActivity2 = this.mRealPlayUi;
                            if (realPlayActivity2 != null) {
                                realPlayActivity2.SetStreamDecodeState(this.mIndexList.get(cellIndex + this.mBaseIndex).intValue(), false);
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
                            RealPlayActivity realPlayActivity3 = this.mRealPlayUi;
                            if (realPlayActivity3 != null) {
                                realPlayActivity3.SetStreamDecodeState(this.mIndexList.get(viewIndex).intValue(), true);
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
                            RealPlayActivity realPlayActivity4 = this.mRealPlayUi;
                            if (realPlayActivity4 != null) {
                                realPlayActivity4.SetStreamDecodeState(this.mIndexList.get(cellIndex + this.mBaseIndex).intValue(), false);
                            }
                        }
                    }
                } else if (currentViewCount == 1) {
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setFocusState(false);
                    this.mBaseIndex = (this.mBaseIndex + 1) % 32;
                    for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                        int baseIndex = this.mBaseIndex;
                        if (viewIndex < baseIndex || viewIndex >= baseIndex + this.mCurViewCount) {
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setVisibility(8);
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setFocusState(false);
                            RealPlayActivity realPlayActivity5 = this.mRealPlayUi;
                            if (realPlayActivity5 != null) {
                                realPlayActivity5.SetStreamDecodeState(this.mIndexList.get(viewIndex).intValue(), true);
                            }
                        } else {
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setFocusState(false);
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
                    RealPlayActivity realPlayActivity6 = this.mRealPlayUi;
                    if (realPlayActivity6 != null) {
                        realPlayActivity6.SetStreamDecodeState(this.mIndexList.get(this.mBaseIndex).intValue(), false);
                    }
                }
                this.mFocusIndex = this.mBaseIndex;
                for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                    int focusIndex = this.mFocusIndex;
                    if (viewIndex == focusIndex) {
                        this.mVideoFrame[this.mIndexList.get(focusIndex).intValue()].setFocusState(true);
                        this.mRealPlayUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                    } else {
                        this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setFocusState(false);
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
                            RealPlayActivity realPlayActivity7 = this.mRealPlayUi;
                            if (realPlayActivity7 != null) {
                                realPlayActivity7.SetStreamDecodeState(this.mIndexList.get(viewIndex).intValue(), true);
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
                            RealPlayActivity realPlayActivity8 = this.mRealPlayUi;
                            if (realPlayActivity8 != null) {
                                realPlayActivity8.SetStreamDecodeState(this.mIndexList.get(cellIndex + this.mBaseIndex).intValue(), false);
                            }
                        }
                    }
                } else if (currentViewCount == 1) {
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setFocusState(false);
                    this.mBaseIndex = (this.mBaseIndex + 1) % 16;
                    for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                        int baseIndex = this.mBaseIndex;
                        if (viewIndex < baseIndex || viewIndex >= baseIndex + this.mCurViewCount) {
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setVisibility(8);
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setFocusState(false);
                            RealPlayActivity realPlayActivity9 = this.mRealPlayUi;
                            if (realPlayActivity9 != null) {
                                realPlayActivity9.SetStreamDecodeState(this.mIndexList.get(viewIndex).intValue(), true);
                            }
                        } else {
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setFocusState(false);
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
                    RealPlayActivity realPlayActivity10 = this.mRealPlayUi;
                    if (realPlayActivity10 != null) {
                        realPlayActivity10.SetStreamDecodeState(this.mIndexList.get(this.mBaseIndex).intValue(), false);
                    }
                }
                this.mFocusIndex = this.mBaseIndex;
                for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                    int focusIndex = this.mFocusIndex;
                    if (viewIndex == focusIndex) {
                        this.mVideoFrame[this.mIndexList.get(focusIndex).intValue()].setFocusState(true);
                        this.mRealPlayUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                    } else {
                        this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setFocusState(false);
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
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setFocusState(false);
                    this.mBaseIndex = (this.mBaseIndex + 1) % 8;
                    for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                        int baseIndex = this.mBaseIndex;
                        if (viewIndex < baseIndex || viewIndex >= baseIndex + this.mCurViewCount) {
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setVisibility(8);
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setFocusState(false);
                            RealPlayActivity realPlayActivity11 = this.mRealPlayUi;
                            if (realPlayActivity11 != null) {
                                realPlayActivity11.SetStreamDecodeState(this.mIndexList.get(viewIndex).intValue(), true);
                            }
                        } else {
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setFocusState(false);
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
                    RealPlayActivity realPlayActivity12 = this.mRealPlayUi;
                    if (realPlayActivity12 != null) {
                        realPlayActivity12.SetStreamDecodeState(this.mIndexList.get(this.mBaseIndex).intValue(), false);
                    }
                }
                this.mFocusIndex = this.mBaseIndex;
                for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                    int focusIndex = this.mFocusIndex;
                    if (viewIndex == focusIndex) {
                        this.mVideoFrame[this.mIndexList.get(focusIndex).intValue()].setFocusState(true);
                        this.mRealPlayUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                    } else {
                        this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setFocusState(false);
                    }
                }
            } else {
                return;
            }
        } else if (initViewCount == 4) {
            int currentViewCount = this.mCurViewCount;
            if (currentViewCount < initViewCount) {
                if (currentViewCount == 1) {
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setFocusState(false);
                    this.mBaseIndex = (this.mBaseIndex + 1) % 4;
                    for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                        this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setFocusState(false);
                        if (viewIndex != this.mBaseIndex) {
                            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setVisibility(8);
                            RealPlayActivity realPlayActivity13 = this.mRealPlayUi;
                            if (realPlayActivity13 != null) {
                                realPlayActivity13.SetStreamDecodeState(this.mIndexList.get(viewIndex).intValue(), true);
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
                    RealPlayActivity realPlayActivity14 = this.mRealPlayUi;
                    if (realPlayActivity14 != null) {
                        realPlayActivity14.SetStreamDecodeState(this.mIndexList.get(this.mBaseIndex).intValue(), false);
                    }
                }
                this.mFocusIndex = this.mBaseIndex;
                for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                    int focusIndex = this.mFocusIndex;
                    if (viewIndex == focusIndex) {
                        this.mVideoFrame[this.mIndexList.get(focusIndex).intValue()].setFocusState(true);
                        this.mRealPlayUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                    } else {
                        this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setFocusState(false);
                    }
                }
            } else {
                return;
            }
        } else if (initViewCount == 1) {
            if (this.mCurViewCount < initViewCount) {
                for (int viewIndex = 0; viewIndex < this.mInitViewCount; viewIndex++) {
                    this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setFocusState(false);
                    int baseIndex = this.mBaseIndex;
                    if (viewIndex < baseIndex || viewIndex >= baseIndex + this.mCurViewCount) {
                        this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].setVisibility(8);
                        RealPlayActivity realPlayActivity15 = this.mRealPlayUi;
                        if (realPlayActivity15 != null) {
                            realPlayActivity15.SetStreamDecodeState(this.mIndexList.get(viewIndex).intValue(), true);
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
                            RealPlayActivity realPlayActivity = this.mRealPlayUi;
                            if (realPlayActivity != null) {
                                realPlayActivity.SetStreamDecodeState(this.mIndexList.get(i4).intValue(), true);
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
                            RealPlayActivity realPlayActivity2 = this.mRealPlayUi;
                            if (realPlayActivity2 != null) {
                                realPlayActivity2.SetStreamDecodeState(this.mIndexList.get(i10 + this.mBaseIndex).intValue(), false);
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
                            RealPlayActivity realPlayActivity3 = this.mRealPlayUi;
                            if (realPlayActivity3 != null) {
                                realPlayActivity3.SetStreamDecodeState(this.mIndexList.get(i12).intValue(), true);
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
                            RealPlayActivity realPlayActivity4 = this.mRealPlayUi;
                            if (realPlayActivity4 != null) {
                                realPlayActivity4.SetStreamDecodeState(this.mIndexList.get(i18 + this.mBaseIndex).intValue(), false);
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
                            RealPlayActivity realPlayActivity5 = this.mRealPlayUi;
                            if (realPlayActivity5 != null) {
                                realPlayActivity5.SetStreamDecodeState(this.mIndexList.get(i20).intValue(), true);
                            }
                        } else {
                            this.mVideoFrame[this.mIndexList.get(i20).intValue()].setFocusState(false);
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
                    RealPlayActivity realPlayActivity6 = this.mRealPlayUi;
                    if (realPlayActivity6 != null) {
                        realPlayActivity6.SetStreamDecodeState(this.mIndexList.get(this.mBaseIndex).intValue(), false);
                    }
                }
                this.mFocusIndex = this.mBaseIndex;
                for (int i24 = 0; i24 < this.mInitViewCount; i24++) {
                    int i25 = this.mFocusIndex;
                    if (i24 == i25) {
                        this.mVideoFrame[this.mIndexList.get(i25).intValue()].setFocusState(true);
                        this.mRealPlayUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                    } else {
                        this.mVideoFrame[this.mIndexList.get(i24).intValue()].setFocusState(false);
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
                            RealPlayActivity realPlayActivity7 = this.mRealPlayUi;
                            if (realPlayActivity7 != null) {
                                realPlayActivity7.SetStreamDecodeState(this.mIndexList.get(i28).intValue(), true);
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
                            RealPlayActivity realPlayActivity8 = this.mRealPlayUi;
                            if (realPlayActivity8 != null) {
                                realPlayActivity8.SetStreamDecodeState(this.mIndexList.get(i34 + this.mBaseIndex).intValue(), false);
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
                            RealPlayActivity realPlayActivity9 = this.mRealPlayUi;
                            if (realPlayActivity9 != null) {
                                realPlayActivity9.SetStreamDecodeState(this.mIndexList.get(i36).intValue(), true);
                            }
                        } else {
                            this.mVideoFrame[this.mIndexList.get(i36).intValue()].setFocusState(false);
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
                    RealPlayActivity realPlayActivity10 = this.mRealPlayUi;
                    if (realPlayActivity10 != null) {
                        realPlayActivity10.SetStreamDecodeState(this.mIndexList.get(this.mBaseIndex).intValue(), false);
                    }
                }
                this.mFocusIndex = this.mBaseIndex;
                for (int i40 = 0; i40 < this.mInitViewCount; i40++) {
                    int i41 = this.mFocusIndex;
                    if (i40 == i41) {
                        this.mVideoFrame[this.mIndexList.get(i41).intValue()].setFocusState(true);
                        this.mRealPlayUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                    } else {
                        this.mVideoFrame[this.mIndexList.get(i40).intValue()].setFocusState(false);
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
                            RealPlayActivity realPlayActivity11 = this.mRealPlayUi;
                            if (realPlayActivity11 != null) {
                                realPlayActivity11.SetStreamDecodeState(this.mIndexList.get(i44).intValue(), true);
                            }
                        } else {
                            this.mVideoFrame[this.mIndexList.get(i44).intValue()].setFocusState(false);
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
                    RealPlayActivity realPlayActivity12 = this.mRealPlayUi;
                    if (realPlayActivity12 != null) {
                        realPlayActivity12.SetStreamDecodeState(this.mIndexList.get(this.mBaseIndex).intValue(), false);
                    }
                }
                this.mFocusIndex = this.mBaseIndex;
                for (int i48 = 0; i48 < this.mInitViewCount; i48++) {
                    int i49 = this.mFocusIndex;
                    if (i48 == i49) {
                        this.mVideoFrame[this.mIndexList.get(i49).intValue()].setFocusState(true);
                        this.mRealPlayUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                    } else {
                        this.mVideoFrame[this.mIndexList.get(i48).intValue()].setFocusState(false);
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
                        this.mVideoFrame[this.mIndexList.get(i52).intValue()].setFocusState(false);
                        if (i52 != this.mBaseIndex) {
                            this.mVideoFrame[this.mIndexList.get(i52).intValue()].setVisibility(8);
                            RealPlayActivity realPlayActivity13 = this.mRealPlayUi;
                            if (realPlayActivity13 != null) {
                                realPlayActivity13.SetStreamDecodeState(this.mIndexList.get(i52).intValue(), true);
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
                    RealPlayActivity realPlayActivity14 = this.mRealPlayUi;
                    if (realPlayActivity14 != null) {
                        realPlayActivity14.SetStreamDecodeState(this.mIndexList.get(this.mBaseIndex).intValue(), false);
                    }
                }
                this.mFocusIndex = this.mBaseIndex;
                for (int i55 = 0; i55 < this.mInitViewCount; i55++) {
                    int i56 = this.mFocusIndex;
                    if (i55 == i56) {
                        this.mVideoFrame[this.mIndexList.get(i56).intValue()].setFocusState(true);
                        this.mRealPlayUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                    } else {
                        this.mVideoFrame[this.mIndexList.get(i55).intValue()].setFocusState(false);
                    }
                }
            } else {
                return;
            }
        }
        if (this.mCurViewCount == 1 && this.mbPtz) {
            if ((this.mPtzState & (1 << this.mIndexList.get(this.mFocusIndex).intValue())) > 0) {
                SetPtzControlState(0);
            } else {
                SetPtzControlState(8);
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
        this.mContainer.bringChildToFront(this.mVideoFrame[this.mIndexList.get(this.mSourceIndex).intValue()]);
        this.mVideoFrame[this.mIndexList.get(this.mSourceIndex).intValue()].requestFocus();
        this.mVideoFrame[this.mIndexList.get(this.mSourceIndex).intValue()].bringToFront();
        FindDestination(motionEvent);
    }

    public void Dragging(MotionEvent motionEvent) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mSourceIndex).intValue()].getLayoutParams();
        layoutParams.leftMargin = (int) (motionEvent.getX() - ((float) (layoutParams.width / 2)));
        layoutParams.topMargin = (int) (motionEvent.getY() - ((float) (layoutParams.height / 2)));
        this.mVideoFrame[this.mIndexList.get(this.mSourceIndex).intValue()].setLayoutParams(layoutParams);
        FindDestination(motionEvent);
    }

    public void endDragging(MotionEvent motionEvent) {
        int sourceIndex = this.mSourceIndex;
        if (sourceIndex != this.mDestinationIndex) {
            this.mVideoFrame[this.mIndexList.get(sourceIndex).intValue()].setLayoutParams(this.mDestinationParams);
            this.mVideoFrame[this.mIndexList.get(this.mDestinationIndex).intValue()].setLayoutParams(this.mSourceParams);
            int sourceChannel = this.mIndexList.get(this.mSourceIndex).intValue();
            this.mIndexList.set(this.mSourceIndex, Integer.valueOf(this.mIndexList.get(this.mDestinationIndex).intValue()));
            this.mIndexList.set(this.mDestinationIndex, Integer.valueOf(sourceChannel));
        } else {
            this.mVideoFrame[this.mIndexList.get(sourceIndex).intValue()].setLayoutParams(this.mSourceParams);
        }
        for (int viewIndex = this.mBaseIndex; viewIndex < this.mCurViewCount + this.mBaseIndex; viewIndex++) {
            this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].SetDragState(false);
        }
    }

    public void FindDestination(MotionEvent motionEvent) {
        boolean foundDestination = false;
        for (int viewIndex = this.mBaseIndex; viewIndex < this.mCurViewCount + this.mBaseIndex; viewIndex++) {
            if (viewIndex != this.mSourceIndex) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].getLayoutParams();
                if (new Rect(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + layoutParams.width, layoutParams.topMargin + layoutParams.height).contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                    this.mDestinationIndex = viewIndex;
                    this.mDestinationParams = layoutParams;
                    this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].SetDragState(true);
                    foundDestination = true;
                } else {
                    this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].SetDragState(false);
                }
            }
        }
        if (!foundDestination) {
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
                this.mRealPlayUi.SetPtzState(false);
            } else if (((1 << this.mIndexList.get(this.mFocusIndex).intValue()) & ptzState) > 0) {
                SetPtzControlState(0);
                this.mRealPlayUi.SetPtzState(true);
            }
            return true;
        }
        this.mRealPlayUi.SetPtzState(false);
        return false;
    }

    public float GetDistance(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }

    private class FlingGestureDetector extends GestureDetector.SimpleOnGestureListener {
        public boolean mbMultiTouch;

        private FlingGestureDetector() {
            this.mbMultiTouch = false;
        }

        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return super.onDoubleTapEvent(motionEvent);
        }

        public boolean onDown(MotionEvent motionEvent) {
            return super.onDown(motionEvent);
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (VideoContainer.this.mTouchMode != 0) {
                boolean unused = VideoContainer.this.mLongPress = false;
            } else if (VideoContainer.this.mCurViewCount == 1) {
                boolean unused2 = VideoContainer.this.mLongPress = false;
            } else {
                boolean unused3 = VideoContainer.this.mLongPress = true;
                VideoContainer videoContainer = VideoContainer.this;
                int access$302 = videoContainer.mFocusIndex = videoContainer.getFocusView(new PointF(motionEvent.getX(), motionEvent.getY()));
                videoContainer.mDestinationIndex = access$302;
                videoContainer.mSourceIndex = access$302;
                VideoContainer.this.mSourceParams = (RelativeLayout.LayoutParams) VideoContainer.this.mVideoFrame[((Integer) VideoContainer.this.mIndexList.get(VideoContainer.this.mSourceIndex)).intValue()].getLayoutParams();
                VideoContainer.this.startDragging(motionEvent);
                super.onLongPress(motionEvent);
            }
        }

        public boolean onScroll(MotionEvent firstEvent, MotionEvent currentEvent, float distanceX, float distanceY) {
            if (currentEvent.getPointerCount() > 1) {
                this.mbMultiTouch = true;
            }
            return super.onScroll(firstEvent, currentEvent, distanceX, distanceY);
        }

        public void onShowPress(MotionEvent motionEvent) {
            super.onShowPress(motionEvent);
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (this.mbMultiTouch) {
                this.mbMultiTouch = false;
                return super.onSingleTapConfirmed(motionEvent);
            }
            VideoContainer.this.getFocusView(new PointF(motionEvent.getX(), motionEvent.getY()));
            return super.onSingleTapConfirmed(motionEvent);
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return super.onSingleTapUp(motionEvent);
        }

        public boolean onFling(MotionEvent startEvent, MotionEvent endEvent, float velocityX, float velocityY) {
            if (this.mbMultiTouch) {
                this.mbMultiTouch = false;
                return super.onFling(startEvent, endEvent, velocityX, velocityY);
            } else if (startEvent.getX() - endEvent.getX() > 100.0f) {
                VideoContainer.this.FlingLeft(startEvent.getX() - endEvent.getX());
                return super.onFling(startEvent, endEvent, velocityX, velocityY);
            } else if (startEvent.getX() - endEvent.getX() < -100.0f) {
                VideoContainer.this.FlingRight(startEvent.getX() - endEvent.getX());
                return super.onFling(startEvent, endEvent, velocityX, velocityY);
            } else if (startEvent.getY() - endEvent.getY() > 100.0f) {
                return super.onFling(startEvent, endEvent, velocityX, velocityY);
            } else {
                if (startEvent.getY() - endEvent.getY() < -100.0f) {
                    return super.onFling(startEvent, endEvent, velocityX, velocityY);
                }
                return super.onFling(startEvent, endEvent, velocityX, velocityY);
            }
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            VideoContainer.this.getFocusView(new PointF(motionEvent.getX(), motionEvent.getY()));
            if (VideoContainer.this.mCurViewCount == 1 && VideoContainer.this.mbPtz) {
                VideoContainer.this.SetPtzControlState(8);
            }
            if (VideoContainer.this.mInitViewCount == 1) {
                return super.onDoubleTap(motionEvent);
            }
            VideoContainer.this.SetFocusViewMax();
            Log.e("onDoubleTap", "onDoubleTap");
            return super.onDoubleTap(motionEvent);
        }
    }

    public int getFocusChannel() {
        return this.mIndexList.get(this.mFocusIndex).intValue();
    }

    public VideoView getVideoView(int channel) {
        return this.mVideoFrame[channel].GetVideoView();
    }

    public int getInitViewCount() {
        return this.mInitViewCount;
    }

    public int getCurViewCount() {
        return this.mCurViewCount;
    }

    public int getVideoFrameVisibility(int viewIndex) {
        return this.mVideoFrame[this.mIndexList.get(viewIndex).intValue()].getVisibility();
    }
}
