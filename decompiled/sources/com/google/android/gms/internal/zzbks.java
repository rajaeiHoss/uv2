package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzbks extends zzew implements zzbkr {
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zza((zzbkn) zzex.zza(parcel, zzbkn.CREATOR));
        } else if (i != 2) {
            return false;
        } else {
            zza((zzbkj) zzex.zza(parcel, zzbkj.CREATOR));
        }
        parcel2.writeNoException();
        return true;
    }
}
