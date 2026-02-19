package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

final class zzao implements RemoteMediaClient.MediaChannelResult {
    private /* synthetic */ Status zzetp;

    zzao(RemoteMediaClient.zzb zzb, Status status) {
        this.zzetp = status;
    }

    public final JSONObject getCustomData() {
        return null;
    }

    public final Status getStatus() {
        return this.zzetp;
    }
}
