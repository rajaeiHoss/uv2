package com.google.android.gms.tagmanager;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
import java.util.HashMap;
import java.util.Map;

public final class zzcp extends zzev implements zzcn {
    zzcp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.tagmanager.IMeasurementProxy");
    }

    public final void logEventInternalNoInterceptor(String str, String str2, Bundle bundle, long j) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzex.zza(zzbc, (Parcelable) bundle);
        zzbc.writeLong(j);
        zzb(2, zzbc);
    }

    public final void zza(zzch zzch) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzch);
        zzb(22, zzbc);
    }

    public final void zza(zzck zzck) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzck);
        zzb(21, zzbc);
    }

    public final Map zzbgl() throws RemoteException {
        Parcel zza = zza(11, zzbc());
        HashMap zzc = zzex.zzc(zza);
        zza.recycle();
        return zzc;
    }
}
