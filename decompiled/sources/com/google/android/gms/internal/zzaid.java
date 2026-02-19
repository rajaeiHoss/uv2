package com.google.android.gms.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@zzabh
public final class zzaid {
    public static final ThreadPoolExecutor zzdfi;
    private static final ThreadPoolExecutor zzdfj;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 20, 1, TimeUnit.MINUTES, new LinkedBlockingQueue(), zzch("Default"));
        zzdfi = threadPoolExecutor;
        ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue(), zzch("Loader"));
        zzdfj = threadPoolExecutor2;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        threadPoolExecutor2.allowCoreThreadTimeOut(true);
    }

    public static zzalt<Void> zza(int i, Runnable runnable) {
        ThreadPoolExecutor threadPoolExecutor;
        Callable zzaif;
        if (i == 1) {
            threadPoolExecutor = zzdfj;
            zzaif = new zzaie(runnable);
        } else {
            threadPoolExecutor = zzdfi;
            zzaif = new zzaif(runnable);
        }
        return zza((ExecutorService) threadPoolExecutor, zzaif);
    }

    public static <T> zzalt<T> zza(ExecutorService executorService, Callable<T> callable) {
        zzamd zzamd = new zzamd();
        try {
            zzamd.zza(new zzaih(zzamd, executorService.submit(new zzaig(zzamd, callable))), zzaly.zzdjt);
        } catch (RejectedExecutionException e) {
            zzahw.zzc("Thread execution is rejected.", e);
            zzamd.setException(e);
        }
        return zzamd;
    }

    public static zzalt<Void> zzb(Runnable runnable) {
        return zza(0, runnable);
    }

    private static ThreadFactory zzch(String str) {
        return new zzaii(str);
    }
}
