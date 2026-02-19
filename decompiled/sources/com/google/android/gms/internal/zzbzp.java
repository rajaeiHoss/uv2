package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionReadResult;

public final class zzbzp extends zzev implements zzbzn {
    zzbzp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.ISessionReadCallback");
    }

    public final void zza(SessionReadResult sessionReadResult) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) sessionReadResult);
        zzc(1, zzbc);
    }
}
