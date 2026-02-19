package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.List;

public final class zzdee extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length == 3);
        zzbq.checkArgument(zzdjqArr[1] instanceof zzdkc);
        zzbq.checkArgument(zzdjqArr[2] instanceof zzdjx);
        zzdjq<?> zzdjq = zzdjqArr[0];
        String str = (String) zzdjqArr[1].value();
        List list = (List) zzdjqArr[2].value();
        if (zzdjq.zzni(str)) {
            zzdjq<?> zznj = zzdjq.zznj(str);
            if (zznj instanceof zzdjv) {
                return ((zzdcp) ((zzdjv) zznj).value()).zzb(zzdbb, (zzdjq[]) list.toArray(new zzdjq[list.size()]));
            }
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 35);
            sb.append("Apply TypeError: ");
            sb.append(str);
            sb.append(" is not a function");
            throw new IllegalArgumentException(sb.toString());
        } else if (zzdjq.zznk(str)) {
            zzdcp zznl = zzdjq.zznl(str);
            list.add(0, zzdjq);
            return zznl.zzb(zzdbb, (zzdjq[]) list.toArray(new zzdjq[list.size()]));
        } else {
            StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 40);
            sb2.append("Apply TypeError: object has no ");
            sb2.append(str);
            sb2.append(" property");
            throw new IllegalArgumentException(sb2.toString());
        }
    }
}
