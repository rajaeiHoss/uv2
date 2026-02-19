package com.google.android.gms.internal;

import android.content.Context;
import android.widget.ImageView;
import com.google.android.gms.R;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzbby extends UIController {
    private final Context zzbky;
    private Cast.Listener zzetn = null;
    private final ImageView zzfhq;
    private final String zzfia;
    private final String zzfib;

    public zzbby(ImageView imageView, Context context) {
        this.zzfhq = imageView;
        Context applicationContext = context.getApplicationContext();
        this.zzbky = applicationContext;
        this.zzfia = applicationContext.getString(R.string.cast_mute);
        this.zzfib = applicationContext.getString(R.string.cast_unmute);
        imageView.setEnabled(false);
    }

    private final void zzbd(boolean z) {
        this.zzfhq.setSelected(z);
        this.zzfhq.setContentDescription(z ? this.zzfia : this.zzfib);
    }

    public final void onMediaStatusUpdated() {
        zzafy();
    }

    public final void onSendingRemoteMediaRequest() {
        this.zzfhq.setEnabled(false);
    }

    public final void onSessionConnected(CastSession castSession) {
        if (this.zzetn == null) {
            this.zzetn = new zzbbz(this);
        }
        super.onSessionConnected(castSession);
        castSession.addCastListener(this.zzetn);
        zzafy();
    }

    public final void onSessionEnded() {
        Cast.Listener listener;
        this.zzfhq.setEnabled(false);
        CastSession currentCastSession = CastContext.getSharedInstance(this.zzbky).getSessionManager().getCurrentCastSession();
        if (!(currentCastSession == null || (listener = this.zzetn) == null)) {
            currentCastSession.removeCastListener(listener);
        }
        super.onSessionEnded();
    }

    /* access modifiers changed from: protected */
    public final void zzafy() {
        CastSession currentCastSession = CastContext.getSharedInstance(this.zzbky).getSessionManager().getCurrentCastSession();
        if (currentCastSession == null || !currentCastSession.isConnected()) {
            this.zzfhq.setEnabled(false);
            return;
        }
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            this.zzfhq.setEnabled(false);
        } else {
            this.zzfhq.setEnabled(true);
        }
        if (currentCastSession.isMute()) {
            zzbd(true);
        } else {
            zzbd(false);
        }
    }
}
