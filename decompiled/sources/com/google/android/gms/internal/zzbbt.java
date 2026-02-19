package com.google.android.gms.internal;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.SeekBar;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.google.android.gms.common.util.zzs;

public final class zzbbt extends UIController {
    private final SeekBar zzfhx;
    private final SeekBar zzfhy;

    public zzbbt(SeekBar seekBar, SeekBar seekBar2) {
        this.zzfhx = seekBar;
        this.zzfhy = seekBar2;
        seekBar.setClickable(false);
        if (zzs.zzanv()) {
            seekBar.setThumb((Drawable) null);
        } else {
            seekBar.setThumb(new ColorDrawable(0));
        }
        seekBar.setMax(1);
        seekBar.setProgress(1);
        seekBar.setOnTouchListener(new zzbbu(this));
    }

    private final void zzaga() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession()) {
            boolean isLiveStream = remoteMediaClient.isLiveStream();
            int i = 0;
            this.zzfhx.setVisibility(isLiveStream ? 0 : 4);
            SeekBar seekBar = this.zzfhy;
            if (isLiveStream) {
                i = 4;
            }
            seekBar.setVisibility(i);
        }
    }

    public final void onMediaStatusUpdated() {
        zzaga();
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        zzaga();
    }
}
