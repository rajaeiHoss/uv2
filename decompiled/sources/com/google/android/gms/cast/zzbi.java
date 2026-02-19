package com.google.android.gms.cast;

import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zzbi implements ResultCallback<Status> {
    private final long zzeyz;
    private final RemoteMediaPlayer zzexz;

    zzbi(RemoteMediaPlayer remoteMediaPlayer, long j) {
        this.zzexz = remoteMediaPlayer;
        this.zzeyz = j;
    }

    public final void onResult(Status status) {
        if (!status.isSuccess()) {
            this.zzexz.zzext.zzc(this.zzeyz, status.getStatusCode());
        }
    }
}
