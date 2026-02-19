package com.google.android.gms.location.places.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.location.places.zzd;
import com.google.android.gms.location.places.zzm;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;
import java.util.Locale;

public final class zzo extends zzab<zzt> {
    private final zzau zzixk;

    zzo(Context context, Looper looper, zzr zzr, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, String str, PlacesOptions placesOptions) {
        super(context, looper, 65, zzr, connectionCallbacks, onConnectionFailedListener);
        this.zzixk = new zzau(str, Locale.getDefault(), zzr.getAccount() != null ? zzr.getAccount().name : null, (String) null, 0);
    }

    public final void zza(zzd zzd, String str) throws RemoteException {
        zzbq.checkNotNull(zzd, "callback cannot be null");
        ((zzt) zzalw()).zza(str, this.zzixk, (zzv) zzd);
    }

    public final void zza(zzd zzd, String str, int i, int i2, int i3) throws RemoteException {
        zzbq.checkNotNull(zzd, "callback cannot be null");
        ((zzt) zzalw()).zza(str, i, i2, i3, this.zzixk, (zzv) zzd);
    }

    public final void zza(zzm zzm, AddPlaceRequest addPlaceRequest) throws RemoteException {
        zzbq.checkNotNull(zzm, "callback == null");
        ((zzt) zzalw()).zza(addPlaceRequest, this.zzixk, (zzx) zzm);
    }

    public final void zza(zzm zzm, String str, LatLngBounds latLngBounds, int i, AutocompleteFilter autocompleteFilter) throws RemoteException {
        zzbq.checkNotNull(zzm, "callback == null");
        if (str == null) {
            str = "";
        }
        String str2 = str;
        if (autocompleteFilter == null) {
            autocompleteFilter = new AutocompleteFilter.Builder().build();
        }
        ((zzt) zzalw()).zza(str2, latLngBounds, i, autocompleteFilter, this.zzixk, (zzx) zzm);
    }

    public final void zza(zzm zzm, List<String> list) throws RemoteException {
        zzbq.checkNotNull(zzm, "callback == null");
        ((zzt) zzalw()).zza(list, this.zzixk, (zzx) zzm);
    }

    /* access modifiers changed from: protected */
    public final zzt zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        return queryLocalInterface instanceof zzt ? (zzt) queryLocalInterface : new zzu(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.android.gms.location.places.GeoDataApi";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.android.gms.location.places.internal.IGooglePlacesService";
    }
}
