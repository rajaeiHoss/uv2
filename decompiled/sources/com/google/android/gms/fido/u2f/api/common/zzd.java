package com.google.android.gms.fido.u2f.api.common;

final /* synthetic */ class zzd {
    static final /* synthetic */ int[] zzhgc;

    static {
        int[] iArr = new int[ChannelIdValue.ChannelIdValueType.values().length];
        zzhgc = iArr;
        try {
            iArr[ChannelIdValue.ChannelIdValueType.ABSENT.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzhgc[ChannelIdValue.ChannelIdValueType.STRING.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zzhgc[ChannelIdValue.ChannelIdValueType.OBJECT.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}
