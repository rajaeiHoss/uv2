package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public abstract class zzauv extends zzew implements zzauu {
    public zzauv() {
        attachInterface(this, "com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zza((Status) zzex.zza(parcel, Status.CREATOR));
        } else if (i == 2) {
            zza((Status) zzex.zza(parcel, Status.CREATOR), (ParcelFileDescriptor) zzex.zza(parcel, ParcelFileDescriptor.CREATOR));
        } else if (i == 3) {
            zza((Status) zzex.zza(parcel, Status.CREATOR), zzex.zza(parcel));
        } else if (i != 4) {
            return false;
        } else {
            zza((zzaug) zzex.zza(parcel, zzaug.CREATOR));
        }
        return true;
    }
}
