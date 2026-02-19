package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.tagmanager.zzce;
import com.google.android.gms.tagmanager.zzcn;

public final class zzdba {
    private final Context mContext;
    private final String zzknc;
    private final zzcn zzkvt;
    private final zzce zzkwc;

    public zzdba(Context context, zzcn zzcn, zzce zzce, String str) {
        this.mContext = context.getApplicationContext();
        this.zzkvt = zzcn;
        this.zzkwc = zzce;
        this.zzknc = str;
    }

    public final zzdav zza(zzdjc zzdjc, zzdjk zzdjk) {
        return new zzdav(this.mContext, this.zzknc, zzdjc, zzdjk, this.zzkvt, this.zzkwc);
    }
}
