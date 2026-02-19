package com.google.android.gms.internal;

import com.google.android.gms.cast.Cast;

final class zzbbz extends Cast.Listener {
    private /* synthetic */ zzbby zzfic;

    zzbbz(zzbby zzbby) {
        this.zzfic = zzbby;
    }

    public final void onVolumeChanged() {
        this.zzfic.zzafy();
    }
}
