package com.google.android.gms.internal;

import android.content.Context;
import android.provider.Settings;
import com.google.android.gms.common.internal.zzbq;

public final class zzdgr implements zzdcp {
    private final Context mContext;

    public zzdgr(Context context) {
        this.mContext = context;
    }

    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(zzdjqArr != null);
        if (zzdjqArr.length != 0) {
            z = false;
        }
        zzbq.checkArgument(z);
        String string = Settings.Secure.getString(this.mContext.getContentResolver(), "android_id");
        if (string == null) {
            string = "";
        }
        return new zzdkc(string);
    }
}
