package com.google.android.gms.cast.framework;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;

public final class zzd extends zzo {
    private final CastStateListener zzfav;

    public zzd(CastStateListener castStateListener) {
        this.zzfav = castStateListener;
    }

    public final void onCastStateChanged(int i) {
        this.zzfav.onCastStateChanged(i);
    }

    public final int zzadx() {
        return 12211278;
    }

    public final IObjectWrapper zzady() {
        return zzn.zzz(this.zzfav);
    }
}
