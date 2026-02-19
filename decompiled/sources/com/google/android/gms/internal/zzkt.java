package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.zzs;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzks;

final class zzkt extends zzks.zza<zzlt> {
    private /* synthetic */ Context val$context;
    private /* synthetic */ zzko zzbip;
    private /* synthetic */ String zzbiq;
    private /* synthetic */ zzwf zzbir;
    private /* synthetic */ zzks zzbis;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzkt(zzks zzks, Context context, zzko zzko, String str, zzwf zzwf) {
        zzks.super();
        this.zzbis = zzks;
        this.val$context = context;
        this.zzbip = zzko;
        this.zzbiq = str;
        this.zzbir = zzwf;
    }

    public final zzlt zza(zzmb zzmb) throws RemoteException {
        return zzmb.createBannerAdManager(zzn.zzz(this.val$context), this.zzbip, this.zzbiq, this.zzbir, zzs.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    public final zzlt zzif() throws RemoteException {
        zzlt zza = this.zzbis.zzbii.zza(this.val$context, this.zzbip, this.zzbiq, this.zzbir, 1);
        if (zza != null) {
            return zza;
        }
        zzks.zza(this.val$context, "banner");
        return new zznh();
    }
}
