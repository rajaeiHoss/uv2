package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzua extends zzafd {
    private /* synthetic */ zztl zzcdm;

    zzua(zztl zztl) {
        this.zzcdm = zztl;
    }

    public final void onRewardedVideoAdClosed() throws RemoteException {
        this.zzcdm.zzaoz.add(new zzue(this));
    }

    public final void onRewardedVideoAdFailedToLoad(int i) throws RemoteException {
        this.zzcdm.zzaoz.add(new zzuh(this, i));
    }

    public final void onRewardedVideoAdLeftApplication() throws RemoteException {
        this.zzcdm.zzaoz.add(new zzug(this));
    }

    public final void onRewardedVideoAdLoaded() throws RemoteException {
        this.zzcdm.zzaoz.add(new zzub(this));
    }

    public final void onRewardedVideoAdOpened() throws RemoteException {
        this.zzcdm.zzaoz.add(new zzuc(this));
    }

    public final void onRewardedVideoCompleted() throws RemoteException {
        this.zzcdm.zzaoz.add(new zzui(this));
    }

    public final void onRewardedVideoStarted() throws RemoteException {
        this.zzcdm.zzaoz.add(new zzud(this));
    }

    public final void zza(zzaeu zzaeu) throws RemoteException {
        this.zzcdm.zzaoz.add(new zzuf(this, zzaeu));
    }
}
