package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public final class zzdhi extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        int i = 1;
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length >= 2);
        if (zzdjqArr[0] == zzdjw.zzlcz || zzdjqArr[1] == zzdjw.zzlcz) {
            return zzdjw.zzlcz;
        }
        String zzd = zzdcq.zzd(zzdjqArr[0]);
        String zzd2 = zzdcq.zzd(zzdjqArr[1]);
        int i2 = 64;
        if (zzdjqArr.length > 2 && zzdjqArr[2] != zzdjw.zzlcz && zzdcq.zza(zzdjqArr[2])) {
            i2 = 66;
        }
        if (zzdjqArr.length > 3 && zzdjqArr[3] != zzdjw.zzlcz) {
            if (!(zzdjqArr[3] instanceof zzdju)) {
                return zzdjw.zzlcz;
            }
            double zzc = zzdcq.zzc(zzdjqArr[3]);
            if (Double.isInfinite(zzc) || zzc < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                return zzdjw.zzlcz;
            }
            i = (int) zzc;
        }
        String str = null;
        try {
            Matcher matcher = Pattern.compile(zzd2, i2).matcher(zzd);
            if (matcher.find() && matcher.groupCount() >= i) {
                str = matcher.group(i);
            }
            return str == null ? zzdjw.zzlcz : new zzdkc(str);
        } catch (PatternSyntaxException unused) {
            return zzdjw.zzlcz;
        }
    }
}
