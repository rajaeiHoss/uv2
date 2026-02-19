package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzavp extends zzev implements zzavo {
    zzavp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.appinvite.internal.IAppInviteService");
    }

    public final void zza(zzavm zzavm) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzavm);
        zzb(3, zzbc);
    }

    public final void zza(zzavm zzavm, String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzavm);
        zzbc.writeString(str);
        zzb(1, zzbc);
    }

    public final void zzb(zzavm zzavm, String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzavm);
        zzbc.writeString(str);
        zzb(2, zzbc);
    }
}
