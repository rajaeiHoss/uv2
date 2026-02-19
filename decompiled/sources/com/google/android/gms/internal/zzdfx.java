package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public final class zzdfx extends zzdcr {
    static String encode(String str, String str2) throws UnsupportedEncodingException {
        int i;
        StringBuilder sb = new StringBuilder();
        Charset forName = Charset.forName("UTF-8");
        int i2 = 0;
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            int indexOf = str2.indexOf(charAt);
            char c = (char) charAt;
            if (indexOf != -1) {
                sb.append(c);
                i2++;
            } else {
                if (Character.isHighSurrogate(c)) {
                    int i3 = i2 + 1;
                    if (i3 >= str.length()) {
                        throw new UnsupportedEncodingException();
                    } else if (Character.isLowSurrogate(str.charAt(i3))) {
                        i = 2;
                    } else {
                        throw new UnsupportedEncodingException();
                    }
                } else {
                    i = 1;
                }
                int i4 = i + i2;
                byte[] bytes = str.substring(i2, i4).getBytes(forName);
                for (int i5 = 0; i5 < bytes.length; i5++) {
                    sb.append("%");
                    sb.append(Character.toUpperCase(Character.forDigit((bytes[i5] >> 4) & 15, 16)));
                    sb.append(Character.toUpperCase(Character.forDigit(bytes[i5] & 15, 16)));
                }
                i2 = i4;
            }
        }
        return sb.toString().replaceAll(" ", "%20");
    }

    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        try {
            return new zzdkc(encode(zzdcq.zzd(zzdjqArr.length > 0 ? (zzdjq) zzbq.checkNotNull(zzdjqArr[0]) : zzdjw.zzlcz), "#;/?:@&=+$,abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_.!~*'()0123456789"));
        } catch (UnsupportedEncodingException unused) {
            return zzdjw.zzlcz;
        }
    }
}
