package com.google.android.gms.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.internal.zzbq;
import java.util.concurrent.TimeUnit;

public abstract class zzff<T1 extends Result, T2 extends Result> extends PendingResult<T1> {
    private final PendingResult<T2> zzalg;

    protected zzff(PendingResult<T2> pendingResult) {
        this.zzalg = (PendingResult) zzbq.checkNotNull(pendingResult);
    }

    public T1 await() {
        return zza(this.zzalg.await());
    }

    public T1 await(long j, TimeUnit timeUnit) {
        return zza(this.zzalg.await(j, timeUnit));
    }

    public void cancel() {
        this.zzalg.cancel();
    }

    public boolean isCanceled() {
        return this.zzalg.isCanceled();
    }

    public void setResultCallback(ResultCallback<? super T1> resultCallback) {
        this.zzalg.setResultCallback(new zzfg(this, resultCallback));
    }

    public void setResultCallback(ResultCallback<? super T1> resultCallback, long j, TimeUnit timeUnit) {
        this.zzalg.setResultCallback(new zzfh(this, resultCallback), j, timeUnit);
    }

    /* access modifiers changed from: protected */
    public abstract T1 zza(T2 t2);

    public final void zza(PendingResult.zza zza) {
        this.zzalg.zza(zza);
    }
}
