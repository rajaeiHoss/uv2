package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcry extends zzew implements zzcrx {
    public zzcry() {
        attachInterface(this, "com.google.android.gms.nearby.internal.connection.IAdvertisingCallback");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 2) {
            zza((zzctd) zzex.zza(parcel, zzctd.CREATOR));
        } else if (i != 3) {
            return false;
        } else {
            zza((zzctv) zzex.zza(parcel, zzctv.CREATOR));
        }
        return true;
    }
}
