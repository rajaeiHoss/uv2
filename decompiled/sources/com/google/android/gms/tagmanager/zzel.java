package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbi;
import com.google.android.gms.internal.zzbt;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

final class zzel extends zzbr {
    private static final String ID = zzbh.REGEX_GROUP.toString();
    private static final String zzkrv = zzbi.ARG0.toString();
    private static final String zzkrw = zzbi.ARG1.toString();
    private static final String zzkrx = zzbi.IGNORE_CASE.toString();
    private static final String zzkry = zzbi.GROUP.toString();

    public zzel() {
        super(ID, zzkrv, zzkrw);
    }

    public final boolean zzbfh() {
        return true;
    }

    public final zzbt zzx(Map<String, zzbt> map) {
        zzbt zzbt = map.get(zzkrv);
        zzbt zzbt2 = map.get(zzkrw);
        if (zzbt == null || zzbt == zzgk.zzbil() || zzbt2 == null || zzbt2 == zzgk.zzbil()) {
            return zzgk.zzbil();
        }
        int i = 64;
        if (zzgk.zzh(map.get(zzkrx)).booleanValue()) {
            i = 66;
        }
        int i2 = 1;
        zzbt zzbt3 = map.get(zzkry);
        if (zzbt3 != null) {
            Long zzf = zzgk.zzf(zzbt3);
            if (zzf == zzgk.zzbig()) {
                return zzgk.zzbil();
            }
            i2 = zzf.intValue();
            if (i2 < 0) {
                return zzgk.zzbil();
            }
        }
        try {
            String zzd = zzgk.zzd(zzbt);
            String zzd2 = zzgk.zzd(zzbt2);
            String str = null;
            Matcher matcher = Pattern.compile(zzd2, i).matcher(zzd);
            if (matcher.find() && matcher.groupCount() >= i2) {
                str = matcher.group(i2);
            }
            return str == null ? zzgk.zzbil() : zzgk.zzam(str);
        } catch (PatternSyntaxException unused) {
            return zzgk.zzbil();
        }
    }
}
