package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzi extends zzew implements zzh {
    public zzi() {
        attachInterface(this, "com.google.android.gms.cast.framework.ICastConnectionController");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            zzq(parcel.readString(), parcel.readString());
        } else if (i == 2) {
            zza(parcel.readString(), (LaunchOptions) zzex.zza(parcel, LaunchOptions.CREATOR));
        } else if (i == 3) {
            zzfp(parcel.readString());
        } else if (i == 4) {
            zzbe(parcel.readInt());
        } else if (i != 5) {
            return false;
        } else {
            zzadx();
            parcel2.writeNoException();
            parcel2.writeInt(12211278);
            return true;
        }
        parcel2.writeNoException();
        return true;
    }
}
