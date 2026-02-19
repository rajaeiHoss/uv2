package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public final class zzdhu extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length == 2 || zzdjqArr.length == 3);
        String zzd = zzdcq.zzd(zzdjqArr[0]);
        String zzd2 = zzdcq.zzd(zzdjqArr[1]);
        int i = 64;
        if (zzdjqArr.length < 3 ? false : "true".equalsIgnoreCase(zzdcq.zzd(zzdjqArr[2]))) {
            i = 66;
        }
        try {
            return new zzdjt(Boolean.valueOf(Pattern.compile(zzd2, i).matcher(zzd).find()));
        } catch (PatternSyntaxException unused) {
            return new zzdjt(false);
        }
    }
}
