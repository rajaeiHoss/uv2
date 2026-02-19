package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;
import java.util.List;

public final class zzdcx extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkNotNull(zzdjqArr);
        zzbq.checkArgument(zzdjqArr.length == 1 || zzdjqArr.length == 2);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdjx);
        List<zzdjq> list = (List) zzdjqArr[0].value();
        zzdjw zzdjwVar = zzdjqArr.length < 2 ? zzdjw.zzlcz : (zzdjw) zzdjqArr[1];
        String zzd = zzdjwVar == zzdjw.zzlcz ? "," : zzdcq.zzd(zzdjwVar);
        ArrayList arrayList = new ArrayList();
        for (zzdjq zzdjq : list) {
            arrayList.add((zzdjq == zzdjw.zzlcy || zzdjq == zzdjw.zzlcz) ? "" : zzdcq.zzd(zzdjq));
        }
        return new zzdkc(TextUtils.join(zzd, arrayList));
    }
}
