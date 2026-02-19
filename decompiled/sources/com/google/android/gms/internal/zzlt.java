package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzlt extends IInterface {
    void destroy() throws RemoteException;

    String getAdUnitId() throws RemoteException;

    String getMediationAdapterClassName() throws RemoteException;

    zzmm getVideoController() throws RemoteException;

    boolean isLoading() throws RemoteException;

    boolean isReady() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void setImmersiveMode(boolean z) throws RemoteException;

    void setManualImpressionsEnabled(boolean z) throws RemoteException;

    void setUserId(String str) throws RemoteException;

    void showInterstitial() throws RemoteException;

    void stopLoading() throws RemoteException;

    void zza(zzafc zzafc) throws RemoteException;

    void zza(zzko zzko) throws RemoteException;

    void zza(zzlf zzlf) throws RemoteException;

    void zza(zzli zzli) throws RemoteException;

    void zza(zzly zzly) throws RemoteException;

    void zza(zzme zzme) throws RemoteException;

    void zza(zzms zzms) throws RemoteException;

    void zza(zzns zzns) throws RemoteException;

    void zza(zzpb zzpb) throws RemoteException;

    void zza(zzyx zzyx) throws RemoteException;

    void zza(zzzd zzzd, String str) throws RemoteException;

    boolean zzb(zzkk zzkk) throws RemoteException;

    IObjectWrapper zzbp() throws RemoteException;

    zzko zzbq() throws RemoteException;

    void zzbs() throws RemoteException;

    zzly zzcc() throws RemoteException;

    zzli zzcd() throws RemoteException;

    String zzco() throws RemoteException;
}
