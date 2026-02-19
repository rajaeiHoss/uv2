package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzn extends zzew implements zzm {
    public zzn() {
        attachInterface(this, "com.google.android.gms.nearby.messages.internal.IMessageListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zza((zzaf) zzex.zza(parcel, zzaf.CREATOR));
        } else if (i == 2) {
            zzb((zzaf) zzex.zza(parcel, zzaf.CREATOR));
        } else if (i != 4) {
            return false;
        } else {
            zzaj(parcel.createTypedArrayList(Update.CREATOR));
        }
        return true;
    }
}
