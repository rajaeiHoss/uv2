package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcse extends zzew implements zzcsd {
    public zzcse() {
        attachInterface(this, "com.google.android.gms.nearby.internal.connection.IConnectionLifecycleListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 2) {
            zza((zzctb) zzex.zza(parcel, zzctb.CREATOR));
        } else if (i == 3) {
            zza((zzcth) zzex.zza(parcel, zzcth.CREATOR));
        } else if (i == 4) {
            zza((zzctj) zzex.zza(parcel, zzctj.CREATOR));
        } else if (i != 5) {
            return false;
        } else {
            zza((zzcsz) zzex.zza(parcel, zzcsz.CREATOR));
        }
        return true;
    }
}
