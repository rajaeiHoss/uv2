package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzz extends IInterface {
    String getCategory() throws RemoteException;

    boolean isSessionRecoverable() throws RemoteException;

    int zzadx() throws RemoteException;

    IObjectWrapper zzfq(String str) throws RemoteException;
}
