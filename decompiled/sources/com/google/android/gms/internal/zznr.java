package com.google.android.gms.internal;

import com.google.android.gms.ads.VideoController;

public final class zznr extends zzmq {
    private final VideoController.VideoLifecycleCallbacks zzamb;

    public zznr(VideoController.VideoLifecycleCallbacks videoLifecycleCallbacks) {
        this.zzamb = videoLifecycleCallbacks;
    }

    public final void onVideoEnd() {
        this.zzamb.onVideoEnd();
    }

    public final void onVideoMute(boolean z) {
        this.zzamb.onVideoMute(z);
    }

    public final void onVideoPause() {
        this.zzamb.onVideoPause();
    }

    public final void onVideoPlay() {
        this.zzamb.onVideoPlay();
    }

    public final void onVideoStart() {
        this.zzamb.onVideoStart();
    }
}
