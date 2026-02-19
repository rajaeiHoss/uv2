package com.google.firebase.database.connection.idl;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
import java.util.List;

public final class zzv extends zzev implements zzt {
    zzv(IBinder iBinder) {
        super(iBinder, "com.google.firebase.database.connection.idl.IPersistentConnection");
    }

    public final void compareAndPut(List<String> list, IObjectWrapper iObjectWrapper, String str, zzah zzah) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeStringList(list);
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzbc.writeString(str);
        zzex.zza(zzbc, (IInterface) zzah);
        zzb(9, zzbc);
    }

    public final void initialize() throws RemoteException {
        zzb(2, zzbc());
    }

    public final void interrupt(String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzb(14, zzbc);
    }

    public final boolean isInterrupted(String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        Parcel zza = zza(16, zzbc);
        boolean zza2 = zzex.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final void listen(List<String> list, IObjectWrapper iObjectWrapper, zzq zzq, long j, zzah zzah) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeStringList(list);
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (IInterface) zzq);
        zzbc.writeLong(j);
        zzex.zza(zzbc, (IInterface) zzah);
        zzb(5, zzbc);
    }

    public final void merge(List<String> list, IObjectWrapper iObjectWrapper, zzah zzah) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeStringList(list);
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (IInterface) zzah);
        zzb(10, zzbc);
    }

    public final void onDisconnectCancel(List<String> list, zzah zzah) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeStringList(list);
        zzex.zza(zzbc, (IInterface) zzah);
        zzb(13, zzbc);
    }

    public final void onDisconnectMerge(List<String> list, IObjectWrapper iObjectWrapper, zzah zzah) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeStringList(list);
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (IInterface) zzah);
        zzb(12, zzbc);
    }

    public final void onDisconnectPut(List<String> list, IObjectWrapper iObjectWrapper, zzah zzah) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeStringList(list);
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (IInterface) zzah);
        zzb(11, zzbc);
    }

    public final void purgeOutstandingWrites() throws RemoteException {
        zzb(7, zzbc());
    }

    public final void put(List<String> list, IObjectWrapper iObjectWrapper, zzah zzah) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeStringList(list);
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (IInterface) zzah);
        zzb(8, zzbc);
    }

    public final void refreshAuthToken() throws RemoteException {
        zzb(4, zzbc());
    }

    public final void refreshAuthToken2(String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzb(17, zzbc);
    }

    public final void resume(String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzb(15, zzbc);
    }

    public final void setup(zzc zzc, zzk zzk, IObjectWrapper iObjectWrapper, zzw zzw) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) zzc);
        zzex.zza(zzbc, (IInterface) zzk);
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, (IInterface) zzw);
        zzb(1, zzbc);
    }

    public final void shutdown() throws RemoteException {
        zzb(3, zzbc());
    }

    public final void unlisten(List<String> list, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeStringList(list);
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzb(6, zzbc);
    }
}
