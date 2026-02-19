package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.zzm;
import com.google.android.gms.maps.model.LatLngBounds;

final class zzn extends zzm.zza<zzo> {
    private /* synthetic */ String val$query;
    private /* synthetic */ LatLngBounds zzixh;
    private /* synthetic */ AutocompleteFilter zzixj;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzn(zzh zzh, Api api, GoogleApiClient googleApiClient, String str, LatLngBounds latLngBounds, AutocompleteFilter autocompleteFilter) {
        super(api, googleApiClient);
        this.val$query = str;
        this.zzixh = latLngBounds;
        this.zzixj = autocompleteFilter;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzo zzo) throws RemoteException {
        zzo.zza(new zzm((zzm.zza) this), this.val$query, this.zzixh, 1, this.zzixj);
    }
}
