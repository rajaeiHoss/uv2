package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.BleDevicesResult;

public abstract class zzcbv extends zzew implements zzcbu {
    public zzcbv() {
        attachInterface(this, "com.google.android.gms.fitness.internal.ble.IBleDevicesCallback");
    }

    public static zzcbu zzbb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.ble.IBleDevicesCallback");
        return queryLocalInterface instanceof zzcbu ? (zzcbu) queryLocalInterface : new zzcbw(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            return false;
        }
        zza((BleDevicesResult) zzex.zza(parcel, BleDevicesResult.CREATOR));
        return true;
    }
}
