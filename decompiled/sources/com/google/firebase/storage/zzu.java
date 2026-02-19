package com.google.firebase.storage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class zzu {
    private static zzu zzovq = new zzu();
    private static BlockingQueue<Runnable> zzovr = new LinkedBlockingQueue(128);
    private static final ThreadPoolExecutor zzovs;
    private static BlockingQueue<Runnable> zzovt = new LinkedBlockingQueue(128);
    private static final ThreadPoolExecutor zzovu;
    private static BlockingQueue<Runnable> zzovv = new LinkedBlockingQueue(128);
    private static final ThreadPoolExecutor zzovw;
    private static BlockingQueue<Runnable> zzovx = new LinkedBlockingQueue(128);
    private static final ThreadPoolExecutor zzovy;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 5, TimeUnit.SECONDS, zzovr, new zzv("Command-"));
        zzovs = threadPoolExecutor;
        ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(2, 2, 5, TimeUnit.SECONDS, zzovt, new zzv("Upload-"));
        zzovu = threadPoolExecutor2;
        ThreadPoolExecutor threadPoolExecutor3 = new ThreadPoolExecutor(3, 3, 5, TimeUnit.SECONDS, zzovv, new zzv("Download-"));
        zzovw = threadPoolExecutor3;
        ThreadPoolExecutor threadPoolExecutor4 = new ThreadPoolExecutor(1, 1, 5, TimeUnit.SECONDS, zzovx, new zzv("Callbacks-"));
        zzovy = threadPoolExecutor4;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        threadPoolExecutor2.allowCoreThreadTimeOut(true);
        threadPoolExecutor3.allowCoreThreadTimeOut(true);
        threadPoolExecutor4.allowCoreThreadTimeOut(true);
    }

    public static void zzt(Runnable runnable) {
        zzovs.execute(runnable);
    }

    public static void zzu(Runnable runnable) {
        zzovu.execute(runnable);
    }

    public static void zzv(Runnable runnable) {
        zzovw.execute(runnable);
    }

    public static void zzw(Runnable runnable) {
        zzovy.execute(runnable);
    }
}
