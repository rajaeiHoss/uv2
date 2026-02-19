package com.google.android.gms.ads.internal.js;

import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.internal.zzaof;
import java.util.Map;

final class zzl implements zzt<zzaof> {
    private /* synthetic */ zze zzcfc;
    /* access modifiers changed from: private */
    public final zzt<? super zzaj> zzcfg;

    public zzl(zze zze, zzt<? super zzaj> zzt) {
        this.zzcfc = zze;
        this.zzcfg = zzt;
    }

    static zzt<? super zzaj> zza(zzl zzl) {
        return zzl.zzcfg;
    }

    public final void zza(zzaof zzaof, Map<String, String> map) {
        this.zzcfg.zza(this.zzcfc, map);
    }
}
