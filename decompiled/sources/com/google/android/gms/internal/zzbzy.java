package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.zzbh;

final class zzbzy extends zzbwx {
    private /* synthetic */ BleScanCallback zzhmi;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbzy(zzbzw zzbzw, GoogleApiClient googleApiClient, BleScanCallback bleScanCallback) {
        super(googleApiClient);
        this.zzhmi = bleScanCallback;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbws zzbws) throws RemoteException {
        ((zzbyw) zzbws.zzalw()).zza(new zzbh(this.zzhmi, (zzbzt) new zzcbq(this)));
    }
}
