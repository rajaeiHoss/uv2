package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.maps.model.internal.zzq;

public abstract class zzi extends zzew implements zzh {
    public zzi() {
        attachInterface(this, "com.google.android.gms.maps.internal.IInfoWindowAdapter");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        IObjectWrapper iObjectWrapper;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            iObjectWrapper = zzh(zzq.zzbm(parcel.readStrongBinder()));
        } else if (i != 2) {
            return false;
        } else {
            iObjectWrapper = zzi(zzq.zzbm(parcel.readStrongBinder()));
        }
        parcel2.writeNoException();
        zzex.zza(parcel2, (IInterface) iObjectWrapper);
        return true;
    }
}
