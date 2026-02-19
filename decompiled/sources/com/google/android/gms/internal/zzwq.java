package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzwq extends zzev implements zzwo {
    zzwq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.IMediationResponseMetadata");
    }

    public final int zzmn() throws RemoteException {
        Parcel zza = zza(1, zzbc());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }
}
