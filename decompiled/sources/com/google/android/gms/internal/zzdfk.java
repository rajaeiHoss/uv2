package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzdfk extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length > 0 && zzdjqArr.length <= 3);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdkc);
        String str = (String) zzdjqArr[0].value();
        if (zzdjqArr.length == 1) {
            return new zzdkc(str);
        }
        String zzd = zzdcq.zzd(zzdjqArr[1]);
        zzdjq<?> zzdjq = zzdjqArr.length < 3 ? zzdjw.zzlcz : zzdjqArr[2];
        int indexOf = str.indexOf(zzd);
        if (indexOf == -1) {
            return new zzdkc(str);
        }
        if (zzdjq instanceof zzdjv) {
            zzdjq = ((zzdcp) ((zzdjv) zzdjq).value()).zzb(zzdbb, new zzdkc(zzd), new zzdju(Double.valueOf((double) indexOf)), new zzdkc(str));
        }
        String zzd2 = zzdcq.zzd(zzdjq);
        String substring = str.substring(0, indexOf);
        String substring2 = str.substring(indexOf + zzd.length());
        StringBuilder sb = new StringBuilder(String.valueOf(substring).length() + String.valueOf(zzd2).length() + String.valueOf(substring2).length());
        sb.append(substring);
        sb.append(zzd2);
        sb.append(substring2);
        return new zzdkc(sb.toString());
    }
}
