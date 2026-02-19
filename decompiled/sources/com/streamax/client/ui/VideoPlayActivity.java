package com.streamax.client.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.DriveFile;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.streamax.client.app.AppActivity;
import com.streamax.client.widget.PlayerView;
import com.zycs.UView.R;
import java.io.File;

// Activity wrapper around PlayerView for video playback.
public class VideoPlayActivity extends AppActivity implements PlayerView.OnPlayListener {
    public static final String INTENT_KEY_PARAMETERS = "parameters";
    private Builder mBuilder;
    private PlayerView mPlayerView;

    public static final class Landscape extends VideoPlayActivity {
    }

    public static final class Portrait extends VideoPlayActivity {
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.video_play_activity;
    }

    public /* synthetic */ void onClickLock(PlayerView playerView) {
        PlayerView.OnPlayListener.CC.$default$onClickLock(this, playerView);
    }

    public /* synthetic */ void onClickPlay(PlayerView playerView) {
        PlayerView.OnPlayListener.CC.$default$onClickPlay(this, playerView);
    }

    /* access modifiers changed from: protected */
    public void initView() {
        PlayerView playerView = (PlayerView) findViewById(R.id.pv_video_play_view);
        this.mPlayerView = playerView;
        playerView.setLifecycleOwner(this);
        this.mPlayerView.setOnPlayListener(this);
    }

    /* access modifiers changed from: protected */
    public void initData() {
        Builder builder = (Builder) getParcelable(INTENT_KEY_PARAMETERS);
        this.mBuilder = builder;
        if (builder != null) {
            this.mPlayerView.setVideoTitle(builder.getVideoTitle());
            this.mPlayerView.setVideoSource(this.mBuilder.getVideoSource());
            this.mPlayerView.setGestureEnabled(this.mBuilder.isGestureEnabled());
            if (this.mBuilder.isAutoPlay()) {
                this.mPlayerView.start();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("are you ok?");
    }

    public void onClickBack(PlayerView playerView) {
        onBackPressed();
    }

    public void onPlayStart(PlayerView playerView) {
        int access$300 = this.mBuilder.getPlayProgress();
        if (access$300 > 0) {
            this.mPlayerView.setProgress(access$300);
        }
    }

    public void onPlayProgress(PlayerView playerView) {
        this.mBuilder.setPlayProgress(playerView.getProgress());
    }

    public void onPlayEnd(PlayerView playerView) {
        if (this.mBuilder.isLoopPlay()) {
            this.mPlayerView.setProgress(0);
            this.mPlayerView.start();
        } else if (this.mBuilder.isAutoOver()) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public ImmersionBar createStatusBarConfig() {
        return super.createStatusBarConfig().hideBar(BarHide.FLAG_HIDE_BAR);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(INTENT_KEY_PARAMETERS, this.mBuilder);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.mBuilder = (Builder) bundle.getParcelable(INTENT_KEY_PARAMETERS);
    }

    public static final class Builder implements Parcelable {
        public static final Parcelable.Creator<Builder> CREATOR = new Parcelable.Creator<Builder>() {
            public Builder createFromParcel(Parcel parcel) {
                return new Builder(parcel);
            }

            public Builder[] newArray(int i) {
                return new Builder[i];
            }
        };
        private int activityOrientation = -1;
        private boolean autoOver = true;
        private boolean autoPlay = true;
        private boolean gestureEnabled = true;
        private boolean loopPlay = false;
        private int playProgress;
        private String videoSource;
        private String videoTitle;

        public int describeContents() {
            return 0;
        }

        public Builder() {
        }

        protected Builder(Parcel parcel) {
            boolean z = true;
            this.videoSource = parcel.readString();
            this.videoTitle = parcel.readString();
            this.activityOrientation = parcel.readInt();
            this.playProgress = parcel.readInt();
            this.gestureEnabled = parcel.readByte() != 0;
            this.loopPlay = parcel.readByte() != 0;
            this.autoPlay = parcel.readByte() != 0;
            this.autoOver = parcel.readByte() == 0 ? false : z;
        }

        public Builder setVideoSource(File file) {
            this.videoSource = file.getPath();
            if (this.videoTitle == null) {
                this.videoTitle = file.getName();
            }
            return this;
        }

        public Builder setVideoSource(String str) {
            this.videoSource = str;
            return this;
        }

        /* access modifiers changed from: private */
        public String getVideoSource() {
            return this.videoSource;
        }

        public Builder setVideoTitle(String str) {
            this.videoTitle = str;
            return this;
        }

        /* access modifiers changed from: private */
        public String getVideoTitle() {
            return this.videoTitle;
        }

        public Builder setPlayProgress(int i) {
            this.playProgress = i;
            return this;
        }

        /* access modifiers changed from: private */
        public int getPlayProgress() {
            return this.playProgress;
        }

        public Builder setGestureEnabled(boolean z) {
            this.gestureEnabled = z;
            return this;
        }

        /* access modifiers changed from: private */
        public boolean isGestureEnabled() {
            return this.gestureEnabled;
        }

        public Builder setLoopPlay(boolean z) {
            this.loopPlay = z;
            return this;
        }

        /* access modifiers changed from: private */
        public boolean isLoopPlay() {
            return this.loopPlay;
        }

        public Builder setAutoPlay(boolean z) {
            this.autoPlay = z;
            return this;
        }

        public boolean isAutoPlay() {
            return this.autoPlay;
        }

        public Builder setAutoOver(boolean z) {
            this.autoOver = z;
            return this;
        }

        /* access modifiers changed from: private */
        public boolean isAutoOver() {
            return this.autoOver;
        }

        public Builder setActivityOrientation(int i) {
            this.activityOrientation = i;
            return this;
        }

        public void start(Context context) {
            Intent intent = new Intent();
            int i = this.activityOrientation;
            if (i == 0) {
                intent.setClass(context, Landscape.class);
            } else if (i != 1) {
                intent.setClass(context, VideoPlayActivity.class);
            } else {
                intent.setClass(context, Portrait.class);
            }
            intent.putExtra(VideoPlayActivity.INTENT_KEY_PARAMETERS, this);
            if (!(context instanceof Activity)) {
                intent.addFlags(DriveFile.MODE_READ_ONLY);
            }
            context.startActivity(intent);
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.videoSource);
            parcel.writeString(this.videoTitle);
            parcel.writeInt(this.activityOrientation);
            parcel.writeInt(this.playProgress);
            parcel.writeByte(this.gestureEnabled ? (byte) 1 : 0);
            parcel.writeByte(this.loopPlay ? (byte) 1 : 0);
            parcel.writeByte(this.autoPlay ? (byte) 1 : 0);
            parcel.writeByte(this.autoOver ? (byte) 1 : 0);
        }
    }
}
