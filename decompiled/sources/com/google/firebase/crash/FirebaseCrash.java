package com.google.firebase.crash;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.internal.zzecm;
import com.google.android.gms.internal.zzecn;
import com.google.android.gms.internal.zzeco;
import com.google.android.gms.internal.zzecp;
import com.google.android.gms.internal.zzecq;
import com.google.android.gms.internal.zzect;
import com.google.android.gms.internal.zzecy;
import com.google.firebase.FirebaseApp;
import java.lang.Thread;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FirebaseCrash {
    private static volatile FirebaseCrash zzmum;
    private final Context mContext;
    private final FirebaseApp zzmpb;
    private final ExecutorService zzmun;
    private final zzb zzmuo;
    private final CountDownLatch zzmup = new CountDownLatch(1);
    private zzecy zzmuq;

    public interface zza {
        zzect zzbux();
    }

    static final class zzb implements zza {
        private final Object zzmus;
        private zzect zzmut;

        private zzb() {
            this.zzmus = new Object();
        }

        /* synthetic */ zzb(zza zza) {
            this();
        }

        /* access modifiers changed from: private */
        public final void zzb(zzect zzect) {
            synchronized (this.zzmus) {
                this.zzmut = zzect;
            }
        }

        public final zzect zzbux() {
            zzect zzect;
            synchronized (this.zzmus) {
                zzect = this.zzmut;
            }
            return zzect;
        }
    }

    class zzc implements Thread.UncaughtExceptionHandler {
        private final Thread.UncaughtExceptionHandler zzmuu;

        public zzc(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.zzmuu = uncaughtExceptionHandler;
        }

        public final void uncaughtException(Thread thread, Throwable th) {
            Log.e("UncaughtException", "", th);
            if (!FirebaseCrash.this.zzbuv()) {
                try {
                    Future<?> zzi = FirebaseCrash.this.zzi(th);
                    if (zzi != null) {
                        zzi.get(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, TimeUnit.MILLISECONDS);
                    }
                } catch (Exception e) {
                    Log.e("UncaughtException", "Ouch! My own exception handler threw an exception.", e);
                }
            }
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.zzmuu;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            }
        }
    }

    private FirebaseCrash(FirebaseApp firebaseApp, ExecutorService executorService) {
        this.zzmpb = firebaseApp;
        this.zzmun = executorService;
        this.mContext = firebaseApp.getApplicationContext();
        this.zzmuo = new zzb((zza) null);
    }

    public static FirebaseCrash getInstance(FirebaseApp firebaseApp) {
        if (zzmum == null) {
            synchronized (FirebaseCrash.class) {
                if (zzmum == null) {
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
                    threadPoolExecutor.allowCoreThreadTimeOut(true);
                    FirebaseCrash firebaseCrash = new FirebaseCrash(firebaseApp, threadPoolExecutor);
                    com.google.firebase.crash.zzc zzc2 = new com.google.firebase.crash.zzc(firebaseApp, (String) null);
                    Thread.setDefaultUncaughtExceptionHandler(firebaseCrash.new zzc(Thread.getDefaultUncaughtExceptionHandler()));
                    com.google.firebase.crash.zzb zzb2 = new com.google.firebase.crash.zzb(firebaseCrash);
                    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
                    newFixedThreadPool.submit(new com.google.firebase.crash.zze(zzc2, newFixedThreadPool.submit(new com.google.firebase.crash.zzd(zzc2)), NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, zzb2));
                    newFixedThreadPool.shutdown();
                    firebaseCrash.zzmun.execute(new com.google.firebase.crash.zza(firebaseCrash));
                    zzmum = firebaseCrash;
                }
            }
        }
        return zzmum;
    }

    public static boolean isCrashCollectionEnabled() {
        return zzbuu().zzbuw();
    }

    public static void log(String str) {
        zzbuu().zzpk(str);
    }

    public static void logcat(int i, String str, String str2) {
        FirebaseCrash zzbuu = zzbuu();
        if (str2 != null) {
            if (str == null) {
                str = "";
            }
            Log.println(i, str, str2);
            zzbuu.zzpk(str2);
        }
    }

    public static void report(Throwable th) {
        FirebaseCrash zzbuu = zzbuu();
        if (th != null && !zzbuu.zzbuv()) {
            zzbuu.zzmun.submit(new zzecm(zzbuu.mContext, zzbuu.zzmuo, th, zzbuu.zzmuq));
        }
    }

    public static void setCrashCollectionEnabled(boolean z) {
        FirebaseCrash zzbuu = zzbuu();
        if (!zzbuu.zzbuv()) {
            zzbuu.zzmun.submit(new zzecq(zzbuu.mContext, zzbuu.zzmuo, z));
        }
    }

    /* access modifiers changed from: private */
    public final void zzbut() {
        try {
            this.zzmup.await(20000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Log.e("FirebaseCrash", "Failed waiting for crash api to load.", e);
        }
    }

    public static FirebaseCrash zzbuu() {
        return zzmum != null ? zzmum : getInstance(FirebaseApp.getInstance());
    }

    private final boolean zzbuw() {
        if (zzbuv()) {
            return false;
        }
        zzbut();
        zzect zzbux = this.zzmuo.zzbux();
        if (zzbux != null) {
            try {
                return zzbux.zzbuw();
            } catch (RemoteException unused) {
            }
        }
        return false;
    }

    private final void zzpk(String str) {
        if (str != null && !zzbuv()) {
            this.zzmun.submit(new zzecn(this.mContext, this.zzmuo, str));
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzect zzect) {
        if (zzect == null) {
            this.zzmun.shutdownNow();
        } else {
            this.zzmuq = zzecy.zzez(this.mContext);
            this.zzmuo.zzb(zzect);
            if (this.zzmuq != null && !zzbuv()) {
                this.zzmuq.zza(this.mContext, this.zzmun, this.zzmuo);
                Log.d("FirebaseCrash", "Firebase Analytics Listener for Firebase Crash is initialized");
            }
        }
        this.zzmup.countDown();
    }

    public final boolean zzbuv() {
        return this.zzmun.isShutdown();
    }

    /* access modifiers changed from: package-private */
    public final void zzcp(boolean z) {
        if (!zzbuv()) {
            this.zzmun.submit(new zzecp(this.mContext, this.zzmuo, z));
        }
    }

    /* access modifiers changed from: package-private */
    public final Future<?> zzi(Throwable th) {
        if (th == null || zzbuv()) {
            return null;
        }
        return this.zzmun.submit(new zzeco(this.mContext, this.zzmuo, th, this.zzmuq));
    }
}
