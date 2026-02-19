package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzcsf extends zzev implements zzcsd {
    zzcsf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.nearby.internal.connection.IConnectionLifecycleListener");
    }

    public final void zza(zzcsz zzcsz) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzcsz);
        zzc(5, zzbc);
    }

    public final void zza(zzctb zzctb) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzctb);
        zzc(2, zzbc);
    }

    public final void zza(zzcth zzcth) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzcth);
        zzc(3, zzbc);
    }

    public final void zza(zzctj zzctj) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzctj);
        zzc(4, zzbc);
    }
}
