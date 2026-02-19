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

    public LvVideoFrame(Context context, int i) {
        super(context);
        this.mContext = context;
        this.mSearial = i;
        LoadViews();
    }

    public LvVideoFrame(Context context, int i, boolean z) {
        super(context);
        this.mContext = context;
        this.mSearial = i;
        this.mbFull = z;
        LoadViews();
    }

    public LvVideoFrame(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.mContext = context;
        this.mSearial = i;
        LoadViews();
    }

    public VideoView GetVideoView() {
        return this.mVideoView;
    }

    public LvVideoFrame(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
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
            View.OnClickListener r1 = new View.OnClickListener() {
                public void onClick(View view) {
                    EventBus.getDefault().post(new ClickFullEvent(LvVideoFrame.this.getId()));
                }
            };
            this.mOnFullClickListener = r1;
            this.mImgFull.setOnClickListener(r1);
            addView(this.mImgFull, layoutParams5);
        }
    }

    public void showFull(boolean z) {
        this.mImgFull.setVisibility(z ? 0 : 8);
    }

    public void SetFocusState(boolean z) {
        this.mbFocus = z;
        this.mVideoView.setFocusState(z);
        this.mCusImageView.setFocusState(z);
    }

    public void SetPlayState(boolean z) {
        this.mbPlay = z;
        if (!z) {
            ClearViews();
        }
    }

    public void SetRecState(boolean z) {
        this.mImgRec.setVisibility(z ? 0 : 4);
    }

    public void SetBusyState(boolean z) {
        this.mProcessBar.setVisibility(z ? 0 : 4);
    }

    public void SetMax(boolean z) {
        this.mbMax = z;
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

    public void setDragState(boolean z) {
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
