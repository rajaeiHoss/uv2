package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import java.util.Map;

public interface zzbjj extends IInterface {
    void zza(Status status, zzbjh zzbjh) throws RemoteException;

    void zza(Status status, Map map) throws RemoteException;

    void zza(Status status, byte[] bArr) throws RemoteException;

    void zzaa(Status status) throws RemoteException;
}
