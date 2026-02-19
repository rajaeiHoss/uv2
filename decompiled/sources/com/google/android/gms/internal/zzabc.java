package com.google.android.gms.internal;

import java.lang.Thread;

final class zzabc implements Thread.UncaughtExceptionHandler {
    private /* synthetic */ Thread.UncaughtExceptionHandler zzcqz;
    private /* synthetic */ zzabb zzcra;

    zzabc(zzabb zzabb, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzcra = zzabb;
        this.zzcqz = uncaughtExceptionHandler;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        try {
            this.zzcra.zza(thread, th);
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.zzcqz;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            }
        } catch (Throwable th2) {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = this.zzcqz;
            if (uncaughtExceptionHandler2 != null) {
                uncaughtExceptionHandler2.uncaughtException(thread, th);
            }
            throw th2;
        }
    }
}
