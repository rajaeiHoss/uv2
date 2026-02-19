package com.google.android.gms.internal;

import android.view.View;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzbbs extends UIController {
    private final View mView;

    public zzbbs(View view) {
        this.mView = view;
        view.setEnabled(false);
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        this.mView.setEnabled(true);
    }

    public final void onSessionEnded() {
        this.mView.setEnabled(false);
        super.onSessionEnded();
    }
}
