package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

final class zzeiu implements Callable<List<? extends zzell>> {
    private /* synthetic */ zzeir zznie;
    private /* synthetic */ zzegr zznih;

    zzeiu(zzeir zzeir, zzegr zzegr) {
        this.zznie = zzeir;
        this.zznih = zzegr;
    }

    public final List<? extends zzell> call() throws Exception {
        zzelh zzelh;
        zzenn zzr;
        zzelu zzbxy = this.zznih.zzbxy();
        zzegu zzbvh = zzbxy.zzbvh();
        zzekw zzd = this.zznie.zznht;
        zzenn zzenn = null;
        zzegu zzegu = zzbvh;
        boolean z = false;
        while (!zzd.isEmpty()) {
            zzeiq zzeiq = (zzeiq) zzd.getValue();
            if (zzeiq != null) {
                if (zzenn == null) {
                    zzenn = zzeiq.zzr(zzegu);
                }
                z = z || zzeiq.zzbzc();
            }
            zzd = zzd.zze(zzegu.isEmpty() ? zzemq.zzqf("") : zzegu.zzbyq());
            zzegu = zzegu.zzbyr();
        }
        zzeiq zzeiq2 = (zzeiq) this.zznie.zznht.zzaj(zzbvh);
        if (zzeiq2 == null) {
            zzeiq2 = new zzeiq(this.zznie.zznhs);
            zzeir zzeir = this.zznie;
            zzekw unused = zzeir.zznht = zzeir.zznht.zzb(zzbvh, zzeiq2);
        } else {
            z = z || zzeiq2.zzbzc();
            if (zzenn == null) {
                zzenn = zzeiq2.zzr(zzegu.zzbyn());
            }
        }
        this.zznie.zznhs.zzg(zzbxy);
        if (zzenn != null) {
            zzelh = new zzelh(zzeng.zza(zzenn, zzbxy.zzcba()), true, false);
        } else {
            zzelh = this.zznie.zznhs.zzf(zzbxy);
            if (!zzelh.zzcai()) {
                zzenn zzcco = zzene.zzcco();
                Iterator it = this.zznie.zznht.zzah(zzbvh).zzcag().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    zzeiq zzeiq3 = (zzeiq) ((zzekw) entry.getValue()).getValue();
                    if (!(zzeiq3 == null || (zzr = zzeiq3.zzr(zzegu.zzbyn())) == null)) {
                        zzcco = zzcco.zze((zzemq) entry.getKey(), zzr);
                    }
                }
                for (zzenm zzenm : zzelh.zzbve()) {
                    if (!zzcco.zzk(zzenm.zzccx())) {
                        zzcco = zzcco.zze(zzenm.zzccx(), zzenm.zzbve());
                    }
                }
                zzelh = new zzelh(zzeng.zza(zzcco, zzbxy.zzcba()), false, false);
            }
        }
        boolean zzc = zzeiq2.zzc(zzbxy);
        if (!zzc && !zzbxy.zzcbe()) {
            zzejk zzf = this.zznie.zzbzf();
            this.zznie.zznhw.put(zzbxy, zzf);
            this.zznie.zznhv.put(zzf, zzbxy);
        }
        List<zzelk> zza = zzeiq2.zza(this.zznih, this.zznie.zznhu.zzt(zzbvh), zzelh);
        if (!zzc && !z) {
            this.zznie.zza(zzbxy, zzeiq2.zzb(zzbxy));
        }
        return zza;
    }
}
