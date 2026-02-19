package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.zzao;
import com.google.android.gms.fitness.request.zzar;

public final class zzbzh extends zzev implements zzbzg {
    zzbzh(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitSensorsApi");
    }

    public final void zza(DataSourcesRequest dataSourcesRequest) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) dataSourcesRequest);
        zzb(1, zzbc);
    }

    public final void zza(zzao zzao) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzao);
        zzb(2, zzbc);
    }

    public final void zza(zzar zzar) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzar);
        zzb(3, zzbc);
    }
}
