package com.google.android.gms.internal;

import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

final class zzcge extends zzcgj {
    private /* synthetic */ LocationRequest zzitt;
    private /* synthetic */ LocationListener zzitu;
    private /* synthetic */ Looper zzity;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcge(zzcfy zzcfy, GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener, Looper looper) {
        super(googleApiClient);
        this.zzitt = locationRequest;
        this.zzitu = locationListener;
        this.zzity = looper;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzchh zzchh) throws RemoteException {
        zzchh.zza(this.zzitt, (zzci<LocationListener>) zzcm.zzb(this.zzitu, zzchz.zzb(this.zzity), LocationListener.class.getSimpleName()), (zzcgr) new zzcgk(this));
    }
}
