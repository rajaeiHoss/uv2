package com.google.android.gms.cast;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Status;

final class zzm implements Cast.ApplicationConnectionResult {
    private /* synthetic */ Status zzetp;

    zzm(Cast.zza zza, Status status) {
        this.zzetp = status;
    }

    public final ApplicationMetadata getApplicationMetadata() {
        return null;
    }

    public final String getApplicationStatus() {
        return null;
    }

    public final String getSessionId() {
        return null;
    }

    public final Status getStatus() {
        return this.zzetp;
    }

    public final boolean getWasLaunched() {
        return false;
    }
}
