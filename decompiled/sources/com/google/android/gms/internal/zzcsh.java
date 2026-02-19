package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcsh extends zzew implements zzcsg {
    public zzcsh() {
        attachInterface(this, "com.google.android.gms.nearby.internal.connection.IConnectionResponseListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 2) {
            return false;
        }
        zza((zzctf) zzex.zza(parcel, zzctf.CREATOR));
        return true;
    }
}
