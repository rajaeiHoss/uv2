package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.zzaj;
import com.google.android.gms.fitness.request.zzbj;
import com.google.android.gms.fitness.request.zzbn;

public final class zzbzf extends zzev implements zzbze {
    zzbzf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitRecordingApi");
    }

    public final void zza(zzaj zzaj) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzaj);
        zzb(3, zzbc);
    }

    public final void zza(zzbj zzbj) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbj);
        zzb(1, zzbc);
    }

    public final void zza(zzbn zzbn) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbn);
        zzb(2, zzbc);
    }
}
