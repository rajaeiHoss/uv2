package com.google.android.gms.internal;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.google.android.gms.common.internal.zzbq;

public final class zzdgm implements zzdcp {
    private final Context mContext;

    public zzdgm(Context context) {
        this.mContext = (Context) zzbq.checkNotNull(context);
    }

    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        String networkOperatorName;
        boolean z = true;
        zzbq.checkArgument(zzdjqArr != null);
        if (zzdjqArr.length != 0) {
            z = false;
        }
        zzbq.checkArgument(z);
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        zzdjw zzdjwVar = zzdjw.zzlcz;
        return (telephonyManager == null || (networkOperatorName = telephonyManager.getNetworkOperatorName()) == null) ? zzdjwVar : new zzdkc(networkOperatorName);
    }
}
