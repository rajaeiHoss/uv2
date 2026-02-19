package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzbq;

public final class zzdgh extends zzdcr {
    private final Context mContext;
    private final zzdaz zzlae;

    public zzdgh(Context context, zzdaz zzdaz) {
        this.mContext = (Context) zzbq.checkNotNull(context);
        this.zzlae = zzdaz;
    }

    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        if (zzdjqArr.length == 0 || zzdjqArr[0] == zzdjw.zzlcz) {
            return new zzdkc("");
        }
        Object obj = this.zzlae.zzbjc().zzbgl().get("_ldl");
        if (obj == null) {
            return new zzdkc("");
        }
        zzdjq<?> zzau = zzdke.zzau(obj);
        if (!(zzau instanceof zzdkc)) {
            return new zzdkc("");
        }
        String str = (String) ((zzdkc) zzau).value();
        if (!zzdak.zzaw(str, "conv").equals(zzdcq.zzd(zzdjqArr[0]))) {
            return new zzdkc("");
        }
        String str2 = null;
        if (zzdjqArr.length > 1 && zzdjqArr[1] != zzdjw.zzlcz) {
            str2 = zzdcq.zzd(zzdjqArr[1]);
        }
        String zzaw = zzdak.zzaw(str, str2);
        return zzaw != null ? new zzdkc(zzaw) : new zzdkc("");
    }
}
