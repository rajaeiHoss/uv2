package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@zzabh
public final class zzali {
    public static <V> zzalt<V> zza(zzalt<V> zzalt, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        zzamd zzamd = new zzamd();
        zzb(zzamd, zzalt);
        ScheduledFuture<?> schedule = scheduledExecutorService.schedule(new zzalm(zzamd), j, timeUnit);
        zzc(zzalt, zzamd);
        zzamd.zza(new zzaln(schedule), zzaly.zzdju);
        return zzamd;
    }

    public static <A, B> zzalt<B> zza(zzalt<A> zzalt, zzald<? super A, ? extends B> zzald, Executor executor) {
        zzamd zzamd = new zzamd();
        zzalt.zza(new zzall(zzamd, zzald, zzalt), executor);
        zzb(zzamd, zzalt);
        return zzamd;
    }

    public static <A, B> zzalt<B> zza(zzalt<A> zzalt, zzale<A, B> zzale, Executor executor) {
        zzamd zzamd = new zzamd();
        zzalt.zza(new zzalk(zzamd, zzale, zzalt), executor);
        zzb(zzamd, zzalt);
        return zzamd;
    }

    public static <V, X extends Throwable> zzalt<V> zza(zzalt<? extends V> zzalt, Class<X> cls, zzald<? super X, ? extends V> zzald, Executor executor) {
        zzamd zzamd = new zzamd();
        zzb(zzamd, zzalt);
        zzalt.zza(new zzalo(zzamd, zzalt, cls, zzald, executor), zzaly.zzdju);
        return zzamd;
    }

    public static <T> T zza(Future<T> future, T t) {
        try {
            return future.get(((Long) zzlc.zzio().zzd(zzoi.zzbre)).longValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            future.cancel(true);
            zzahw.zzc("InterruptedException caught while resolving future.", e);
            Thread.currentThread().interrupt();
            zzbt.zzep().zza(e, "Futures.resolveFuture");
            return t;
        } catch (Exception e) {
            future.cancel(true);
            zzahw.zzb("Error waiting for future.", e);
            zzbt.zzep().zza(e, "Futures.resolveFuture");
            return t;
        }
    }

    public static <T> T zza(Future<T> future, T t, long j, TimeUnit timeUnit) {
        try {
            return future.get(j, timeUnit);
        } catch (InterruptedException e) {
            future.cancel(true);
            zzahw.zzc("InterruptedException caught while resolving future.", e);
            Thread.currentThread().interrupt();
            zzbt.zzep().zza(e, "Futures.resolveFuture");
            return t;
        } catch (Exception e) {
            future.cancel(true);
            zzahw.zzb("Error waiting for future.", e);
            zzbt.zzep().zza(e, "Futures.resolveFuture");
            return t;
        }
    }

    public static <V> void zza(zzalt<V> zzalt, zzalf<V> zzalf, Executor executor) {
        zzalt.zza(new zzalj(zzalf, zzalt), executor);
    }

    private static <V> void zzc(zzalt<? extends V> zzalt, zzamd<V> zzamd) {
        zzb(zzamd, zzalt);
        zzalt.zza(new zzalp(zzamd, zzalt), zzaly.zzdju);
    }

    private static <A, B> void zzb(zzalt<A> zzalt, Future<B> future) {
        zzalt.zza(new zzalq(zzalt, future), zzaly.zzdju);
    }

    static final /* synthetic */ void zza(zzamd zzamd, zzald zzald, zzalt zzalt) {
        if (!zzamd.isCancelled()) {
            try {
                zzc(zzald.zzc(zzalt.get()), zzamd);
            } catch (CancellationException unused) {
                zzamd.cancel(true);
            } catch (ExecutionException e) {
                zzamd.setException(e.getCause());
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
                zzamd.setException(e2);
            } catch (Exception e3) {
                zzamd.setException(e3);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001e  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static final /* synthetic */ void zza(com.google.android.gms.internal.zzamd r1, com.google.android.gms.internal.zzalt r2, java.lang.Class r3, com.google.android.gms.internal.zzald r4, java.util.concurrent.Executor r5) {
        /*
            java.lang.Object r2 = r2.get()     // Catch:{ ExecutionException -> 0x0013, InterruptedException -> 0x000a, Exception -> 0x0008 }
            r1.set(r2)     // Catch:{ ExecutionException -> 0x0013, InterruptedException -> 0x000a, Exception -> 0x0008 }
            return
        L_0x0008:
            r2 = move-exception
            goto L_0x0018
        L_0x000a:
            r2 = move-exception
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r0.interrupt()
            goto L_0x0018
        L_0x0013:
            r2 = move-exception
            java.lang.Throwable r2 = r2.getCause()
        L_0x0018:
            boolean r3 = r3.isInstance(r2)
            if (r3 == 0) goto L_0x002a
            com.google.android.gms.internal.zzals r2 = zzh(r2)
            com.google.android.gms.internal.zzalt r2 = zza(r2, r4, (java.util.concurrent.Executor) r5)
            zza(r2, r1)
            return
        L_0x002a:
            r1.setException(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzali.zza(com.google.android.gms.internal.zzamd, com.google.android.gms.internal.zzalt, java.lang.Class, com.google.android.gms.internal.zzald, java.util.concurrent.Executor):void");
    }

    public static <T> zzalr<T> zzd(Throwable th) {
        return new zzalr<>(th);
    }

    public static <T> zzals<T> zzh(T t) {
        return new zzals<>(t);
    }
}
