package com.google.android.gms.internal;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

final class zzcgc extends zzcgj {
    private /* synthetic */ Location zzitx;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcgc(zzcfy zzcfy, GoogleApiClient googleApiClient, Location location) {
        super(googleApiClient);
        this.zzitx = location;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzchh zzchh) throws RemoteException {
        zzchh.zzc(this.zzitx);
        setResult(Status.zzftq);
    }
}
