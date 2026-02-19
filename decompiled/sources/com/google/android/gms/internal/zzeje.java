package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

final class zzeje implements Callable<List<? extends zzell>> {
    private /* synthetic */ zzeir zznie;
    private /* synthetic */ zzejk zznif;

    zzeje(zzeir zzeir, zzejk zzejk) {
        this.zznie = zzeir;
        this.zznif = zzejk;
    }

    public final List<? extends zzell> call() throws Exception {
        zzelu zza = this.zznie.zzb(this.zznif);
        if (zza == null) {
            return Collections.emptyList();
        }
        this.zznie.zznhs.zzi(zza);
        return this.zznie.zza(zza, (zzejy) new zzejw(zzeka.zzc(zza.zzcbh()), zzegu.zzbyn()));
    }
}
