package com.streamax.client.ui.devlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.streamax.client.CustomImageView;
import com.streamax.client.VideoView;
import com.zycs.UView.R;
import de.greenrobot.event.EventBus;

public class LvVideoFrame extends FrameLayout {
    public Context mContext;
    public CustomImageView mCusImageView;
    public ImageView mImgFull;
    public ImageView mImgRec;
    public View.OnClickListener mOnFullClickListener;
    public ProgressBar mProcessBar;
    public int mSearial = 0;
    public VideoView mVideoView;
    public int mView = 0;
    public boolean mbFocus = false;
    public boolean mbFull = false;
    public boolean mbMax = false;
    public boolean mbPlay = false;

    public LvVideoFrame(Context context, int serial) {
        super(context);
        this.mContext = context;
        this.mSearial = serial;
        LoadViews();
    }

    public LvVideoFrame(Context context, int serial, boolean fullMode) {
        super(context);
        this.mContext = context;
        this.mSearial = serial;
        this.mbFull = fullMode;
        LoadViews();
    }

    public LvVideoFrame(Context context, AttributeSet attributeSet, int serial) {
        super(context, attributeSet);
        this.mContext = context;
        this.mSearial = serial;
        LoadViews();
    }

    public VideoView GetVideoView() {
        return this.mVideoView;
    }

    public LvVideoFrame(Context context, AttributeSet attributeSet, int defStyleAttr, int serial) {
        super(context, attributeSet, defStyleAttr);
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
        new FrameLayout.LayoutParams(-2, -2).gravity = 17;
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams3.gravity = 17;
        ProgressBar progressBar = new ProgressBar(this.mContext);
        this.mProcessBar = progressBar;
        addView(progressBar, layoutParams3);
        this.mProcessBar.setVisibility(4);
        FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams4.gravity = 85;
        ImageView imageView = new ImageView(this.mContext);
        this.mImgRec = imageView;
        imageView.setImageResource(R.drawable.record_normal);
        this.mImgRec.setVisibility(4);
        addView(this.mImgRec, layoutParams4);
        if (this.mbFull) {
            FrameLayout.LayoutParams layoutParams5 = new FrameLayout.LayoutParams(55, 55);
            layoutParams5.gravity = 85;
            layoutParams5.rightMargin = 15;
            layoutParams5.bottomMargin = 5;
            ImageView imageView2 = new ImageView(this.mContext);
            this.mImgFull = imageView2;
            imageView2.setImageResource(R.drawable.full);
            this.mImgFull.setVisibility(0);
            View.OnClickListener fullClickListener = new View.OnClickListener() {
                public void onClick(View view) {
                    EventBus.getDefault().post(new ClickFullEvent(LvVideoFrame.this.getId()));
                }
            };
            this.mOnFullClickListener = fullClickListener;
            this.mImgFull.setOnClickListener(fullClickListener);
            addView(this.mImgFull, layoutParams5);
        }
    }

    public void showFull(boolean visible) {
        this.mImgFull.setVisibility(visible ? 0 : 8);
    }

    public void SetFocusState(boolean focused) {
        this.mbFocus = focused;
        this.mVideoView.setFocusState(focused);
        this.mCusImageView.setFocusState(focused);
    }

    public void SetPlayState(boolean playing) {
        this.mbPlay = playing;
        if (!playing) {
            ClearViews();
        }
    }

    public void SetRecState(boolean recording) {
        this.mImgRec.setVisibility(recording ? 0 : 4);
    }

    public void SetBusyState(boolean busy) {
        this.mProcessBar.setVisibility(busy ? 0 : 4);
    }

    public void SetMax(boolean max) {
        this.mbMax = max;
        postInvalidate();
    }

    public float getDigitalScales() {
        return this.mVideoView.getDigitalScales();
    }

    public void resetDigitalScales() {
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

    public void setDragState(boolean dragging) {
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
