package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.R;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzbca extends UIController {
    private final ImageView zzfhq;
    private final View zzfid;
    private final boolean zzfie;
    private final Drawable zzfif;
    private final String zzfig;
    private final Drawable zzfih;
    private final String zzfii;
    private final Drawable zzfij;
    private final String zzfik;

    public zzbca(ImageView imageView, Context context, Drawable drawable, Drawable drawable2, Drawable drawable3, View view, boolean z) {
        this.zzfhq = imageView;
        this.zzfif = drawable;
        this.zzfih = drawable2;
        this.zzfij = drawable3 != null ? drawable3 : drawable2;
        this.zzfig = context.getString(R.string.cast_play);
        this.zzfii = context.getString(R.string.cast_pause);
        this.zzfik = context.getString(R.string.cast_stop);
        this.zzfid = view;
        this.zzfie = z;
        imageView.setEnabled(false);
    }

    private final void zza(Drawable drawable, String str) {
        this.zzfhq.setImageDrawable(drawable);
        this.zzfhq.setContentDescription(str);
        this.zzfhq.setVisibility(0);
        this.zzfhq.setEnabled(true);
        View view = this.zzfid;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    private final void zzafy() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            this.zzfhq.setEnabled(false);
        } else if (remoteMediaClient.isPaused()) {
            zza(this.zzfif, this.zzfig);
        } else if (remoteMediaClient.isPlaying()) {
            if (remoteMediaClient.isLiveStream()) {
                zza(this.zzfij, this.zzfik);
            } else {
                zza(this.zzfih, this.zzfii);
            }
        } else if (remoteMediaClient.isBuffering()) {
            zzbe(false);
        } else if (remoteMediaClient.isLoadingNextItem()) {
            zzbe(true);
        }
    }

    private final void zzbe(boolean z) {
        View view = this.zzfid;
        int i = 0;
        if (view != null) {
            view.setVisibility(0);
        }
        ImageView imageView = this.zzfhq;
        if (this.zzfie) {
            i = 4;
        }
        imageView.setVisibility(i);
        this.zzfhq.setEnabled(!z);
    }

    public final void onMediaStatusUpdated() {
        zzafy();
    }

    public final void onSendingRemoteMediaRequest() {
        zzbe(true);
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        zzafy();
    }

    public final void onSessionEnded() {
        this.zzfhq.setEnabled(false);
        super.onSessionEnded();
    }
}
