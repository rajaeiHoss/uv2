package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import kotlin.jvm.internal.ByteCompanionObject;

public final class zzdfv extends zzdcr {
    static String decode(String str, String str2) throws UnsupportedEncodingException {
        Charset forName = Charset.forName("UTF-8");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt != '%') {
                sb.append((char) charAt);
                i++;
            } else {
                byte zzz = zzz(str, i);
                int i2 = i + 3;
                if ((zzz & ByteCompanionObject.MIN_VALUE) != 0) {
                    int i3 = 0;
                    while (((zzz << i3) & 128) != 0) {
                        i3++;
                    }
                    if (i3 < 2 || i3 > 4) {
                        throw new UnsupportedEncodingException();
                    }
                    byte[] bArr = new byte[i3];
                    bArr[0] = zzz;
                    int i4 = 1;
                    while (i4 < i3) {
                        byte zzz2 = zzz(str, i2);
                        i2 += 3;
                        if ((zzz2 & 192) == 128) {
                            bArr[i4] = zzz2;
                            i4++;
                        } else {
                            throw new UnsupportedEncodingException();
                        }
                    }
                    CharBuffer decode = forName.decode(ByteBuffer.wrap(bArr));
                    if (decode.length() != 1 || str2.indexOf(decode.charAt(0)) == -1) {
                        sb.append(decode);
                        i = i2;
                    }
                } else if (str2.indexOf(zzz) == -1) {
                    sb.append((char) zzz);
                    i = i2;
                } else {
                    i = i2 - 3;
                }
                sb.append(str.substring(i, i2));
                i = i2;
            }
        }
        return sb.toString();
    }

    private static byte zzz(String str, int i) throws UnsupportedEncodingException {
        int i2 = i + 3;
        if (i2 > str.length() || str.charAt(i) != '%') {
            throw new UnsupportedEncodingException();
        }
        String substring = str.substring(i + 1, i2);
        if (substring.charAt(0) == '+' || substring.charAt(0) == '-') {
            throw new UnsupportedEncodingException();
        }
        try {
            return (byte) Integer.parseInt(substring, 16);
        } catch (NumberFormatException unused) {
            throw new UnsupportedEncodingException();
        }
    }

    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        try {
            return new zzdkc(decode(zzdcq.zzd(zzdjqArr.length > 0 ? (zzdjq) zzbq.checkNotNull(zzdjqArr[0]) : zzdjw.zzlcz), "#;/?:@&=+$,"));
        } catch (UnsupportedEncodingException unused) {
            return zzdjw.zzlcz;
        }
    }
}
