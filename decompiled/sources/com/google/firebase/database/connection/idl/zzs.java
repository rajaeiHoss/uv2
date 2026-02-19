package com.google.firebase.database.connection.idl;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zzs extends zzev implements zzq {
    zzs(IBinder iBinder) {
        super(iBinder, "com.google.firebase.database.connection.idl.IListenHashProvider");
    }

    public final String zzbwq() throws RemoteException {
        Parcel zza = zza(1, zzbc());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final boolean zzbwr() throws RemoteException {
        Parcel zza = zza(2, zzbc());
        boolean zza2 = zzex.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final zza zzbxq() throws RemoteException {
        Parcel parcel = zza(3, zzbc());
        zza result = (zza) zzex.zza(parcel, zza.CREATOR);
        parcel.recycle();
        return result;
    }
}
