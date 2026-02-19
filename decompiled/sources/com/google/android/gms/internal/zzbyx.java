package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.request.zzah;
import com.google.android.gms.fitness.request.zzbh;
import com.google.android.gms.fitness.request.zzbl;
import com.google.android.gms.fitness.request.zze;

public final class zzbyx extends zzev implements zzbyw {
    zzbyx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitBleApi");
    }

    public final void zza(StartBleScanRequest startBleScanRequest) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) startBleScanRequest);
        zzb(1, zzbc);
    }

    public final void zza(zzah zzah) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzah);
        zzb(5, zzbc);
    }

    public final void zza(zzbh zzbh) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbh);
        zzb(2, zzbc);
    }

    public final void zza(zzbl zzbl) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbl);
        zzb(4, zzbc);
    }

    public final void zza(zze zze) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zze);
        zzb(3, zzbc);
    }
}
