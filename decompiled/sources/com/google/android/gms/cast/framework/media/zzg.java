package com.google.android.gms.cast.framework.media;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;
import java.util.List;

public abstract class zzg extends zzew implements zzf {
    public zzg() {
        attachInterface(this, "com.google.android.gms.cast.framework.media.INotificationActionsProvider");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 1) {
            int zzadx = zzadx();
            parcel2.writeNoException();
            parcel2.writeInt(zzadx);
        } else if (i == 2) {
            IObjectWrapper zzafg = zzafg();
            parcel2.writeNoException();
            zzex.zza(parcel2, (IInterface) zzafg);
        } else if (i == 3) {
            List<NotificationAction> notificationActions = getNotificationActions();
            parcel2.writeNoException();
            parcel2.writeTypedList(notificationActions);
        } else if (i != 4) {
            return false;
        } else {
            int[] compactViewActionIndices = getCompactViewActionIndices();
            parcel2.writeNoException();
            parcel2.writeIntArray(compactViewActionIndices);
        }
        return true;
    }
}
