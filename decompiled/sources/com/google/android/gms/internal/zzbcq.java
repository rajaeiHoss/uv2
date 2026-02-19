package com.google.android.gms.internal;

import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zzbcq implements ResultCallback<Status> {
    private /* synthetic */ zzbcl zzfkt;
    private /* synthetic */ long zzfky;

    zzbcq(zzbcl zzbcl, long j) {
        this.zzfkt = zzbcl;
        this.zzfky = j;
    }

    public final void onResult(Status status) {
        if (!status.isSuccess()) {
            this.zzfkt.zzc(this.zzfky, status.getStatusCode());
        }
    }
}
