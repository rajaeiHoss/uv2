package com.google.android.gms.internal;

import android.view.Display;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.Status;

final class zzbey implements CastRemoteDisplay.CastRemoteDisplaySessionResult {
    private final Status mStatus;
    private final Display zzdmh;

    public zzbey(Display display) {
        this.mStatus = Status.zzftq;
        this.zzdmh = display;
    }

    public zzbey(Status status) {
        this.mStatus = status;
        this.zzdmh = null;
    }

    public final Display getPresentationDisplay() {
        return this.zzdmh;
    }

    public final Status getStatus() {
        return this.mStatus;
    }
}
