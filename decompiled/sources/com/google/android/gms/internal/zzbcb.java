package com.google.android.gms.internal;

import android.widget.ProgressBar;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzbcb extends UIController implements RemoteMediaClient.ProgressListener {
    private final ProgressBar zzfil;
    private final long zzfim;

    public zzbcb(ProgressBar progressBar, long j) {
        this.zzfil = progressBar;
        this.zzfim = j;
    }

    public final void onMediaStatusUpdated() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            this.zzfil.setMax(1);
            this.zzfil.setProgress(0);
        }
    }

    public final void onProgressUpdated(long j, long j2) {
        this.zzfil.setMax((int) j2);
        this.zzfil.setProgress((int) j);
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.addProgressListener(this, this.zzfim);
            if (remoteMediaClient.hasMediaSession()) {
                this.zzfil.setMax((int) remoteMediaClient.getStreamDuration());
                this.zzfil.setProgress((int) remoteMediaClient.getApproximateStreamPosition());
                return;
            }
            this.zzfil.setMax(1);
            this.zzfil.setProgress(0);
        }
    }

    public final void onSessionEnded() {
        if (getRemoteMediaClient() != null) {
            getRemoteMediaClient().removeProgressListener(this);
        }
        this.zzfil.setMax(1);
        this.zzfil.setProgress(0);
        super.onSessionEnded();
    }
}
