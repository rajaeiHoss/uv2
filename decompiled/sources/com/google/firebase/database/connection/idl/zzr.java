package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzr extends zzew implements zzq {
    public zzr() {
        attachInterface(this, "com.google.firebase.database.connection.idl.IListenHashProvider");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            String zzbwq = zzbwq();
            parcel2.writeNoException();
            parcel2.writeString(zzbwq);
        } else if (i == 2) {
            boolean zzbwr = zzbwr();
            parcel2.writeNoException();
            zzex.zza(parcel2, zzbwr);
        } else if (i != 3) {
            return false;
        } else {
            zza zzbxq = zzbxq();
            parcel2.writeNoException();
            zzex.zzb(parcel2, zzbxq);
        }
        return true;
    }
}
