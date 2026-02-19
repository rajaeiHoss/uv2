package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;

public final class zzdfn extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        int i = 1;
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length == 1 || zzdjqArr.length == 2);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdkc);
        ArrayList arrayList = new ArrayList();
        if (zzdjqArr.length == 1) {
            arrayList.add(zzdjqArr[0]);
        } else {
            String str = (String) zzdjqArr[0].value();
            String zzd = zzdcq.zzd(zzdjqArr[1]);
            boolean equals = zzd.equals("");
            String[] split = str.split(zzd, equals ? 0 : -1);
            if (!equals || split.length <= 0 || !split[0].equals("")) {
                i = 0;
            }
            while (i < split.length) {
                arrayList.add(new zzdkc(split[i]));
                i++;
            }
        }
        return new zzdjx(arrayList);
    }
}
