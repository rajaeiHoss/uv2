package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionReadResult;

public abstract class zzbzo extends zzew implements zzbzn {
    public zzbzo() {
        attachInterface(this, "com.google.android.gms.fitness.internal.ISessionReadCallback");
    }

    public static zzbzn zzay(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.ISessionReadCallback");
        return queryLocalInterface instanceof zzbzn ? (zzbzn) queryLocalInterface : new zzbzp(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            return false;
        }
        zza((SessionReadResult) zzex.zza(parcel, SessionReadResult.CREATOR));
        return true;
    }
}
