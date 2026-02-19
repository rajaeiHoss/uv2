package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;

public interface zzbed extends IInterface {
    void onApplicationDisconnected(int i) throws RemoteException;

    void zza(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) throws RemoteException;

    void zza(String str, double d, boolean z) throws RemoteException;

    void zza(String str, long j, int i) throws RemoteException;

    void zza(String str, byte[] bArr) throws RemoteException;

    void zzb(zzbdd zzbdd) throws RemoteException;

    void zzb(zzbdx zzbdx) throws RemoteException;

    void zzb(String str, long j) throws RemoteException;

    void zzbf(int i) throws RemoteException;

    void zzbk(int i) throws RemoteException;

    void zzbl(int i) throws RemoteException;

    void zzbm(int i) throws RemoteException;

    void zzr(String str, String str2) throws RemoteException;
}
