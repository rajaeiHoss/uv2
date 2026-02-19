package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzbq;

public final class zzdhd extends zzdcr {
    private final Context mContext;
    private final zzdaz zzlae;

    public zzdhd(Context context, zzdaz zzdaz) {
        this.mContext = context;
        this.zzlae = zzdaz;
    }

    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length > 0);
        Object obj = this.zzlae.zzbjc().zzbgl().get(zzdcq.zzd(zzdjqArr[0]));
        Object obj2 = obj;
        if (obj == null) {
            obj2 = obj;
            if (zzdjqArr.length > 1) {
                obj2 = zzdjqArr[1];
            }
        }
        return zzdke.zzau(obj2);
    }
}
