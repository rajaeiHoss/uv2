package com.google.android.gms.vision;

import android.util.SparseArray;

public final class zzc {
    private static final Object sLock = new Object();
    private static int zzlgg;
    private SparseArray<Integer> zzlgh = new SparseArray<>();
    private SparseArray<Integer> zzlgi = new SparseArray<>();

    public final int zzfm(int i) {
        synchronized (sLock) {
            Integer num = this.zzlgh.get(i);
            if (num != null) {
                int intValue = num.intValue();
                return intValue;
            }
            int i2 = zzlgg;
            zzlgg = i2 + 1;
            this.zzlgh.append(i, Integer.valueOf(i2));
            this.zzlgi.append(i2, Integer.valueOf(i));
            return i2;
        }
    }

    public final int zzfn(int i) {
        int intValue;
        synchronized (sLock) {
            intValue = this.zzlgi.get(i).intValue();
        }
        return intValue;
    }
}
