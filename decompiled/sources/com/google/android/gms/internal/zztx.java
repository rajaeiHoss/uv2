package com.google.android.gms.internal;

import android.os.RemoteException;

final class zztx implements zzuk {
    private /* synthetic */ zzoy zzcdp;

    zztx(zztw zztw, zzoy zzoy) {
        this.zzcdp = zzoy;
    }

    public final void zzb(zzul zzul) throws RemoteException {
        if (zzul.zzcdu != null) {
            zzul.zzcdu.zza(this.zzcdp);
        }
    }
}
