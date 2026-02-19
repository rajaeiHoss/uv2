package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

final class zzejf implements Callable<List<? extends zzell>> {
    private /* synthetic */ zzegu zzneb;
    private /* synthetic */ zzeir zznie;
    private /* synthetic */ zzejk zznif;
    private /* synthetic */ zzenn zzniq;

    zzejf(zzeir zzeir, zzejk zzejk, zzegu zzegu, zzenn zzenn) {
        this.zznie = zzeir;
        this.zznif = zzejk;
        this.zzneb = zzegu;
        this.zzniq = zzenn;
    }

    public final List<? extends zzell> call() throws Exception {
        zzelu zza = this.zznie.zzb(this.zznif);
        if (zza == null) {
            return Collections.emptyList();
        }
        zzegu zza2 = zzegu.zza(zza.zzbvh(), this.zzneb);
        this.zznie.zznhs.zza(zza2.isEmpty() ? zza : zzelu.zzam(this.zzneb), this.zzniq);
        return this.zznie.zza(zza, (zzejy) new zzekc(zzeka.zzc(zza.zzcbh()), zza2, this.zzniq));
    }
}
