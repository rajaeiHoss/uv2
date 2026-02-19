package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzbam extends zzev implements zzbal {
    zzbam(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.internal.IMediaRouterCallback");
    }

    public final void zza(String str, Bundle bundle, int i) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (Parcelable) bundle);
        zzbc.writeInt(i);
        zzb(6, zzbc);
    }

    public final void zzc(String str, Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (Parcelable) bundle);
        zzb(1, zzbc);
    }

    public final void zzd(String str, Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (Parcelable) bundle);
        zzb(2, zzbc);
    }

    public final void zze(String str, Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (Parcelable) bundle);
        zzb(3, zzbc);
    }

    public final void zzf(String str, Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (Parcelable) bundle);
        zzb(4, zzbc);
    }
}
