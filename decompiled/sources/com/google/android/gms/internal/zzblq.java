package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzblq extends zzev implements zzblp {
    zzblq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.contextmanager.internal.IContextManagerService");
    }

    public final void zza(zzbln zzbln, String str, String str2, String str3, zzazw zzazw) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzbln);
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzbc.writeString(str3);
        zzex.zza(zzbc, (Parcelable) zzazw);
        zzb(15, zzbc);
    }

    public final void zza(zzbln zzbln, String str, String str2, String str3, zzbkg zzbkg) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzbln);
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzbc.writeString(str3);
        zzex.zza(zzbc, (Parcelable) zzbkg);
        zzb(16, zzbc);
    }

    public final void zza(zzbln zzbln, String str, String str2, String str3, zzbkp zzbkp) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzbln);
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzbc.writeString(str3);
        zzex.zza(zzbc, (Parcelable) zzbkp);
        zzb(13, zzbc);
    }
}
