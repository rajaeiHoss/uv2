package com.google.android.gms.internal;

import android.view.View;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzbbv extends UIController {
    private final View mView;

    public zzbbv(View view) {
        this.mView = view;
    }

    private final void zzafy() {
        View view;
        int i;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || remoteMediaClient.isBuffering()) {
            view = this.mView;
            i = 0;
        } else {
            view = this.mView;
            i = 8;
        }
        view.setVisibility(i);
    }

    public final void onMediaStatusUpdated() {
        zzafy();
    }

    public final void onSendingRemoteMediaRequest() {
        this.mView.setVisibility(0);
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        zzafy();
    }

    public final void onSessionEnded() {
        this.mView.setVisibility(8);
        super.onSessionEnded();
    }
}
