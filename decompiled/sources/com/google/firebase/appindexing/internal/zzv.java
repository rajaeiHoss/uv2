package com.google.firebase.appindexing.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzca;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zzv extends zzev implements zzu {
    zzv(IBinder iBinder) {
        super(iBinder, "com.google.firebase.appindexing.internal.IAppIndexingService");
    }

    public final void zza(zzca zzca) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzca);
        zzb(3, zzbc);
    }

    public final void zza(zzca zzca, Thing[] thingArr) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzca);
        zzbc.writeTypedArray(thingArr, 0);
        zzb(1, zzbc);
    }

    public final void zza(zzca zzca, String[] strArr) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzca);
        zzbc.writeStringArray(strArr);
        zzb(2, zzbc);
    }
}
