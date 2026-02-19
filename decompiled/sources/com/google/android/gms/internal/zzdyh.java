package com.google.android.gms.internal;

public final class zzdyh {
    private final byte[] data;

    private zzdyh(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        this.data = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, i2);
    }

    public static zzdyh zzal(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return new zzdyh(bArr, 0, bArr.length);
    }

    public final byte[] getBytes() {
        byte[] bArr = this.data;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }
}
