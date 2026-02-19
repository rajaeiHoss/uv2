package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import java.util.Map;

public abstract class zzbjk extends zzew implements zzbjj {
    public zzbjk() {
        attachInterface(this, "com.google.android.gms.config.internal.IConfigCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zza((Status) zzex.zza(parcel, Status.CREATOR), parcel.createByteArray());
        } else if (i == 2) {
            zza((Status) zzex.zza(parcel, Status.CREATOR), (Map) zzex.zzc(parcel));
        } else if (i == 3) {
            zzaa((Status) zzex.zza(parcel, Status.CREATOR));
        } else if (i != 4) {
            return false;
        } else {
            zza((Status) zzex.zza(parcel, Status.CREATOR), (zzbjh) zzex.zza(parcel, zzbjh.CREATOR));
        }
        return true;
    }
}
