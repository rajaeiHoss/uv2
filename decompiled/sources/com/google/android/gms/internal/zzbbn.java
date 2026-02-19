package com.google.android.gms.internal;

import android.content.Context;
import android.view.View;
import com.google.android.gms.R;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import java.util.Iterator;
import java.util.List;

public final class zzbbn extends UIController {
    private final View mView;
    private final String zzfeb;
    private final String zzfhp;

    public zzbbn(View view, Context context) {
        this.mView = view;
        this.zzfeb = context.getString(R.string.cast_closed_captions);
        this.zzfhp = context.getString(R.string.cast_closed_captions_unavailable);
        view.setEnabled(false);
    }

    private final void zzafy() {
        View view;
        String str;
        boolean z;
        List<MediaTrack> mediaTracks;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession()) {
            MediaInfo mediaInfo = remoteMediaClient.getMediaInfo();
            if (mediaInfo != null && (mediaTracks = mediaInfo.getMediaTracks()) != null && !mediaTracks.isEmpty()) {
                Iterator<MediaTrack> it = mediaTracks.iterator();
                int i = 0;
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    MediaTrack next = it.next();
                    if (next.getType() == 2) {
                        i++;
                        if (i > 1) {
                            break;
                        }
                    } else if (next.getType() == 1) {
                        break;
                    }
                }
                z = true;
                if (z && !remoteMediaClient.isPlayingAd()) {
                    this.mView.setEnabled(true);
                    view = this.mView;
                    str = this.zzfeb;
                    view.setContentDescription(str);
                }
            }
            z = false;
            this.mView.setEnabled(true);
            view = this.mView;
            str = this.zzfeb;
            view.setContentDescription(str);
        }
        this.mView.setEnabled(false);
        view = this.mView;
        str = this.zzfhp;
        view.setContentDescription(str);
    }

    public final void onMediaStatusUpdated() {
        zzafy();
    }

    public final void onSendingRemoteMediaRequest() {
        this.mView.setEnabled(false);
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        this.mView.setEnabled(true);
        zzafy();
    }

    public final void onSessionEnded() {
        this.mView.setEnabled(false);
        super.onSessionEnded();
    }
}
