package com.google.android.gms.internal;

import kotlin.UByte;

public final class zzffa {
    private final byte[] zzpjv = new byte[256];
    private int zzpjw;
    private int zzpjx;

    public zzffa(byte[] bArr) {
        for (int i = 0; i < 256; i++) {
            this.zzpjv[i] = (byte) i;
        }
        byte b = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            byte[] bArr2 = this.zzpjv;
            b = (byte) ((b + bArr2[i2] + bArr[i2 % bArr.length]) & UByte.MAX_VALUE);
            byte b2 = bArr2[i2];
            bArr2[i2] = bArr2[b];
            bArr2[b] = b2;
        }
        this.zzpjw = 0;
        this.zzpjx = 0;
    }

    public final void zzay(byte[] bArr) {
        int i = this.zzpjw;
        int i2 = this.zzpjx;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & 255;
            byte[] bArr2 = this.zzpjv;
            i2 = (i2 + bArr2[i]) & 255;
            byte b = bArr2[i];
            bArr2[i] = bArr2[i2];
            bArr2[i2] = b;
            bArr[i3] = (byte) (bArr2[(bArr2[i] + bArr2[i2]) & 255] ^ bArr[i3]);
        }
        this.zzpjw = i;
        this.zzpjx = i2;
    }
}
