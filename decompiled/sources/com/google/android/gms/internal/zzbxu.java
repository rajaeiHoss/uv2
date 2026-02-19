package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;

abstract class zzbxu extends zzbxs<Status> {
    zzbxu(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final Status zzb(Status status) {
        zzbq.checkArgument(!status.isSuccess());
        return status;
    }
}
