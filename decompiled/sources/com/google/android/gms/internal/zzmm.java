package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzmm extends IInterface {
    float getAspectRatio() throws RemoteException;

    int getPlaybackState() throws RemoteException;

    boolean isClickToExpandEnabled() throws RemoteException;

    boolean isCustomControlsEnabled() throws RemoteException;

    boolean isMuted() throws RemoteException;

    void mute(boolean z) throws RemoteException;

    void pause() throws RemoteException;

    void play() throws RemoteException;

    void zza(zzmp zzmp) throws RemoteException;

    float zziq() throws RemoteException;

    float zzir() throws RemoteException;

    zzmp zzis() throws RemoteException;
}
