package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionStopResult;

public abstract class zzbzr extends zzew implements zzbzq {
    public zzbzr() {
        attachInterface(this, "com.google.android.gms.fitness.internal.ISessionStopCallback");
    }

    public static zzbzq zzaz(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.ISessionStopCallback");
        return queryLocalInterface instanceof zzbzq ? (zzbzq) queryLocalInterface : new zzbzs(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            return false;
        }
        zza((SessionStopResult) zzex.zza(parcel, SessionStopResult.CREATOR));
        return true;
    }
}
