package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;

public final class zzedf implements zzegt {
    private final Handler handler = new Handler(Looper.getMainLooper());

    public final void restart() {
    }

    public final void shutdown() {
    }

    public final void zzn(Runnable runnable) {
        this.handler.post(runnable);
    }
}
