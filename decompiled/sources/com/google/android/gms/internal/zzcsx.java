package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcsx extends zzew implements zzcsw {
    public zzcsx() {
        attachInterface(this, "com.google.android.gms.nearby.internal.connection.IStartAdvertisingResultListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 2) {
            return false;
        }
        zza((zzctt) zzex.zza(parcel, zzctt.CREATOR));
        return true;
    }
}
