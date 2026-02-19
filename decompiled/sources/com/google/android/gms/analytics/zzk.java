package com.google.android.gms.analytics;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzaql;
import com.google.android.gms.internal.zzaqq;
import com.google.android.gms.internal.zzatt;
import java.lang.Thread;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzk {
    private static volatile zzk zzdvk;
    private final Context mContext;
    /* access modifiers changed from: private */
    public final List<zzn> zzdvl = new CopyOnWriteArrayList();
    private final zze zzdvm = new zze();
    private final zza zzdvn = new zza();
    private volatile zzaql zzdvo;
    /* access modifiers changed from: private */
    public Thread.UncaughtExceptionHandler zzdvp;

    class zza extends ThreadPoolExecutor {
        public zza() {
            super(1, 1, 1, TimeUnit.MINUTES, new LinkedBlockingQueue());
            setThreadFactory(new zzb((zzl) null));
            allowCoreThreadTimeOut(true);
        }

        /* access modifiers changed from: protected */
        public final <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
            return new zzm(this, runnable, t);
        }

        Thread.UncaughtExceptionHandler zzwk() {
            return zzk.this.zzdvp;
        }
    }

    static class zzb implements ThreadFactory {
        private static final AtomicInteger zzdvt = new AtomicInteger();

        private zzb() {
        }

        /* synthetic */ zzb(zzl zzl) {
            this();
        }

        public final Thread newThread(Runnable runnable) {
            int incrementAndGet = zzdvt.incrementAndGet();
            StringBuilder sb = new StringBuilder(23);
            sb.append("measurement-");
            sb.append(incrementAndGet);
            return new zzc(runnable, sb.toString());
        }
    }

    static class zzc extends Thread {
        zzc(Runnable runnable, String str) {
            super(runnable, str);
        }

        public final void run() {
            Process.setThreadPriority(10);
            super.run();
        }
    }

    private zzk(Context context) {
        Context applicationContext = context.getApplicationContext();
        zzbq.checkNotNull(applicationContext);
        this.mContext = applicationContext;
    }

    /* access modifiers changed from: private */
    public static void zzb(zzg zzg) {
        zzbq.zzgw("deliver should be called from worker thread");
        zzbq.checkArgument(zzg.zzwb(), "Measurement must be submitted");
        List<zzo> transports = zzg.getTransports();
        if (!transports.isEmpty()) {
            HashSet hashSet = new HashSet();
            for (zzo next : transports) {
                Uri zzvu = next.zzvu();
                if (!hashSet.contains(zzvu)) {
                    hashSet.add(zzvu);
                    next.zzb(zzg);
                }
            }
        }
    }

    public static zzk zzbk(Context context) {
        zzbq.checkNotNull(context);
        if (zzdvk == null) {
            synchronized (zzk.class) {
                if (zzdvk == null) {
                    zzdvk = new zzk(context);
                }
            }
        }
        return zzdvk;
    }

    public static void zzwj() {
        if (!(Thread.currentThread() instanceof zzc)) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final <V> Future<V> zza(Callable<V> callable) {
        zzbq.checkNotNull(callable);
        if (!(Thread.currentThread() instanceof zzc)) {
            return this.zzdvn.submit(callable);
        }
        FutureTask futureTask = new FutureTask(callable);
        futureTask.run();
        return futureTask;
    }

    public final void zza(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzdvp = uncaughtExceptionHandler;
    }

    public final void zzd(Runnable runnable) {
        zzbq.checkNotNull(runnable);
        this.zzdvn.submit(runnable);
    }

    /* access modifiers changed from: package-private */
    public final void zze(zzg zzg) {
        if (zzg.zzwe()) {
            throw new IllegalStateException("Measurement prototype can't be submitted");
        } else if (!zzg.zzwb()) {
            zzg zzvx = zzg.zzvx();
            zzvx.zzwc();
            this.zzdvn.execute(new zzl(this, zzvx));
        } else {
            throw new IllegalStateException("Measurement can only be submitted once");
        }
    }

    public final zzaql zzwh() {
        if (this.zzdvo == null) {
            synchronized (this) {
                if (this.zzdvo == null) {
                    zzaql zzaql = new zzaql();
                    PackageManager packageManager = this.mContext.getPackageManager();
                    String packageName = this.mContext.getPackageName();
                    zzaql.setAppId(packageName);
                    zzaql.setAppInstallerId(packageManager.getInstallerPackageName(packageName));
                    String str = null;
                    try {
                        PackageInfo packageInfo = packageManager.getPackageInfo(this.mContext.getPackageName(), 0);
                        if (packageInfo != null) {
                            CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                            if (!TextUtils.isEmpty(applicationLabel)) {
                                packageName = applicationLabel.toString();
                            }
                            str = packageInfo.versionName;
                        }
                    } catch (PackageManager.NameNotFoundException unused) {
                        String valueOf = String.valueOf(packageName);
                        Log.e("GAv4", valueOf.length() != 0 ? "Error retrieving package info: appName set to ".concat(valueOf) : new String("Error retrieving package info: appName set to "));
                    }
                    zzaql.setAppName(packageName);
                    zzaql.setAppVersion(str);
                    this.zzdvo = zzaql;
                }
            }
        }
        return this.zzdvo;
    }

    public final zzaqq zzwi() {
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        zzaqq zzaqq = new zzaqq();
        zzaqq.setLanguage(zzatt.zza(Locale.getDefault()));
        zzaqq.zzcly = displayMetrics.widthPixels;
        zzaqq.zzclz = displayMetrics.heightPixels;
        return zzaqq;
    }
}
