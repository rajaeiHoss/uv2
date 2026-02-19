package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzfbj extends zzev implements zzfbi {
    zzfbj(IBinder iBinder) {
        super(iBinder, "com.google.firebase.storage.network.INetworkRequest");
    }

    public final int getResultCode() throws RemoteException {
        Parcel zza = zza(12, zzbc());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    public final void reset() throws RemoteException {
        zzb(2, zzbc());
    }

    public final void zzbo(String str, String str2) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzb(5, zzbc);
    }

    public final void zzcon() throws RemoteException {
        zzb(4, zzbc());
    }

    public final IObjectWrapper zzcoo() throws RemoteException {
        Parcel zza = zza(7, zzbc());
        IObjectWrapper zzaq = IObjectWrapper.zza.zzaq(zza.readStrongBinder());
        zza.recycle();
        return zzaq;
    }

    public final IObjectWrapper zzcop() throws RemoteException {
        Parcel zza = zza(8, zzbc());
        IObjectWrapper zzaq = IObjectWrapper.zza.zzaq(zza.readStrongBinder());
        zza.recycle();
        return zzaq;
    }

    public final String zzcoq() throws RemoteException {
        Parcel zza = zza(9, zzbc());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final IObjectWrapper zzcor() throws RemoteException {
        Parcel zza = zza(11, zzbc());
        IObjectWrapper zzaq = IObjectWrapper.zza.zzaq(zza.readStrongBinder());
        zza.recycle();
        return zzaq;
    }

    public final boolean zzcos() throws RemoteException {
        Parcel zza = zza(13, zzbc());
        boolean zza2 = zzex.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final int zzcot() throws RemoteException {
        Parcel zza = zza(14, zzbc());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    public final void zzsr(String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzb(1, zzbc);
    }

    public final void zzss(String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzb(3, zzbc);
    }

    public final String zzst(String str) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        Parcel zza = zza(6, zzbc);
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }
}
