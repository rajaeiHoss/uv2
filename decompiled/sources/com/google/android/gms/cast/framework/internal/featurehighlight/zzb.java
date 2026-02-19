package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.view.GestureDetector;
import android.view.MotionEvent;

final class zzb extends GestureDetector.SimpleOnGestureListener {
    private /* synthetic */ zza zzfcp;

    zzb(zza zza) {
        this.zzfcp = zza;
    }

    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (this.zzfcp.zzc(x, y) && this.zzfcp.zzfcf.zzd(x, y)) {
            return true;
        }
        this.zzfcp.zzfcn.dismiss();
        return true;
    }
}
