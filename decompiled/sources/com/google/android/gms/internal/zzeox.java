package com.google.android.gms.internal;

import java.lang.Thread;

final class zzeox implements Thread.UncaughtExceptionHandler {
    private /* synthetic */ zzeow zznre;

    zzeox(zzeow zzeow) {
        this.zznre = zzeow;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        this.zznre.zznrd.zzj(th);
    }
}
