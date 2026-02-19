package com.google.android.gms.cast.framework;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzbei;

public final class zze {
    private static final zzbei zzeui = new zzbei("DiscoveryManager");
    private final zzp zzfaw;

    zze(zzp zzp) {
        this.zzfaw = zzp;
    }

    public final IObjectWrapper zzaec() {
        try {
            return this.zzfaw.zzaei();
        } catch (RemoteException e) {
            zzeui.zzb(e, "Unable to call %s on %s.", "getWrappedThis", zzp.class.getSimpleName());
            return null;
        }
    }
}
