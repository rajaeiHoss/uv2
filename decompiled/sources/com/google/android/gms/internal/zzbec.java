package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzab;

public final class zzbec extends zzev implements zzbeb {
    zzbec(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.internal.ICastDeviceController");
    }

    public final void disconnect() throws RemoteException {
        zzc(1, zzbc());
    }

    public final void requestStatus() throws RemoteException {
        zzc(6, zzbc());
    }

    public final void zza(double d, double d2, boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeDouble(d);
        zzbc.writeDouble(d2);
        zzex.zza(zzbc, z);
        zzc(7, zzbc);
    }

    public final void zza(String str, String str2, zzab zzab) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzex.zza(zzbc, (Parcelable) zzab);
        zzc(14, zzbc);
    }

    public final void zza(boolean z, double d, boolean z2) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, z);
        zzbc.writeDouble(d);
        zzex.zza(zzbc, z2);
        zzc(8, zzbc);
    }

    public final void zzagx() throws RemoteException {
        zzc(4, zzbc());
    }

    public final void zzb(String str, LaunchOptions launchOptions) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzex.zza(zzbc, (Parcelable) launchOptions);
        zzc(13, zzbc);
    }

    public final void zzb(String str, String str2, long j) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzbc.writeLong(j);
        zzc(9, zzbc);
    }

    public final void zzfp(String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzc(5, zzbc);
    }

    public final void zzfy(String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzc(11, zzbc);
    }

    public final void zzfz(String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzc(12, zzbc);
    }
}
