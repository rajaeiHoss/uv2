package com.google.android.gms.internal;

import java.util.Comparator;

final class zzal implements Comparator<byte[]> {
    zzal() {
    }

    public final int compare(byte[] bArr, byte[] bArr2) {
        return bArr.length - bArr2.length;
    }
}
