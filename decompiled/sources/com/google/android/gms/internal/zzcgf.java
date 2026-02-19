package com.google.android.gms.internal;

import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;

final class zzcgf extends zzcgj {
    private /* synthetic */ LocationRequest zzitt;
    private /* synthetic */ LocationCallback zzitv;
    private /* synthetic */ Looper zzity;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcgf(zzcfy zzcfy, GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationCallback locationCallback, Looper looper) {
        super(googleApiClient);
        this.zzitt = locationRequest;
        this.zzitv = locationCallback;
        this.zzity = looper;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzchh zzchh) throws RemoteException {
        zzchh.zza(zzchl.zza(this.zzitt), (zzci<LocationCallback>) zzcm.zzb(this.zzitv, zzchz.zzb(this.zzity), LocationCallback.class.getSimpleName()), (zzcgr) new zzcgk(this));
    }
}
