package com.google.android.gms.internal;

final /* synthetic */ class zzxv {
    private static /* synthetic */ int[] zzckm;
    static final /* synthetic */ int[] zzckn;

    static {
        int[] iArr = new int[com.google.ads.AdRequest.ErrorCode.values().length];
        zzckn = iArr;
        try {
            iArr[com.google.ads.AdRequest.ErrorCode.INTERNAL_ERROR.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzckn[com.google.ads.AdRequest.ErrorCode.INVALID_REQUEST.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zzckn[com.google.ads.AdRequest.ErrorCode.NETWORK_ERROR.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            zzckn[com.google.ads.AdRequest.ErrorCode.NO_FILL.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        int[] iArr2 = new int[com.google.ads.AdRequest.Gender.values().length];
        zzckm = iArr2;
        try {
            iArr2[com.google.ads.AdRequest.Gender.FEMALE.ordinal()] = 1;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            zzckm[com.google.ads.AdRequest.Gender.MALE.ordinal()] = 2;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            zzckm[com.google.ads.AdRequest.Gender.UNKNOWN.ordinal()] = 3;
        } catch (NoSuchFieldError unused7) {
        }
    }
}
