package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbi;
import com.google.android.gms.internal.zzbt;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

final class zzbw extends zzbr {
    private static final String ID = zzbh.HASH.toString();
    private static final String zzkpn = zzbi.ARG0.toString();
    private static final String zzkpp = zzbi.INPUT_FORMAT.toString();
    private static final String zzkpt = zzbi.ALGORITHM.toString();

    public zzbw() {
        super(ID, zzkpn);
    }

    public final boolean zzbfh() {
        return true;
    }

    public final zzbt zzx(Map<String, zzbt> map) {
        String str;
        byte[] bArr;
        zzbt zzbt = map.get(zzkpn);
        if (zzbt == null || zzbt == zzgk.zzbil()) {
            return zzgk.zzbil();
        }
        String zzd = zzgk.zzd(zzbt);
        zzbt zzbt2 = map.get(zzkpt);
        String zzd2 = zzbt2 == null ? "MD5" : zzgk.zzd(zzbt2);
        zzbt zzbt3 = map.get(zzkpp);
        String zzd3 = zzbt3 == null ? "text" : zzgk.zzd(zzbt3);
        if ("text".equals(zzd3)) {
            bArr = zzd.getBytes();
        } else if ("Base16".equals(zzd3)) {
            bArr = zzo.decode(zzd);
        } else {
            String valueOf = String.valueOf(zzd3);
            str = valueOf.length() != 0 ? "Hash: unknown input format: ".concat(valueOf) : new String("Hash: unknown input format: ");
            zzdj.e(str);
            return zzgk.zzbil();
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(zzd2);
            instance.update(bArr);
            return zzgk.zzam(zzo.zzj(instance.digest()));
        } catch (NoSuchAlgorithmException unused) {
            String valueOf2 = String.valueOf(zzd2);
            str = valueOf2.length() != 0 ? "Hash: unknown algorithm: ".concat(valueOf2) : new String("Hash: unknown algorithm: ");
            zzdj.e(str);
            return zzgk.zzbil();
        }
    }
}
