package com.google.android.gms.internal;

import android.util.Base64;
import com.google.android.gms.common.internal.zzbq;

public final class zzdgu extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        byte[] bArr;
        String str;
        boolean z = true;
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length > 0);
        String zzd = zzdcq.zzd(zzdjqArr[0]);
        String zzd2 = zzdjqArr.length > 1 ? zzdcq.zzd(zzdjqArr[1]) : "text";
        int i = 2;
        String zzd3 = zzdjqArr.length > 2 ? zzdcq.zzd(zzdjqArr[2]) : "Base16";
        if (zzdjqArr.length <= 3 || !zzdcq.zza(zzdjqArr[3])) {
            z = false;
        }
        if (z) {
            i = 3;
        }
        try {
            if ("text".equals(zzd2)) {
                bArr = zzd.getBytes();
            } else if ("Base16".equals(zzd2)) {
                bArr = zzczf.decode(zzd);
            } else if ("Base64".equals(zzd2)) {
                bArr = Base64.decode(zzd, i);
            } else if ("base64url".equals(zzd2)) {
                bArr = Base64.decode(zzd, i | 8);
            } else {
                String valueOf = String.valueOf(zzd2);
                throw new UnsupportedOperationException(valueOf.length() != 0 ? "Encode: unknown input format: ".concat(valueOf) : new String("Encode: unknown input format: "));
            }
            if ("Base16".equals(zzd3)) {
                str = zzczf.zzj(bArr);
            } else if ("Base64".equals(zzd3)) {
                str = Base64.encodeToString(bArr, i);
            } else if ("base64url".equals(zzd3)) {
                str = Base64.encodeToString(bArr, i | 8);
            } else {
                String valueOf2 = String.valueOf(zzd3);
                throw new RuntimeException(valueOf2.length() != 0 ? "Encode: unknown output format: ".concat(valueOf2) : new String("Encode: unknown output format: "));
            }
            return new zzdkc(str);
        } catch (IllegalArgumentException unused) {
            String valueOf3 = String.valueOf(zzd2);
            throw new RuntimeException(valueOf3.length() != 0 ? "Encode: invalid input:".concat(valueOf3) : new String("Encode: invalid input:"));
        }
    }
}
