package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

final class zzbfb extends zzbfh {
    private /* synthetic */ zzbfg zzfox;
    private /* synthetic */ zzbfa zzfoy;

    zzbfb(zzbfa zzbfa, zzbfg zzbfg) {
        this.zzfoy = zzbfa;
        this.zzfox = zzbfg;
    }

    public final void zzbn(int i) throws RemoteException {
        zzbfa.zzeus.zzb("onRemoteDisplayEnded", new Object[0]);
        zzbfg zzbfg = this.zzfox;
        if (zzbfg != null) {
            zzbfg.zzbn(i);
        }
        if (this.zzfoy.zzfov != null) {
            this.zzfoy.zzfov.onRemoteDisplayEnded(new Status(i));
        }
    }
}
