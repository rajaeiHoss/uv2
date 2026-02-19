package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzac extends zzew implements zzab {
    public zzac() {
        attachInterface(this, "com.google.android.gms.cast.framework.ISessionProxy");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                IObjectWrapper zzael = zzael();
                parcel2.writeNoException();
                zzex.zza(parcel2, (IInterface) zzael);
                break;
            case 2:
                start((Bundle) zzex.zza(parcel, Bundle.CREATOR));
                break;
            case 3:
                resume((Bundle) zzex.zza(parcel, Bundle.CREATOR));
                break;
            case 4:
                end(zzex.zza(parcel));
                break;
            case 5:
                long sessionRemainingTimeMs = getSessionRemainingTimeMs();
                parcel2.writeNoException();
                parcel2.writeLong(sessionRemainingTimeMs);
                break;
            case 6:
                zzadx();
                parcel2.writeNoException();
                parcel2.writeInt(12211278);
                break;
            case 7:
                onStarting((Bundle) zzex.zza(parcel, Bundle.CREATOR));
                break;
            case 8:
                onResuming((Bundle) zzex.zza(parcel, Bundle.CREATOR));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
