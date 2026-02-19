package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzev;
import java.util.ArrayList;
import java.util.List;

public final class zzh extends zzev implements zzf {
    zzh(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.media.INotificationActionsProvider");
    }

    public final int[] getCompactViewActionIndices() throws RemoteException {
        Parcel zza = zza(4, zzbc());
        int[] createIntArray = zza.createIntArray();
        zza.recycle();
        return createIntArray;
    }

    public final List<NotificationAction> getNotificationActions() throws RemoteException {
        Parcel zza = zza(3, zzbc());
        ArrayList<NotificationAction> createTypedArrayList = zza.createTypedArrayList(NotificationAction.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    public final int zzadx() throws RemoteException {
        Parcel zza = zza(1, zzbc());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    public final IObjectWrapper zzafg() throws RemoteException {
        Parcel zza = zza(2, zzbc());
        IObjectWrapper zzaq = IObjectWrapper.zza.zzaq(zza.readStrongBinder());
        zza.recycle();
        return zzaq;
    }
}
