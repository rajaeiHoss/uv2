package com.google.android.gms.internal;

import kotlin.UByte;

public final class zzcbx {
    private static final ThreadLocal<String> zzhno = new ThreadLocal<>();

    public static String zzhw(String str) {
        ThreadLocal<String> threadLocal = zzhno;
        String str2 = threadLocal.get();
        int i = 0;
        if (str2 == null || str2.startsWith("com.google")) {
            return str;
        }
        String str3 = threadLocal.get();
        if (str == null || str3 == null) {
            return str;
        }
        int length = str.length() + str3.length();
        byte[] bArr = new byte[length];
        System.arraycopy(str.getBytes(), 0, bArr, 0, str.length());
        System.arraycopy(str3.getBytes(), 0, bArr, str.length(), str3.length());
        int i2 = (length & -4) + 0;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4 += 4) {
            int i5 = ((bArr[i4] & UByte.MAX_VALUE) | ((bArr[i4 + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i4 + 2] & UByte.MAX_VALUE) << 16) | (bArr[i4 + 3] << 24)) * -862048943;
            int i6 = i3 ^ (((i5 << 15) | (i5 >>> 17)) * 461845907);
            i3 = (((i6 >>> 19) | (i6 << 13)) * 5) - 430675100;
        }
        int i7 = length & 3;
        if (i7 != 1) {
            if (i7 != 2) {
                if (i7 == 3) {
                    i = (bArr[i2 + 2] & UByte.MAX_VALUE) << 16;
                }
                int i8 = i3 ^ length;
                int i9 = (i8 ^ (i8 >>> 16)) * -2048144789;
                int i10 = (i9 ^ (i9 >>> 13)) * -1028477387;
                return Integer.toHexString(i10 ^ (i10 >>> 16));
            }
            i |= (bArr[i2 + 1] & UByte.MAX_VALUE) << 8;
        }
        int i11 = ((bArr[i2] & UByte.MAX_VALUE) | i) * -862048943;
        i3 ^= ((i11 >>> 17) | (i11 << 15)) * 461845907;
        int i82 = i3 ^ length;
        int i92 = (i82 ^ (i82 >>> 16)) * -2048144789;
        int i102 = (i92 ^ (i92 >>> 13)) * -1028477387;
        return Integer.toHexString(i102 ^ (i102 >>> 16));
    }
}
