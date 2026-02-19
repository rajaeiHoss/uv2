package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcsm extends zzew implements zzcsl {
    public zzcsm() {
        attachInterface(this, "com.google.android.gms.nearby.internal.connection.IDiscoveryListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 2) {
            zza((zzctl) zzex.zza(parcel, zzctl.CREATOR));
        } else if (i == 3) {
            zza((zzctn) zzex.zza(parcel, zzctn.CREATOR));
        } else if (i != 4) {
            return false;
        } else {
            zza((zzctx) zzex.zza(parcel, zzctx.CREATOR));
        }
        return true;
    }
}
