package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzdaj extends zzev implements zzdah {
    zzdaj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.tagmanager.internal.ITagManagerService");
    }

    public final void dispatch() throws RemoteException {
        zzb(102, zzbc());
    }

    public final void zza(String str, Bundle bundle, String str2, long j, boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (Parcelable) bundle);
        zzbc.writeString(str2);
        zzbc.writeLong(j);
        zzex.zza(zzbc, z);
        zzb(101, zzbc);
    }

    public final void zza(String str, String str2, String str3, zzdae zzdae) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzbc.writeString(str3);
        zzex.zza(zzbc, (IInterface) zzdae);
        zzb(2, zzbc);
    }

    public final void zzbiv() throws RemoteException {
        zzb(3, zzbc());
    }

    public final void zzn(String str, String str2, String str3) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzbc.writeString(str3);
        zzb(1, zzbc);
    }
}
