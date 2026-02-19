package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbt;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

final class zzgo {
    private static zzea<zzbt> zza(zzea<zzbt> zzea) {
        try {
            return new zzea<>(zzgk.zzam(zzmm(zzgk.zzd(zzea.getObject()))), zzea.zzbhd());
        } catch (UnsupportedEncodingException e) {
            zzdj.zzb("Escape URI: unsupported encoding", e);
            return zzea;
        }
    }

    static zzea<zzbt> zza(zzea<zzbt> zzea, int... iArr) {
        for (int i : iArr) {
            String sb = null;
            if (!(zzgk.zzi(zzea.getObject()) instanceof String)) {
                sb = "Escaping can only be applied to strings.";
            } else if (i != 12) {
                StringBuilder sb2 = new StringBuilder(39);
                sb2.append("Unsupported Value Escaping: ");
                sb2.append(i);
                sb = sb2.toString();
            } else {
                zzea = zza(zzea);
            }
            if (sb != null) {
                zzdj.e(sb);
            }
        }
        return zzea;
    }

    static String zzmm(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
    }
}
