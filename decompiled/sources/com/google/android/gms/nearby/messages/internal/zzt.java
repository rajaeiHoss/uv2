package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zzt extends zzev implements zzs {
    zzt(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.nearby.messages.internal.INearbyMessagesService");
    }

    public final void zza(SubscribeRequest subscribeRequest) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) subscribeRequest);
        zzc(3, zzbc);
    }

    public final void zza(zzbz zzbz) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzbz);
        zzc(1, zzbc);
    }

    public final void zza(zzcb zzcb) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzcb);
        zzc(8, zzbc);
    }

    public final void zza(zzce zzce) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzce);
        zzc(2, zzbc);
    }

    public final void zza(zzcg zzcg) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzcg);
        zzc(4, zzbc);
    }

    public final void zza(zzh zzh) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzh);
        zzc(7, zzbc);
    }

    public final void zza(zzj zzj) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzj);
        zzc(9, zzbc);
    }
}
