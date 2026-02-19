package com.google.android.gms.internal;

import android.widget.SeekBar;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzbcc extends UIController implements RemoteMediaClient.ProgressListener {
    private boolean zzfhf = true;
    private final SeekBar zzfhy;
    private final long zzfim;

    public zzbcc(SeekBar seekBar, long j) {
        this.zzfhy = seekBar;
        this.zzfim = j;
        seekBar.setEnabled(false);
    }

    public final void onProgressUpdated(long j, long j2) {
        if (this.zzfhf) {
            this.zzfhy.setMax((int) j2);
            this.zzfhy.setProgress((int) j);
            this.zzfhy.setEnabled(true);
        }
    }

    public final void onSendingRemoteMediaRequest() {
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        boolean z = true;
        if (remoteMediaClient != null) {
            remoteMediaClient.addProgressListener(this, this.zzfim);
            if (remoteMediaClient.hasMediaSession()) {
                this.zzfhy.setMax((int) remoteMediaClient.getStreamDuration());
                this.zzfhy.setProgress((int) remoteMediaClient.getApproximateStreamPosition());
                this.zzfhy.setEnabled(z);
            }
        }
        this.zzfhy.setMax(1);
        z = false;
        this.zzfhy.setProgress(0);
        this.zzfhy.setEnabled(z);
    }

    public final void onSessionEnded() {
        if (getRemoteMediaClient() != null) {
            getRemoteMediaClient().removeProgressListener(this);
        }
        this.zzfhy.setMax(1);
        this.zzfhy.setProgress(0);
        this.zzfhy.setEnabled(false);
        super.onSessionEnded();
    }

    public final void zzbf(boolean z) {
        this.zzfhf = z;
    }
}
