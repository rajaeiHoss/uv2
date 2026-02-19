package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzcxx extends zzev implements zzcxw {
    zzcxx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.search.internal.ISearchAuthService");
    }

    public final void zza(zzcxu zzcxu, String str, String str2) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzcxu);
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzb(1, zzbc);
    }

    public final void zzb(zzcxu zzcxu, String str, String str2) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzcxu);
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzb(2, zzbc);
    }
}
