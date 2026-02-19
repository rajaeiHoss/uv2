package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

final class zzbk implements RemoteMediaPlayer.MediaChannelResult {
    private /* synthetic */ Status zzetp;

    zzbk(RemoteMediaPlayer.zzb zzb, Status status) {
        this.zzetp = status;
    }

    public final JSONObject getCustomData() {
        return null;
    }

    public final Status getStatus() {
        return this.zzetp;
    }
}
