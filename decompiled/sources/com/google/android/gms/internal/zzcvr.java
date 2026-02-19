package com.google.android.gms.internal;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.panorama.PanoramaApi;

abstract class zzcvr extends zzcvt<PanoramaApi.PanoramaResult> {
    public zzcvr(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final PanoramaApi.PanoramaResult zzb(Status status) {
        return new zzcvv(status, (Intent) null);
    }
}
