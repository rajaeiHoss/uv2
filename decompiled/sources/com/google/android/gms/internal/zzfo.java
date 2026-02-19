package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzfo extends zzev implements zzfm {
    zzfo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.clearcut.IClearcut");
    }

    public final void log() throws RemoteException {
        zzb(3, zzbc());
    }

    public final void zza(IObjectWrapper iObjectWrapper, String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzbc.writeString(str);
        zzb(2, zzbc);
    }

    public final void zza(IObjectWrapper iObjectWrapper, String str, String str2) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzbc.writeString(str);
        zzbc.writeString((String) null);
        zzb(8, zzbc);
    }

    public final void zza(int[] iArr) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeIntArray((int[]) null);
        zzb(4, zzbc);
    }

    public final void zzc(byte[] bArr) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeByteArray(bArr);
        zzb(5, zzbc);
    }

    public final void zzg(int i) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeInt(i);
        zzb(6, zzbc);
    }

    public final void zzh(int i) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeInt(i);
        zzb(7, zzbc);
    }
}
