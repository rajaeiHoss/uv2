package com.google.android.gms.internal;

import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzbch extends UIController implements RemoteMediaClient.ProgressListener {
    private final TextView zzfip;
    private final String zzfiq;
    private final View zzfir;

    public zzbch(TextView textView, String str, View view) {
        this.zzfip = textView;
        this.zzfiq = str;
        this.zzfir = view;
    }

    private final void zzb(long j, boolean z) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            this.zzfip.setVisibility(0);
            this.zzfip.setText(this.zzfiq);
            View view = this.zzfir;
            if (view != null) {
                view.setVisibility(4);
            }
        } else if (!remoteMediaClient.isLiveStream()) {
            if (z) {
                j = remoteMediaClient.getStreamDuration();
            }
            this.zzfip.setVisibility(0);
            this.zzfip.setText(DateUtils.formatElapsedTime(j / 1000));
            View view2 = this.zzfir;
            if (view2 != null) {
                view2.setVisibility(4);
            }
        } else {
            this.zzfip.setText(this.zzfiq);
            if (this.zzfir != null) {
                this.zzfip.setVisibility(4);
                this.zzfir.setVisibility(0);
            }
        }
    }

    public final void onMediaStatusUpdated() {
        zzb(-1, true);
    }

    public final void onProgressUpdated(long j, long j2) {
        zzb(j2, false);
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        if (getRemoteMediaClient() != null) {
            getRemoteMediaClient().addProgressListener(this, 1000);
        }
        zzb(-1, true);
    }

    public final void onSessionEnded() {
        this.zzfip.setText(this.zzfiq);
        if (getRemoteMediaClient() != null) {
            getRemoteMediaClient().removeProgressListener(this);
        }
        super.onSessionEnded();
    }
}
