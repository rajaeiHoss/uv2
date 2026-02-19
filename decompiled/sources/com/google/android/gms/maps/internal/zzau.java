package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.maps.model.internal.zzq;

public abstract class zzau extends zzew implements zzat {
    public zzau() {
        attachInterface(this, "com.google.android.gms.maps.internal.IOnMarkerDragListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zzb(zzq.zzbm(parcel.readStrongBinder()));
        } else if (i == 2) {
            zzd(zzq.zzbm(parcel.readStrongBinder()));
        } else if (i != 3) {
            return false;
        } else {
            zzc(zzq.zzbm(parcel.readStrongBinder()));
        }
        parcel2.writeNoException();
        return true;
    }
}
