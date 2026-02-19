package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.internal.zzbdp;

final class zzg extends Cast.zza {
    private /* synthetic */ String zzetg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzg(Cast.CastApi.zza zza, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.zzetg = str;
    }


    public final void zza(zzbdp zzbdp) throws RemoteException {
        try {
            String str = this.zzetg;
            LaunchOptions launchOptions = new LaunchOptions();
            launchOptions.setRelaunchIfRunning(false);
            zzbdp.zza(str, launchOptions, (zzn<Cast.ApplicationConnectionResult>) this);
        } catch (IllegalStateException unused) {
            zzbj(2001);
        }
    }
}
