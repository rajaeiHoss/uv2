package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

final class zzeiz implements Callable<List<? extends zzell>> {
    private /* synthetic */ long zzngk;
    private /* synthetic */ boolean zznia;
    private /* synthetic */ zzeir zznie;
    private /* synthetic */ boolean zznio;
    private /* synthetic */ zzeos zznip;

    zzeiz(zzeir zzeir, boolean z, long j, boolean z2, zzeos zzeos) {
        this.zznie = zzeir;
        this.zznia = z;
        this.zzngk = j;
        this.zznio = z2;
        this.zznip = zzeos;
    }

    public final List<? extends zzell> call() throws Exception {
        if (this.zznia) {
            this.zznie.zznhs.zzbm(this.zzngk);
        }
        zzejn zzbw = this.zznie.zznhu.zzbw(this.zzngk);
        boolean zzbx = this.zznie.zznhu.zzbx(this.zzngk);
        if (zzbw.isVisible() && !this.zznio) {
            Map<String, Object> zza = zzeih.zza(this.zznip);
            if (zzbw.zzbzk()) {
                this.zznie.zznhs.zzk(zzbw.zzbvh(), zzeih.zza(zzbw.zzbzi(), zza));
            } else {
                this.zznie.zznhs.zzc(zzbw.zzbvh(), zzeih.zza(zzbw.zzbzj(), zza));
            }
        }
        if (!zzbx) {
            return Collections.emptyList();
        }
        zzekw zzcaf = zzekw.zzcaf();
        if (zzbw.zzbzk()) {
            zzcaf = zzcaf.zzb(zzegu.zzbyn(), true);
        } else {
            Iterator<Map.Entry<zzegu, zzenn>> it = zzbw.zzbzj().iterator();
            while (it.hasNext()) {
                zzcaf = zzcaf.zzb((zzegu) it.next().getKey(), true);
            }
        }
        return this.zznie.zza((zzejy) new zzejv(zzbw.zzbvh(), zzcaf, this.zznio));
    }
}
