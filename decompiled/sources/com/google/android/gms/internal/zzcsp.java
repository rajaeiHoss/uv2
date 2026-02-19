package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.cast.CastStatusCodes;

public final class zzcsp extends zzev implements zzcso {
    zzcsp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
    }

    public final void zza(zzcop zzcop) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzcop);
        zzb(CastStatusCodes.MESSAGE_TOO_LARGE, zzbc);
    }

    public final void zza(zzcor zzcor) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzcor);
        zzb(2012, zzbc);
    }

    public final void zza(zzcot zzcot) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzcot);
        zzb(2011, zzbc);
    }

    public final void zza(zzcrv zzcrv) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzcrv);
        zzb(2009, zzbc);
    }

    public final void zza(zzcue zzcue) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzcue);
        zzb(CastStatusCodes.MESSAGE_SEND_BUFFER_TOO_FULL, zzbc);
    }

    public final void zza(zzcug zzcug) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzcug);
        zzb(CastStatusCodes.APPLICATION_NOT_RUNNING, zzbc);
    }

    public final void zza(zzcui zzcui) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzcui);
        zzb(2008, zzbc);
    }

    public final void zza(zzcuk zzcuk) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzcuk);
        zzb(2001, zzbc);
    }

    public final void zza(zzcum zzcum) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzcum);
        zzb(CastStatusCodes.NOT_ALLOWED, zzbc);
    }

    public final void zza(zzcuo zzcuo) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzcuo);
        zzb(2002, zzbc);
    }

    public final void zza(zzcuq zzcuq) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzcuq);
        zzb(2010, zzbc);
    }

    public final void zza(zzcus zzcus) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzcus);
        zzb(CastStatusCodes.APPLICATION_NOT_FOUND, zzbc);
    }
}
