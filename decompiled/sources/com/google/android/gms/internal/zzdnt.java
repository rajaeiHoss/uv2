package com.google.android.gms.internal;

import android.view.Choreographer;

public abstract class zzdnt {
    private Runnable zzjhk;
    private Choreographer.FrameCallback zzlxy;

    public abstract void doFrame(long j);

    /* access modifiers changed from: package-private */
    public final Choreographer.FrameCallback zzbmg() {
        if (this.zzlxy == null) {
            this.zzlxy = new zzdnu(this);
        }
        return this.zzlxy;
    }

    /* access modifiers changed from: package-private */
    public final Runnable zzbmh() {
        if (this.zzjhk == null) {
            this.zzjhk = new zzdnv(this);
        }
        return this.zzjhk;
    }
}
