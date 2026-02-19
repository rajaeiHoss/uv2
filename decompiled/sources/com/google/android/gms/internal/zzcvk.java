package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcvk extends zzew implements zzcvj {
    public zzcvk() {
        attachInterface(this, "com.google.android.gms.panorama.internal.IPanoramaCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            return false;
        }
        zza(parcel.readInt(), (Bundle) zzex.zza(parcel, Bundle.CREATOR), parcel.readInt(), (Intent) zzex.zza(parcel, Intent.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
