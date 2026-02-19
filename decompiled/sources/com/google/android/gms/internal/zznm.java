package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zznm extends zzaey {
    /* access modifiers changed from: private */
    public zzafc zzbkk;

    public final void destroy() throws RemoteException {
    }

    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    public final boolean isLoaded() throws RemoteException {
        return false;
    }

    public final void pause() throws RemoteException {
    }

    public final void resume() throws RemoteException {
    }

    public final void setImmersiveMode(boolean z) throws RemoteException {
    }

    public final void setUserId(String str) throws RemoteException {
    }

    public final void show() throws RemoteException {
    }

    public final void zza(zzafc zzafc) throws RemoteException {
        this.zzbkk = zzafc;
    }

    public final void zza(zzafi zzafi) throws RemoteException {
        zzaky.e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzako.zzaju.post(new zznn(this));
    }

    public final void zzb(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final void zzc(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final void zzd(IObjectWrapper iObjectWrapper) throws RemoteException {
    }
}
