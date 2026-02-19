package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzj;

final class zzat extends zzbj {
    private /* synthetic */ zzj zzfxy;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzat(zzar zzar, zzbh zzbh, zzj zzj) {
        super(zzbh);
        this.zzfxy = zzj;
    }

    public final void zzajj() {
        this.zzfxy.zzf(new ConnectionResult(16, (PendingIntent) null));
    }
}
