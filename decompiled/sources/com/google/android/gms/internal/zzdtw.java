package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

public final class zzdtw implements zzdsy {
    private static final byte[] zzmfg = new byte[0];
    private final zzdwl zzmfh;
    private final zzdsy zzmfi;

    public zzdtw(zzdwl zzdwl, zzdsy zzdsy) {
        this.zzmfh = zzdwl;
        this.zzmfi = zzdsy;
    }

    public final byte[] zzd(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] byteArray = zzdtn.zzb(this.zzmfh).toByteArray();
        byte[] zzd = this.zzmfi.zzd(byteArray, zzmfg);
        byte[] zzd2 = ((zzdsy) zzdtn.zzf(this.zzmfh.zzbqu(), byteArray)).zzd(bArr, bArr2);
        return ByteBuffer.allocate(zzd.length + 4 + zzd2.length).putInt(zzd.length).put(zzd).put(zzd2).array();
    }
}
