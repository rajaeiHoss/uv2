package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzbrp extends zzew implements zzbro {
    public zzbrp() {
        attachInterface(this, "com.google.android.gms.drive.internal.IEventCallback");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            return false;
        }
        zzc((zzbsf) zzex.zza(parcel, zzbsf.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
