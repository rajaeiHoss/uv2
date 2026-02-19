package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class zzdfl extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length == 1 || zzdjqArr.length == 2);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdkc);
        Matcher matcher = Pattern.compile(zzdjqArr.length < 2 ? "" : zzdcq.zzd(zzdjqArr[1])).matcher((String) zzdjqArr[0].value());
        return matcher.find() ? new zzdju(Double.valueOf((double) matcher.start())) : new zzdju(Double.valueOf(-1.0d));
    }
}
