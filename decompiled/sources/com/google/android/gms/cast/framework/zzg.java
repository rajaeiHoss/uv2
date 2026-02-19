package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzg extends zzew implements zzf {
    public zzg() {
        attachInterface(this, "com.google.android.gms.cast.framework.IAppVisibilityListener");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            if (i == 2) {
                onAppEnteredForeground();
            } else if (i == 3) {
                onAppEnteredBackground();
            } else if (i != 4) {
                return false;
            } else {
                zzadx();
                parcel2.writeNoException();
                parcel2.writeInt(12211278);
            }
            parcel2.writeNoException();
        } else {
            IObjectWrapper zzady = zzady();
            parcel2.writeNoException();
            zzex.zza(parcel2, (IInterface) zzady);
        }
        return true;
    }
}
