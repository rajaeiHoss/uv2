package com.google.android.gms.internal;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@zzabh
public class zzamj<T> implements zzamf<T> {
    private final Object mLock = new Object();
    private int zzcfl = 0;
    private BlockingQueue<zzamk> zzdkd = new LinkedBlockingQueue();
    private T zzdke;

    public final int getStatus() {
        return this.zzcfl;
    }

    public final void reject() {
        synchronized (this.mLock) {
            if (this.zzcfl == 0) {
                this.zzcfl = -1;
                for (zzamk zzamk : this.zzdkd) {
                    zzamk.zzdkg.run();
                }
                this.zzdkd.clear();
            } else {
                throw new UnsupportedOperationException();
            }
        }
    }

    public final void zza(zzami<T> zzami, zzamg zzamg) {
        synchronized (this.mLock) {
            int i = this.zzcfl;
            if (i == 1) {
                zzami.zze(this.zzdke);
            } else if (i == -1) {
                zzamg.run();
            } else if (i == 0) {
                this.zzdkd.add(new zzamk(this, zzami, zzamg));
            }
        }
    }

    public final void zzj(T t) {
        synchronized (this.mLock) {
            if (this.zzcfl == 0) {
                this.zzdke = t;
                this.zzcfl = 1;
                for (zzamk zzamk : this.zzdkd) {
                    zzamk.zzdkf.zze(t);
                }
                this.zzdkd.clear();
            } else {
                throw new UnsupportedOperationException();
            }
        }
    }
}
