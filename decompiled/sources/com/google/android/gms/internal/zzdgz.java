package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzbq;

public final class zzdgz implements zzdcp {
    private Context mContext;

    public zzdgz(Context context) {
        this.mContext = (Context) zzbq.checkNotNull(context);
    }

    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(zzdjqArr != null);
        String str = null;
        if (zzdjqArr.length > 0 && zzdjqArr[0] != zzdjw.zzlcz) {
            str = zzdcq.zzd(zzdke.zza(zzdbb, (zzdjq) zzdjqArr[0]));
        }
        String zzaf = zzdak.zzaf(this.mContext, str);
        return zzaf != null ? new zzdkc(zzaf) : zzdjw.zzlcz;
    }
}
