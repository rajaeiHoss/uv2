package com.google.android.gms.internal;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

final class zzdbx implements ComponentCallbacks2 {
    final /* synthetic */ zzdbo zzkyv;

    zzdbx(zzdbo zzdbo) {
        this.zzkyv = zzdbo;
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public final void onLowMemory() {
    }

    public final void onTrimMemory(int i) {
        if (i == 20) {
            this.zzkyv.zzkvr.execute(new zzdby(this));
        }
    }
}
