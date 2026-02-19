package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zzm extends zzev implements zzl {
    zzm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.ICastSession");
    }

    public final void onConnected(Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) bundle);
        zzb(1, zzbc);
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) connectionResult);
        zzb(3, zzbc);
    }

    public final void onConnectionSuspended(int i) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeInt(i);
        zzb(2, zzbc);
    }

    public final void zza(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) applicationMetadata);
        zzbc.writeString(str);
        zzbc.writeString(str2);
        zzex.zza(zzbc, z);
        zzb(4, zzbc);
    }

    public final void zzb(boolean z, int i) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, z);
        zzbc.writeInt(0);
        zzb(6, zzbc);
    }

    public final void zzbf(int i) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeInt(i);
        zzb(5, zzbc);
    }
}
