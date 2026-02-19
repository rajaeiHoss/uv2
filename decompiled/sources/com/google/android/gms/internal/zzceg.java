package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.instantapps.zzi;

public final class zzceg extends zzev implements zzcef {
    zzceg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.instantapps.internal.IInstantAppsService");
    }

    public final void zza(zzced zzced) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzced);
        zzb(29, zzbc);
    }

    public final void zza(zzced zzced, String str, zzi zzi) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzced);
        zzbc.writeString(str);
        zzex.zza(zzbc, (Parcelable) zzi);
        zzb(19, zzbc);
    }
}
