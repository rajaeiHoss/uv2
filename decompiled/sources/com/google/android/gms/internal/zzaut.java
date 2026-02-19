package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.firebase.appindexing.internal.zza;

public final class zzaut extends zzev implements zzaus {
    zzaut(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch");
    }

    public final void zza(zzauu zzauu, String str, zzauo[] zzauoArr) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzauu);
        zzbc.writeString((String) null);
        zzbc.writeTypedArray(zzauoArr, 0);
        zzb(1, zzbc);
    }

    public final void zza(zzauu zzauu, zza[] zzaArr) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzauu);
        zzbc.writeTypedArray(zzaArr, 0);
        zzb(7, zzbc);
    }
}
