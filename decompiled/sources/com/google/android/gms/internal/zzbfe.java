package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;

public interface zzbfe extends IInterface {
    void disconnect() throws RemoteException;

    void zza(zzbfc zzbfc) throws RemoteException;

    void zza(zzbfc zzbfc, int i) throws RemoteException;

    void zza(zzbfc zzbfc, PendingIntent pendingIntent, String str, String str2, Bundle bundle) throws RemoteException;

    void zza(zzbfc zzbfc, zzbfg zzbfg, String str, String str2, Bundle bundle) throws RemoteException;
}
