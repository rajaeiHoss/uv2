package com.google.android.gms.internal;

import java.util.Random;

@zzabh
public final class zzld extends zzmf {
    private Object mLock = new Object();
    private final Random zzbje = new Random();
    private long zzbjf;

    public zzld() {
        zzip();
    }

    public final long getValue() {
        return this.zzbjf;
    }

    public final void zzip() {
        synchronized (this.mLock) {
            int i = 3;
            long j = 0;
            while (true) {
                i--;
                if (i <= 0) {
                    break;
                }
                j = ((long) this.zzbje.nextInt()) + 2147483648L;
                if (j != this.zzbjf && j != 0) {
                    break;
                }
            }
            this.zzbjf = j;
        }
    }
}
