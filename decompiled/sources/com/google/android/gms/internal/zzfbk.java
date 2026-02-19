package com.google.android.gms.internal;

import android.net.Uri;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzfbk extends IInterface {
    zzfbi zza(Uri uri, IObjectWrapper iObjectWrapper) throws RemoteException;

    zzfbi zza(Uri uri, IObjectWrapper iObjectWrapper, long j) throws RemoteException;

    zzfbi zza(Uri uri, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException;

    zzfbi zza(Uri uri, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, String str) throws RemoteException;

    zzfbi zza(Uri uri, IObjectWrapper iObjectWrapper, String str) throws RemoteException;

    zzfbi zza(Uri uri, IObjectWrapper iObjectWrapper, String str, IObjectWrapper iObjectWrapper2, long j, int i, boolean z) throws RemoteException;

    zzfbi zzb(Uri uri, IObjectWrapper iObjectWrapper) throws RemoteException;

    zzfbi zzb(Uri uri, IObjectWrapper iObjectWrapper, String str) throws RemoteException;

    String zzcou() throws RemoteException;

    String zzu(Uri uri) throws RemoteException;
}
