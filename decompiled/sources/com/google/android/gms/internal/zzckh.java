package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import kotlin.jvm.internal.LongCompanionObject;

final class zzckh<V> extends FutureTask<V> implements Comparable<zzckh> {
    private final String zzjnl;
    private /* synthetic */ zzcke zzjnm;
    private final long zzjnn;
    final boolean zzjno;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzckh(zzcke zzcke, Runnable runnable, boolean z, String str) {
        super(runnable, null);
        this.zzjnm = zzcke;
        zzbq.checkNotNull(str);
        long andIncrement = zzcke.zzjnk.getAndIncrement();
        this.zzjnn = andIncrement;
        this.zzjnl = str;
        this.zzjno = false;
        if (andIncrement == LongCompanionObject.MAX_VALUE) {
            zzcke.zzayp().zzbau().log("Tasks index overflow");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzckh(zzcke zzcke, Callable<V> callable, boolean z, String str) {
        super(callable);
        this.zzjnm = zzcke;
        zzbq.checkNotNull(str);
        long andIncrement = zzcke.zzjnk.getAndIncrement();
        this.zzjnn = andIncrement;
        this.zzjnl = str;
        this.zzjno = z;
        if (andIncrement == LongCompanionObject.MAX_VALUE) {
            zzcke.zzayp().zzbau().log("Tasks index overflow");
        }
    }

    public final int compareTo(zzckh zzckh) {
        boolean z = this.zzjno;
        if (z != zzckh.zzjno) {
            return z ? -1 : 1;
        }
        long j = this.zzjnn;
        long j2 = zzckh.zzjnn;
        if (j < j2) {
            return -1;
        }
        if (j > j2) {
            return 1;
        }
        this.zzjnm.zzayp().zzbav().zzj("Two tasks share the same index. index", Long.valueOf(this.zzjnn));
        return 0;
    }

    /* access modifiers changed from: protected */
    public final void setException(Throwable th) {
        this.zzjnm.zzayp().zzbau().zzj(this.zzjnl, th);
        if (th instanceof zzckf) {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }
}
