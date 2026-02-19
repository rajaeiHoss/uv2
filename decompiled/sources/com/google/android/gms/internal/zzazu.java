package com.google.android.gms.internal;

import com.google.android.gms.awareness.snapshot.BeaconStateResult;
import com.google.android.gms.awareness.state.BeaconState;
import com.google.android.gms.common.api.Status;

final class zzazu implements BeaconStateResult {
    private /* synthetic */ zzazy zzert;

    zzazu(zzazt zzazt, zzazy zzazy) {
        this.zzert = zzazy;
    }

    public final BeaconState getBeaconState() {
        if (this.zzert.zzadl() == null) {
            return null;
        }
        return this.zzert.zzadl().zzadg();
    }

    public final Status getStatus() {
        return this.zzert.getStatus();
    }
}
