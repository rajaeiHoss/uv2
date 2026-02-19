package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzabh
public class zzamd<T> implements zzalt<T> {
    private final Object mLock = new Object();
    private T mValue;
    private boolean zzcjn;
    private Throwable zzdka;
    private boolean zzdkb;
    private final zzalu zzdkc = new zzalu();

    private final boolean zzsh() {
        return this.zzdka != null || this.zzdkb;
    }

    public boolean cancel(boolean z) {
        if (!z) {
            return false;
        }
        synchronized (this.mLock) {
            if (zzsh()) {
                return false;
            }
            this.zzcjn = true;
            this.zzdkb = true;
            this.mLock.notifyAll();
            this.zzdkc.zzsf();
            return true;
        }
    }

    public T get() throws CancellationException, ExecutionException, InterruptedException {
        T t;
        synchronized (this.mLock) {
            if (!zzsh()) {
                try {
                    this.mLock.wait();
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            if (this.zzdka != null) {
                throw new ExecutionException(this.zzdka);
            } else if (!this.zzcjn) {
                t = this.mValue;
            } else {
                throw new CancellationException("SettableFuture was cancelled.");
            }
        }
        return t;
    }

    public T get(long j, TimeUnit timeUnit) throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
        T t;
        synchronized (this.mLock) {
            if (!zzsh()) {
                try {
                    long millis = timeUnit.toMillis(j);
                    if (millis != 0) {
                        this.mLock.wait(millis);
                    }
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            if (this.zzdka != null) {
                throw new ExecutionException(this.zzdka);
            } else if (!this.zzdkb) {
                throw new TimeoutException("SettableFuture timed out.");
            } else if (!this.zzcjn) {
                t = this.mValue;
            } else {
                throw new CancellationException("SettableFuture was cancelled.");
            }
        }
        return t;
    }

    public boolean isCancelled() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcjn;
        }
        return z;
    }

    public boolean isDone() {
        boolean zzsh;
        synchronized (this.mLock) {
            zzsh = zzsh();
        }
        return zzsh;
    }

    public final void set(T t) {
        synchronized (this.mLock) {
            if (!this.zzcjn) {
                if (zzsh()) {
                    zzbt.zzep().zza(new IllegalStateException("Provided SettableFuture with multiple values."), "SettableFuture.set");
                    return;
                }
                this.zzdkb = true;
                this.mValue = t;
                this.mLock.notifyAll();
                this.zzdkc.zzsf();
            }
        }
    }

    public final void setException(Throwable th) {
        synchronized (this.mLock) {
            if (!this.zzcjn) {
                if (zzsh()) {
                    zzbt.zzep().zza(new IllegalStateException("Provided SettableFuture with multiple values."), "SettableFuture.setException");
                    return;
                }
                this.zzdka = th;
                this.mLock.notifyAll();
                this.zzdkc.zzsf();
            }
        }
    }

    public final void zza(Runnable runnable, Executor executor) {
        this.zzdkc.zza(runnable, executor);
    }
}
