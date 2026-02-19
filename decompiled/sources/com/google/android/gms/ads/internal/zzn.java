package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View;

final class zzn implements View.OnTouchListener {
    private /* synthetic */ zzl zzaoc;
    private /* synthetic */ zzw zzaod;

    zzn(zzl zzl, zzw zzw) {
        this.zzaoc = zzl;
        this.zzaod = zzw;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        this.zzaod.recordClick();
        if (this.zzaoc.zzaoa == null) {
            return false;
        }
        this.zzaoc.zzaoa.zzpg();
        return false;
    }
}
