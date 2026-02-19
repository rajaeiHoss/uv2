package com.google.android.gms.cast.framework.media;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzf extends IInterface {
    int[] getCompactViewActionIndices() throws RemoteException;

    List<NotificationAction> getNotificationActions() throws RemoteException;

    int zzadx() throws RemoteException;

    IObjectWrapper zzafg() throws RemoteException;
}
