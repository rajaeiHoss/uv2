package com.google.android.gms.internal;

import java.util.List;
import java.util.concurrent.Callable;

final class zzejd implements Callable<List<? extends zzell>> {
    private /* synthetic */ zzegu zzneb;
    private /* synthetic */ zzeir zznie;

    zzejd(zzeir zzeir, zzegu zzegu) {
        this.zznie = zzeir;
        this.zzneb = zzegu;
    }

    public final List<? extends zzell> call() throws Exception {
        this.zznie.zznhs.zzi(zzelu.zzam(this.zzneb));
        return this.zznie.zza((zzejy) new zzejw(zzeka.zznjx, this.zzneb));
    }
}
