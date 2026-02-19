package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class zzdxf implements zzdyi {
    private final SecretKeySpec zzmkf;
    private final int zzmkg;
    private final int zzmkh;

    public zzdxf(byte[] bArr, int i) throws GeneralSecurityException {
        this.zzmkf = new SecretKeySpec(bArr, "AES");
        int blockSize = zzdxx.zzmlk.zzoy("AES/CTR/NoPadding").getBlockSize();
        this.zzmkh = blockSize;
        if (i < 12 || i > blockSize) {
            throw new GeneralSecurityException("invalid IV size");
        }
        this.zzmkg = i;
    }

    public final byte[] zzaj(byte[] bArr) throws GeneralSecurityException {
        int length = bArr.length;
        int i = this.zzmkg;
        if (length <= Integer.MAX_VALUE - i) {
            byte[] bArr2 = new byte[(bArr.length + i)];
            byte[] zzgy = zzdyl.zzgy(i);
            System.arraycopy(zzgy, 0, bArr2, 0, this.zzmkg);
            int length2 = bArr.length;
            int i2 = this.zzmkg;
            Cipher zzoy = zzdxx.zzmlk.zzoy("AES/CTR/NoPadding");
            byte[] bArr3 = new byte[this.zzmkh];
            System.arraycopy(zzgy, 0, bArr3, 0, this.zzmkg);
            zzoy.init(1, this.zzmkf, new IvParameterSpec(bArr3));
            if (zzoy.doFinal(bArr, 0, length2, bArr2, i2) == length2) {
                return bArr2;
            }
            throw new GeneralSecurityException("stored output's length does not match input's length");
        }
        StringBuilder sb = new StringBuilder(43);
        sb.append("plaintext length can not exceed ");
        sb.append(Integer.MAX_VALUE - this.zzmkg);
        throw new GeneralSecurityException(sb.toString());
    }
}
