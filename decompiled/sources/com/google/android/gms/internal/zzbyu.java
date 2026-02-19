package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.GoalsResult;

public abstract class zzbyu extends zzew implements zzbyt {
    public zzbyu() {
        attachInterface(this, "com.google.android.gms.fitness.internal.IGoalsReadCallback");
    }

    public static zzbyt zzaw(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoalsReadCallback");
        return queryLocalInterface instanceof zzbyt ? (zzbyt) queryLocalInterface : new zzbyv(iBinder);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            return false;
        }
        zza((GoalsResult) zzex.zza(parcel, GoalsResult.CREATOR));
        return true;
    }
}
