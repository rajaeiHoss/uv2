package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzbkt extends zzev implements zzbkr {
    zzbkt(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.contextmanager.fence.internal.IContextFenceListener");
    }

    public final void zza(zzbkj zzbkj) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbkj);
        zzb(2, zzbc);
    }

    public final void zza(zzbkn zzbkn) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbkn);
        zzb(1, zzbc);
    }
}
