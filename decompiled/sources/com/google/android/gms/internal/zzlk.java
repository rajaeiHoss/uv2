package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzlk extends zzev implements zzli {
    zzlk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdListener");
    }

    public final void onAdClicked() throws RemoteException {
        zzb(6, zzbc());
    }

    public final void onAdClosed() throws RemoteException {
        zzb(1, zzbc());
    }

    public final void onAdFailedToLoad(int i) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeInt(i);
        zzb(2, zzbc);
    }

    public final void onAdImpression() throws RemoteException {
        zzb(7, zzbc());
    }

    public final void onAdLeftApplication() throws RemoteException {
        zzb(3, zzbc());
    }

    public final void onAdLoaded() throws RemoteException {
        zzb(4, zzbc());
    }

    public final void onAdOpened() throws RemoteException {
        zzb(5, zzbc());
    }
}
