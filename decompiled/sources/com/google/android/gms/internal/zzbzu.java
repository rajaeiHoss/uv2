package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public abstract class zzbzu extends zzew implements zzbzt {
    public zzbzu() {
        attachInterface(this, "com.google.android.gms.fitness.internal.IStatusCallback");
    }

    public static zzbzt zzba(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IStatusCallback");
        return queryLocalInterface instanceof zzbzt ? (zzbzt) queryLocalInterface : new zzbzv(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            return false;
        }
        zzn((Status) zzex.zza(parcel, Status.CREATOR));
        return true;
    }
}
