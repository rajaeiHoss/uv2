package com.google.android.gms.internal;

final /* synthetic */ class zzegq {
    static final /* synthetic */ int[] zzneq;

    static {
        int[] iArr = new int[com.google.firebase.database.Logger.Level.values().length];
        zzneq = iArr;
        try {
            iArr[com.google.firebase.database.Logger.Level.DEBUG.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzneq[com.google.firebase.database.Logger.Level.INFO.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zzneq[com.google.firebase.database.Logger.Level.WARN.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            zzneq[com.google.firebase.database.Logger.Level.ERROR.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zzneq[com.google.firebase.database.Logger.Level.NONE.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
    }
}
