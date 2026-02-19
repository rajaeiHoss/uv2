package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzwl extends IInterface {
    void onAdClicked() throws RemoteException;

    void onAdClosed() throws RemoteException;

    void onAdFailedToLoad(int i) throws RemoteException;

    void onAdImpression() throws RemoteException;

    void onAdLeftApplication() throws RemoteException;

    void onAdLoaded() throws RemoteException;

    void onAdOpened() throws RemoteException;

    void onAppEvent(String str, String str2) throws RemoteException;

    void onVideoEnd() throws RemoteException;

    void zza(zzwo zzwo) throws RemoteException;

    void zzb(zzro zzro, String str) throws RemoteException;
}
