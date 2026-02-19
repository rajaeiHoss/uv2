package com.google.android.gms.internal;

import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.panorama.PanoramaApi;

final class zzcvv implements PanoramaApi.PanoramaResult {
    private final Status mStatus;
    private final Intent zzkfh;

    public zzcvv(Status status, Intent intent) {
        this.mStatus = (Status) zzbq.checkNotNull(status);
        this.zzkfh = intent;
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    public final Intent getViewerIntent() {
        return this.zzkfh;
    }
}
