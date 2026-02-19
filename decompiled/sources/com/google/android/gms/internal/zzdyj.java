package com.google.android.gms.internal;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;

public final class zzdyj implements zzdtj {
    private Mac zzmlu;
    private final int zzmlv;
    private final String zzmlw;
    private final Key zzmlx;

    public zzdyj(String str, Key key, int i) throws GeneralSecurityException {
        if (i >= 10) {
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1823053428:
                    if (str.equals("HMACSHA1")) {
                        c = 0;
                        break;
                    }
                    break;
                case 392315118:
                    if (str.equals("HMACSHA256")) {
                        c = 1;
                        break;
                    }
                    break;
                case 392317873:
                    if (str.equals("HMACSHA512")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    if (i > 20) {
                        throw new InvalidAlgorithmParameterException("tag size too big");
                    }
                    break;
                case 1:
                    if (i > 32) {
                        throw new InvalidAlgorithmParameterException("tag size too big");
                    }
                    break;
                case 2:
                    if (i > 64) {
                        throw new InvalidAlgorithmParameterException("tag size too big");
                    }
                    break;
                default:
                    String valueOf = String.valueOf(str);
                    throw new NoSuchAlgorithmException(valueOf.length() != 0 ? "unknown Hmac algorithm: ".concat(valueOf) : new String("unknown Hmac algorithm: "));
            }
            this.zzmlw = str;
            this.zzmlv = i;
            this.zzmlx = key;
            Mac zzoy = zzdxx.zzmll.zzoy(str);
            this.zzmlu = zzoy;
            zzoy.init(key);
            return;
        }
        throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
    }

    public final byte[] zzaf(byte[] bArr) throws GeneralSecurityException {
        Mac mac;
        try {
            mac = (Mac) this.zzmlu.clone();
        } catch (CloneNotSupportedException unused) {
            mac = zzdxx.zzmll.zzoy(this.zzmlw);
            mac.init(this.zzmlx);
        }
        mac.update(bArr);
        byte[] bArr2 = new byte[this.zzmlv];
        System.arraycopy(mac.doFinal(), 0, bArr2, 0, this.zzmlv);
        return bArr2;
    }
}
