package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzbjm extends zzev implements zzbjl {
    zzbjm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.config.internal.IConfigService");
    }

    public final void zza(zzbjj zzbjj, zzbjf zzbjf) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzbjj);
        zzex.zza(zzbc, (Parcelable) zzbjf);
        zzb(8, zzbc);
    }
}
