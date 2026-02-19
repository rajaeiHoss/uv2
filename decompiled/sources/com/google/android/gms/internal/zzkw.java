package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.zzs;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzks;

final class zzkw extends zzks.zza<zzlo> {
    private /* synthetic */ Context val$context;
    private /* synthetic */ String zzbiq;
    private /* synthetic */ zzwf zzbir;
    private /* synthetic */ zzks zzbis;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzkw(zzks zzks, Context context, String str, zzwf zzwf) {
        zzks.super();
        this.zzbis = zzks;
        this.val$context = context;
        this.zzbiq = str;
        this.zzbir = zzwf;
    }

    public final zzlo zza(zzmb zzmb) throws RemoteException {
        return zzmb.createAdLoaderBuilder(zzn.zzz(this.val$context), this.zzbiq, this.zzbir, zzs.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    public final zzlo zzif() throws RemoteException {
        zzlo zza = this.zzbis.zzbij.zza(this.val$context, this.zzbiq, this.zzbir);
        if (zza != null) {
            return zza;
        }
        zzks.zza(this.val$context, "native_ad");
        return new zznd();
    }
}
