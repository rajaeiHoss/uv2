package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzir extends zzev implements zziq {
    zzir(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.cache.ICacheService");
    }

    public final zzik zza(zzin zzin) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzin);
        Parcel zza = zza(1, zzbc);
        zzik zzikVar = (zzik) zzex.zza(zza, zzik.CREATOR);
        zza.recycle();
        return zzikVar;
    }
}
