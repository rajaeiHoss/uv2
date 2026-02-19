package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public abstract class zzepu extends zzew implements zzept {
    public zzepu() {
        attachInterface(this, "com.google.firebase.dynamiclinks.internal.IDynamicLinksCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zza((Status) zzex.zza(parcel, Status.CREATOR), (zzepi) zzex.zza(parcel, zzepi.CREATOR));
        } else if (i != 2) {
            return false;
        } else {
            zza((Status) zzex.zza(parcel, Status.CREATOR), (zzepx) zzex.zza(parcel, zzepx.CREATOR));
        }
        return true;
    }
}
