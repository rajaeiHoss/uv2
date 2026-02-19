package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcsr extends zzew implements zzcsq {
    public zzcsr() {
        attachInterface(this, "com.google.android.gms.nearby.internal.connection.IPayloadListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 2) {
            zza((zzctp) zzex.zza(parcel, zzctp.CREATOR));
        } else if (i != 3) {
            return false;
        } else {
            zza((zzctr) zzex.zza(parcel, zzctr.CREATOR));
        }
        return true;
    }
}
