package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

final class zzc extends GestureDetector.SimpleOnGestureListener {
    private /* synthetic */ View zzfcq;
    private /* synthetic */ boolean zzfcr = true;
    private /* synthetic */ zzh zzfcs;

    zzc(zza zza, View view, boolean z, zzh zzh) {
        this.zzfcq = view;
        this.zzfcs = zzh;
    }

    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        if (this.zzfcq.getParent() != null) {
            this.zzfcq.performClick();
        }
        if (!this.zzfcr) {
            return true;
        }
        this.zzfcs.zzaey();
        return true;
    }
}
