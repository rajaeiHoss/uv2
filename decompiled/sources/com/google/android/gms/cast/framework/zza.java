package com.google.android.gms.cast.framework;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;

public final class zza extends zzg {
    private final AppVisibilityListener zzezq;

    public zza(AppVisibilityListener appVisibilityListener) {
        this.zzezq = appVisibilityListener;
    }

    public final void onAppEnteredBackground() {
        this.zzezq.onAppEnteredBackground();
    }

    public final void onAppEnteredForeground() {
        this.zzezq.onAppEnteredForeground();
    }

    public final int zzadx() {
        return 12211278;
    }

    public final IObjectWrapper zzady() {
        return zzn.zzz(this.zzezq);
    }
}
