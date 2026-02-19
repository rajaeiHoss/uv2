package com.google.android.gms.internal;

import java.util.List;
import java.util.concurrent.Callable;

final class zzeiy implements Callable<List<? extends zzell>> {
    private /* synthetic */ zzegu zzneb;
    private /* synthetic */ long zzngk;
    private /* synthetic */ boolean zznia;
    private /* synthetic */ zzeir zznie;
    private /* synthetic */ zzegi zznim;
    private /* synthetic */ zzegi zznin;

    zzeiy(zzeir zzeir, boolean z, zzegu zzegu, zzegi zzegi, long j, zzegi zzegi2) {
        this.zznie = zzeir;
        this.zznia = z;
        this.zzneb = zzegu;
        this.zznim = zzegi;
        this.zzngk = j;
        this.zznin = zzegi2;
    }

    public final List<? extends zzell> call() throws Exception {
        if (this.zznia) {
            this.zznie.zznhs.zza(this.zzneb, this.zznim, this.zzngk);
        }
        this.zznie.zznhu.zza(this.zzneb, this.zznin, Long.valueOf(this.zzngk));
        return this.zznie.zza((zzejy) new zzejx(zzeka.zznjw, this.zzneb, this.zznin));
    }
}
