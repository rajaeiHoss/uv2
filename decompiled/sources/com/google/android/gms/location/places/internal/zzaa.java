package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.zzm;

final class zzaa extends zzm.zzd<zzac> {
    private /* synthetic */ PlaceFilter zzixl;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaa(zzz zzz, Api api, GoogleApiClient googleApiClient, PlaceFilter placeFilter) {
        super(api, googleApiClient);
        this.zzixl = placeFilter;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzac zzac) throws RemoteException {
        zzac.zza(new zzm((zzm.zzd) this), this.zzixl);
    }
}
