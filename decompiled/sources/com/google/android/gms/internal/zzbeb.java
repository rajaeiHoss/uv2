package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzab;

public interface zzbeb extends IInterface {
    void disconnect() throws RemoteException;

    void requestStatus() throws RemoteException;

    void zza(double d, double d2, boolean z) throws RemoteException;

    void zza(String str, String str2, zzab zzab) throws RemoteException;

    void zza(boolean z, double d, boolean z2) throws RemoteException;

    void zzagx() throws RemoteException;

    void zzb(String str, LaunchOptions launchOptions) throws RemoteException;

    void zzb(String str, String str2, long j) throws RemoteException;

    void zzfp(String str) throws RemoteException;

    void zzfy(String str) throws RemoteException;

    void zzfz(String str) throws RemoteException;
}
