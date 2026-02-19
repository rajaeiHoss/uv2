package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzbdp;
import com.google.android.gms.internal.zzbea;

final class zzj extends zzbea {
    zzj(Cast.CastApi.zza zza, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }


    public final void zza(zzbdp zzbdp) throws RemoteException {
        try {
            zzbdp.zzb(this);
        } catch (IllegalStateException unused) {
            zzbj(2001);
        }
    }
}
