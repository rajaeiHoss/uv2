package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class zzdyg {
    public static byte[] zza(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, int i) throws GeneralSecurityException {
        Mac zzoy = zzdxx.zzmll.zzoy(str);
        if (i <= zzoy.getMacLength() * 255) {
            if (bArr2 == null || bArr2.length == 0) {
                zzoy.init(new SecretKeySpec(new byte[zzoy.getMacLength()], str));
            } else {
                zzoy.init(new SecretKeySpec(bArr2, str));
            }
            byte[] bArr4 = new byte[i];
            zzoy.init(new SecretKeySpec(zzoy.doFinal(bArr), str));
            byte[] bArr5 = new byte[0];
            int i2 = 1;
            int i3 = 0;
            while (true) {
                zzoy.update(bArr5);
                zzoy.update(bArr3);
                zzoy.update((byte) i2);
                bArr5 = zzoy.doFinal();
                if (bArr5.length + i3 < i) {
                    System.arraycopy(bArr5, 0, bArr4, i3, bArr5.length);
                    i3 += bArr5.length;
                    i2++;
                } else {
                    System.arraycopy(bArr5, 0, bArr4, i3, i - i3);
                    return bArr4;
                }
            }
        } else {
            throw new GeneralSecurityException("size too large");
        }
    }
}
