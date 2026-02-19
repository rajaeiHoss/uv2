package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzbff extends zzev implements zzbfe {
    zzbff(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
    }

    public final void disconnect() throws RemoteException {
        zzc(3, zzbc());
    }

    public final void zza(zzbfc zzbfc) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzbfc);
        zzc(6, zzbc);
    }

    public final void zza(zzbfc zzbfc, int i) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzbfc);
        zzbc.writeInt(i);
        zzc(5, zzbc);
    }

    public final void zza(zzbfc zzbfc, PendingIntent pendingIntent, String str, String str2, Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzbfc);
        zzex.zza(zzbc, (Parcelable) pendingIntent);
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzex.zza(zzbc, (Parcelable) bundle);
        zzc(8, zzbc);
    }

    public final void zza(zzbfc zzbfc, zzbfg zzbfg, String str, String str2, Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzbfc);
        zzex.zza(zzbc, (IInterface) zzbfg);
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzex.zza(zzbc, (Parcelable) bundle);
        zzc(7, zzbc);
    }
}
