package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;

public abstract class zzd extends zzew implements zzc {
    public zzd() {
        attachInterface(this, "com.google.android.gms.maps.internal.ICancelableCallback");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            onFinish();
        } else if (i != 2) {
            return false;
        } else {
            onCancel();
        }
        parcel2.writeNoException();
        return true;
    }
}
