package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzafe extends zzev implements zzafc {
    zzafe(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
    }

    public final void onRewardedVideoAdClosed() throws RemoteException {
        zzb(4, zzbc());
    }

    public final void onRewardedVideoAdFailedToLoad(int i) throws RemoteException {
        Parcel zzbc = zzbc();
        zzbc.writeInt(i);
        zzb(7, zzbc);
    }

    public final void onRewardedVideoAdLeftApplication() throws RemoteException {
        zzb(6, zzbc());
    }

    public final void onRewardedVideoAdLoaded() throws RemoteException {
        zzb(1, zzbc());
    }

    public final void onRewardedVideoAdOpened() throws RemoteException {
        zzb(2, zzbc());
    }

    public final void onRewardedVideoCompleted() throws RemoteException {
        zzb(8, zzbc());
    }

    public final void onRewardedVideoStarted() throws RemoteException {
        zzb(3, zzbc());
    }

    public final void zza(zzaeu zzaeu) throws RemoteException {
        Parcel zzbc = zzbc();
        zzex.zza(zzbc, (IInterface) zzaeu);
        zzb(5, zzbc);
    }
}
