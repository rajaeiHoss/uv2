package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View;

final class zzbp implements View.OnTouchListener {
    private /* synthetic */ zzbn zzasj;

    zzbp(zzbn zzbn) {
        this.zzasj = zzbn;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.zzasj.zzash == null) {
            return false;
        }
        this.zzasj.zzash.zza(motionEvent);
        return false;
    }
}
