package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzdaf extends zzew implements zzdae {
    public zzdaf() {
        attachInterface(this, "com.google.android.gms.tagmanager.internal.ITagManagerLoadContainerCallback");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            return false;
        }
        zza(zzex.zza(parcel), parcel.readString());
        return true;
    }
}
