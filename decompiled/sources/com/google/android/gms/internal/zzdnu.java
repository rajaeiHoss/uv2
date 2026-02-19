package com.google.android.gms.internal;

import android.view.Choreographer;

final class zzdnu implements Choreographer.FrameCallback {
    private /* synthetic */ zzdnt zzlxz;

    zzdnu(zzdnt zzdnt) {
        this.zzlxz = zzdnt;
    }

    public final void doFrame(long j) {
        this.zzlxz.doFrame(j);
    }
}
