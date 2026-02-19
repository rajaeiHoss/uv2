package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.zzah;
import com.google.android.gms.fitness.result.BleDevicesResult;

final class zzcac extends zzbwv<BleDevicesResult> {
    zzcac(zzbzw zzbzw, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbws zzbws) throws RemoteException {
        ((zzbyw) zzbws.zzalw()).zza(new zzah((zzcbu) new zzcad(this, (zzbzx) null)));
    }

    /* access modifiers changed from: protected */
    public final BleDevicesResult zzb(Status status) {
        return BleDevicesResult.zzae(status);
    }
}
