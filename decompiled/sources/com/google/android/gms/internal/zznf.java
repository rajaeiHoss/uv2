package com.google.android.gms.internal;

import android.os.RemoteException;

final class zznf extends zzlm {
    final /* synthetic */ zznd zzbkh;

    zznf(zznd zznd) {
        this.zzbkh = zznd;
    }

    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    public final boolean isLoading() throws RemoteException {
        return false;
    }

    public final void zza(zzkk zzkk, int i) throws RemoteException {
        zzaky.e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzako.zzaju.post(new zzng(this));
    }

    public final String zzco() throws RemoteException {
        return null;
    }

    public final void zzd(zzkk zzkk) throws RemoteException {
        zza(zzkk, 1);
    }
}
