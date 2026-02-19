package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbi;
import com.google.android.gms.internal.zzbt;
import java.util.Map;

final class zzbk extends zzbr {
    private static final String ID = zzbh.ENCODE.toString();
    private static final String zzkpn = zzbi.ARG0.toString();
    private static final String zzkpo = zzbi.NO_PADDING.toString();
    private static final String zzkpp = zzbi.INPUT_FORMAT.toString();
    private static final String zzkpq = zzbi.OUTPUT_FORMAT.toString();

    public zzbk() {
        super(ID, zzkpn);
    }

    public final boolean zzbfh() {
        return true;
    }

    public final zzbt zzx(Map<String, zzbt> map) {
        String str;
        byte[] bArr;
        String str2;
        zzbt zzbt = map.get(zzkpn);
        if (zzbt == null || zzbt == zzgk.zzbil()) {
            return zzgk.zzbil();
        }
        String zzd = zzgk.zzd(zzbt);
        zzbt zzbt2 = map.get(zzkpp);
        String zzd2 = zzbt2 == null ? "text" : zzgk.zzd(zzbt2);
        zzbt zzbt3 = map.get(zzkpq);
        String zzd3 = zzbt3 == null ? "Base16" : zzgk.zzd(zzbt3);
        int i = 2;
        zzbt zzbt4 = map.get(zzkpo);
        if (zzbt4 != null && zzgk.zzh(zzbt4).booleanValue()) {
            i = 3;
        }
        try {
            if ("text".equals(zzd2)) {
                bArr = zzd.getBytes();
            } else if ("Base16".equals(zzd2)) {
                bArr = zzo.decode(zzd);
            } else if ("Base64".equals(zzd2)) {
                bArr = Base64.decode(zzd, i);
            } else if ("base64url".equals(zzd2)) {
                bArr = Base64.decode(zzd, i | 8);
            } else {
                String valueOf = String.valueOf(zzd2);
                zzdj.e(valueOf.length() != 0 ? "Encode: unknown input format: ".concat(valueOf) : new String("Encode: unknown input format: "));
                return zzgk.zzbil();
            }
            if ("Base16".equals(zzd3)) {
                str2 = zzo.zzj(bArr);
            } else if ("Base64".equals(zzd3)) {
                str2 = Base64.encodeToString(bArr, i);
            } else if ("base64url".equals(zzd3)) {
                str2 = Base64.encodeToString(bArr, i | 8);
            } else {
                String valueOf2 = String.valueOf(zzd3);
                str = valueOf2.length() != 0 ? "Encode: unknown output format: ".concat(valueOf2) : new String("Encode: unknown output format: ");
                zzdj.e(str);
                return zzgk.zzbil();
            }
            return zzgk.zzam(str2);
        } catch (IllegalArgumentException unused) {
            str = "Encode: invalid input:";
            zzdj.e(str);
            return zzgk.zzbil();
        }
    }
}
