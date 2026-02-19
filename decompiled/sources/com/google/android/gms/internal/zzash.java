package com.google.android.gms.internal;

public enum zzash {
    NONE,
    GZIP;

    public static zzash zzek(String str) {
        return "GZIP".equalsIgnoreCase(str) ? GZIP : NONE;
    }
}
