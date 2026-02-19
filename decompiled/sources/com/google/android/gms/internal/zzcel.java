package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.instantapps.LaunchData;

final class zzcel extends zzceq {
    private /* synthetic */ zzcek zzipm;

    zzcel(zzcek zzcek) {
        this.zzipm = zzcek;
    }

    public final void zza(Status status, LaunchData launchData) {
        this.zzipm.setResult(new zzces(status, launchData));
    }
}
