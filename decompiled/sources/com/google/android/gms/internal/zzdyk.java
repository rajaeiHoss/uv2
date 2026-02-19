package com.google.android.gms.internal;

import java.util.Arrays;
import kotlin.UByte;

final class zzdyk {
    private static void zza(byte[] bArr, long j, int i) {
        int i2 = 0;
        while (i2 < 4) {
            bArr[i + i2] = (byte) ((int) (255 & j));
            i2++;
            j >>= 8;
        }
    }

    private static long zzd(byte[] bArr, int i, int i2) {
        return (zzg(bArr, i) >> i2) & 67108863;
    }

    static byte[] zzf(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        if (bArr3.length == 32) {
            long zzd = zzd(bArr3, 0, 0) & 67108863;
            int i = 3;
            long zzd2 = zzd(bArr3, 3, 2) & 67108611;
            long zzd3 = zzd(bArr3, 6, 4) & 67092735;
            long zzd4 = zzd(bArr3, 9, 6) & 66076671;
            long zzd5 = zzd(bArr3, 12, 8) & 1048575;
            long j = zzd2 * 5;
            long j2 = zzd3 * 5;
            long j3 = zzd4 * 5;
            long j4 = zzd5 * 5;
            int i2 = 17;
            byte[] bArr5 = new byte[17];
            long j5 = 0;
            long j6 = 0;
            long j7 = 0;
            long j8 = 0;
            long j9 = 0;
            int i3 = 0;
            while (i3 < bArr4.length) {
                int min = Math.min(16, bArr4.length - i3);
                System.arraycopy(bArr4, i3, bArr5, 0, min);
                bArr5[min] = 1;
                if (min != 16) {
                    Arrays.fill(bArr5, min + 1, i2, (byte) 0);
                }
                long zzd6 = j9 + zzd(bArr5, 0, 0);
                long zzd7 = j5 + zzd(bArr5, i, 2);
                long zzd8 = j6 + zzd(bArr5, 6, 4);
                long zzd9 = j7 + zzd(bArr5, 9, 6);
                long zzd10 = j8 + (zzd(bArr5, 12, 8) | ((long) (bArr5[16] << 24)));
                long j10 = (zzd6 * zzd) + (zzd7 * j4) + (zzd8 * j3) + (zzd9 * j2) + (zzd10 * j);
                long j11 = (zzd6 * zzd2) + (zzd7 * zzd) + (zzd8 * j4) + (zzd9 * j3) + (zzd10 * j2) + (j10 >> 26);
                long j12 = (zzd6 * zzd3) + (zzd7 * zzd2) + (zzd8 * zzd) + (zzd9 * j4) + (zzd10 * j3) + (j11 >> 26);
                long j13 = (zzd6 * zzd4) + (zzd7 * zzd3) + (zzd8 * zzd2) + (zzd9 * zzd) + (zzd10 * j4) + (j12 >> 26);
                long j14 = (zzd6 * zzd5) + (zzd7 * zzd4) + (zzd8 * zzd3) + (zzd9 * zzd2) + (zzd10 * zzd) + (j13 >> 26);
                long j15 = (j10 & 67108863) + ((j14 >> 26) * 5);
                j5 = (j11 & 67108863) + (j15 >> 26);
                i3 += 16;
                j6 = j12 & 67108863;
                j7 = j13 & 67108863;
                j8 = j14 & 67108863;
                i2 = 17;
                i = 3;
                j9 = j15 & 67108863;
            }
            long j16 = j6 + (j5 >> 26);
            long j17 = j16 & 67108863;
            long j18 = j7 + (j16 >> 26);
            long j19 = j18 & 67108863;
            long j20 = j8 + (j18 >> 26);
            long j21 = j20 & 67108863;
            long j22 = j9 + ((j20 >> 26) * 5);
            long j23 = j22 & 67108863;
            long j24 = (j5 & 67108863) + (j22 >> 26);
            long j25 = j23 + 5;
            long j26 = j25 & 67108863;
            long j27 = (j25 >> 26) + j24;
            long j28 = j17 + (j27 >> 26);
            long j29 = j19 + (j28 >> 26);
            long j30 = (j21 + (j29 >> 26)) - 67108864;
            long j31 = j30 >> 63;
            long j32 = ~j31;
            long j33 = (j23 & j31) | (j26 & j32);
            long j34 = (j24 & j31) | (j27 & 67108863 & j32);
            long j35 = (j17 & j31) | (j28 & 67108863 & j32);
            long j36 = (j19 & j31) | (j29 & 67108863 & j32);
            long j37 = ((j30 & j32) | (j21 & j31)) << 8;
            long zzg = (((j34 << 26) | j33) & 4294967295L) + zzg(bArr3, 16);
            long j38 = zzg & 4294967295L;
            long zzg2 = (((j34 >> 6) | (j35 << 20)) & 4294967295L) + zzg(bArr3, 20) + (zzg >> 32);
            long zzg3 = (((j35 >> 12) | (j36 << 14)) & 4294967295L) + zzg(bArr3, 24) + (zzg2 >> 32);
            byte[] bArr6 = new byte[16];
            zza(bArr6, j38, 0);
            zza(bArr6, zzg2 & 4294967295L, 4);
            zza(bArr6, zzg3 & 4294967295L, 8);
            zza(bArr6, (((j37 | (j36 >> 18)) & 4294967295L) + zzg(bArr3, 28) + (zzg3 >> 32)) & 4294967295L, 12);
            return bArr6;
        }
        throw new IllegalArgumentException("The key length in bytes must be 32.");
    }

    private static long zzg(byte[] bArr, int i) {
        return ((long) (((bArr[i + 3] & UByte.MAX_VALUE) << 24) | (bArr[i] & UByte.MAX_VALUE) | ((bArr[i + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i + 2] & UByte.MAX_VALUE) << 16))) & 4294967295L;
    }
}
