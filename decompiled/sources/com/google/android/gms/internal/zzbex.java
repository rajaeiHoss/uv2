package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public final class zzbex extends zzbeu {
    private /* synthetic */ zzbev zzfou;

    protected zzbex(zzbev zzbev) {
        this.zzfou = zzbev;
    }

    public final void onDisconnected() throws RemoteException {
        zzbeq.zzeus.zzb("onDisconnected", new Object[0]);
        this.zzfou.zzfor.zzadn();
        this.zzfou.setResult(new zzbey(Status.zzftq));
    }

    public final void onError(int i) throws RemoteException {
        zzbeq.zzeus.zzb("onError: %d", Integer.valueOf(i));
        this.zzfou.zzfor.zzadn();
        this.zzfou.setResult(new zzbey(Status.zzfts));
    }
}
