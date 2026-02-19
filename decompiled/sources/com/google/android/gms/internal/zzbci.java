package com.google.android.gms.internal;

import android.text.format.DateUtils;
import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzbci extends UIController implements RemoteMediaClient.ProgressListener {
    private boolean zzfhf = true;
    private final long zzfim;
    private final TextView zzfip;
    private final String zzfis;

    public zzbci(TextView textView, long j, String str) {
        this.zzfip = textView;
        this.zzfim = j;
        this.zzfis = str;
    }

    public final void onProgressUpdated(long j, long j2) {
        if (this.zzfhf) {
            this.zzfip.setText(DateUtils.formatElapsedTime(j / 1000));
        }
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.addProgressListener(this, this.zzfim);
            if (remoteMediaClient.hasMediaSession()) {
                this.zzfip.setText(DateUtils.formatElapsedTime(remoteMediaClient.getApproximateStreamPosition() / 1000));
            } else {
                this.zzfip.setText(this.zzfis);
            }
        }
    }

    public final void onSessionEnded() {
        this.zzfip.setText(this.zzfis);
        if (getRemoteMediaClient() != null) {
            getRemoteMediaClient().removeProgressListener(this);
        }
        super.onSessionEnded();
    }

    public final void zzbf(boolean z) {
        this.zzfhf = z;
    }

    public final void zzy(long j) {
        this.zzfip.setText(DateUtils.formatElapsedTime(j / 1000));
    }
}
