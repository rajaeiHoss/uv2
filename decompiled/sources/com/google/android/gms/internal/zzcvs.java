package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.panorama.PanoramaApi;

final class zzcvs extends zzcvk {
    private final zzn<PanoramaApi.PanoramaResult> zzgbf;

    public zzcvs(zzn<PanoramaApi.PanoramaResult> zzn) {
        this.zzgbf = zzn;
    }

    public final void zza(int i, Bundle bundle, int i2, Intent intent) {
        this.zzgbf.setResult(new zzcvv(new Status(i, (String) null, bundle != null ? (PendingIntent) bundle.getParcelable("pendingIntent") : null), intent));
    }
}
