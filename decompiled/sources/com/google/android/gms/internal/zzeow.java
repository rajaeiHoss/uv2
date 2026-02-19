package com.google.android.gms.internal;

import java.lang.Thread;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

final class zzeow implements ThreadFactory {
    final /* synthetic */ zzeou zznrd;

    private zzeow(zzeou zzeou) {
        this.zznrd = zzeou;
    }

    /* synthetic */ zzeow(zzeou zzeou, zzeov zzeov) {
        this(zzeou);
    }

    public final Thread newThread(Runnable runnable) {
        Thread newThread = Executors.defaultThreadFactory().newThread(runnable);
        zzejl handler = zzejl.zzniu;
        handler.zza(newThread, "FirebaseDatabaseWorker");
        handler.zza(newThread, true);
        handler.zza(newThread, (Thread.UncaughtExceptionHandler) new zzeox(this));
        return newThread;
    }
}
