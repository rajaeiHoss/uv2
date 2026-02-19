package com.google.android.gms.internal;

import android.os.Handler;
import com.google.android.gms.awareness.fence.zza;

public final class zzbjz extends zzbks {
    public static final zzfl<zza, zzbjz> zzgnc = new zzbka();
    private final Handler mHandler;
    private final zza zzgnd;

    public zzbjz(Handler handler, zza zza) {
        this.mHandler = handler;
        this.zzgnd = zza;
    }

    public final void zza(zzbkj zzbkj) {
        this.mHandler.post(new zzbkb(this.zzgnd, zzbkj));
    }

    @Deprecated
    public final void zza(zzbkn zzbkn) {
        zzfi.zza("ContextFenceListenerWrapper", "Unexpected call to deprecated method onFenceTriggered() with %s.", (Object) zzbkn);
        this.mHandler.post(new zzbkb(this.zzgnd, new zzbkj(zzbkn.zzgns ? 2 : 1, 0, zzbkn.key, 0)));
    }
}
