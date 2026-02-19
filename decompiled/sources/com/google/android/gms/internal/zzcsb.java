package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcsb extends zzew implements zzcsa {
    public zzcsb() {
        attachInterface(this, "com.google.android.gms.nearby.internal.connection.IConnectionEventListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 2) {
            zza((zzctp) zzex.zza(parcel, zzctp.CREATOR));
        } else if (i == 3) {
            zza((zzctj) zzex.zza(parcel, zzctj.CREATOR));
        } else if (i != 4) {
            return false;
        } else {
            zza((zzctr) zzex.zza(parcel, zzctr.CREATOR));
        }
        return true;
    }
}
