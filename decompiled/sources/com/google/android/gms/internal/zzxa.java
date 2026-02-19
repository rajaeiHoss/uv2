package com.google.android.gms.internal;

import android.os.RemoteException;

public final class zzxa extends zzmn {
    private final Object mLock = new Object();
    private volatile zzmp zzcjx;

    public final float getAspectRatio() throws RemoteException {
        throw new RemoteException();
    }

    public final int getPlaybackState() throws RemoteException {
        throw new RemoteException();
    }

    public final boolean isClickToExpandEnabled() throws RemoteException {
        throw new RemoteException();
    }

    public final boolean isCustomControlsEnabled() throws RemoteException {
        throw new RemoteException();
    }

    public final boolean isMuted() throws RemoteException {
        throw new RemoteException();
    }

    public final void mute(boolean z) throws RemoteException {
        throw new RemoteException();
    }

    public final void pause() throws RemoteException {
        throw new RemoteException();
    }

    public final void play() throws RemoteException {
        throw new RemoteException();
    }

    public final void zza(zzmp zzmp) throws RemoteException {
        synchronized (this.mLock) {
            this.zzcjx = zzmp;
        }
    }

    public final float zziq() throws RemoteException {
        throw new RemoteException();
    }

    public final float zzir() throws RemoteException {
        throw new RemoteException();
    }

    public final zzmp zzis() throws RemoteException {
        zzmp zzmp;
        synchronized (this.mLock) {
            zzmp = this.zzcjx;
        }
        return zzmp;
    }
}
