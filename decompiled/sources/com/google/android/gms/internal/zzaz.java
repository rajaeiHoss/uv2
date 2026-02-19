package com.google.android.gms.internal;

public final class zzaz {
    public static int zzd(int i) {
        if (i == 0 || i == 1 || i == 2 || i == 1000) {
            return i;
        }
        StringBuilder sb = new StringBuilder(43);
        sb.append(i);
        sb.append(" is not a valid enum EnumBoolean");
        throw new IllegalArgumentException(sb.toString());
    }
}
