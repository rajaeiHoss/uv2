package com.google.android.gms.internal;

import android.view.View;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzbck extends UIController {
    private final View mView;
    private final int zzfin;

    public zzbck(View view, int i) {
        this.mView = view;
        this.zzfin = i;
    }

    private final void zzafy() {
        View view;
        int i;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            view = this.mView;
            i = this.zzfin;
        } else {
            view = this.mView;
            i = 0;
        }
        view.setVisibility(i);
    }

    public final void onMediaStatusUpdated() {
        zzafy();
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        zzafy();
    }

    public final void onSessionEnded() {
        this.mView.setVisibility(this.zzfin);
        super.onSessionEnded();
    }
}
