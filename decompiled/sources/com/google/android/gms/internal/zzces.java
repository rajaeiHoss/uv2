package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.instantapps.LaunchData;
import com.google.android.gms.instantapps.zze;

final class zzces implements zze {
    private final Status zzefs;
    private final LaunchData zzipp;

    zzces(Status status, LaunchData launchData) {
        this.zzefs = status;
        this.zzipp = launchData;
    }

    public final Status getStatus() {
        return this.zzefs;
    }

    public final LaunchData zzaxa() {
        return this.zzipp;
    }
}
