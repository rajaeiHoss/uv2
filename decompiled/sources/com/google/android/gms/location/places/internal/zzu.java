package com.google.android.gms.location.places.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;

public final class zzu extends zzev implements zzt {
    zzu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.places.internal.IGooglePlacesService");
    }

    public final void zza(AddPlaceRequest addPlaceRequest, zzau zzau, zzx zzx) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) addPlaceRequest);
        zzex.zza(zzbc, (Parcelable) zzau);
        zzex.zza(zzbc, (IInterface) zzx);
        zzb(14, zzbc);
    }

    public final void zza(String str, int i, int i2, int i3, zzau zzau, zzv zzv) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeInt(i);
        zzbc.writeInt(i2);
        zzbc.writeInt(i3);
        zzex.zza(zzbc, (Parcelable) zzau);
        zzex.zza(zzbc, (IInterface) zzv);
        zzb(20, zzbc);
    }

    public final void zza(String str, zzau zzau, zzv zzv) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (Parcelable) zzau);
        zzex.zza(zzbc, (IInterface) zzv);
        zzb(19, zzbc);
    }

    public final void zza(String str, LatLngBounds latLngBounds, int i, AutocompleteFilter autocompleteFilter, zzau zzau, zzx zzx) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (Parcelable) latLngBounds);
        zzbc.writeInt(i);
        zzex.zza(zzbc, (Parcelable) autocompleteFilter);
        zzex.zza(zzbc, (Parcelable) zzau);
        zzex.zza(zzbc, (IInterface) zzx);
        zzb(28, zzbc);
    }

    public final void zza(List<String> list, zzau zzau, zzx zzx) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeStringList(list);
        zzex.zza(zzbc, (Parcelable) zzau);
        zzex.zza(zzbc, (IInterface) zzx);
        zzb(17, zzbc);
    }
}
