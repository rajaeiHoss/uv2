package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzcsn extends zzev implements zzcsl {
    zzcsn(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.nearby.internal.connection.IDiscoveryListener");
    }

    public final void zza(zzctl zzctl) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzctl);
        zzc(2, zzbc);
    }

    public final void zza(zzctn zzctn) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzctn);
        zzc(3, zzbc);
    }

    public final void zza(zzctx zzctx) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzctx);
        zzc(4, zzbc);
    }
}
