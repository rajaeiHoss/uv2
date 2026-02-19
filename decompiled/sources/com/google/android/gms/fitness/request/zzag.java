package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zzag extends zzev implements zzae {
    zzag(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.request.IBleScanCallback");
    }

    public final void onDeviceFound(BleDevice bleDevice) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) bleDevice);
        zzc(1, zzbc);
    }

    public final void onScanStopped() throws RemoteException {
        zzc(2, zzbc());
    }
}
