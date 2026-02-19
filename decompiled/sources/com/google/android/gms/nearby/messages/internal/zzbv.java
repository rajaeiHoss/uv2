package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.nearby.Nearby;

abstract class zzbv extends zzm<Status, zzah> {
    private final zzci<zzn<Status>> zzgwf;

    public zzbv(GoogleApiClient googleApiClient) {
        super((Api<?>) Nearby.MESSAGES_API, googleApiClient);
        this.zzgwf = googleApiClient.zzt(this);
    }

    public final Status zzb(Status status) {
        return status;
    }

    /* access modifiers changed from: package-private */
    public final zzci<zzn<Status>> zzbdx() {
        return this.zzgwf;
    }
}
