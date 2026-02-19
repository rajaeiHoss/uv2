package com.google.firebase.database.connection.idl;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
import java.util.List;

public final class zzy extends zzev implements zzw {
    zzy(IBinder iBinder) {
        super(iBinder, "com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
    }

    public final void onDisconnect() throws RemoteException {
        zzb(4, zzbc());
    }

    public final void zza(List<String> list, IObjectWrapper iObjectWrapper, boolean z, long j) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeStringList(list);
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzex.zza(zzbc, z);
        zzbc.writeLong(j);
        zzb(1, zzbc);
    }

    public final void zza(List<String> list, List<zzak> list2, IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeStringList(list);
        zzbc.writeTypedList(list2);
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzbc.writeLong(j);
        zzb(2, zzbc);
    }

    public final void zzah(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzb(6, zzbc);
    }

    public final void zzbwt() throws RemoteException {
        zzb(3, zzbc());
    }

    public final void zzcs(boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, z);
        zzb(5, zzbc);
    }
}
