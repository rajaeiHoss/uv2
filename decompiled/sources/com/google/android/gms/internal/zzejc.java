package com.google.android.gms.internal;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

final class zzejc implements Callable<List<? extends zzell>> {
    private /* synthetic */ zzegu zzneb;
    private /* synthetic */ zzeir zznie;
    private /* synthetic */ Map zznig;

    zzejc(zzeir zzeir, Map map, zzegu zzegu) {
        this.zznie = zzeir;
        this.zznig = map;
        this.zzneb = zzegu;
    }

    public final List<? extends zzell> call() throws Exception {
        zzegi zzan = zzegi.zzan(this.zznig);
        this.zznie.zznhs.zzd(this.zzneb, zzan);
        return this.zznie.zza((zzejy) new zzejx(zzeka.zznjx, this.zzneb, zzan));
    }
}
