package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;

public abstract class zzbfd extends zzew implements zzbfc {
    public zzbfd() {
        attachInterface(this, "com.google.android.gms.cast.remote_display.ICastRemoteDisplayCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zza(parcel.readInt(), parcel.readInt(), (Surface) zzex.zza(parcel, Surface.CREATOR));
        } else if (i == 2) {
            onError(parcel.readInt());
        } else if (i == 3) {
            onDisconnected();
        } else if (i != 4) {
            return false;
        } else {
            zzado();
        }
        parcel2.writeNoException();
        return true;
    }
}
