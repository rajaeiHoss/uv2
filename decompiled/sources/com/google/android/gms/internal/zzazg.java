package com.google.android.gms.internal;

import com.google.android.gms.awareness.snapshot.TimeIntervalsResult;
import com.google.android.gms.awareness.state.TimeIntervals;
import com.google.android.gms.common.api.Status;

final class zzazg implements TimeIntervalsResult {
    private /* synthetic */ zzazy zzert;

    zzazg(zzazf zzazf, zzazy zzazy) {
        this.zzert = zzazy;
    }

    public final Status getStatus() {
        return this.zzert.getStatus();
    }

    public final TimeIntervals getTimeIntervals() {
        if (this.zzert.zzadl() == null) {
            return null;
        }
        return this.zzert.zzadl().zzadk();
    }
}
