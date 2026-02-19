package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzbia;
import com.google.android.gms.internal.zzcwn;

public interface zzb extends IInterface {
    void zza(int i, Bundle bundle, Bundle bundle2) throws RemoteException;

    void zza(int i, Bundle bundle, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    void zza(int i, Bundle bundle, zzbia zzbia) throws RemoteException;

    void zza(int i, zzcwn zzcwn) throws RemoteException;

    void zza(DataHolder dataHolder, String str) throws RemoteException;

    void zza(DataHolder dataHolder, String str, String str2) throws RemoteException;

    void zzaq(Status status) throws RemoteException;

    void zzk(int i, Bundle bundle) throws RemoteException;

    void zzlb(String str) throws RemoteException;

    void zzlc(String str) throws RemoteException;
}
