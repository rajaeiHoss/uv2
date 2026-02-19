package com.google.android.gms.internal;

final /* synthetic */ class zzdug {
    static final /* synthetic */ int[] zzmfk;
    static final /* synthetic */ int[] zzmfl;
    static final /* synthetic */ int[] zzmfm;

    static {
        int[] iArr = new int[zzdvi.values().length];
        zzmfm = iArr;
        try {
            iArr[zzdvi.UNCOMPRESSED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzmfm[zzdvi.COMPRESSED.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        int[] iArr2 = new int[zzdvw.values().length];
        zzmfl = iArr2;
        try {
            iArr2[zzdvw.NIST_P256.ordinal()] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            zzmfl[zzdvw.NIST_P384.ordinal()] = 2;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zzmfl[zzdvw.NIST_P521.ordinal()] = 3;
        } catch (NoSuchFieldError unused5) {
        }
        int[] iArr3 = new int[zzdvy.values().length];
        zzmfk = iArr3;
        try {
            iArr3[zzdvy.SHA1.ordinal()] = 1;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            zzmfk[zzdvy.SHA256.ordinal()] = 2;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            zzmfk[zzdvy.SHA512.ordinal()] = 3;
        } catch (NoSuchFieldError unused8) {
        }
    }
}
