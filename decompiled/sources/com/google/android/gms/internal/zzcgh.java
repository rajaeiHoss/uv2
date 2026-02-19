package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.location.LocationListener;

final class zzcgh extends zzcgj {
    private /* synthetic */ LocationListener zzitu;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcgh(zzcfy zzcfy, GoogleApiClient googleApiClient, LocationListener locationListener) {
        super(googleApiClient);
        this.zzitu = locationListener;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzchh zzchh) throws RemoteException {
        zzchh.zza((zzck<LocationListener>) zzcm.zzb(this.zzitu, LocationListener.class.getSimpleName()), (zzcgr) new zzcgk(this));
    }
}
