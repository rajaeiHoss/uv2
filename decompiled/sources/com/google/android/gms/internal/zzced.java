package com.google.android.gms.internal;

import android.content.pm.PackageInfo;
import android.os.IInterface;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.instantapps.LaunchData;
import java.util.List;

public interface zzced extends IInterface {
    void zza(Status status, int i) throws RemoteException;

    void zza(Status status, PackageInfo packageInfo) throws RemoteException;

    void zza(Status status, BitmapTeleporter bitmapTeleporter) throws RemoteException;

    void zza(Status status, LaunchData launchData) throws RemoteException;

    void zza(Status status, zzceh zzceh) throws RemoteException;

    void zza(Status status, zzcey zzcey) throws RemoteException;

    void zza(Status status, zzcfb zzcfb) throws RemoteException;

    void zza(Status status, List<zzcfi> list) throws RemoteException;

    void zzb(Status status, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    void zzee(int i) throws RemoteException;
}
