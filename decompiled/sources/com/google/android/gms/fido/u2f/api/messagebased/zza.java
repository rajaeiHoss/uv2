package com.google.android.gms.fido.u2f.api.messagebased;

final /* synthetic */ class zza {
    static final /* synthetic */ int[] zzhha;

    static {
        int[] iArr = new int[RequestType.values().length];
        zzhha = iArr;
        try {
            iArr[RequestType.REGISTER.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzhha[RequestType.SIGN.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
    }
}
