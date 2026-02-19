package com.google.android.gms.internal;

import android.content.Context;
import android.provider.Settings;
import com.google.android.gms.common.internal.zzbq;

public final class zzdhe implements zzdcp {
    private final Context mContext;

    public zzdhe(Context context) {
        this.mContext = (Context) zzbq.checkNotNull(context);
    }

    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(zzdjqArr != null);
        if (zzdjqArr.length != 0) {
            z = false;
        }
        zzbq.checkArgument(z);
        String string = Settings.Secure.getString(this.mContext.getContentResolver(), "android_id");
        return string != null ? new zzdkc(string) : zzdjw.zzlcz;
    }
}
