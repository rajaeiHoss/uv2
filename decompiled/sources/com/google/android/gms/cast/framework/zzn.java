package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzn extends IInterface {
    void onCastStateChanged(int i) throws RemoteException;

    int zzadx() throws RemoteException;

    IObjectWrapper zzady() throws RemoteException;
}
