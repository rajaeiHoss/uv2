package com.google.android.gms.internal;

final /* synthetic */ class zzdxt {
    static final /* synthetic */ int[] zzmkx;
    static final /* synthetic */ int[] zzmky;

    static {
        int[] iArr = new int[zzdxu.values().length];
        zzmky = iArr;
        try {
            iArr[zzdxu.NIST_P256.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzmky[zzdxu.NIST_P384.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zzmky[zzdxu.NIST_P521.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        int[] iArr2 = new int[zzdxv.values().length];
        zzmkx = iArr2;
        try {
            iArr2[zzdxv.UNCOMPRESSED.ordinal()] = 1;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zzmkx[zzdxv.COMPRESSED.ordinal()] = 2;
        } catch (NoSuchFieldError unused5) {
        }
    }
}
