package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.zzs;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzks;

final class zzla extends zzks.zza<zzaex> {
    private /* synthetic */ Context val$context;
    private /* synthetic */ zzwf zzbir;
    private /* synthetic */ zzks zzbis;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzla(zzks zzks, Context context, zzwf zzwf) {
        zzks.super();
        this.zzbis = zzks;
        this.val$context = context;
        this.zzbir = zzwf;
    }

    public final zzaex zza(zzmb zzmb) throws RemoteException {
        return zzmb.createRewardedVideoAd(zzn.zzz(this.val$context), this.zzbir, zzs.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    public final zzaex zzif() throws RemoteException {
        zzaex zza = this.zzbis.zzbim.zza(this.val$context, this.zzbir);
        if (zza != null) {
            return zza;
        }
        zzks.zza(this.val$context, "rewarded_video");
        return new zznm();
    }
}
