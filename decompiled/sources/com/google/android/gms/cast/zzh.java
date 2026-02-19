package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.internal.zzbdp;

final class zzh extends Cast.zza {
    private /* synthetic */ String zzetg;
    private /* synthetic */ LaunchOptions zzeth;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzh(Cast.CastApi.zza zza, GoogleApiClient googleApiClient, String str, LaunchOptions launchOptions) {
        super(googleApiClient);
        this.zzetg = str;
        this.zzeth = launchOptions;
    }


    public final void zza(zzbdp zzbdp) throws RemoteException {
        try {
            zzbdp.zza(this.zzetg, this.zzeth, (zzn<Cast.ApplicationConnectionResult>) this);
        } catch (IllegalStateException unused) {
            zzbj(2001);
        }
    }
}
