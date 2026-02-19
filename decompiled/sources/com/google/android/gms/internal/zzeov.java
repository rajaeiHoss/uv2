package com.google.android.gms.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

final class zzeov extends ScheduledThreadPoolExecutor {
    private /* synthetic */ zzeou zznrd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzeov(zzeou zzeou, int i, ThreadFactory threadFactory) {
        super(1, threadFactory);
        this.zznrd = zzeou;
    }

    /* access modifiers changed from: protected */
    public final void afterExecute(Runnable runnable, Throwable th) {
        super.afterExecute(runnable, th);
        if (th == null && (runnable instanceof Future)) {
            Future future = (Future) runnable;
            try {
                if (future.isDone()) {
                    future.get();
                }
            } catch (CancellationException unused) {
            } catch (ExecutionException e) {
                th = e.getCause();
            } catch (InterruptedException unused2) {
                Thread.currentThread().interrupt();
            }
        }
        if (th != null) {
            this.zznrd.zzj(th);
        }
    }
}
