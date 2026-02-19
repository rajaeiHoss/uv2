package com.google.android.gms.cast.framework.media;

import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zzam implements ResultCallback<Status> {
    private final long zzeyz;
    private final RemoteMediaClient zzfft;

    zzam(RemoteMediaClient remoteMediaClient, long j) {
        this.zzfft = remoteMediaClient;
        this.zzeyz = j;
    }

    public final void onResult(Status status) {
        if (!status.isSuccess()) {
            this.zzfft.zzext.zzc(this.zzeyz, status.getStatusCode());
        }
    }
}
