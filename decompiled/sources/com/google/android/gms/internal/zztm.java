package com.google.android.gms.internal;

import android.os.RemoteException;

final class zztm extends zzlj {
    private /* synthetic */ zztl zzcdm;

    zztm(zztl zztl) {
        this.zzcdm = zztl;
    }

    public final void onAdClicked() throws RemoteException {
        this.zzcdm.zzaoz.add(new zztt(this));
    }

    public final void onAdClosed() throws RemoteException {
        this.zzcdm.zzaoz.add(new zztn(this));
    }

    public final void onAdFailedToLoad(int i) throws RemoteException {
        this.zzcdm.zzaoz.add(new zzto(this, i));
        zzahw.v("Pooled interstitial failed to load.");
    }

    public final void onAdImpression() throws RemoteException {
        this.zzcdm.zzaoz.add(new zzts(this));
    }

    public final void onAdLeftApplication() throws RemoteException {
        this.zzcdm.zzaoz.add(new zztp(this));
    }

    public final void onAdLoaded() throws RemoteException {
        this.zzcdm.zzaoz.add(new zztq(this));
        zzahw.v("Pooled interstitial loaded.");
    }

    public final void onAdOpened() throws RemoteException {
        this.zzcdm.zzaoz.add(new zztr(this));
    }
}
