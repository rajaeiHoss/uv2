package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.zzs;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzks;

final class zzkx extends zzks.zza<zzmh> {
    private /* synthetic */ Context val$context;
    private /* synthetic */ zzks zzbis;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzkx(zzks zzks, Context context) {
        zzks.super();
        this.zzbis = zzks;
        this.val$context = context;
    }

    public final zzmh zza(zzmb zzmb) throws RemoteException {
        return zzmb.getMobileAdsSettingsManagerWithClientJarVersion(zzn.zzz(this.val$context), zzs.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    public final zzmh zzif() throws RemoteException {
        zzmh zzg = this.zzbis.zzbik.zzg(this.val$context);
        if (zzg != null) {
            return zzg;
        }
        zzks.zza(this.val$context, "mobile_ads_settings");
        return new zznj();
    }
}
