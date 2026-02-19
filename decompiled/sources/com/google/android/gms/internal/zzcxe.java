package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzcxe extends zzev implements zzcxd {
    zzcxe(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.safetynet.internal.ISafetyNetService");
    }

    public final void zza(zzcxb zzcxb) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzcxb);
        zzb(12, zzbc);
    }

    public final void zza(zzcxb zzcxb, String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzcxb);
        zzbc.writeString(str);
        zzb(6, zzbc);
    }

    public final void zza(zzcxb zzcxb, String str, int[] iArr, int i, String str2) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzcxb);
        zzbc.writeString(str);
        zzbc.writeIntArray(iArr);
        zzbc.writeInt(i);
        zzbc.writeString(str2);
        zzb(3, zzbc);
    }

    public final void zza(zzcxb zzcxb, byte[] bArr, String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzcxb);
        zzbc.writeByteArray(bArr);
        zzbc.writeString(str);
        zzb(7, zzbc);
    }

    public final void zzb(zzcxb zzcxb) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzcxb);
        zzb(14, zzbc);
    }

    public final void zzber() throws RemoteException {
        zzb(13, zzbc());
    }

    public final void zzc(zzcxb zzcxb) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzcxb);
        zzb(4, zzbc);
    }

    public final void zzd(zzcxb zzcxb) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzcxb);
        zzb(5, zzbc);
    }
}
