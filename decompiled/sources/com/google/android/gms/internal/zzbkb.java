package com.google.android.gms.internal;

import com.google.android.gms.awareness.fence.zza;
import com.google.android.gms.common.internal.zzbq;

final class zzbkb implements Runnable {
    private final zza zzgnd;
    private final zzbkj zzgne;

    public zzbkb(zza zza, zzbkj zzbkj) {
        this.zzgnd = (zza) zzbq.checkNotNull(zza);
        this.zzgne = (zzbkj) zzbq.checkNotNull(zzbkj);
    }

    public final void run() {
        this.zzgnd.zza(this.zzgne);
    }
}
