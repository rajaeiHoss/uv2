package com.google.android.gms.internal;

import java.lang.Thread;

final class zzabd implements Thread.UncaughtExceptionHandler {
    private /* synthetic */ zzabb zzcra;
    private /* synthetic */ Thread.UncaughtExceptionHandler zzcrb;

    zzabd(zzabb zzabb, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzcra = zzabb;
        this.zzcrb = uncaughtExceptionHandler;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        try {
            this.zzcra.zza(thread, th);
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.zzcrb;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            }
        } catch (Throwable th2) {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = this.zzcrb;
            if (uncaughtExceptionHandler2 != null) {
                uncaughtExceptionHandler2.uncaughtException(thread, th);
            }
            throw th2;
        }
    }
}
