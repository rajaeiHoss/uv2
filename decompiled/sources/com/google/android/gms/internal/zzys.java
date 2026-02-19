package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzys extends zzev implements zzyq {
    zzys(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
    }

    public final void onActivityResult(int i, int i2, Intent intent) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeInt(i);
        zzbc.writeInt(i2);
        zzex.zza(zzbc, (Parcelable) intent);
        zzb(12, zzbc);
    }

    public final void onBackPressed() throws RemoteException {
        zzb(10, zzbc());
    }

    public final void onCreate(Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) bundle);
        zzb(1, zzbc);
    }

    public final void onDestroy() throws RemoteException {
        zzb(8, zzbc());
    }

    public final void onPause() throws RemoteException {
        zzb(5, zzbc());
    }

    public final void onRestart() throws RemoteException {
        zzb(2, zzbc());
    }

    public final void onResume() throws RemoteException {
        zzb(4, zzbc());
    }

    public final void onSaveInstanceState(Bundle bundle) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (Parcelable) bundle);
        Parcel zza = zza(6, zzbc);
        if (zza.readInt() != 0) {
            bundle.readFromParcel(zza);
        }
        zza.recycle();
    }

    public final void onStart() throws RemoteException {
        zzb(3, zzbc());
    }

    public final void onStop() throws RemoteException {
        zzb(7, zzbc());
    }

    public final void zzbd() throws RemoteException {
        zzb(9, zzbc());
    }

    public final void zzk(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) iObjectWrapper);
        zzb(13, zzbc);
    }

    public final boolean zzni() throws RemoteException {
        Parcel zza = zza(11, zzbc());
        boolean zza2 = zzex.zza(zza);
        zza.recycle();
        return zza2;
    }
}
