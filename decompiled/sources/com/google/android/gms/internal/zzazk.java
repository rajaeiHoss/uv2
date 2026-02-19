package com.google.android.gms.internal;

import com.google.android.gms.awareness.snapshot.DetectedActivityResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognitionResult;

final class zzazk implements DetectedActivityResult {
    private /* synthetic */ zzazy zzert;

    zzazk(zzazj zzazj, zzazy zzazy) {
        this.zzert = zzazy;
    }

    public final ActivityRecognitionResult getActivityRecognitionResult() {
        if (this.zzert.zzadl() == null) {
            return null;
        }
        return this.zzert.zzadl().getActivityRecognitionResult();
    }

    public final Status getStatus() {
        return this.zzert.getStatus();
    }
}
