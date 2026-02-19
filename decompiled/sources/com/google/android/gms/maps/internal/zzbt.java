package com.google.android.gms.maps.internal;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzbt extends zzew implements zzbs {
    public zzbt() {
        attachInterface(this, "com.google.android.gms.maps.internal.ISnapshotReadyCallback");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            onSnapshotReady((Bitmap) zzex.zza(parcel, Bitmap.CREATOR));
        } else if (i != 2) {
            return false;
        } else {
            zzaa(IObjectWrapper.zza.zzaq(parcel.readStrongBinder()));
        }
        parcel2.writeNoException();
        return true;
    }
}
