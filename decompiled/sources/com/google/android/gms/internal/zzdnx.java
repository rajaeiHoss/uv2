package com.google.android.gms.internal;

import android.view.Choreographer;

final class zzdnx extends zzdnr {
    private Choreographer zzlya = Choreographer.getInstance();

    public final void zza(zzdnt zzdnt) {
        this.zzlya.postFrameCallback(zzdnt.zzbmg());
    }
}
