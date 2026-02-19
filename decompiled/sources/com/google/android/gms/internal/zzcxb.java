package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafeBrowsingData;
import com.google.android.gms.safetynet.zza;
import com.google.android.gms.safetynet.zzd;
import com.google.android.gms.safetynet.zzf;
import com.google.android.gms.safetynet.zzh;

public interface zzcxb extends IInterface {
    void zza(Status status, SafeBrowsingData safeBrowsingData) throws RemoteException;

    void zza(Status status, zza zza) throws RemoteException;

    void zza(Status status, zzd zzd) throws RemoteException;

    void zza(Status status, zzf zzf) throws RemoteException;

    void zza(Status status, zzh zzh) throws RemoteException;

    void zzao(Status status) throws RemoteException;

    void zzb(Status status, boolean z) throws RemoteException;

    void zzc(Status status, boolean z) throws RemoteException;

    void zzld(String str) throws RemoteException;
}
