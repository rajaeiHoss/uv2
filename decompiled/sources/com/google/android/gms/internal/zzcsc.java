package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzcsc extends zzev implements zzcsa {
    zzcsc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.nearby.internal.connection.IConnectionEventListener");
    }

    public final void zza(zzctj zzctj) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzctj);
        zzc(3, zzbc);
    }

    public final void zza(zzctp zzctp) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzctp);
        zzc(2, zzbc);
    }

    public final void zza(zzctr zzctr) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzctr);
        zzc(4, zzbc);
    }
}
