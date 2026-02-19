package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;

final class zzdnw extends zzdnr {
    private Handler handler;

    public zzdnw(Looper looper) {
        this.handler = new Handler(looper);
    }

    public final void zza(zzdnt zzdnt) {
        this.handler.postDelayed(zzdnt.zzbmh(), 0);
    }
}
