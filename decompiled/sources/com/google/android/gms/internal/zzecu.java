package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public final class zzecu extends zzev implements zzect {
    zzecu(IBinder iBinder) {
        super(iBinder, "com.google.firebase.crash.internal.IFirebaseCrashApi");
    }

    public final void log(String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzb(2, zzbc);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzecr zzecr) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (Parcelable) zzecr);
        zzb(1, zzbc);
    }

    public final void zza(String str, long j, Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeLong(j);
        zzex.zza(zzbc, (Parcelable) bundle);
        zzb(7, zzbc);
    }

    public final void zzaf(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzb(4, zzbc);
    }

    public final void zzag(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzb(5, zzbc);
    }

    public final void zzas(List<String> list) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeStringList(list);
        zzb(11, zzbc);
    }

    public final boolean zzbuw() throws RemoteException {
        Parcel zza = zza(9, zzbc());
        boolean zza2 = zzex.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final void zzcp(boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, z);
        zzb(10, zzbc);
    }

    public final void zzcq(boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, z);
        zzb(8, zzbc);
    }

    public final void zzpl(String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzb(6, zzbc);
    }
}
