package com.google.android.gms.tagmanager;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzcf extends zzew implements zzce {
    public zzcf() {
        attachInterface(this, "com.google.android.gms.tagmanager.ICustomEvaluatorProxy");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zzf(parcel.readString(), zzex.zzc(parcel));
            parcel2.writeNoException();
        } else if (i != 2) {
            return false;
        } else {
            String zzg = zzg(parcel.readString(), zzex.zzc(parcel));
            parcel2.writeNoException();
            parcel2.writeString(zzg);
        }
        return true;
    }
}
