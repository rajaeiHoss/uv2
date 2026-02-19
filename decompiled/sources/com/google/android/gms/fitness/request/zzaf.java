package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzaf extends zzew implements zzae {
    public zzaf() {
        attachInterface(this, "com.google.android.gms.fitness.request.IBleScanCallback");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            onDeviceFound((BleDevice) zzex.zza(parcel, BleDevice.CREATOR));
        } else if (i != 2) {
            return false;
        } else {
            onScanStopped();
        }
        return true;
    }
}
