package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzy extends zzew implements zzx {
    public zzy() {
        attachInterface(this, "com.google.android.gms.location.places.internal.IPlacesCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zzbk((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
        } else if (i == 2) {
            zzbl((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
        } else if (i == 3) {
            zzbm((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
        } else if (i == 4) {
            zzao((Status) zzex.zza(parcel, Status.CREATOR));
        } else if (i != 5) {
            return false;
        } else {
            zzbn((DataHolder) zzex.zza(parcel, DataHolder.CREATOR));
        }
        return true;
    }
}
