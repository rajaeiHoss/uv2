package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzwi extends IInterface {
    void destroy() throws RemoteException;

    Bundle getInterstitialAdapterInfo() throws RemoteException;

    zzmm getVideoController() throws RemoteException;

    IObjectWrapper getView() throws RemoteException;

    boolean isInitialized() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void setImmersiveMode(boolean z) throws RemoteException;

    void showInterstitial() throws RemoteException;

    void showVideo() throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzafz zzafz, List<String> list) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzkk zzkk, String str, zzafz zzafz, String str2) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzkk zzkk, String str, zzwl zzwl) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzkk zzkk, String str, String str2, zzwl zzwl) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzkk zzkk, String str, String str2, zzwl zzwl, zzqh zzqh, List<String> list) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzko zzko, zzkk zzkk, String str, zzwl zzwl) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzko zzko, zzkk zzkk, String str, String str2, zzwl zzwl) throws RemoteException;

    void zza(zzkk zzkk, String str, String str2) throws RemoteException;

    void zzc(zzkk zzkk, String str) throws RemoteException;

    void zzg(IObjectWrapper iObjectWrapper) throws RemoteException;

    zzwr zzmp() throws RemoteException;

    zzwu zzmq() throws RemoteException;

    Bundle zzmr() throws RemoteException;

    Bundle zzms() throws RemoteException;

    boolean zzmt() throws RemoteException;

    zzro zzmu() throws RemoteException;

    zzwx zzmv() throws RemoteException;
}
