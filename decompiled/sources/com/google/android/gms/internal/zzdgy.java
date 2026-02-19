package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class zzdgy extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        byte[] bArr;
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length > 0);
        if (zzdjqArr[0] == zzdjw.zzlcz) {
            return zzdjw.zzlcz;
        }
        String zzd = zzdcq.zzd(zzdjqArr[0]);
        String str = "MD5";
        if (zzdjqArr.length > 1 && zzdjqArr[1] != zzdjw.zzlcz) {
            str = zzdcq.zzd(zzdjqArr[1]);
        }
        String zzd2 = (zzdjqArr.length <= 2 || zzdjqArr[2] == zzdjw.zzlcz) ? "text" : zzdcq.zzd(zzdjqArr[2]);
        if ("text".equals(zzd2)) {
            bArr = zzd.getBytes();
        } else if ("Base16".equals(zzd2)) {
            bArr = zzczf.decode(zzd);
        } else {
            String valueOf = String.valueOf(zzd2);
            throw new RuntimeException(valueOf.length() != 0 ? "Hash: Unknown input format: ".concat(valueOf) : new String("Hash: Unknown input format: "));
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return new zzdkc(zzczf.zzj(instance.digest()));
        } catch (NoSuchAlgorithmException e) {
            String valueOf2 = String.valueOf(str);
            throw new RuntimeException(valueOf2.length() != 0 ? "Hash: Unknown algorithm: ".concat(valueOf2) : new String("Hash: Unknown algorithm: "), e);
        }
    }
}
