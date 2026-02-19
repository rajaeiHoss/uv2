package com.google.android.gms.games.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzv extends zzew implements zzu {
    public zzv() {
        attachInterface(this, "com.google.android.gms.games.internal.IGamesClient");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1001) {
            return false;
        }
        zzy zzatc = zzatc();
        parcel2.writeNoException();
        zzex.zzb(parcel2, zzatc);
        return true;
    }
}
