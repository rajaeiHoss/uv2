package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.gms.common.internal.zzbq;

public final class zzdgl implements zzdcp {
    private final Context mContext;

    public zzdgl(Context context) {
        this.mContext = (Context) zzbq.checkNotNull(context);
    }

    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(zzdjqArr != null);
        if (zzdjqArr.length != 0) {
            z = false;
        }
        zzbq.checkArgument(z);
        try {
            return new zzdkc(this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            String packageName = this.mContext.getPackageName();
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(packageName).length() + 25 + String.valueOf(valueOf).length());
            sb.append("Package name ");
            sb.append(packageName);
            sb.append(" not found. ");
            sb.append(valueOf);
            zzdal.e(sb.toString());
            return zzdjw.zzlcz;
        }
    }
}
