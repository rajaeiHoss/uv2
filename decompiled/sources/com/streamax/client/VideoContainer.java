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
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            postDelayed(new Runnable() {
                public void run() {
                    VideoContainer videoContainer = VideoContainer.this;
                    videoContainer.ArrayViews(videoContainer.mCurViewCount, true);
                }
            }, 100);
        }
        super.onLayout(z, i, i2, i3, i4);
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

    public VideoContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        if (this.mGestureDetector == null) {
            this.mGestureDetector = new GestureDetector(this.mContext, new FlingGestureDetector());
        }
        this.mContainer = this;
        LoadViews();
    }

    public void SetInitMode(int i) {
        this.mLastArrayMode = i;
        this.mCurViewCount = i;
        this.mInitViewCount = i;
        this.mIndexList.clear();
        for (int i2 = 0; i2 < this.mMax; i2++) {
            this.mIndexList.add(i2, Integer.valueOf(i2));
        }
        this.mFocusIndex = 0;
        for (int i3 = 0; i3 < this.mInitViewCount; i3++) {
            if (i3 == this.mFocusIndex) {
                this.mVideoFrame[this.mIndexList.get(i3).intValue()].setFocusState(true);
            } else {
                this.mVideoFrame[this.mIndexList.get(i3).intValue()].setFocusState(false);
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
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mInitViewCount; i++) {
            VideoFrame[] videoFrameArr = this.mVideoFrame;
            if (videoFrameArr[i] != null && videoFrameArr[i].getVisibility() == 0) {
                arrayList.add(Integer.valueOf(this.mIndexList.get(i).intValue()));
            }
        }
        return arrayList;
    }

    public void SetActivity(RealPlayActivity realPlayActivity) {
        this.mRealPlayUi = realPlayActivity;
    }

    public void LoadViews() {
        this.mVideoFrame = new VideoFrame[this.mMax];
        this.mIndexList.clear();
        setVerticalGravity(17);
        setHorizontalGravity(17);
        int i = 0;
        while (i < this.mMax) {
            int i2 = i + 1;
            this.mVideoFrame[i] = new VideoFrame(this.mContext, i2);
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
        for (int i3 = 0; i3 < this.mMax; i3++) {
            final int index = i3;
            this.mVideoFrame[i3].mImgAdd.setOnClickListener(new View.OnClickListener() {
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
                if (VideoContainer.this.mDvrNet != null && VideoContainer.this.mCurViewCount == 1) {
                    new Thread(new Runnable() {
                        public void run() {
                            VideoContainer.this.mDvrNet.PTZControl(((Integer) VideoContainer.this.mIndexList.get(VideoContainer.this.mFocusIndex)).intValue(), i, VideoContainer.this.mRealPlayUi.mApp.mPtzSpeed);
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
        int[] iArr = {R.id.ptz_control_left, R.id.ptz_control_right, R.id.ptz_control_up, R.id.ptz_control_down};
        for (int i4 = 0; i4 < 4; i4++) {
            ImageView imageView = (ImageView) findViewById(iArr[i4]);
            if (imageView != null) {
                imageView.setOnClickListener(this.mOnclickListener);
            }
        }
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
    }

    public void SetPlayState(int i, boolean z) {
        this.mVideoFrame[i].setPlayState(z);
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

    /* JADX WARNING: Removed duplicated region for block: B:443:0x0cdf  */
    /* JADX WARNING: Removed duplicated region for block: B:485:0x0e42  */
    /* JADX WARNING: Removed duplicated region for block: B:505:0x0efb  */
    /* JADX WARNING: Removed duplicated region for block: B:521:0x0f29  */
    /* JADX WARNING: Removed duplicated region for block: B:563:0x108c  */
    /* JADX WARNING: Removed duplicated region for block: B:583:0x1145  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void ArrayViews(int r17, boolean r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            int r2 = r16.getWidth()
            if (r2 == 0) goto L_0x1309
            int r2 = r16.getHeight()
            if (r2 != 0) goto L_0x0012
            goto L_0x1309
        L_0x0012:
            boolean r2 = r0.mLongPress
            if (r2 == 0) goto L_0x0017
            return
        L_0x0017:
            int r2 = r0.mInitViewCount
        L_0x0019:
            int r3 = r0.mMax
            r4 = 8
            r5 = 0
            if (r2 >= r3) goto L_0x0049
            com.streamax.client.VideoFrame[] r3 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r6 = r0.mIndexList
            java.lang.Object r6 = r6.get(r2)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r3 = r3[r6]
            r3.setVisibility(r4)
            com.streamax.client.VideoFrame[] r3 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r4 = r0.mIndexList
            java.lang.Object r4 = r4.get(r2)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            r3 = r3[r4]
            r3.setFocusState(r5)
            int r2 = r2 + 1
            goto L_0x0019
        L_0x0049:
            int r2 = r16.getWidth()
            r0.mWidth = r2
            int r2 = r16.getHeight()
            r0.mHeight = r2
            double r2 = (double) r1
            double r2 = java.lang.Math.sqrt(r2)
            int r2 = (int) r2
            int r3 = r0.mWidth
            int r3 = r3 / r2
            int r6 = r0.mHeight
            int r6 = r6 / r2
            int r7 = r0.mInitViewCount
            r8 = 1
            if (r7 != r8) goto L_0x00ea
            if (r1 <= r8) goto L_0x0069
            return
        L_0x0069:
            if (r1 != r8) goto L_0x12f8
            int r2 = r0.mFocusIndex
            com.streamax.client.VideoFrame[] r4 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r7 = r0.mIndexList
            java.lang.Object r7 = r7.get(r5)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r4 = r4[r7]
            android.view.ViewGroup$LayoutParams r4 = r4.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r4 = (android.widget.RelativeLayout.LayoutParams) r4
            if (r4 != 0) goto L_0x008a
            android.widget.RelativeLayout$LayoutParams r4 = new android.widget.RelativeLayout$LayoutParams
            r4.<init>(r3, r6)
        L_0x008a:
            r4.width = r3
            r4.height = r6
            r4.leftMargin = r5
            r4.topMargin = r5
            com.streamax.client.VideoFrame[] r3 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r6 = r0.mIndexList
            java.lang.Object r6 = r6.get(r2)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r3 = r3[r6]
            r3.setVisibility(r5)
            com.streamax.client.VideoFrame[] r3 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r6 = r0.mIndexList
            java.lang.Object r6 = r6.get(r2)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r3 = r3[r6]
            r3.setLayoutParams(r4)
            com.streamax.client.VideoFrame[] r3 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r4 = r0.mIndexList
            java.lang.Object r4 = r4.get(r2)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            r3 = r3[r4]
            r3.SetMax(r8)
            com.streamax.client.RealPlayActivity r3 = r0.mRealPlayUi
            if (r3 == 0) goto L_0x00de
            java.util.List<java.lang.Integer> r4 = r0.mIndexList
            java.lang.Object r2 = r4.get(r2)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            r3.SetStreamDecodeState(r2, r5)
        L_0x00de:
            if (r18 != 0) goto L_0x00e4
            int r2 = r0.mCurViewCount
            r0.mLastArrayMode = r2
        L_0x00e4:
            r0.mCurViewCount = r1
            r0.mBaseIndex = r5
            goto L_0x12f8
        L_0x00ea:
            r9 = -1
            r10 = 4
            if (r7 != r10) goto L_0x0274
            if (r1 <= r10) goto L_0x00f1
            return
        L_0x00f1:
            if (r1 != r8) goto L_0x01e1
            int r2 = r0.mFocusIndex
            if (r2 != r9) goto L_0x00f8
            r2 = 0
        L_0x00f8:
            r7 = 0
        L_0x00f9:
            int r9 = r0.mInitViewCount
            if (r7 >= r9) goto L_0x0164
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r7)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            r9.SetMax(r5)
            int r9 = r0.mFocusIndex
            if (r7 != r9) goto L_0x0128
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r7)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            r9.setFocusState(r8)
            goto L_0x0161
        L_0x0128:
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r7)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            r9.setVisibility(r4)
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r7)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            r9.setFocusState(r5)
            com.streamax.client.RealPlayActivity r9 = r0.mRealPlayUi
            if (r9 == 0) goto L_0x0161
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r7)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9.SetStreamDecodeState(r10, r8)
        L_0x0161:
            int r7 = r7 + 1
            goto L_0x00f9
        L_0x0164:
            com.streamax.client.VideoFrame[] r4 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r7 = r0.mIndexList
            java.lang.Object r7 = r7.get(r2)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r4 = r4[r7]
            android.view.ViewGroup$LayoutParams r4 = r4.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r4 = (android.widget.RelativeLayout.LayoutParams) r4
            if (r4 != 0) goto L_0x0181
            android.widget.RelativeLayout$LayoutParams r4 = new android.widget.RelativeLayout$LayoutParams
            r4.<init>(r3, r6)
        L_0x0181:
            r4.width = r3
            r4.height = r6
            r4.leftMargin = r5
            r4.topMargin = r5
            com.streamax.client.VideoFrame[] r3 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r6 = r0.mIndexList
            java.lang.Object r6 = r6.get(r2)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r3 = r3[r6]
            r3.setVisibility(r5)
            com.streamax.client.VideoFrame[] r3 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r6 = r0.mIndexList
            java.lang.Object r6 = r6.get(r2)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r3 = r3[r6]
            r3.setLayoutParams(r4)
            com.streamax.client.VideoFrame[] r3 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r4 = r0.mIndexList
            java.lang.Object r4 = r4.get(r2)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            r3 = r3[r4]
            r3.SetMax(r8)
            com.streamax.client.RealPlayActivity r3 = r0.mRealPlayUi
            if (r3 == 0) goto L_0x01d5
            java.util.List<java.lang.Integer> r4 = r0.mIndexList
            java.lang.Object r4 = r4.get(r2)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            r3.SetStreamDecodeState(r4, r5)
        L_0x01d5:
            if (r18 != 0) goto L_0x01db
            int r3 = r0.mCurViewCount
            r0.mLastArrayMode = r3
        L_0x01db:
            r0.mCurViewCount = r1
            r0.mBaseIndex = r2
            goto L_0x12f8
        L_0x01e1:
            if (r1 != r10) goto L_0x12f8
            r4 = 0
        L_0x01e4:
            if (r4 >= r2) goto L_0x0268
            r7 = 0
        L_0x01e7:
            if (r7 >= r2) goto L_0x0264
            com.streamax.client.VideoFrame[] r8 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            int r10 = r4 * r2
            int r10 = r10 + r7
            java.lang.Object r9 = r9.get(r10)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8 = r8[r9]
            android.view.ViewGroup$LayoutParams r8 = r8.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r8 = (android.widget.RelativeLayout.LayoutParams) r8
            if (r8 != 0) goto L_0x0209
            android.widget.RelativeLayout$LayoutParams r8 = new android.widget.RelativeLayout$LayoutParams
            r8.<init>(r3, r6)
        L_0x0209:
            r8.width = r3
            r8.height = r6
            int r9 = r3 * r7
            r8.leftMargin = r9
            int r9 = r6 * r4
            r8.topMargin = r9
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r10)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r9 = r9[r11]
            r9.setVisibility(r5)
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r10)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r9 = r9[r11]
            r9.setLayoutParams(r8)
            com.streamax.client.VideoFrame[] r8 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            java.lang.Object r9 = r9.get(r10)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8 = r8[r9]
            r8.SetMax(r5)
            com.streamax.client.RealPlayActivity r8 = r0.mRealPlayUi
            if (r8 == 0) goto L_0x0261
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            java.lang.Object r9 = r9.get(r10)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8.SetStreamDecodeState(r9, r5)
        L_0x0261:
            int r7 = r7 + 1
            goto L_0x01e7
        L_0x0264:
            int r4 = r4 + 1
            goto L_0x01e4
        L_0x0268:
            if (r18 != 0) goto L_0x026e
            int r2 = r0.mCurViewCount
            r0.mLastArrayMode = r2
        L_0x026e:
            r0.mCurViewCount = r1
            r0.mBaseIndex = r5
            goto L_0x12f8
        L_0x0274:
            r11 = 7
            r12 = 3
            r13 = 9
            r14 = 2
            if (r7 != r13) goto L_0x057f
            if (r1 <= r7) goto L_0x027e
            return
        L_0x027e:
            if (r1 != r8) goto L_0x0346
            int r2 = r0.mFocusIndex
            if (r2 != r9) goto L_0x0285
            r2 = 0
        L_0x0285:
            r7 = 0
        L_0x0286:
            int r9 = r0.mInitViewCount
            if (r7 >= r9) goto L_0x02cb
            int r9 = r0.mFocusIndex
            if (r7 != r9) goto L_0x028f
            goto L_0x02c8
        L_0x028f:
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r7)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            r9.setVisibility(r4)
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r7)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            r9.setFocusState(r5)
            com.streamax.client.RealPlayActivity r9 = r0.mRealPlayUi
            if (r9 == 0) goto L_0x02c8
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r7)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9.SetStreamDecodeState(r10, r8)
        L_0x02c8:
            int r7 = r7 + 1
            goto L_0x0286
        L_0x02cb:
            com.streamax.client.VideoFrame[] r4 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r7 = r0.mIndexList
            java.lang.Object r7 = r7.get(r2)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r4 = r4[r7]
            android.view.ViewGroup$LayoutParams r4 = r4.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r4 = (android.widget.RelativeLayout.LayoutParams) r4
            if (r4 != 0) goto L_0x02e8
            android.widget.RelativeLayout$LayoutParams r4 = new android.widget.RelativeLayout$LayoutParams
            r4.<init>(r3, r6)
        L_0x02e8:
            r4.width = r3
            r4.height = r6
            r4.leftMargin = r5
            r4.topMargin = r5
            com.streamax.client.VideoFrame[] r3 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r6 = r0.mIndexList
            java.lang.Object r6 = r6.get(r2)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r3 = r3[r6]
            r3.setVisibility(r5)
            com.streamax.client.VideoFrame[] r3 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r6 = r0.mIndexList
            java.lang.Object r6 = r6.get(r2)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r3 = r3[r6]
            r3.setLayoutParams(r4)
            com.streamax.client.VideoFrame[] r3 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r4 = r0.mIndexList
            java.lang.Object r4 = r4.get(r2)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            r3 = r3[r4]
            r3.SetMax(r8)
            com.streamax.client.RealPlayActivity r3 = r0.mRealPlayUi
            java.util.List<java.lang.Integer> r4 = r0.mIndexList
            java.lang.Object r4 = r4.get(r2)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            r3.SetStreamDecodeState(r4, r5)
            if (r18 != 0) goto L_0x0340
            int r3 = r0.mCurViewCount
            r0.mLastArrayMode = r3
        L_0x0340:
            r0.mCurViewCount = r1
            r0.mBaseIndex = r2
            goto L_0x12f8
        L_0x0346:
            if (r1 != r10) goto L_0x04ee
            int r7 = r0.mFocusIndex
            if (r7 < r4) goto L_0x034e
            r0.mFocusIndex = r11
        L_0x034e:
            int r7 = r0.mCurViewCount
            if (r7 != r10) goto L_0x041f
            int r7 = r0.mBaseIndex
            if (r7 != 0) goto L_0x0357
            goto L_0x0358
        L_0x0357:
            r10 = 0
        L_0x0358:
            r7 = 0
        L_0x0359:
            int r9 = r0.mInitViewCount
            if (r7 >= r9) goto L_0x038d
            if (r7 < r10) goto L_0x0364
            int r9 = r10 + r1
            if (r7 >= r9) goto L_0x0364
            goto L_0x038a
        L_0x0364:
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r7)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r9 = r9[r11]
            r9.setVisibility(r4)
            com.streamax.client.RealPlayActivity r9 = r0.mRealPlayUi
            if (r9 == 0) goto L_0x038a
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r7)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r9.SetStreamDecodeState(r11, r8)
        L_0x038a:
            int r7 = r7 + 1
            goto L_0x0359
        L_0x038d:
            r4 = 0
        L_0x038e:
            if (r4 >= r14) goto L_0x0413
            r7 = 0
        L_0x0391:
            if (r7 >= r14) goto L_0x040f
            com.streamax.client.VideoFrame[] r8 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            int r11 = r4 * r2
            int r11 = r11 + r7
            int r11 = r11 + r10
            java.lang.Object r9 = r9.get(r11)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8 = r8[r9]
            android.view.ViewGroup$LayoutParams r8 = r8.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r8 = (android.widget.RelativeLayout.LayoutParams) r8
            if (r8 != 0) goto L_0x03b4
            android.widget.RelativeLayout$LayoutParams r8 = new android.widget.RelativeLayout$LayoutParams
            r8.<init>(r3, r6)
        L_0x03b4:
            r8.width = r3
            r8.height = r6
            int r9 = r3 * r7
            r8.leftMargin = r9
            int r9 = r6 * r4
            r8.topMargin = r9
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r12 = r0.mIndexList
            java.lang.Object r12 = r12.get(r11)
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r9 = r9[r12]
            r9.setVisibility(r5)
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r12 = r0.mIndexList
            java.lang.Object r12 = r12.get(r11)
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r9 = r9[r12]
            r9.setLayoutParams(r8)
            com.streamax.client.VideoFrame[] r8 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            java.lang.Object r9 = r9.get(r11)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8 = r8[r9]
            r8.SetMax(r5)
            com.streamax.client.RealPlayActivity r8 = r0.mRealPlayUi
            if (r8 == 0) goto L_0x040c
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            java.lang.Object r9 = r9.get(r11)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8.SetStreamDecodeState(r9, r5)
        L_0x040c:
            int r7 = r7 + 1
            goto L_0x0391
        L_0x040f:
            int r4 = r4 + 1
            goto L_0x038e
        L_0x0413:
            if (r18 != 0) goto L_0x0419
            int r2 = r0.mCurViewCount
            r0.mLastArrayMode = r2
        L_0x0419:
            r0.mCurViewCount = r1
            r0.mBaseIndex = r10
            goto L_0x12f8
        L_0x041f:
            int r7 = r0.mFocusIndex
            if (r7 != r9) goto L_0x0424
            r7 = 0
        L_0x0424:
            int r7 = r7 / r10
            int r7 = r7 * 4
            r8 = 0
        L_0x0428:
            int r9 = r0.mInitViewCount
            if (r8 >= r9) goto L_0x045c
            if (r8 < r7) goto L_0x0433
            int r9 = r7 + r1
            if (r8 >= r9) goto L_0x0433
            goto L_0x0459
        L_0x0433:
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r8)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            r9.setVisibility(r4)
            com.streamax.client.RealPlayActivity r9 = r0.mRealPlayUi
            if (r9 == 0) goto L_0x0459
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r8)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9.SetStreamDecodeState(r10, r5)
        L_0x0459:
            int r8 = r8 + 1
            goto L_0x0428
        L_0x045c:
            r4 = 0
        L_0x045d:
            if (r4 >= r14) goto L_0x04e2
            r8 = 0
        L_0x0460:
            if (r8 >= r14) goto L_0x04de
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            int r11 = r4 * r2
            int r11 = r11 + r8
            int r11 = r11 + r7
            java.lang.Object r10 = r10.get(r11)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            android.view.ViewGroup$LayoutParams r9 = r9.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r9 = (android.widget.RelativeLayout.LayoutParams) r9
            if (r9 != 0) goto L_0x0483
            android.widget.RelativeLayout$LayoutParams r9 = new android.widget.RelativeLayout$LayoutParams
            r9.<init>(r3, r6)
        L_0x0483:
            r9.width = r3
            r9.height = r6
            int r10 = r3 * r8
            r9.leftMargin = r10
            int r10 = r6 * r4
            r9.topMargin = r10
            com.streamax.client.VideoFrame[] r10 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r12 = r0.mIndexList
            java.lang.Object r12 = r12.get(r11)
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r10 = r10[r12]
            r10.setVisibility(r5)
            com.streamax.client.VideoFrame[] r10 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r12 = r0.mIndexList
            java.lang.Object r12 = r12.get(r11)
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r10 = r10[r12]
            r10.setLayoutParams(r9)
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r11)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            r9.SetMax(r5)
            com.streamax.client.RealPlayActivity r9 = r0.mRealPlayUi
            if (r9 == 0) goto L_0x04db
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r11)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9.SetStreamDecodeState(r10, r5)
        L_0x04db:
            int r8 = r8 + 1
            goto L_0x0460
        L_0x04de:
            int r4 = r4 + 1
            goto L_0x045d
        L_0x04e2:
            if (r18 != 0) goto L_0x04e8
            int r2 = r0.mCurViewCount
            r0.mLastArrayMode = r2
        L_0x04e8:
            r0.mCurViewCount = r1
            r0.mBaseIndex = r7
            goto L_0x12f8
        L_0x04ee:
            if (r1 != r13) goto L_0x12f8
            r4 = 0
        L_0x04f1:
            if (r4 >= r12) goto L_0x0575
            r7 = 0
        L_0x04f4:
            if (r7 >= r12) goto L_0x0571
            com.streamax.client.VideoFrame[] r8 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            int r10 = r4 * r2
            int r10 = r10 + r7
            java.lang.Object r9 = r9.get(r10)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8 = r8[r9]
            android.view.ViewGroup$LayoutParams r8 = r8.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r8 = (android.widget.RelativeLayout.LayoutParams) r8
            if (r8 != 0) goto L_0x0516
            android.widget.RelativeLayout$LayoutParams r8 = new android.widget.RelativeLayout$LayoutParams
            r8.<init>(r3, r6)
        L_0x0516:
            r8.width = r3
            r8.height = r6
            int r9 = r3 * r7
            r8.leftMargin = r9
            int r9 = r6 * r4
            r8.topMargin = r9
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r10)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r9 = r9[r11]
            r9.setVisibility(r5)
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r10)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r9 = r9[r11]
            r9.setLayoutParams(r8)
            com.streamax.client.VideoFrame[] r8 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            java.lang.Object r9 = r9.get(r10)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8 = r8[r9]
            r8.SetMax(r5)
            com.streamax.client.RealPlayActivity r8 = r0.mRealPlayUi
            if (r8 == 0) goto L_0x056e
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            java.lang.Object r9 = r9.get(r10)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8.SetStreamDecodeState(r9, r5)
        L_0x056e:
            int r7 = r7 + 1
            goto L_0x04f4
        L_0x0571:
            int r4 = r4 + 1
            goto L_0x04f1
        L_0x0575:
            int r2 = r0.mCurViewCount
            r0.mLastArrayMode = r2
            r0.mCurViewCount = r1
            r0.mBaseIndex = r5
            goto L_0x12f8
        L_0x057f:
            r15 = 16
            if (r7 != r15) goto L_0x0a3a
            if (r1 <= r7) goto L_0x0586
            return
        L_0x0586:
            if (r1 != r8) goto L_0x0650
            int r2 = r0.mFocusIndex
            if (r2 != r9) goto L_0x058d
            r2 = 0
        L_0x058d:
            r7 = 0
        L_0x058e:
            int r9 = r0.mInitViewCount
            if (r7 >= r9) goto L_0x05d3
            int r9 = r0.mFocusIndex
            if (r7 != r9) goto L_0x0597
            goto L_0x05d0
        L_0x0597:
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r7)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            r9.setVisibility(r4)
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r7)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            r9.setFocusState(r5)
            com.streamax.client.RealPlayActivity r9 = r0.mRealPlayUi
            if (r9 == 0) goto L_0x05d0
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r7)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9.SetStreamDecodeState(r10, r8)
        L_0x05d0:
            int r7 = r7 + 1
            goto L_0x058e
        L_0x05d3:
            com.streamax.client.VideoFrame[] r4 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r7 = r0.mIndexList
            java.lang.Object r7 = r7.get(r2)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r4 = r4[r7]
            android.view.ViewGroup$LayoutParams r4 = r4.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r4 = (android.widget.RelativeLayout.LayoutParams) r4
            if (r4 != 0) goto L_0x05f0
            android.widget.RelativeLayout$LayoutParams r4 = new android.widget.RelativeLayout$LayoutParams
            r4.<init>(r3, r6)
        L_0x05f0:
            r4.width = r3
            r4.height = r6
            r4.leftMargin = r5
            r4.topMargin = r5
            com.streamax.client.VideoFrame[] r3 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r6 = r0.mIndexList
            java.lang.Object r6 = r6.get(r2)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r3 = r3[r6]
            r3.setVisibility(r5)
            com.streamax.client.VideoFrame[] r3 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r6 = r0.mIndexList
            java.lang.Object r6 = r6.get(r2)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r3 = r3[r6]
            r3.setLayoutParams(r4)
            com.streamax.client.VideoFrame[] r3 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r4 = r0.mIndexList
            java.lang.Object r4 = r4.get(r2)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            r3 = r3[r4]
            r3.SetMax(r8)
            com.streamax.client.RealPlayActivity r3 = r0.mRealPlayUi
            if (r3 == 0) goto L_0x0644
            java.util.List<java.lang.Integer> r4 = r0.mIndexList
            java.lang.Object r4 = r4.get(r2)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            r3.SetStreamDecodeState(r4, r5)
        L_0x0644:
            if (r18 != 0) goto L_0x064a
            int r3 = r0.mCurViewCount
            r0.mLastArrayMode = r3
        L_0x064a:
            r0.mCurViewCount = r1
            r0.mBaseIndex = r2
            goto L_0x12f8
        L_0x0650:
            r7 = 15
            if (r1 != r10) goto L_0x07fd
            int r11 = r0.mFocusIndex
            if (r11 < r15) goto L_0x065a
            r0.mFocusIndex = r7
        L_0x065a:
            int r7 = r0.mCurViewCount
            if (r7 != r10) goto L_0x072e
            int r7 = r0.mBaseIndex
            int r9 = r7 + 4
            if (r9 < r15) goto L_0x0666
            r7 = 0
            goto L_0x0667
        L_0x0666:
            int r7 = r7 + r10
        L_0x0667:
            r9 = 0
        L_0x0668:
            int r10 = r0.mInitViewCount
            if (r9 >= r10) goto L_0x069c
            if (r9 < r7) goto L_0x0673
            int r10 = r7 + r1
            if (r9 >= r10) goto L_0x0673
            goto L_0x0699
        L_0x0673:
            com.streamax.client.VideoFrame[] r10 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r9)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r10 = r10[r11]
            r10.setVisibility(r4)
            com.streamax.client.RealPlayActivity r10 = r0.mRealPlayUi
            if (r10 == 0) goto L_0x0699
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r9)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r10.SetStreamDecodeState(r11, r8)
        L_0x0699:
            int r9 = r9 + 1
            goto L_0x0668
        L_0x069c:
            r4 = 0
        L_0x069d:
            if (r4 >= r14) goto L_0x0722
            r8 = 0
        L_0x06a0:
            if (r8 >= r14) goto L_0x071e
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            int r11 = r4 * r2
            int r11 = r11 + r8
            int r11 = r11 + r7
            java.lang.Object r10 = r10.get(r11)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            android.view.ViewGroup$LayoutParams r9 = r9.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r9 = (android.widget.RelativeLayout.LayoutParams) r9
            if (r9 != 0) goto L_0x06c3
            android.widget.RelativeLayout$LayoutParams r9 = new android.widget.RelativeLayout$LayoutParams
            r9.<init>(r3, r6)
        L_0x06c3:
            r9.width = r3
            r9.height = r6
            int r10 = r3 * r8
            r9.leftMargin = r10
            int r10 = r6 * r4
            r9.topMargin = r10
            com.streamax.client.VideoFrame[] r10 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r12 = r0.mIndexList
            java.lang.Object r12 = r12.get(r11)
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r10 = r10[r12]
            r10.setVisibility(r5)
            com.streamax.client.VideoFrame[] r10 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r12 = r0.mIndexList
            java.lang.Object r12 = r12.get(r11)
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r10 = r10[r12]
            r10.setLayoutParams(r9)
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r11)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            r9.SetMax(r5)
            com.streamax.client.RealPlayActivity r9 = r0.mRealPlayUi
            if (r9 == 0) goto L_0x071b
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r11)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9.SetStreamDecodeState(r10, r5)
        L_0x071b:
            int r8 = r8 + 1
            goto L_0x06a0
        L_0x071e:
            int r4 = r4 + 1
            goto L_0x069d
        L_0x0722:
            if (r18 != 0) goto L_0x0728
            int r2 = r0.mCurViewCount
            r0.mLastArrayMode = r2
        L_0x0728:
            r0.mCurViewCount = r1
            r0.mBaseIndex = r7
            goto L_0x12f8
        L_0x072e:
            int r7 = r0.mFocusIndex
            if (r7 != r9) goto L_0x0733
            r7 = 0
        L_0x0733:
            int r7 = r7 / r10
            int r7 = r7 * 4
            r9 = 0
        L_0x0737:
            int r10 = r0.mInitViewCount
            if (r9 >= r10) goto L_0x076b
            if (r9 < r7) goto L_0x0742
            int r10 = r7 + r1
            if (r9 >= r10) goto L_0x0742
            goto L_0x0768
        L_0x0742:
            com.streamax.client.VideoFrame[] r10 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r9)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r10 = r10[r11]
            r10.setVisibility(r4)
            com.streamax.client.RealPlayActivity r10 = r0.mRealPlayUi
            if (r10 == 0) goto L_0x0768
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r9)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r10.SetStreamDecodeState(r11, r8)
        L_0x0768:
            int r9 = r9 + 1
            goto L_0x0737
        L_0x076b:
            r4 = 0
        L_0x076c:
            if (r4 >= r14) goto L_0x07f1
            r8 = 0
        L_0x076f:
            if (r8 >= r14) goto L_0x07ed
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            int r11 = r4 * r2
            int r11 = r11 + r8
            int r11 = r11 + r7
            java.lang.Object r10 = r10.get(r11)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            android.view.ViewGroup$LayoutParams r9 = r9.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r9 = (android.widget.RelativeLayout.LayoutParams) r9
            if (r9 != 0) goto L_0x0792
            android.widget.RelativeLayout$LayoutParams r9 = new android.widget.RelativeLayout$LayoutParams
            r9.<init>(r3, r6)
        L_0x0792:
            r9.width = r3
            r9.height = r6
            int r10 = r3 * r8
            r9.leftMargin = r10
            int r10 = r6 * r4
            r9.topMargin = r10
            com.streamax.client.VideoFrame[] r10 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r12 = r0.mIndexList
            java.lang.Object r12 = r12.get(r11)
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r10 = r10[r12]
            r10.setVisibility(r5)
            com.streamax.client.VideoFrame[] r10 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r12 = r0.mIndexList
            java.lang.Object r12 = r12.get(r11)
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r10 = r10[r12]
            r10.setLayoutParams(r9)
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r11)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            r9.SetMax(r5)
            com.streamax.client.RealPlayActivity r9 = r0.mRealPlayUi
            if (r9 == 0) goto L_0x07ea
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r11)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9.SetStreamDecodeState(r10, r5)
        L_0x07ea:
            int r8 = r8 + 1
            goto L_0x076f
        L_0x07ed:
            int r4 = r4 + 1
            goto L_0x076c
        L_0x07f1:
            if (r18 != 0) goto L_0x07f7
            int r2 = r0.mCurViewCount
            r0.mLastArrayMode = r2
        L_0x07f7:
            r0.mCurViewCount = r1
            r0.mBaseIndex = r7
            goto L_0x12f8
        L_0x07fd:
            if (r1 != r13) goto L_0x09a7
            int r10 = r0.mFocusIndex
            if (r10 < r15) goto L_0x0805
            r0.mFocusIndex = r7
        L_0x0805:
            int r7 = r0.mCurViewCount
            if (r7 != r13) goto L_0x08d4
            int r7 = r0.mBaseIndex
            int r7 = r7 + r13
            if (r7 < r15) goto L_0x080f
            r11 = 0
        L_0x080f:
            r7 = 0
        L_0x0810:
            int r9 = r0.mInitViewCount
            if (r7 >= r9) goto L_0x0842
            if (r7 < r11) goto L_0x081b
            int r9 = r11 + r1
            if (r7 >= r9) goto L_0x081b
            goto L_0x083f
        L_0x081b:
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r7)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            r9.setVisibility(r4)
            com.streamax.client.RealPlayActivity r9 = r0.mRealPlayUi
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r7)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9.SetStreamDecodeState(r10, r8)
        L_0x083f:
            int r7 = r7 + 1
            goto L_0x0810
        L_0x0842:
            r4 = 0
        L_0x0843:
            if (r4 >= r12) goto L_0x08c8
            r7 = 0
        L_0x0846:
            if (r7 >= r12) goto L_0x08c4
            com.streamax.client.VideoFrame[] r8 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            int r10 = r4 * r2
            int r10 = r10 + r7
            int r10 = r10 + r11
            java.lang.Object r9 = r9.get(r10)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8 = r8[r9]
            android.view.ViewGroup$LayoutParams r8 = r8.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r8 = (android.widget.RelativeLayout.LayoutParams) r8
            if (r8 != 0) goto L_0x0869
            android.widget.RelativeLayout$LayoutParams r8 = new android.widget.RelativeLayout$LayoutParams
            r8.<init>(r3, r6)
        L_0x0869:
            r8.width = r3
            r8.height = r6
            int r9 = r3 * r7
            r8.leftMargin = r9
            int r9 = r6 * r4
            r8.topMargin = r9
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r13 = r0.mIndexList
            java.lang.Object r13 = r13.get(r10)
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            r9 = r9[r13]
            r9.setVisibility(r5)
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r13 = r0.mIndexList
            java.lang.Object r13 = r13.get(r10)
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            r9 = r9[r13]
            r9.setLayoutParams(r8)
            com.streamax.client.VideoFrame[] r8 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            java.lang.Object r9 = r9.get(r10)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8 = r8[r9]
            r8.SetMax(r5)
            com.streamax.client.RealPlayActivity r8 = r0.mRealPlayUi
            if (r8 == 0) goto L_0x08c1
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            java.lang.Object r9 = r9.get(r10)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8.SetStreamDecodeState(r9, r5)
        L_0x08c1:
            int r7 = r7 + 1
            goto L_0x0846
        L_0x08c4:
            int r4 = r4 + 1
            goto L_0x0843
        L_0x08c8:
            if (r18 != 0) goto L_0x08ce
            int r2 = r0.mCurViewCount
            r0.mLastArrayMode = r2
        L_0x08ce:
            r0.mCurViewCount = r1
            r0.mBaseIndex = r11
            goto L_0x12f8
        L_0x08d4:
            int r7 = r0.mFocusIndex
            if (r7 != r9) goto L_0x08d9
            r7 = 0
        L_0x08d9:
            int r7 = r7 / r13
            int r7 = r7 * 9
            if (r7 < r13) goto L_0x08df
            goto L_0x08e0
        L_0x08df:
            r11 = 0
        L_0x08e0:
            r7 = 0
        L_0x08e1:
            int r8 = r0.mInitViewCount
            if (r7 >= r8) goto L_0x0915
            if (r7 < r11) goto L_0x08ec
            int r8 = r11 + r1
            if (r7 >= r8) goto L_0x08ec
            goto L_0x0912
        L_0x08ec:
            com.streamax.client.VideoFrame[] r8 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            java.lang.Object r9 = r9.get(r7)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8 = r8[r9]
            r8.setVisibility(r4)
            com.streamax.client.RealPlayActivity r8 = r0.mRealPlayUi
            if (r8 == 0) goto L_0x0912
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            java.lang.Object r9 = r9.get(r7)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8.SetStreamDecodeState(r9, r5)
        L_0x0912:
            int r7 = r7 + 1
            goto L_0x08e1
        L_0x0915:
            r4 = 0
        L_0x0916:
            if (r4 >= r12) goto L_0x099b
            r7 = 0
        L_0x0919:
            if (r7 >= r12) goto L_0x0997
            com.streamax.client.VideoFrame[] r8 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            int r10 = r4 * r2
            int r10 = r10 + r7
            int r10 = r10 + r11
            java.lang.Object r9 = r9.get(r10)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8 = r8[r9]
            android.view.ViewGroup$LayoutParams r8 = r8.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r8 = (android.widget.RelativeLayout.LayoutParams) r8
            if (r8 != 0) goto L_0x093c
            android.widget.RelativeLayout$LayoutParams r8 = new android.widget.RelativeLayout$LayoutParams
            r8.<init>(r3, r6)
        L_0x093c:
            r8.width = r3
            r8.height = r6
            int r9 = r3 * r7
            r8.leftMargin = r9
            int r9 = r6 * r4
            r8.topMargin = r9
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r13 = r0.mIndexList
            java.lang.Object r13 = r13.get(r10)
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            r9 = r9[r13]
            r9.setVisibility(r5)
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r13 = r0.mIndexList
            java.lang.Object r13 = r13.get(r10)
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            r9 = r9[r13]
            r9.setLayoutParams(r8)
            com.streamax.client.VideoFrame[] r8 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            java.lang.Object r9 = r9.get(r10)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8 = r8[r9]
            r8.SetMax(r5)
            com.streamax.client.RealPlayActivity r8 = r0.mRealPlayUi
            if (r8 == 0) goto L_0x0994
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            java.lang.Object r9 = r9.get(r10)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8.SetStreamDecodeState(r9, r5)
        L_0x0994:
            int r7 = r7 + 1
            goto L_0x0919
        L_0x0997:
            int r4 = r4 + 1
            goto L_0x0916
        L_0x099b:
            if (r18 != 0) goto L_0x09a1
            int r2 = r0.mCurViewCount
            r0.mLastArrayMode = r2
        L_0x09a1:
            r0.mCurViewCount = r1
            r0.mBaseIndex = r11
            goto L_0x12f8
        L_0x09a7:
            if (r1 != r15) goto L_0x12f8
            r4 = 0
        L_0x09aa:
            if (r4 >= r10) goto L_0x0a2e
            r7 = 0
        L_0x09ad:
            if (r7 >= r10) goto L_0x0a2a
            com.streamax.client.VideoFrame[] r8 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            int r11 = r4 * r2
            int r11 = r11 + r7
            java.lang.Object r9 = r9.get(r11)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8 = r8[r9]
            android.view.ViewGroup$LayoutParams r8 = r8.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r8 = (android.widget.RelativeLayout.LayoutParams) r8
            if (r8 != 0) goto L_0x09cf
            android.widget.RelativeLayout$LayoutParams r8 = new android.widget.RelativeLayout$LayoutParams
            r8.<init>(r3, r6)
        L_0x09cf:
            r8.width = r3
            r8.height = r6
            int r9 = r3 * r7
            r8.leftMargin = r9
            int r9 = r6 * r4
            r8.topMargin = r9
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r12 = r0.mIndexList
            java.lang.Object r12 = r12.get(r11)
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r9 = r9[r12]
            r9.setVisibility(r5)
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r12 = r0.mIndexList
            java.lang.Object r12 = r12.get(r11)
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r9 = r9[r12]
            r9.setLayoutParams(r8)
            com.streamax.client.VideoFrame[] r8 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            java.lang.Object r9 = r9.get(r11)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8 = r8[r9]
            r8.SetMax(r5)
            com.streamax.client.RealPlayActivity r8 = r0.mRealPlayUi
            if (r8 == 0) goto L_0x0a27
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            java.lang.Object r9 = r9.get(r11)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8.SetStreamDecodeState(r9, r5)
        L_0x0a27:
            int r7 = r7 + 1
            goto L_0x09ad
        L_0x0a2a:
            int r4 = r4 + 1
            goto L_0x09aa
        L_0x0a2e:
            if (r18 != 0) goto L_0x0a34
            int r2 = r0.mCurViewCount
            r0.mLastArrayMode = r2
        L_0x0a34:
            r0.mCurViewCount = r1
            r0.mBaseIndex = r5
            goto L_0x12f8
        L_0x0a3a:
            r11 = 32
            if (r7 != r11) goto L_0x12f8
            if (r1 <= r7) goto L_0x0a41
            return
        L_0x0a41:
            if (r1 != r8) goto L_0x0b0d
            int r2 = r0.mFocusIndex
            if (r2 != r9) goto L_0x0a48
            r2 = 0
        L_0x0a48:
            r7 = 0
        L_0x0a49:
            int r9 = r0.mInitViewCount
            if (r7 >= r9) goto L_0x0aee
            int r9 = r0.mFocusIndex
            if (r7 != r9) goto L_0x0a53
            goto L_0x0aea
        L_0x0a53:
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r7)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            r9.setVisibility(r4)
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r7)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            r9.setFocusState(r5)
            com.streamax.client.RealPlayActivity r9 = r0.mRealPlayUi
            if (r9 == 0) goto L_0x0a8c
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r7)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9.SetStreamDecodeState(r10, r8)
        L_0x0a8c:
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r2)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            android.view.ViewGroup$LayoutParams r9 = r9.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r9 = (android.widget.RelativeLayout.LayoutParams) r9
            if (r9 != 0) goto L_0x0aa9
            android.widget.RelativeLayout$LayoutParams r9 = new android.widget.RelativeLayout$LayoutParams
            r9.<init>(r3, r6)
        L_0x0aa9:
            r9.width = r3
            r9.height = r6
            r9.leftMargin = r5
            r9.topMargin = r5
            com.streamax.client.VideoFrame[] r10 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r2)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r10 = r10[r11]
            r10.setVisibility(r5)
            com.streamax.client.VideoFrame[] r10 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r2)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r10 = r10[r11]
            r10.setLayoutParams(r9)
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r2)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            r9.SetMax(r8)
        L_0x0aea:
            int r7 = r7 + 1
            goto L_0x0a49
        L_0x0aee:
            com.streamax.client.RealPlayActivity r3 = r0.mRealPlayUi
            if (r3 == 0) goto L_0x0b01
            java.util.List<java.lang.Integer> r4 = r0.mIndexList
            java.lang.Object r4 = r4.get(r2)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            r3.SetStreamDecodeState(r4, r5)
        L_0x0b01:
            if (r18 != 0) goto L_0x0b07
            int r3 = r0.mCurViewCount
            r0.mLastArrayMode = r3
        L_0x0b07:
            r0.mCurViewCount = r1
            r0.mBaseIndex = r2
            goto L_0x12f8
        L_0x0b0d:
            r7 = 31
            if (r1 != r10) goto L_0x0cb2
            int r12 = r0.mFocusIndex
            if (r12 < r11) goto L_0x0b17
            r0.mFocusIndex = r7
        L_0x0b17:
            int r7 = r0.mCurViewCount
            if (r7 != r10) goto L_0x0be5
            int r7 = r0.mBaseIndex
            int r7 = r7 + r10
            r7 = 0
        L_0x0b1f:
            int r9 = r0.mInitViewCount
            if (r7 >= r9) goto L_0x0b53
            if (r7 < 0) goto L_0x0b2a
            int r9 = r5 + r1
            if (r7 >= r9) goto L_0x0b2a
            goto L_0x0b50
        L_0x0b2a:
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r7)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            r9.setVisibility(r4)
            com.streamax.client.RealPlayActivity r9 = r0.mRealPlayUi
            if (r9 == 0) goto L_0x0b50
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r7)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9.SetStreamDecodeState(r10, r8)
        L_0x0b50:
            int r7 = r7 + 1
            goto L_0x0b1f
        L_0x0b53:
            r4 = 0
        L_0x0b54:
            if (r4 >= r14) goto L_0x0bd9
            r7 = 0
        L_0x0b57:
            if (r7 >= r14) goto L_0x0bd5
            com.streamax.client.VideoFrame[] r8 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            int r10 = r4 * r2
            int r10 = r10 + r7
            int r10 = r10 + r5
            java.lang.Object r9 = r9.get(r10)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8 = r8[r9]
            android.view.ViewGroup$LayoutParams r8 = r8.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r8 = (android.widget.RelativeLayout.LayoutParams) r8
            if (r8 != 0) goto L_0x0b7a
            android.widget.RelativeLayout$LayoutParams r8 = new android.widget.RelativeLayout$LayoutParams
            r8.<init>(r3, r6)
        L_0x0b7a:
            r8.width = r3
            r8.height = r6
            int r9 = r3 * r7
            r8.leftMargin = r9
            int r9 = r6 * r4
            r8.topMargin = r9
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r10)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r9 = r9[r11]
            r9.setVisibility(r5)
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r10)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r9 = r9[r11]
            r9.setLayoutParams(r8)
            com.streamax.client.VideoFrame[] r8 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            java.lang.Object r9 = r9.get(r10)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8 = r8[r9]
            r8.SetMax(r5)
            com.streamax.client.RealPlayActivity r8 = r0.mRealPlayUi
            if (r8 == 0) goto L_0x0bd2
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            java.lang.Object r9 = r9.get(r10)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8.SetStreamDecodeState(r9, r5)
        L_0x0bd2:
            int r7 = r7 + 1
            goto L_0x0b57
        L_0x0bd5:
            int r4 = r4 + 1
            goto L_0x0b54
        L_0x0bd9:
            if (r18 != 0) goto L_0x0bdf
            int r2 = r0.mCurViewCount
            r0.mLastArrayMode = r2
        L_0x0bdf:
            r0.mCurViewCount = r1
            r0.mBaseIndex = r5
            goto L_0x12f8
        L_0x0be5:
            int r7 = r0.mFocusIndex
            if (r7 != r9) goto L_0x0bea
            r7 = 0
        L_0x0bea:
            int r7 = r7 / r10
            int r7 = r7 * 4
            r9 = 0
        L_0x0bee:
            int r10 = r0.mInitViewCount
            if (r9 >= r10) goto L_0x0c22
            if (r9 < r7) goto L_0x0bf9
            int r10 = r7 + r1
            if (r9 >= r10) goto L_0x0bf9
            goto L_0x0c1f
        L_0x0bf9:
            com.streamax.client.VideoFrame[] r10 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r9)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r10 = r10[r11]
            r10.setVisibility(r4)
            com.streamax.client.RealPlayActivity r10 = r0.mRealPlayUi
            if (r10 == 0) goto L_0x0c1f
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r9)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r10.SetStreamDecodeState(r11, r8)
        L_0x0c1f:
            int r9 = r9 + 1
            goto L_0x0bee
        L_0x0c22:
            r4 = 0
        L_0x0c23:
            if (r4 >= r14) goto L_0x0ca8
            r8 = 0
        L_0x0c26:
            if (r8 >= r14) goto L_0x0ca4
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            int r11 = r4 * r2
            int r11 = r11 + r8
            int r11 = r11 + r7
            java.lang.Object r10 = r10.get(r11)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            android.view.ViewGroup$LayoutParams r9 = r9.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r9 = (android.widget.RelativeLayout.LayoutParams) r9
            if (r9 != 0) goto L_0x0c49
            android.widget.RelativeLayout$LayoutParams r9 = new android.widget.RelativeLayout$LayoutParams
            r9.<init>(r3, r6)
        L_0x0c49:
            r9.width = r3
            r9.height = r6
            int r10 = r3 * r8
            r9.leftMargin = r10
            int r10 = r6 * r4
            r9.topMargin = r10
            com.streamax.client.VideoFrame[] r10 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r12 = r0.mIndexList
            java.lang.Object r12 = r12.get(r11)
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r10 = r10[r12]
            r10.setVisibility(r5)
            com.streamax.client.VideoFrame[] r10 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r12 = r0.mIndexList
            java.lang.Object r12 = r12.get(r11)
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r10 = r10[r12]
            r10.setLayoutParams(r9)
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r11)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            r9.SetMax(r5)
            com.streamax.client.RealPlayActivity r9 = r0.mRealPlayUi
            if (r9 == 0) goto L_0x0ca1
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r11)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9.SetStreamDecodeState(r10, r5)
        L_0x0ca1:
            int r8 = r8 + 1
            goto L_0x0c26
        L_0x0ca4:
            int r4 = r4 + 1
            goto L_0x0c23
        L_0x0ca8:
            if (r18 != 0) goto L_0x0cae
            int r2 = r0.mCurViewCount
            r0.mLastArrayMode = r2
        L_0x0cae:
            r0.mCurViewCount = r1
            goto L_0x12f8
        L_0x0cb2:
            if (r1 != r13) goto L_0x114f
            int r9 = r0.mFocusIndex
            if (r9 < r11) goto L_0x0cba
            r0.mFocusIndex = r7
        L_0x0cba:
            int r7 = r0.mCurViewCount
            r9 = 27
            if (r7 != r13) goto L_0x0f05
            int r10 = r0.mFlingStatus
            if (r10 != r8) goto L_0x0cce
            if (r7 != r13) goto L_0x0cda
            int r7 = r0.mBaseIndex
            if (r7 != 0) goto L_0x0ccb
            goto L_0x0cdb
        L_0x0ccb:
            int r9 = r7 + -9
            goto L_0x0cdb
        L_0x0cce:
            if (r10 != 0) goto L_0x0cda
            int r7 = r0.mBaseIndex
            int r9 = r7 + 9
            if (r9 < r11) goto L_0x0cd7
            goto L_0x0cda
        L_0x0cd7:
            int r9 = r7 + 9
            goto L_0x0cdb
        L_0x0cda:
            r9 = 0
        L_0x0cdb:
            int r7 = r9 + r1
            if (r7 < r11) goto L_0x0e42
            r7 = 0
        L_0x0ce0:
            int r10 = r0.mInitViewCount
            if (r7 >= r10) goto L_0x0d15
            if (r7 < r9) goto L_0x0ce8
            if (r7 < r11) goto L_0x0d12
        L_0x0ce8:
            int r10 = r10 - r9
            int r10 = r1 - r10
            if (r7 >= r10) goto L_0x0cee
            goto L_0x0d12
        L_0x0cee:
            com.streamax.client.VideoFrame[] r10 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r13 = r0.mIndexList
            java.lang.Object r13 = r13.get(r7)
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            r10 = r10[r13]
            r10.setVisibility(r4)
            com.streamax.client.RealPlayActivity r10 = r0.mRealPlayUi
            java.util.List<java.lang.Integer> r13 = r0.mIndexList
            java.lang.Object r13 = r13.get(r7)
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            r10.SetStreamDecodeState(r13, r8)
        L_0x0d12:
            int r7 = r7 + 1
            goto L_0x0ce0
        L_0x0d15:
            r4 = 0
        L_0x0d16:
            if (r4 >= r12) goto L_0x0da0
            r7 = 0
        L_0x0d19:
            if (r7 >= r12) goto L_0x0d9c
            int r10 = r4 * r2
            int r10 = r10 + r7
            int r10 = r10 + r9
            int r11 = r0.mInitViewCount
            if (r10 >= r11) goto L_0x0d98
            com.streamax.client.VideoFrame[] r11 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r13 = r0.mIndexList
            java.lang.Object r13 = r13.get(r10)
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            r11 = r11[r13]
            android.view.ViewGroup$LayoutParams r11 = r11.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r11 = (android.widget.RelativeLayout.LayoutParams) r11
            if (r11 != 0) goto L_0x0d40
            android.widget.RelativeLayout$LayoutParams r11 = new android.widget.RelativeLayout$LayoutParams
            r11.<init>(r3, r6)
        L_0x0d40:
            r11.width = r3
            r11.height = r6
            int r13 = r3 * r7
            r11.leftMargin = r13
            int r13 = r6 * r4
            r11.topMargin = r13
            com.streamax.client.VideoFrame[] r13 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r15 = r0.mIndexList
            java.lang.Object r15 = r15.get(r10)
            java.lang.Integer r15 = (java.lang.Integer) r15
            int r15 = r15.intValue()
            r13 = r13[r15]
            r13.setVisibility(r5)
            com.streamax.client.VideoFrame[] r13 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r15 = r0.mIndexList
            java.lang.Object r15 = r15.get(r10)
            java.lang.Integer r15 = (java.lang.Integer) r15
            int r15 = r15.intValue()
            r13 = r13[r15]
            r13.setLayoutParams(r11)
            com.streamax.client.VideoFrame[] r11 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r13 = r0.mIndexList
            java.lang.Object r13 = r13.get(r10)
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            r11 = r11[r13]
            r11.SetMax(r5)
            com.streamax.client.RealPlayActivity r11 = r0.mRealPlayUi
            if (r11 == 0) goto L_0x0d98
            java.util.List<java.lang.Integer> r13 = r0.mIndexList
            java.lang.Object r10 = r13.get(r10)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r11.SetStreamDecodeState(r10, r5)
        L_0x0d98:
            int r7 = r7 + 1
            goto L_0x0d19
        L_0x0d9c:
            int r4 = r4 + 1
            goto L_0x0d16
        L_0x0da0:
            r2 = 0
        L_0x0da1:
            int r4 = r0.mInitViewCount
            int r4 = r4 - r9
            if (r2 >= r4) goto L_0x0ef9
            com.streamax.client.VideoFrame[] r4 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r7 = r0.mIndexList
            java.lang.Object r7 = r7.get(r2)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r4 = r4[r7]
            android.view.ViewGroup$LayoutParams r4 = r4.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r4 = (android.widget.RelativeLayout.LayoutParams) r4
            if (r4 != 0) goto L_0x0dc3
            android.widget.RelativeLayout$LayoutParams r4 = new android.widget.RelativeLayout$LayoutParams
            r4.<init>(r3, r6)
        L_0x0dc3:
            r4.width = r3
            r4.height = r6
            if (r2 != 0) goto L_0x0dd2
            int r7 = r3 * 2
            r4.leftMargin = r7
            int r7 = r6 * 1
            r4.topMargin = r7
            goto L_0x0df2
        L_0x0dd2:
            if (r2 != r8) goto L_0x0ddd
            int r7 = r3 * 0
            r4.leftMargin = r7
            int r7 = r6 * 2
            r4.topMargin = r7
            goto L_0x0df2
        L_0x0ddd:
            if (r2 != r14) goto L_0x0de8
            int r7 = r3 * 1
            r4.leftMargin = r7
            int r7 = r6 * 2
            r4.topMargin = r7
            goto L_0x0df2
        L_0x0de8:
            if (r2 != r12) goto L_0x0df2
            int r7 = r3 * 2
            r4.leftMargin = r7
            int r7 = r6 * 2
            r4.topMargin = r7
        L_0x0df2:
            com.streamax.client.VideoFrame[] r7 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r2)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r7 = r7[r10]
            r7.setVisibility(r5)
            com.streamax.client.VideoFrame[] r7 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r2)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r7 = r7[r10]
            r7.setLayoutParams(r4)
            com.streamax.client.VideoFrame[] r4 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r7 = r0.mIndexList
            java.lang.Object r7 = r7.get(r2)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r4 = r4[r7]
            r4.SetMax(r5)
            com.streamax.client.RealPlayActivity r4 = r0.mRealPlayUi
            if (r4 == 0) goto L_0x0e3e
            java.util.List<java.lang.Integer> r7 = r0.mIndexList
            java.lang.Object r7 = r7.get(r2)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r4.SetStreamDecodeState(r7, r5)
        L_0x0e3e:
            int r2 = r2 + 1
            goto L_0x0da1
        L_0x0e42:
            r10 = 0
        L_0x0e43:
            int r11 = r0.mInitViewCount
            if (r10 >= r11) goto L_0x0e73
            if (r10 < r9) goto L_0x0e4c
            if (r10 >= r7) goto L_0x0e4c
            goto L_0x0e70
        L_0x0e4c:
            com.streamax.client.VideoFrame[] r11 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r13 = r0.mIndexList
            java.lang.Object r13 = r13.get(r10)
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            r11 = r11[r13]
            r11.setVisibility(r4)
            com.streamax.client.RealPlayActivity r11 = r0.mRealPlayUi
            java.util.List<java.lang.Integer> r13 = r0.mIndexList
            java.lang.Object r13 = r13.get(r10)
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            r11.SetStreamDecodeState(r13, r8)
        L_0x0e70:
            int r10 = r10 + 1
            goto L_0x0e43
        L_0x0e73:
            r4 = 0
        L_0x0e74:
            if (r4 >= r12) goto L_0x0ef9
            r7 = 0
        L_0x0e77:
            if (r7 >= r12) goto L_0x0ef5
            com.streamax.client.VideoFrame[] r8 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            int r11 = r4 * r2
            int r11 = r11 + r7
            int r11 = r11 + r9
            java.lang.Object r10 = r10.get(r11)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r8 = r8[r10]
            android.view.ViewGroup$LayoutParams r8 = r8.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r8 = (android.widget.RelativeLayout.LayoutParams) r8
            if (r8 != 0) goto L_0x0e9a
            android.widget.RelativeLayout$LayoutParams r8 = new android.widget.RelativeLayout$LayoutParams
            r8.<init>(r3, r6)
        L_0x0e9a:
            r8.width = r3
            r8.height = r6
            int r10 = r3 * r7
            r8.leftMargin = r10
            int r10 = r6 * r4
            r8.topMargin = r10
            com.streamax.client.VideoFrame[] r10 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r13 = r0.mIndexList
            java.lang.Object r13 = r13.get(r11)
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            r10 = r10[r13]
            r10.setVisibility(r5)
            com.streamax.client.VideoFrame[] r10 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r13 = r0.mIndexList
            java.lang.Object r13 = r13.get(r11)
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            r10 = r10[r13]
            r10.setLayoutParams(r8)
            com.streamax.client.VideoFrame[] r8 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r11)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r8 = r8[r10]
            r8.SetMax(r5)
            com.streamax.client.RealPlayActivity r8 = r0.mRealPlayUi
            if (r8 == 0) goto L_0x0ef2
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r11)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r8.SetStreamDecodeState(r10, r5)
        L_0x0ef2:
            int r7 = r7 + 1
            goto L_0x0e77
        L_0x0ef5:
            int r4 = r4 + 1
            goto L_0x0e74
        L_0x0ef9:
            if (r18 != 0) goto L_0x0eff
            int r2 = r0.mCurViewCount
            r0.mLastArrayMode = r2
        L_0x0eff:
            r0.mCurViewCount = r1
            r0.mBaseIndex = r9
            goto L_0x12f8
        L_0x0f05:
            int r7 = r0.mFocusIndex
            r10 = 18
            if (r7 >= r13) goto L_0x0f0f
            r0.mBaseIndex = r5
        L_0x0f0d:
            r13 = 0
            goto L_0x0f25
        L_0x0f0f:
            if (r7 < r13) goto L_0x0f16
            if (r7 >= r10) goto L_0x0f16
            r0.mBaseIndex = r13
            goto L_0x0f25
        L_0x0f16:
            if (r7 < r10) goto L_0x0f1f
            if (r7 >= r9) goto L_0x0f1f
            r0.mBaseIndex = r10
            r13 = 18
            goto L_0x0f25
        L_0x0f1f:
            if (r7 < r9) goto L_0x0f0d
            r0.mBaseIndex = r9
            r13 = 27
        L_0x0f25:
            int r7 = r13 + r1
            if (r7 < r11) goto L_0x108c
            r7 = 0
        L_0x0f2a:
            int r9 = r0.mInitViewCount
            if (r7 >= r9) goto L_0x0f5f
            if (r7 < r13) goto L_0x0f32
            if (r7 < r11) goto L_0x0f5c
        L_0x0f32:
            int r9 = r9 - r13
            int r9 = r1 - r9
            if (r7 >= r9) goto L_0x0f38
            goto L_0x0f5c
        L_0x0f38:
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r7)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9 = r9[r10]
            r9.setVisibility(r4)
            com.streamax.client.RealPlayActivity r9 = r0.mRealPlayUi
            java.util.List<java.lang.Integer> r10 = r0.mIndexList
            java.lang.Object r10 = r10.get(r7)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r9.SetStreamDecodeState(r10, r8)
        L_0x0f5c:
            int r7 = r7 + 1
            goto L_0x0f2a
        L_0x0f5f:
            r4 = 0
        L_0x0f60:
            if (r4 >= r12) goto L_0x0fea
            r7 = 0
        L_0x0f63:
            if (r7 >= r12) goto L_0x0fe6
            int r9 = r4 * r2
            int r9 = r9 + r7
            int r9 = r9 + r13
            int r10 = r0.mInitViewCount
            if (r9 >= r10) goto L_0x0fe2
            com.streamax.client.VideoFrame[] r10 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r9)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r10 = r10[r11]
            android.view.ViewGroup$LayoutParams r10 = r10.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r10 = (android.widget.RelativeLayout.LayoutParams) r10
            if (r10 != 0) goto L_0x0f8a
            android.widget.RelativeLayout$LayoutParams r10 = new android.widget.RelativeLayout$LayoutParams
            r10.<init>(r3, r6)
        L_0x0f8a:
            r10.width = r3
            r10.height = r6
            int r11 = r3 * r7
            r10.leftMargin = r11
            int r11 = r6 * r4
            r10.topMargin = r11
            com.streamax.client.VideoFrame[] r11 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r15 = r0.mIndexList
            java.lang.Object r15 = r15.get(r9)
            java.lang.Integer r15 = (java.lang.Integer) r15
            int r15 = r15.intValue()
            r11 = r11[r15]
            r11.setVisibility(r5)
            com.streamax.client.VideoFrame[] r11 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r15 = r0.mIndexList
            java.lang.Object r15 = r15.get(r9)
            java.lang.Integer r15 = (java.lang.Integer) r15
            int r15 = r15.intValue()
            r11 = r11[r15]
            r11.setLayoutParams(r10)
            com.streamax.client.VideoFrame[] r10 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r9)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r10 = r10[r11]
            r10.SetMax(r5)
            com.streamax.client.RealPlayActivity r10 = r0.mRealPlayUi
            if (r10 == 0) goto L_0x0fe2
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r9 = r11.get(r9)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r10.SetStreamDecodeState(r9, r5)
        L_0x0fe2:
            int r7 = r7 + 1
            goto L_0x0f63
        L_0x0fe6:
            int r4 = r4 + 1
            goto L_0x0f60
        L_0x0fea:
            r2 = 0
        L_0x0feb:
            int r4 = r0.mInitViewCount
            int r4 = r4 - r13
            if (r2 >= r4) goto L_0x1143
            com.streamax.client.VideoFrame[] r4 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r7 = r0.mIndexList
            java.lang.Object r7 = r7.get(r2)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r4 = r4[r7]
            android.view.ViewGroup$LayoutParams r4 = r4.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r4 = (android.widget.RelativeLayout.LayoutParams) r4
            if (r4 != 0) goto L_0x100d
            android.widget.RelativeLayout$LayoutParams r4 = new android.widget.RelativeLayout$LayoutParams
            r4.<init>(r3, r6)
        L_0x100d:
            r4.width = r3
            r4.height = r6
            if (r2 != 0) goto L_0x101c
            int r7 = r3 * 2
            r4.leftMargin = r7
            int r7 = r6 * 1
            r4.topMargin = r7
            goto L_0x103c
        L_0x101c:
            if (r2 != r8) goto L_0x1027
            int r7 = r3 * 0
            r4.leftMargin = r7
            int r7 = r6 * 2
            r4.topMargin = r7
            goto L_0x103c
        L_0x1027:
            if (r2 != r14) goto L_0x1032
            int r7 = r3 * 1
            r4.leftMargin = r7
            int r7 = r6 * 2
            r4.topMargin = r7
            goto L_0x103c
        L_0x1032:
            if (r2 != r12) goto L_0x103c
            int r7 = r3 * 2
            r4.leftMargin = r7
            int r7 = r6 * 2
            r4.topMargin = r7
        L_0x103c:
            com.streamax.client.VideoFrame[] r7 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            java.lang.Object r9 = r9.get(r2)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r7 = r7[r9]
            r7.setVisibility(r5)
            com.streamax.client.VideoFrame[] r7 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            java.lang.Object r9 = r9.get(r2)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r7 = r7[r9]
            r7.setLayoutParams(r4)
            com.streamax.client.VideoFrame[] r4 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r7 = r0.mIndexList
            java.lang.Object r7 = r7.get(r2)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r4 = r4[r7]
            r4.SetMax(r5)
            com.streamax.client.RealPlayActivity r4 = r0.mRealPlayUi
            if (r4 == 0) goto L_0x1088
            java.util.List<java.lang.Integer> r7 = r0.mIndexList
            java.lang.Object r7 = r7.get(r2)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r4.SetStreamDecodeState(r7, r5)
        L_0x1088:
            int r2 = r2 + 1
            goto L_0x0feb
        L_0x108c:
            r9 = 0
        L_0x108d:
            int r10 = r0.mInitViewCount
            if (r9 >= r10) goto L_0x10bd
            if (r9 < r13) goto L_0x1096
            if (r9 >= r7) goto L_0x1096
            goto L_0x10ba
        L_0x1096:
            com.streamax.client.VideoFrame[] r10 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r9)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r10 = r10[r11]
            r10.setVisibility(r4)
            com.streamax.client.RealPlayActivity r10 = r0.mRealPlayUi
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r9)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r10.SetStreamDecodeState(r11, r8)
        L_0x10ba:
            int r9 = r9 + 1
            goto L_0x108d
        L_0x10bd:
            r4 = 0
        L_0x10be:
            if (r4 >= r12) goto L_0x1143
            r7 = 0
        L_0x10c1:
            if (r7 >= r12) goto L_0x113f
            com.streamax.client.VideoFrame[] r8 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            int r10 = r4 * r2
            int r10 = r10 + r7
            int r10 = r10 + r13
            java.lang.Object r9 = r9.get(r10)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8 = r8[r9]
            android.view.ViewGroup$LayoutParams r8 = r8.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r8 = (android.widget.RelativeLayout.LayoutParams) r8
            if (r8 != 0) goto L_0x10e4
            android.widget.RelativeLayout$LayoutParams r8 = new android.widget.RelativeLayout$LayoutParams
            r8.<init>(r3, r6)
        L_0x10e4:
            r8.width = r3
            r8.height = r6
            int r9 = r3 * r7
            r8.leftMargin = r9
            int r9 = r6 * r4
            r8.topMargin = r9
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r10)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r9 = r9[r11]
            r9.setVisibility(r5)
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r10)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r9 = r9[r11]
            r9.setLayoutParams(r8)
            com.streamax.client.VideoFrame[] r8 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            java.lang.Object r9 = r9.get(r10)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8 = r8[r9]
            r8.SetMax(r5)
            com.streamax.client.RealPlayActivity r8 = r0.mRealPlayUi
            if (r8 == 0) goto L_0x113c
            java.util.List<java.lang.Integer> r9 = r0.mIndexList
            java.lang.Object r9 = r9.get(r10)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r8.SetStreamDecodeState(r9, r5)
        L_0x113c:
            int r7 = r7 + 1
            goto L_0x10c1
        L_0x113f:
            int r4 = r4 + 1
            goto L_0x10be
        L_0x1143:
            if (r18 != 0) goto L_0x1149
            int r2 = r0.mCurViewCount
            r0.mLastArrayMode = r2
        L_0x1149:
            r0.mCurViewCount = r1
            r0.mBaseIndex = r13
            goto L_0x12f8
        L_0x114f:
            if (r1 != r15) goto L_0x12f8
            int r12 = r0.mFocusIndex
            if (r12 < r11) goto L_0x1157
            r0.mFocusIndex = r7
        L_0x1157:
            int r7 = r0.mCurViewCount
            if (r7 != r15) goto L_0x122b
            int r7 = r0.mBaseIndex
            int r9 = r7 + 16
            if (r9 < r11) goto L_0x1163
            r7 = 0
            goto L_0x1164
        L_0x1163:
            int r7 = r7 + r15
        L_0x1164:
            r9 = 0
        L_0x1165:
            int r11 = r0.mInitViewCount
            if (r9 >= r11) goto L_0x1199
            if (r9 < r7) goto L_0x1170
            int r11 = r7 + r1
            if (r9 >= r11) goto L_0x1170
            goto L_0x1196
        L_0x1170:
            com.streamax.client.VideoFrame[] r11 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r12 = r0.mIndexList
            java.lang.Object r12 = r12.get(r9)
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r11 = r11[r12]
            r11.setVisibility(r4)
            com.streamax.client.RealPlayActivity r11 = r0.mRealPlayUi
            if (r11 == 0) goto L_0x1196
            java.util.List<java.lang.Integer> r12 = r0.mIndexList
            java.lang.Object r12 = r12.get(r9)
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r11.SetStreamDecodeState(r12, r8)
        L_0x1196:
            int r9 = r9 + 1
            goto L_0x1165
        L_0x1199:
            r4 = 0
        L_0x119a:
            if (r4 >= r10) goto L_0x121f
            r8 = 0
        L_0x119d:
            if (r8 >= r10) goto L_0x121b
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            int r12 = r4 * r2
            int r12 = r12 + r8
            int r12 = r12 + r7
            java.lang.Object r11 = r11.get(r12)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r9 = r9[r11]
            android.view.ViewGroup$LayoutParams r9 = r9.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r9 = (android.widget.RelativeLayout.LayoutParams) r9
            if (r9 != 0) goto L_0x11c0
            android.widget.RelativeLayout$LayoutParams r9 = new android.widget.RelativeLayout$LayoutParams
            r9.<init>(r3, r6)
        L_0x11c0:
            r9.width = r3
            r9.height = r6
            int r11 = r3 * r8
            r9.leftMargin = r11
            int r11 = r6 * r4
            r9.topMargin = r11
            com.streamax.client.VideoFrame[] r11 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r13 = r0.mIndexList
            java.lang.Object r13 = r13.get(r12)
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            r11 = r11[r13]
            r11.setVisibility(r5)
            com.streamax.client.VideoFrame[] r11 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r13 = r0.mIndexList
            java.lang.Object r13 = r13.get(r12)
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            r11 = r11[r13]
            r11.setLayoutParams(r9)
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r12)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r9 = r9[r11]
            r9.SetMax(r5)
            com.streamax.client.RealPlayActivity r9 = r0.mRealPlayUi
            if (r9 == 0) goto L_0x1218
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r12)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r9.SetStreamDecodeState(r11, r5)
        L_0x1218:
            int r8 = r8 + 1
            goto L_0x119d
        L_0x121b:
            int r4 = r4 + 1
            goto L_0x119a
        L_0x121f:
            if (r18 != 0) goto L_0x1225
            int r2 = r0.mCurViewCount
            r0.mLastArrayMode = r2
        L_0x1225:
            r0.mCurViewCount = r1
            r0.mBaseIndex = r7
            goto L_0x12f8
        L_0x122b:
            int r7 = r0.mFocusIndex
            if (r7 != r9) goto L_0x1230
            r7 = 0
        L_0x1230:
            int r7 = r7 / r15
            int r7 = r7 * 16
            r9 = 0
        L_0x1234:
            int r11 = r0.mInitViewCount
            if (r9 >= r11) goto L_0x1268
            if (r9 < r7) goto L_0x123f
            int r11 = r7 + r1
            if (r9 >= r11) goto L_0x123f
            goto L_0x1265
        L_0x123f:
            com.streamax.client.VideoFrame[] r11 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r12 = r0.mIndexList
            java.lang.Object r12 = r12.get(r9)
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r11 = r11[r12]
            r11.setVisibility(r4)
            com.streamax.client.RealPlayActivity r11 = r0.mRealPlayUi
            if (r11 == 0) goto L_0x1265
            java.util.List<java.lang.Integer> r12 = r0.mIndexList
            java.lang.Object r12 = r12.get(r9)
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r11.SetStreamDecodeState(r12, r8)
        L_0x1265:
            int r9 = r9 + 1
            goto L_0x1234
        L_0x1268:
            r4 = 0
        L_0x1269:
            if (r4 >= r10) goto L_0x12ee
            r8 = 0
        L_0x126c:
            if (r8 >= r10) goto L_0x12ea
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            int r12 = r4 * r2
            int r12 = r12 + r8
            int r12 = r12 + r7
            java.lang.Object r11 = r11.get(r12)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r9 = r9[r11]
            android.view.ViewGroup$LayoutParams r9 = r9.getLayoutParams()
            android.widget.RelativeLayout$LayoutParams r9 = (android.widget.RelativeLayout.LayoutParams) r9
            if (r9 != 0) goto L_0x128f
            android.widget.RelativeLayout$LayoutParams r9 = new android.widget.RelativeLayout$LayoutParams
            r9.<init>(r3, r6)
        L_0x128f:
            r9.width = r3
            r9.height = r6
            int r11 = r3 * r8
            r9.leftMargin = r11
            int r11 = r6 * r4
            r9.topMargin = r11
            com.streamax.client.VideoFrame[] r11 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r13 = r0.mIndexList
            java.lang.Object r13 = r13.get(r12)
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            r11 = r11[r13]
            r11.setVisibility(r5)
            com.streamax.client.VideoFrame[] r11 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r13 = r0.mIndexList
            java.lang.Object r13 = r13.get(r12)
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            r11 = r11[r13]
            r11.setLayoutParams(r9)
            com.streamax.client.VideoFrame[] r9 = r0.mVideoFrame
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r12)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r9 = r9[r11]
            r9.SetMax(r5)
            com.streamax.client.RealPlayActivity r9 = r0.mRealPlayUi
            if (r9 == 0) goto L_0x12e7
            java.util.List<java.lang.Integer> r11 = r0.mIndexList
            java.lang.Object r11 = r11.get(r12)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r9.SetStreamDecodeState(r11, r5)
        L_0x12e7:
            int r8 = r8 + 1
            goto L_0x126c
        L_0x12ea:
            int r4 = r4 + 1
            goto L_0x1269
        L_0x12ee:
            if (r18 != 0) goto L_0x12f4
            int r2 = r0.mCurViewCount
            r0.mLastArrayMode = r2
        L_0x12f4:
            r0.mCurViewCount = r1
            r0.mBaseIndex = r7
        L_0x12f8:
            com.streamax.client.RealPlayActivity r1 = r0.mRealPlayUi
            if (r1 == 0) goto L_0x1309
            java.lang.Thread r1 = new java.lang.Thread
            com.streamax.client.VideoContainer$5 r2 = new com.streamax.client.VideoContainer$5
            r2.<init>()
            r1.<init>(r2)
            r1.start()
        L_0x1309:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamax.client.VideoContainer.ArrayViews(int, boolean):void");
    }

    public void SetPtzControlState(int i) {
        RealPlayActivity realPlayActivity;
        int[] iArr = {R.id.ptz_control_left, R.id.ptz_control_right, R.id.ptz_control_up, R.id.ptz_control_down};
        for (int i2 = 0; i2 < 4; i2++) {
            ImageView imageView = (ImageView) findViewById(iArr[i2]);
            if (imageView != null) {
                if (imageView.getVisibility() != i) {
                    imageView.setVisibility(i);
                }
                imageView.bringToFront();
            }
        }
        if (i == 8 || i == 4) {
            RealPlayActivity realPlayActivity2 = this.mRealPlayUi;
            if (realPlayActivity2 != null) {
                realPlayActivity2.SetPtzState(false);
            }
        } else if (i == 0 && (realPlayActivity = this.mRealPlayUi) != null) {
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

    public void FlingRight(float f) {
        if ((this.mCurViewCount != 1 || this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].GetDigitalScales() == 1.0f) && this.mInitViewCount != this.mCurViewCount) {
            applyRotation(-1, 0.0f, 90.0f);
        }
    }

    public void FlingLeft(float f) {
        if ((this.mCurViewCount != 1 || this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].GetDigitalScales() == 1.0f) && this.mInitViewCount != this.mCurViewCount) {
            applyRotation(1, 0.0f, -90.0f);
        }
    }

    private void applyRotation(int i, float f, float f2) {
        Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(f, f2, ((float) this.mContainer.getWidth()) / 2.0f, ((float) this.mContainer.getHeight()) / 2.0f, 250.0f, true);
        rotate3dAnimation.setDuration(500);
        rotate3dAnimation.setFillAfter(true);
        rotate3dAnimation.setInterpolator(new AccelerateInterpolator());
        rotate3dAnimation.setAnimationListener(new DisplayNextView(i));
        this.mContainer.startAnimation(rotate3dAnimation);
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
            VideoContainer.this.mContainer.post(new SwapViews(this.mPosition));
        }
    }

    private final class SwapViews implements Runnable {
        private final int mPosition;

        public SwapViews(int i) {
            this.mPosition = i;
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
                    int i = this.mTouchMode;
                    if (i == 2) {
                        float GetDistance = GetDistance(motionEvent);
                        if (this.mCurViewCount == 1 && ((!this.mbPtz || (this.mPtzState & (1 << this.mIndexList.get(this.mFocusIndex).intValue())) <= 0) && GetDistance > 0.0f)) {
                            this.matrix.set(this.savedMatrix);
                            float f = GetDistance / this.mDignitalDistance;
                            this.mDignitalDistance = GetDistance;
                            this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].setFatherW_H(getLeft(), getTop(), getRight(), getBottom(), getWidth(), getHeight());
                            this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].SetDigitalZoomIn(f);
                        }
                    } else if (i == 1 && this.mCurViewCount == 1 && this.mVideoFrame[this.mIndexList.get(this.mFocusIndex).intValue()].GetDigitalScales() != 1.0f) {
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

    public int getFocusView(PointF pointF) {
        boolean z = false;
        for (int i = 0; i < this.mInitViewCount; i++) {
            VideoFrame[] videoFrameArr = this.mVideoFrame;
            if (videoFrameArr[i] != null) {
                if (videoFrameArr[i].getVisibility() != 0) {
                    this.mVideoFrame[i].setFocusState(false);
                } else {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(i).intValue()].getLayoutParams();
                    if (new Rect(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + layoutParams.width, layoutParams.topMargin + layoutParams.height).contains((int) pointF.x, (int) pointF.y)) {
                        this.mFocusIndex = i;
                        this.mVideoFrame[this.mIndexList.get(i).intValue()].setFocusState(true);
                        RealPlayActivity realPlayActivity = this.mRealPlayUi;
                        if (realPlayActivity != null) {
                            realPlayActivity.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                        }
                        z = true;
                    } else {
                        this.mVideoFrame[this.mIndexList.get(i).intValue()].setFocusState(false);
                    }
                }
            }
        }
        if (!z) {
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
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setFocusState(false);
                    this.mBaseIndex = (this.mBaseIndex + 1) % 32;
                    for (int i19 = 0; i19 < this.mInitViewCount; i19++) {
                        int i20 = this.mBaseIndex;
                        if (i19 < i20 || i19 >= i20 + this.mCurViewCount) {
                            this.mVideoFrame[this.mIndexList.get(i19).intValue()].setVisibility(8);
                            this.mVideoFrame[this.mIndexList.get(i19).intValue()].setFocusState(false);
                            RealPlayActivity realPlayActivity5 = this.mRealPlayUi;
                            if (realPlayActivity5 != null) {
                                realPlayActivity5.SetStreamDecodeState(this.mIndexList.get(i19).intValue(), true);
                            }
                        } else {
                            this.mVideoFrame[this.mIndexList.get(i19).intValue()].setFocusState(false);
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
                    RealPlayActivity realPlayActivity6 = this.mRealPlayUi;
                    if (realPlayActivity6 != null) {
                        realPlayActivity6.SetStreamDecodeState(this.mIndexList.get(this.mBaseIndex).intValue(), false);
                    }
                }
                this.mFocusIndex = this.mBaseIndex;
                for (int i23 = 0; i23 < this.mInitViewCount; i23++) {
                    int i24 = this.mFocusIndex;
                    if (i23 == i24) {
                        this.mVideoFrame[this.mIndexList.get(i24).intValue()].setFocusState(true);
                        this.mRealPlayUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                    } else {
                        this.mVideoFrame[this.mIndexList.get(i23).intValue()].setFocusState(false);
                    }
                }
            } else {
                return;
            }
        } else if (i == 16) {
            int i25 = this.mCurViewCount;
            if (i25 < i) {
                if (i25 == 9) {
                    this.mFlingStatus = 0;
                    ArrayViews(9, true);
                } else if (i25 == 4) {
                    int i26 = this.mBaseIndex;
                    if (i26 + 4 >= 16) {
                        this.mBaseIndex = 0;
                    } else {
                        this.mBaseIndex = i26 + 4;
                    }
                    for (int i27 = 0; i27 < this.mInitViewCount; i27++) {
                        int i28 = this.mBaseIndex;
                        if (i27 < i28 || i27 >= i28 + 4) {
                            this.mVideoFrame[this.mIndexList.get(i27).intValue()].setVisibility(8);
                            RealPlayActivity realPlayActivity7 = this.mRealPlayUi;
                            if (realPlayActivity7 != null) {
                                realPlayActivity7.SetStreamDecodeState(this.mIndexList.get(i27).intValue(), true);
                            }
                        }
                    }
                    int i29 = this.mWidth / 2;
                    int i30 = this.mHeight / 2;
                    for (int i31 = 0; i31 < 2; i31++) {
                        for (int i32 = 0; i32 < 2; i32++) {
                            int i33 = (i31 * 2) + i32;
                            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i33).intValue()].getLayoutParams();
                            if (layoutParams4 == null) {
                                layoutParams4 = new RelativeLayout.LayoutParams(i29, i30);
                            }
                            layoutParams4.width = i29;
                            layoutParams4.height = i30;
                            layoutParams4.leftMargin = i29 * i32;
                            layoutParams4.topMargin = i30 * i31;
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i33).intValue()].setVisibility(0);
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i33).intValue()].setLayoutParams(layoutParams4);
                            this.mVideoFrame[this.mIndexList.get(this.mBaseIndex + i33).intValue()].SetMax(false);
                            RealPlayActivity realPlayActivity8 = this.mRealPlayUi;
                            if (realPlayActivity8 != null) {
                                realPlayActivity8.SetStreamDecodeState(this.mIndexList.get(i33 + this.mBaseIndex).intValue(), false);
                            }
                        }
                    }
                } else if (i25 == 1) {
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setFocusState(false);
                    this.mBaseIndex = (this.mBaseIndex + 1) % 16;
                    for (int i34 = 0; i34 < this.mInitViewCount; i34++) {
                        int i35 = this.mBaseIndex;
                        if (i34 < i35 || i34 >= i35 + this.mCurViewCount) {
                            this.mVideoFrame[this.mIndexList.get(i34).intValue()].setVisibility(8);
                            this.mVideoFrame[this.mIndexList.get(i34).intValue()].setFocusState(false);
                            RealPlayActivity realPlayActivity9 = this.mRealPlayUi;
                            if (realPlayActivity9 != null) {
                                realPlayActivity9.SetStreamDecodeState(this.mIndexList.get(i34).intValue(), true);
                            }
                        } else {
                            this.mVideoFrame[this.mIndexList.get(i34).intValue()].setFocusState(false);
                        }
                    }
                    int i36 = this.mWidth;
                    int i37 = this.mHeight;
                    RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].getLayoutParams();
                    if (layoutParams5 == null) {
                        layoutParams5 = new RelativeLayout.LayoutParams(i36, i37);
                    }
                    layoutParams5.width = i36;
                    layoutParams5.height = i37;
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
                for (int i38 = 0; i38 < this.mInitViewCount; i38++) {
                    int i39 = this.mFocusIndex;
                    if (i38 == i39) {
                        this.mVideoFrame[this.mIndexList.get(i39).intValue()].setFocusState(true);
                        this.mRealPlayUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                    } else {
                        this.mVideoFrame[this.mIndexList.get(i38).intValue()].setFocusState(false);
                    }
                }
            } else {
                return;
            }
        } else if (i == 9) {
            int i40 = this.mCurViewCount;
            if (i40 < i) {
                if (i40 == 4) {
                    ArrayViews(4, true);
                } else if (i40 == 1) {
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setFocusState(false);
                    this.mBaseIndex = (this.mBaseIndex + 1) % 8;
                    for (int i41 = 0; i41 < this.mInitViewCount; i41++) {
                        int i42 = this.mBaseIndex;
                        if (i41 < i42 || i41 >= i42 + this.mCurViewCount) {
                            this.mVideoFrame[this.mIndexList.get(i41).intValue()].setVisibility(8);
                            this.mVideoFrame[this.mIndexList.get(i41).intValue()].setFocusState(false);
                            RealPlayActivity realPlayActivity11 = this.mRealPlayUi;
                            if (realPlayActivity11 != null) {
                                realPlayActivity11.SetStreamDecodeState(this.mIndexList.get(i41).intValue(), true);
                            }
                        } else {
                            this.mVideoFrame[this.mIndexList.get(i41).intValue()].setFocusState(false);
                        }
                    }
                    int i43 = this.mWidth;
                    int i44 = this.mHeight;
                    RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].getLayoutParams();
                    if (layoutParams6 == null) {
                        layoutParams6 = new RelativeLayout.LayoutParams(i43, i44);
                    }
                    layoutParams6.width = i43;
                    layoutParams6.height = i44;
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
                for (int i45 = 0; i45 < this.mInitViewCount; i45++) {
                    int i46 = this.mFocusIndex;
                    if (i45 == i46) {
                        this.mVideoFrame[this.mIndexList.get(i46).intValue()].setFocusState(true);
                        this.mRealPlayUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                    } else {
                        this.mVideoFrame[this.mIndexList.get(i45).intValue()].setFocusState(false);
                    }
                }
            } else {
                return;
            }
        } else if (i == 4) {
            int i47 = this.mCurViewCount;
            if (i47 < i) {
                if (i47 == 1) {
                    this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].setFocusState(false);
                    this.mBaseIndex = (this.mBaseIndex + 1) % 4;
                    for (int i48 = 0; i48 < this.mInitViewCount; i48++) {
                        this.mVideoFrame[this.mIndexList.get(i48).intValue()].setFocusState(false);
                        if (i48 != this.mBaseIndex) {
                            this.mVideoFrame[this.mIndexList.get(i48).intValue()].setVisibility(8);
                            RealPlayActivity realPlayActivity13 = this.mRealPlayUi;
                            if (realPlayActivity13 != null) {
                                realPlayActivity13.SetStreamDecodeState(this.mIndexList.get(i48).intValue(), true);
                            }
                        }
                    }
                    int i49 = this.mWidth;
                    int i50 = this.mHeight;
                    RelativeLayout.LayoutParams layoutParams7 = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(this.mBaseIndex).intValue()].getLayoutParams();
                    if (layoutParams7 == null) {
                        layoutParams7 = new RelativeLayout.LayoutParams(i49, i50);
                    }
                    layoutParams7.width = i49;
                    layoutParams7.height = i50;
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
                for (int i51 = 0; i51 < this.mInitViewCount; i51++) {
                    int i52 = this.mFocusIndex;
                    if (i51 == i52) {
                        this.mVideoFrame[this.mIndexList.get(i52).intValue()].setFocusState(true);
                        this.mRealPlayUi.openSound(this.mIndexList.get(this.mFocusIndex).intValue());
                    } else {
                        this.mVideoFrame[this.mIndexList.get(i51).intValue()].setFocusState(false);
                    }
                }
            } else {
                return;
            }
        } else if (i == 1) {
            if (this.mCurViewCount < i) {
                for (int i53 = 0; i53 < this.mInitViewCount; i53++) {
                    this.mVideoFrame[this.mIndexList.get(i53).intValue()].setFocusState(false);
                    int i54 = this.mBaseIndex;
                    if (i53 < i54 || i53 >= i54 + this.mCurViewCount) {
                        this.mVideoFrame[this.mIndexList.get(i53).intValue()].setVisibility(8);
                        RealPlayActivity realPlayActivity15 = this.mRealPlayUi;
                        if (realPlayActivity15 != null) {
                            realPlayActivity15.SetStreamDecodeState(this.mIndexList.get(i53).intValue(), true);
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
        for (int i2 = this.mBaseIndex; i2 < this.mCurViewCount + this.mBaseIndex; i2++) {
            this.mVideoFrame[this.mIndexList.get(i2).intValue()].SetDragState(false);
        }
    }

    public void FindDestination(MotionEvent motionEvent) {
        boolean z = false;
        for (int i = this.mBaseIndex; i < this.mCurViewCount + this.mBaseIndex; i++) {
            if (i != this.mSourceIndex) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoFrame[this.mIndexList.get(i).intValue()].getLayoutParams();
                if (new Rect(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + layoutParams.width, layoutParams.topMargin + layoutParams.height).contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                    this.mDestinationIndex = i;
                    this.mDestinationParams = layoutParams;
                    this.mVideoFrame[this.mIndexList.get(i).intValue()].SetDragState(true);
                    z = true;
                } else {
                    this.mVideoFrame[this.mIndexList.get(i).intValue()].SetDragState(false);
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
                this.mRealPlayUi.SetPtzState(false);
            } else if (((1 << this.mIndexList.get(this.mFocusIndex).intValue()) & i) > 0) {
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

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (motionEvent2.getPointerCount() > 1) {
                this.mbMultiTouch = true;
            }
            return super.onScroll(motionEvent, motionEvent2, f, f2);
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

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (this.mbMultiTouch) {
                this.mbMultiTouch = false;
                return super.onFling(motionEvent, motionEvent2, f, f2);
            } else if (motionEvent.getX() - motionEvent2.getX() > 100.0f) {
                VideoContainer.this.FlingLeft(motionEvent.getX() - motionEvent2.getX());
                return super.onFling(motionEvent, motionEvent2, f, f2);
            } else if (motionEvent.getX() - motionEvent2.getX() < -100.0f) {
                VideoContainer.this.FlingRight(motionEvent.getX() - motionEvent2.getX());
                return super.onFling(motionEvent, motionEvent2, f, f2);
            } else if (motionEvent.getY() - motionEvent2.getY() > 100.0f) {
                return super.onFling(motionEvent, motionEvent2, f, f2);
            } else {
                if (motionEvent.getY() - motionEvent2.getY() < -100.0f) {
                    return super.onFling(motionEvent, motionEvent2, f, f2);
                }
                return super.onFling(motionEvent, motionEvent2, f, f2);
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

    public VideoView getVideoView(int i) {
        return this.mVideoFrame[i].GetVideoView();
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
