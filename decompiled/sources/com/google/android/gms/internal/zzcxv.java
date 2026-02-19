package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.search.GoogleNowAuthState;

public abstract class zzcxv extends zzew implements zzcxu {
    public zzcxv() {
        attachInterface(this, "com.google.android.gms.search.internal.ISearchAuthCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zza((Status) zzex.zza(parcel, Status.CREATOR), (GoogleNowAuthState) zzex.zza(parcel, GoogleNowAuthState.CREATOR));
        } else if (i != 2) {
            return false;
        } else {
            zzar((Status) zzex.zza(parcel, Status.CREATOR));
        }
        return true;
    }
}
