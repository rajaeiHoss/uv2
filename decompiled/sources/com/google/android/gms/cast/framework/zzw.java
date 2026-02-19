package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zzw extends zzev implements zzv {
    zzw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.ISessionManager");
    }

    public final int getCastState() throws RemoteException {
        Parcel zza = zza(8, zzbc());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    public final void zza(zzn zzn) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzn);
        zzb(4, zzbc);
    }

    public final void zza(zzx zzx) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzx);
        zzb(2, zzbc);
    }

    public final IObjectWrapper zzaei() throws RemoteException {
        Parcel zza = zza(7, zzbc());
        IObjectWrapper zzaq = IObjectWrapper.zza.zzaq(zza.readStrongBinder());
        zza.recycle();
        return zzaq;
    }

    public final IObjectWrapper zzaek() throws RemoteException {
        Parcel zza = zza(1, zzbc());
        IObjectWrapper zzaq = IObjectWrapper.zza.zzaq(zza.readStrongBinder());
        zza.recycle();
        return zzaq;
    }

    public final void zzb(zzn zzn) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzn);
        zzb(5, zzbc);
    }

    public final void zzb(zzx zzx) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzx);
        zzb(3, zzbc);
    }

    public final void zzb(boolean z, boolean z2) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, true);
        zzex.zza(zzbc, z2);
        zzb(6, zzbc);
    }

    public final void zzh(Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) bundle);
        zzb(9, zzbc);
    }
}
