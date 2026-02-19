package com.google.android.gms.ads;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzaky;
import com.google.android.gms.internal.zzmm;
import com.google.android.gms.internal.zznr;

@zzabh
public final class VideoController {
    public static final int PLAYBACK_STATE_ENDED = 3;
    public static final int PLAYBACK_STATE_PAUSED = 2;
    public static final int PLAYBACK_STATE_PLAYING = 1;
    public static final int PLAYBACK_STATE_READY = 5;
    public static final int PLAYBACK_STATE_UNKNOWN = 0;
    private final Object mLock = new Object();
    private zzmm zzama;
    private VideoLifecycleCallbacks zzamb;

    public static abstract class VideoLifecycleCallbacks {
        public void onVideoEnd() {
        }

        public void onVideoMute(boolean z) {
        }

        public void onVideoPause() {
        }

        public void onVideoPlay() {
        }

        public void onVideoStart() {
        }
    }

    public final float getAspectRatio() {
        synchronized (this.mLock) {
            zzmm zzmm = this.zzama;
            if (zzmm == null) {
                return 0.0f;
            }
            try {
                float aspectRatio = zzmm.getAspectRatio();
                return aspectRatio;
            } catch (RemoteException e) {
                zzaky.zzb("Unable to call getAspectRatio on video controller.", e);
                return 0.0f;
            }
        }
    }

    public final int getPlaybackState() {
        synchronized (this.mLock) {
            zzmm zzmm = this.zzama;
            if (zzmm == null) {
                return 0;
            }
            try {
                int playbackState = zzmm.getPlaybackState();
                return playbackState;
            } catch (RemoteException e) {
                zzaky.zzb("Unable to call getPlaybackState on video controller.", e);
                return 0;
            }
        }
    }

    public final VideoLifecycleCallbacks getVideoLifecycleCallbacks() {
        VideoLifecycleCallbacks videoLifecycleCallbacks;
        synchronized (this.mLock) {
            videoLifecycleCallbacks = this.zzamb;
        }
        return videoLifecycleCallbacks;
    }

    public final boolean hasVideoContent() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzama != null;
        }
        return z;
    }

    public final boolean isClickToExpandEnabled() {
        synchronized (this.mLock) {
            zzmm zzmm = this.zzama;
            if (zzmm == null) {
                return false;
            }
            try {
                boolean isClickToExpandEnabled = zzmm.isClickToExpandEnabled();
                return isClickToExpandEnabled;
            } catch (RemoteException e) {
                zzaky.zzb("Unable to call isClickToExpandEnabled.", e);
                return false;
            }
        }
    }

    public final boolean isCustomControlsEnabled() {
        synchronized (this.mLock) {
            zzmm zzmm = this.zzama;
            if (zzmm == null) {
                return false;
            }
            try {
                boolean isCustomControlsEnabled = zzmm.isCustomControlsEnabled();
                return isCustomControlsEnabled;
            } catch (RemoteException e) {
                zzaky.zzb("Unable to call isUsingCustomPlayerControls.", e);
                return false;
            }
        }
    }

    public final boolean isMuted() {
        synchronized (this.mLock) {
            zzmm zzmm = this.zzama;
            if (zzmm == null) {
                return true;
            }
            try {
                boolean isMuted = zzmm.isMuted();
                return isMuted;
            } catch (RemoteException e) {
                zzaky.zzb("Unable to call isMuted on video controller.", e);
                return true;
            }
        }
    }

    public final void mute(boolean z) {
        synchronized (this.mLock) {
            zzmm zzmm = this.zzama;
            if (zzmm != null) {
                try {
                    zzmm.mute(z);
                } catch (RemoteException e) {
                    zzaky.zzb("Unable to call mute on video controller.", e);
                }
            }
        }
    }

    public final void pause() {
        synchronized (this.mLock) {
            zzmm zzmm = this.zzama;
            if (zzmm != null) {
                try {
                    zzmm.pause();
                } catch (RemoteException e) {
                    zzaky.zzb("Unable to call pause on video controller.", e);
                }
            }
        }
    }

    public final void play() {
        synchronized (this.mLock) {
            zzmm zzmm = this.zzama;
            if (zzmm != null) {
                try {
                    zzmm.play();
                } catch (RemoteException e) {
                    zzaky.zzb("Unable to call play on video controller.", e);
                }
            }
        }
    }

    public final void setVideoLifecycleCallbacks(VideoLifecycleCallbacks videoLifecycleCallbacks) {
        zzbq.checkNotNull(videoLifecycleCallbacks, "VideoLifecycleCallbacks may not be null.");
        synchronized (this.mLock) {
            this.zzamb = videoLifecycleCallbacks;
            zzmm zzmm = this.zzama;
            if (zzmm != null) {
                try {
                    zzmm.zza(new zznr(videoLifecycleCallbacks));
                } catch (RemoteException e) {
                    zzaky.zzb("Unable to call setVideoLifecycleCallbacks on video controller.", e);
                }
            }
        }
    }

    public final void zza(zzmm zzmm) {
        synchronized (this.mLock) {
            this.zzama = zzmm;
            VideoLifecycleCallbacks videoLifecycleCallbacks = this.zzamb;
            if (videoLifecycleCallbacks != null) {
                setVideoLifecycleCallbacks(videoLifecycleCallbacks);
            }
        }
    }

    public final zzmm zzbh() {
        zzmm zzmm;
        synchronized (this.mLock) {
            zzmm = this.zzama;
        }
        return zzmm;
    }
}
