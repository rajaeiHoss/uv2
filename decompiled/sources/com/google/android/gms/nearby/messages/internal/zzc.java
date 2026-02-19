package com.google.android.gms.nearby.messages.internal;

import java.util.Arrays;

public class zzc {
    private static final char[] zzkcs = "0123456789abcdef".toCharArray();
    private final byte[] zzkci;

    protected zzc(byte[] bArr) {
        this.zzkci = bArr;
    }

    public static byte[] zzky(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) ((Character.digit(str.charAt(i2), 16) << 4) + Character.digit(str.charAt(i2 + 1), 16));
        }
        return bArr;
    }

    public static String zzw(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            char[] cArr = zzkcs;
            sb.append(cArr[(b >> 4) & 15]);
            sb.append(cArr[b & 15]);
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!obj.getClass().isAssignableFrom(getClass())) {
            return false;
        }
        return Arrays.equals(this.zzkci, ((zzc) obj).zzkci);
    }

    public final byte[] getBytes() {
        return this.zzkci;
    }

    public final String getHex() {
        return zzw(this.zzkci);
    }

    public int hashCode() {
        return Arrays.hashCode(this.zzkci);
    }

    public String toString() {
        return zzw(this.zzkci);
    }
}
