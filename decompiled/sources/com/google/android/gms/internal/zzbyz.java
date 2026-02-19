package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.request.zzaa;
import com.google.android.gms.fitness.request.zzs;

public final class zzbyz extends zzev implements zzbyy {
    zzbyz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitConfigApi");
    }

    public final void zza(DataTypeCreateRequest dataTypeCreateRequest) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) dataTypeCreateRequest);
        zzb(1, zzbc);
    }

    public final void zza(zzaa zzaa) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzaa);
        zzb(22, zzbc);
    }

    public final void zza(zzs zzs) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzs);
        zzb(2, zzbc);
    }
}
