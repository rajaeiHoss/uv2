package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.location.LocationCallback;

final class zzcga extends zzcgj {
    private /* synthetic */ LocationCallback zzitv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcga(zzcfy zzcfy, GoogleApiClient googleApiClient, LocationCallback locationCallback) {
        super(googleApiClient);
        this.zzitv = locationCallback;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzchh zzchh) throws RemoteException {
        zzchh.zzb(zzcm.zzb(this.zzitv, LocationCallback.class.getSimpleName()), new zzcgk(this));
    }
}
