package com.google.android.gms.internal;

import java.util.Random;

public final class zzepc {
    private static final Random zznrh = new Random();
    private static long zznri = 0;
    private static final int[] zznrj = new int[12];

    public static synchronized String zzca(long j) {
        String sb;
        synchronized (zzepc.class) {
            boolean z = j == zznri;
            zznri = j;
            char[] cArr = new char[8];
            StringBuilder sb2 = new StringBuilder(20);
            for (int i = 7; i >= 0; i--) {
                cArr[i] = "-0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz".charAt((int) (j % 64));
                j /= 64;
            }
            sb2.append(cArr);
            if (!z) {
                for (int i2 = 0; i2 < 12; i2++) {
                    zznrj[i2] = zznrh.nextInt(64);
                }
            } else {
                zzcdq();
            }
            for (int i3 = 0; i3 < 12; i3++) {
                sb2.append("-0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz".charAt(zznrj[i3]));
            }
            sb = sb2.toString();
        }
        return sb;
    }

    private static void zzcdq() {
        int i = 11;
        while (i >= 0) {
            int[] iArr = zznrj;
            if (iArr[i] != 63) {
                iArr[i] = iArr[i] + 1;
                return;
            } else {
                iArr[i] = 0;
                i--;
            }
        }
    }
}
