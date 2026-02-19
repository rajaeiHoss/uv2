package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public abstract class zzavn extends zzew implements zzavm {
    public zzavn() {
        attachInterface(this, "com.google.android.gms.appinvite.internal.IAppInviteCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zzc((Status) zzex.zza(parcel, Status.CREATOR));
        } else if (i != 2) {
            return false;
        } else {
            zza((Status) zzex.zza(parcel, Status.CREATOR), (Intent) zzex.zza(parcel, Intent.CREATOR));
        }
        parcel2.writeNoException();
        return true;
    }
}
