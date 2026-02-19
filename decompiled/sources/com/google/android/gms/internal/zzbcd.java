package com.google.android.gms.internal;

import android.view.View;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzbcd extends UIController {
    private final View mView;

    public zzbcd(View view) {
        this.mView = view;
        view.setEnabled(false);
    }

    private final void zzafy() {
        View view;
        boolean z;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || remoteMediaClient.isLiveStream() || remoteMediaClient.isPlayingAd()) {
            view = this.mView;
            z = false;
        } else {
            view = this.mView;
            z = true;
        }
        view.setEnabled(z);
    }

    public final void onMediaStatusUpdated() {
        zzafy();
    }

    public final void onSendingRemoteMediaRequest() {
        this.mView.setEnabled(false);
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        zzafy();
    }

    public final void onSessionEnded() {
        this.mView.setEnabled(false);
        super.onSessionEnded();
    }
}
