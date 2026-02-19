package com.google.android.gms.location.places.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;

public final class zzs extends zzev implements zzr {
    zzs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
    }

    public final void zza(PlaceFilter placeFilter, zzau zzau, zzx zzx) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) placeFilter);
        zzex.zza(zzbc, (Parcelable) zzau);
        zzex.zza(zzbc, (IInterface) zzx);
        zzb(6, zzbc);
    }

    public final void zza(PlaceReport placeReport, zzau zzau, zzx zzx) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) placeReport);
        zzex.zza(zzbc, (Parcelable) zzau);
        zzex.zza(zzbc, (IInterface) zzx);
        zzb(7, zzbc);
    }
}
