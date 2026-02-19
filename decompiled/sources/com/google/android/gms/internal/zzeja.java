package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

final class zzeja implements Callable<List<? extends zzell>> {
    private /* synthetic */ zzeir zznie;

    zzeja(zzeir zzeir) {
        this.zznie = zzeir;
    }

    public final List<? extends zzell> call() throws Exception {
        this.zznie.zznhs.zzbvn();
        if (this.zznie.zznhu.zzbzn().isEmpty()) {
            return Collections.emptyList();
        }
        return this.zznie.zza((zzejy) new zzejv(zzegu.zzbyn(), new zzekw(true), true));
    }
}
