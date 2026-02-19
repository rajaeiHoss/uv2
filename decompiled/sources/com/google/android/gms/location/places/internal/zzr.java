package com.google.android.gms.location.places.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;

public interface zzr extends IInterface {
    void zza(PlaceFilter placeFilter, zzau zzau, zzx zzx) throws RemoteException;

    void zza(PlaceReport placeReport, zzau zzau, zzx zzx) throws RemoteException;
}
