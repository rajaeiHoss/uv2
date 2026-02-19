package com.google.android.gms.internal;

public final class zzdxr {
    private final zzdyh zzmkv;
    private final zzdyh zzmkw;

    public zzdxr(byte[] bArr, byte[] bArr2) {
        this.zzmkv = zzdyh.zzal(bArr);
        this.zzmkw = zzdyh.zzal(bArr2);
    }

    public final byte[] zzbsq() {
        zzdyh zzdyh = this.zzmkv;
        if (zzdyh == null) {
            return null;
        }
        return zzdyh.getBytes();
    }

    public final byte[] zzbsr() {
        zzdyh zzdyh = this.zzmkw;
        if (zzdyh == null) {
            return null;
        }
        return zzdyh.getBytes();
    }
}
