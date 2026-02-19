package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

final class zzcfz extends zzcgj {
    private /* synthetic */ LocationRequest zzitt;
    private /* synthetic */ LocationListener zzitu;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcfz(zzcfy zzcfy, GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener) {
        super(googleApiClient);
        this.zzitt = locationRequest;
        this.zzitu = locationListener;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzchh zzchh) throws RemoteException {
        zzchh.zza(this.zzitt, (zzci<LocationListener>) zzcm.zzb(this.zzitu, zzchz.zzaxp(), LocationListener.class.getSimpleName()), (zzcgr) new zzcgk(this));
    }
}
