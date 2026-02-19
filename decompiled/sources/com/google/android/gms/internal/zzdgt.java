package com.google.android.gms.internal;

import android.os.Build;
import com.google.android.gms.common.internal.zzbq;

public final class zzdgt implements zzdcp {
    private final String zzlah = Build.MANUFACTURER;
    private final String zzlai = Build.MODEL;

    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = false;
        zzbq.checkArgument(zzdjqArr != null);
        if (zzdjqArr.length == 0) {
            z = true;
        }
        zzbq.checkArgument(z);
        String str = this.zzlah;
        String str2 = this.zzlai;
        if (!str2.startsWith(str) && !str.equals("unknown")) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
            sb.append(str);
            sb.append(" ");
            sb.append(str2);
            str2 = sb.toString();
        }
        return new zzdkc(str2);
    }
}
