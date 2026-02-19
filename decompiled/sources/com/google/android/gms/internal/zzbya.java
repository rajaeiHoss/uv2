package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;

abstract class zzbya extends zzbxy<Status> {
    zzbya(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzad */
    public Status zzb(Status status) {
        zzbq.checkArgument(!status.isSuccess());
        return status;
    }
}
