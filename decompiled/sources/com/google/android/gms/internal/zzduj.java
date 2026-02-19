package com.google.android.gms.internal;

final /* synthetic */ class zzduj {
    static final /* synthetic */ int[] zzmfk;

    static {
        int[] iArr = new int[zzdvy.values().length];
        zzmfk = iArr;
        try {
            iArr[zzdvy.SHA1.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzmfk[zzdvy.SHA256.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zzmfk[zzdvy.SHA512.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}
