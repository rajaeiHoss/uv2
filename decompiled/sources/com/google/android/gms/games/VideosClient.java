package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.api.zzp;
import com.google.android.gms.games.internal.zzg;
import com.google.android.gms.games.video.CaptureState;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.games.video.Videos;
import com.google.android.gms.tasks.Task;

public class VideosClient extends zzp {
    public static final int CAPTURE_OVERLAY_STATE_CAPTURE_STARTED = 2;
    public static final int CAPTURE_OVERLAY_STATE_CAPTURE_STOPPED = 3;
    public static final int CAPTURE_OVERLAY_STATE_DISMISSED = 4;
    public static final int CAPTURE_OVERLAY_STATE_SHOWN = 1;
    private static final zzbo<Videos.CaptureAvailableResult, Boolean> zzhwa = new zzda();
    private static final zzbo<Videos.CaptureStateResult, CaptureState> zzhwb = new zzdb();
    private static final zzbo<Videos.CaptureCapabilitiesResult, VideoCapabilities> zzhwc = new zzdc();

    public interface OnCaptureOverlayStateListener extends Videos.CaptureOverlayStateListener {
        void onCaptureOverlayStateChanged(int i);
    }

    VideosClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    VideosClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<VideoCapabilities> getCaptureCapabilities() {
        return zzg.zza(Games.Videos.getCaptureCapabilities(zzahw()), zzhwc);
    }

    public Task<Intent> getCaptureOverlayIntent() {
        return zza(new zzcw(this));
    }

    public Task<CaptureState> getCaptureState() {
        return zzg.zza(Games.Videos.getCaptureState(zzahw()), zzhwb);
    }

    public Task<Boolean> isCaptureAvailable(int i) {
        return zzg.zza(Games.Videos.isCaptureAvailable(zzahw(), i), zzhwa);
    }

    public Task<Boolean> isCaptureSupported() {
        return zza(new zzcx(this));
    }

    public Task<Void> registerOnCaptureOverlayStateChangedListener(OnCaptureOverlayStateListener onCaptureOverlayStateListener) {
        zzci zza = zza(onCaptureOverlayStateListener, OnCaptureOverlayStateListener.class.getSimpleName());
        return zza(new zzcy(this, zza, zza), new zzcz(this, zza.zzakx()));
    }

    public Task<Boolean> unregisterOnCaptureOverlayStateChangedListener(OnCaptureOverlayStateListener onCaptureOverlayStateListener) {
        return zza((zzck<?>) zzcm.zzb(onCaptureOverlayStateListener, OnCaptureOverlayStateListener.class.getSimpleName()));
    }
}
