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

    public VideoFrame(Context context, int serial) {
        this(context, (AttributeSet) null, serial);
    }

    public VideoFrame(Context context, AttributeSet attributeSet, int serial) {
        this(context, (AttributeSet) null, 0, serial);
    }

    public VideoView GetVideoView() {
        return this.mVideoView;
    }

    public VideoFrame(Context context, AttributeSet attributeSet, int defStyleAttr, int serial) {
        super(context, attributeSet, defStyleAttr);
        this.mSearial = 0;
        this.mbFocus = false;
        this.mbMax = false;
        this.mbPlay = false;
        this.mContext = context;
        this.mSearial = serial;
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

    public void setFocusState(boolean focused) {
        this.mbFocus = focused;
        if (focused) {
            this.mImgAdd.setImageResource(R.drawable.add_clicked);
            this.mImgAdd.setClickable(true);
            EventBus.getDefault().post(new RecordStatus(this.mRecordStatus));
        } else {
            this.mImgAdd.setImageResource(R.drawable.add_normal);
            this.mImgAdd.setClickable(false);
        }
        this.mVideoView.setFocusState(focused);
        this.mCusImageView.setFocusState(focused);
    }

    public void setPlayState(boolean playing) {
        this.mbPlay = playing;
        if (playing) {
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

    public void SetRecState(boolean recording) {
        ImageView imageView = this.mImgRec;
        this.mRecordStatus = recording;
        imageView.setVisibility(recording ? 0 : 4);
    }

    public void SetBusyState(boolean busy) {
        if (busy) {
            this.mProcessBar.setVisibility(0);
            this.mImgAdd.setVisibility(4);
            return;
        }
        this.mProcessBar.setVisibility(4);
        if (!this.mbPlay) {
            this.mImgAdd.setVisibility(0);
        }
    }

    public void SetMax(boolean max) {
        this.mbMax = max;
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

    public void SetDigitalTranslate(float deltaX, float deltaY) {
        this.mVideoView.setDigitalTranslate(deltaX, deltaY);
    }

    public void SetDigitalZoomIn(float scaleFactor) {
        this.mVideoView.setDigitalZoomIn(scaleFactor);
    }

    public void SetDragState(boolean dragging) {
        this.mVideoView.setDragState(dragging);
    }

    public void setFatherW_H(int startLeft, int startTop, int startRight, int startBottom, int initialWidth, int initialHeight) {
        this.mVideoView.setFatherW_H(startLeft, startTop, startRight, startBottom, initialWidth, initialHeight);
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
