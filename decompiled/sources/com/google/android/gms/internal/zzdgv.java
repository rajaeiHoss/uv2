package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class zzdgv extends zzdcr {
    private static final Pattern zzlaj = Pattern.compile("(.+)/(.+)/(.+)");

    private static String zza(Cipher cipher, String str, SecretKeySpec secretKeySpec, IvParameterSpec ivParameterSpec) {
        if (str == null || str.length() == 0) {
            throw new RuntimeException("Encrypt: empty input string");
        }
        try {
            cipher.init(1, secretKeySpec, ivParameterSpec);
            return zzczf.zzj(cipher.doFinal(str.getBytes()));
        } catch (Exception e) {
            String valueOf = String.valueOf(e.getMessage());
            throw new RuntimeException(valueOf.length() != 0 ? "Encrypt: ".concat(valueOf) : new String("Encrypt: "));
        }
    }

    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length >= 3);
        String zzd = zzdcq.zzd(zzdjqArr[0]);
        String zzd2 = zzdcq.zzd(zzdjqArr[1]);
        String zzd3 = zzdcq.zzd(zzdjqArr[2]);
        String zzd4 = zzdjqArr.length < 4 ? "AES/CBC/NoPadding" : zzdcq.zzd(zzdjqArr[3]);
        Matcher matcher = zzlaj.matcher(zzd4);
        if (!matcher.matches()) {
            String valueOf = String.valueOf(zzd4);
            throw new RuntimeException(valueOf.length() != 0 ? "Encrypt: invalid transformation:".concat(valueOf) : new String("Encrypt: invalid transformation:"));
        }
        try {
            return new zzdkc(zza(Cipher.getInstance(zzd4), zzd, new SecretKeySpec(zzd2.getBytes(), matcher.group(1)), new IvParameterSpec(zzd3.getBytes())));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException unused) {
            String valueOf2 = String.valueOf(zzd4);
            throw new RuntimeException(valueOf2.length() != 0 ? "Encrypt: invalid transformation:".concat(valueOf2) : new String("Encrypt: invalid transformation:"));
        }
    }
}
