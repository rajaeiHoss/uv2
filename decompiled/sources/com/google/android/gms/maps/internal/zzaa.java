package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.maps.model.internal.zzk;

public abstract class zzaa extends zzew implements zzz {
    public zzaa() {
        attachInterface(this, "com.google.android.gms.maps.internal.IOnIndoorStateChangeListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            onIndoorBuildingFocused();
        } else if (i != 2) {
            return false;
        } else {
            zza(zzk.zzbk(parcel.readStrongBinder()));
        }
        parcel2.writeNoException();
        return true;
    }
}
