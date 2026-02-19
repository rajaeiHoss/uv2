package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.maps.model.LatLngBounds;

final class zzm extends com.google.android.gms.location.places.zzm.zza<zzo> {
    private /* synthetic */ String val$query;
    private /* synthetic */ LatLngBounds zzixh;
    private /* synthetic */ int zzixi;
    private /* synthetic */ AutocompleteFilter zzixj;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzm(zzh zzh, Api api, GoogleApiClient googleApiClient, String str, LatLngBounds latLngBounds, int i, AutocompleteFilter autocompleteFilter) {
        super(api, googleApiClient);
        this.val$query = str;
        this.zzixh = latLngBounds;
        this.zzixi = i;
        this.zzixj = autocompleteFilter;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzo zzo) throws RemoteException {
        zzo.zza(new com.google.android.gms.location.places.zzm((com.google.android.gms.location.places.zzm.zza) this), this.val$query, this.zzixh, this.zzixi, this.zzixj);
    }
}
