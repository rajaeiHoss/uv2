package com.google.firebase.database.connection.idl;

import com.google.android.gms.internal.zzemo;

final /* synthetic */ class zzd {
    static final /* synthetic */ int[] zznct;

    static {
        int[] iArr = new int[zzemo.values().length];
        zznct = iArr;
        try {
            iArr[zzemo.DEBUG.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zznct[zzemo.INFO.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zznct[zzemo.WARN.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            zznct[zzemo.ERROR.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zznct[zzemo.NONE.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
    }
}
