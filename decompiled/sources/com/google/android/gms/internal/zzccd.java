package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.service.FitnessSensorServiceRequest;

public abstract class zzccd extends zzew implements zzccc {
    public zzccd() {
        attachInterface(this, "com.google.android.gms.fitness.internal.service.IFitnessSensorService");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zza((zzcby) zzex.zza(parcel, zzcby.CREATOR), zzbyo.zzau(parcel.readStrongBinder()));
        } else if (i == 2) {
            zza((FitnessSensorServiceRequest) zzex.zza(parcel, FitnessSensorServiceRequest.CREATOR), zzbzu.zzba(parcel.readStrongBinder()));
        } else if (i != 3) {
            return false;
        } else {
            zza((zzcca) zzex.zza(parcel, zzcca.CREATOR), zzbzu.zzba(parcel.readStrongBinder()));
        }
        return true;
    }
}
