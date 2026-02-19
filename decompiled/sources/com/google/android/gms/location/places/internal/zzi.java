package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.zzm;

final class zzi extends zzm.zzc<zzo> {
    private /* synthetic */ AddPlaceRequest zzixa;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzi(zzh zzh, Api api, GoogleApiClient googleApiClient, AddPlaceRequest addPlaceRequest) {
        super(api, googleApiClient);
        this.zzixa = addPlaceRequest;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzo zzo) throws RemoteException {
        zzo.zza(new zzm((zzm.zzc) this), this.zzixa);
    }
}
