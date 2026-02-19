package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzaa extends zzew implements zzz {
    public zzaa() {
        attachInterface(this, "com.google.android.gms.cast.framework.ISessionProvider");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            IObjectWrapper zzfq = zzfq(parcel.readString());
            parcel2.writeNoException();
            zzex.zza(parcel2, (IInterface) zzfq);
        } else if (i == 2) {
            boolean isSessionRecoverable = isSessionRecoverable();
            parcel2.writeNoException();
            zzex.zza(parcel2, isSessionRecoverable);
        } else if (i == 3) {
            String category = getCategory();
            parcel2.writeNoException();
            parcel2.writeString(category);
        } else if (i != 4) {
            return false;
        } else {
            zzadx();
            parcel2.writeNoException();
            parcel2.writeInt(12211278);
        }
        return true;
    }
}
