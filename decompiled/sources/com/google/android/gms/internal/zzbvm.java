package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public abstract class zzbvm extends zzew implements zzbvl {
    public zzbvm() {
        attachInterface(this, "com.google.android.gms.fido.fido2.internal.privileged.IFido2PrivilegedCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            return false;
        }
        zza((Status) zzex.zza(parcel, Status.CREATOR), (PendingIntent) zzex.zza(parcel, PendingIntent.CREATOR));
        return true;
    }
}
