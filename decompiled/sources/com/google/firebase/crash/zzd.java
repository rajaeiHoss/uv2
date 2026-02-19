package com.google.firebase.crash;

import com.google.android.gms.internal.zzect;
import java.util.concurrent.Callable;

final class zzd implements Callable<zzect> {
    private /* synthetic */ zzc zzmux;

    zzd(zzc zzc) {
        this.zzmux = zzc;
    }

    public final zzect call() throws Exception {
        return this.zzmux.zzbuz();
    }
}
