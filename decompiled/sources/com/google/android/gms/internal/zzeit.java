package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

final class zzeit implements Callable<List<? extends zzell>> {
    private /* synthetic */ zzegu zzneb;
    private /* synthetic */ zzeir zznie;
    private /* synthetic */ zzejk zznif;
    private /* synthetic */ Map zznig;

    zzeit(zzeir zzeir, zzejk zzejk, zzegu zzegu, Map map) {
        this.zznie = zzeir;
        this.zznif = zzejk;
        this.zzneb = zzegu;
        this.zznig = map;
    }

    public final List<? extends zzell> call() throws Exception {
        zzelu zza = this.zznie.zzb(this.zznif);
        if (zza == null) {
            return Collections.emptyList();
        }
        zzegu zza2 = zzegu.zza(zza.zzbvh(), this.zzneb);
        zzegi zzan = zzegi.zzan(this.zznig);
        this.zznie.zznhs.zzd(this.zzneb, zzan);
        return this.zznie.zza(zza, (zzejy) new zzejx(zzeka.zzc(zza.zzcbh()), zza2, zzan));
    }
}
