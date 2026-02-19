package com.streamax.client;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.streamax.client.record.RecordStatus;
import com.zycs.UView.R;
import de.greenrobot.event.EventBus;

// Wrapper view for a single preview tile and its overlays.
public class VideoFrame extends FrameLayout {
    public Context mContext;
    public CustomImageView mCusImageView;
    public ImageView mImgAdd;
    public ImageView mImgRec;
    public ProgressBar mProcessBar;
    public boolean mRecordStatus;
    public int mSearial;
    public VideoView mVideoView;
    public boolean mbFocus;
    public boolean mbMax;
    public boolean mbPlay;

    public VideoFrame(Context context, int i) {
        this(context, (AttributeSet) null, i);
    }

    public VideoFrame(Context context, AttributeSet attributeSet, int i) {
        this(context, (AttributeSet) null, 0, i);
    }

    public VideoView GetVideoView() {
        return this.mVideoView;
    }

    public VideoFrame(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.mSearial = 0;
        this.mbFocus = false;
        this.mbMax = false;
        this.mbPlay = false;
        this.mContext = context;
        this.mSearial = i2;
        LoadViews();
    }

    public void LoadViews() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        VideoView videoView = new VideoView(this.mContext, this.mSearial);
        this.mVideoView = videoView;
        addView(videoView, layoutParams);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        layoutParams2.gravity = 17;
        CustomImageView customImageView = new CustomImageView(this.mContext, this.mSearial);
        this.mCusImageView = customImageView;
        addView(customImageView, layoutParams2);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams3.gravity = 17;
        ImageView imageView = new ImageView(this.mContext);
        this.mImgAdd = imageView;
        imageView.setImageResource(R.drawable.add_normal);
        this.mImgAdd.setClickable(true);
        addView(this.mImgAdd, layoutParams3);
        FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams4.gravity = 17;
        ProgressBar progressBar = new ProgressBar(this.mContext);
        this.mProcessBar = progressBar;
        addView(progressBar, layoutParams4);
        this.mProcessBar.setVisibility(4);
        FrameLayout.LayoutParams layoutParams5 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams5.gravity = 85;
        ImageView imageView2 = new ImageView(this.mContext);
        this.mImgRec = imageView2;
        imageView2.setImageResource(R.drawable.record_normal);
        this.mImgRec.setVisibility(4);
        addView(this.mImgRec, layoutParams5);
    }

    public void setFocusState(boolean z) {
        this.mbFocus = z;
        if (z) {
            this.mImgAdd.setImageResource(R.drawable.add_clicked);
            this.mImgAdd.setClickable(true);
            EventBus.getDefault().post(new RecordStatus(this.mRecordStatus));
        } else {
            this.mImgAdd.setImageResource(R.drawable.add_normal);
            this.mImgAdd.setClickable(false);
        }
        this.mVideoView.setFocusState(z);
        this.mCusImageView.setFocusState(z);
    }

    public void setPlayState(boolean z) {
        this.mbPlay = z;
        if (z) {
            this.mImgAdd.post(new Runnable() {
                public void run() {
                    VideoFrame.this.mImgAdd.setVisibility(4);
                }
            });
            return;
        }
        ClearViews();
        this.mImgAdd.post(new Runnable() {
            public void run() {
                VideoFrame.this.mImgAdd.setVisibility(0);
            }
        });
    }

    public void SetRecState(boolean z) {
        ImageView imageView = this.mImgRec;
        this.mRecordStatus = z;
        imageView.setVisibility(z ? 0 : 4);
    }

    public void SetBusyState(boolean z) {
        if (z) {
            this.mProcessBar.setVisibility(0);
            this.mImgAdd.setVisibility(4);
            return;
        }
        this.mProcessBar.setVisibility(4);
        if (!this.mbPlay) {
            this.mImgAdd.setVisibility(0);
        }
    }

    public void SetMax(boolean z) {
        this.mbMax = z;
        postInvalidate();
    }

    public float GetDigitalScales() {
        return this.mVideoView.getDigitalScales();
    }

    public void ResetDigitalScales() {
        this.mVideoView.resetDigitalScales();
        invalidate();
    }

    public void ClearViews() {
        this.mVideoView.clearDraw();
    }

    public void SetDigitalTranslate(float f, float f2) {
        this.mVideoView.setDigitalTranslate(f, f2);
    }

    public void SetDigitalZoomIn(float f) {
        this.mVideoView.setDigitalZoomIn(f);
    }

    public void SetDragState(boolean z) {
        this.mVideoView.setDragState(z);
    }

    public void setFatherW_H(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mVideoView.setFatherW_H(i, i2, i3, i4, i5, i6);
    }

    public void onTouchMove(MotionEvent motionEvent) {
        this.mVideoView.onTouchMove(motionEvent);
    }

    public void onTouchDown(MotionEvent motionEvent) {
        this.mVideoView.onTouchDown(motionEvent);
    }

    public void onPointerDown(MotionEvent motionEvent) {
        this.mVideoView.onPointerDown(motionEvent);
    }
}
