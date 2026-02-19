package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public final class zzekf implements zzeki {
    private final zzemm zzmxz;
    private final zzekj zznkg;
    private final zzekp zznkh;
    private final zzekd zznki;
    private long zznkj;

    public zzekf(zzegm zzegm, zzekj zzekj, zzekd zzekd) {
        this(zzegm, zzekj, zzekd, new zzeot());
    }

    private zzekf(zzegm zzegm, zzekj zzekj, zzekd zzekd, zzeos zzeos) {
        this.zznkj = 0;
        this.zznkg = zzekj;
        zzemm zzqb = zzegm.zzqb("Persistence");
        this.zzmxz = zzqb;
        this.zznkh = new zzekp(zzekj, zzqb, zzeos);
        this.zznki = zzekd;
    }

    private final void zzcaa() {
        long j = this.zznkj + 1;
        this.zznkj = j;
        if (this.zznki.zzby(j)) {
            if (this.zzmxz.zzcbu()) {
                this.zzmxz.zzb("Reached prune check threshold.", (Throwable) null, new Object[0]);
            }
            this.zznkj = 0;
            boolean z = true;
            long zzbvl = this.zznkg.zzbvl();
            if (this.zzmxz.zzcbu()) {
                zzemm zzemm = this.zzmxz;
                StringBuilder sb = new StringBuilder(32);
                sb.append("Cache size: ");
                sb.append(zzbvl);
                zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
            }
            while (z && this.zznki.zzj(zzbvl, this.zznkh.zzcad())) {
                zzekk zza = this.zznkh.zza(this.zznki);
                if (zza.zzcab()) {
                    this.zznkg.zza(zzegu.zzbyn(), zza);
                } else {
                    z = false;
                }
                zzbvl = this.zznkg.zzbvl();
                if (this.zzmxz.zzcbu()) {
                    zzemm zzemm2 = this.zzmxz;
                    StringBuilder sb2 = new StringBuilder(44);
                    sb2.append("Cache size after prune: ");
                    sb2.append(zzbvl);
                    zzemm2.zzb(sb2.toString(), (Throwable) null, new Object[0]);
                }
            }
        }
    }

    public final void zza(zzegu zzegu, zzegi zzegi, long j) {
        this.zznkg.zza(zzegu, zzegi, j);
    }

    public final void zza(zzegu zzegu, zzenn zzenn, long j) {
        this.zznkg.zza(zzegu, zzenn, j);
    }

    public final void zza(zzelu zzelu, zzenn zzenn) {
        if (zzelu.zzcbe()) {
            this.zznkg.zza(zzelu.zzbvh(), zzenn);
        } else {
            this.zznkg.zzb(zzelu.zzbvh(), zzenn);
        }
        zzi(zzelu);
        zzcaa();
    }

    public final void zza(zzelu zzelu, Set<zzemq> set) {
        this.zznkg.zza(this.zznkh.zzk(zzelu).id, set);
    }

    public final void zza(zzelu zzelu, Set<zzemq> set, Set<zzemq> set2) {
        this.zznkg.zza(this.zznkh.zzk(zzelu).id, set, set2);
    }

    public final void zzbm(long j) {
        this.zznkg.zzbm(j);
    }

    public final List<zzejn> zzbvk() {
        return this.zznkg.zzbvk();
    }

    public final void zzbvn() {
        this.zznkg.zzbvn();
    }

    public final void zzc(zzegu zzegu, zzegi zzegi) {
        Iterator<Map.Entry<zzegu, zzenn>> it = zzegi.iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            zzk(zzegu.zzh((zzegu) next.getKey()), (zzenn) next.getValue());
        }
    }

    public final void zzd(zzegu zzegu, zzegi zzegi) {
        this.zznkg.zza(zzegu, zzegi);
        zzcaa();
    }

    public final <T> T zze(Callable<T> callable) {
        this.zznkg.beginTransaction();
        try {
            T call = callable.call();
            this.zznkg.setTransactionSuccessful();
            this.zznkg.endTransaction();
            return call;
        } catch (RuntimeException e) {
            this.zznkg.endTransaction();
            throw e;
        } catch (Error e2) {
            this.zznkg.endTransaction();
            throw e2;
        } catch (Exception e3) {
            this.zznkg.endTransaction();
            throw new RuntimeException(e3);
        }
    }

    public final zzelh zzf(zzelu zzelu) {
        boolean z;
        Set<zzemq> set;
        if (this.zznkh.zzm(zzelu)) {
            zzeko zzk = this.zznkh.zzk(zzelu);
            set = (zzelu.zzcbe() || zzk == null || !zzk.complete) ? null : this.zznkg.zzbp(zzk.id);
            z = true;
        } else {
            set = this.zznkh.zzaa(zzelu.zzbvh());
            z = false;
        }
        zzenn zza = this.zznkg.zza(zzelu.zzbvh());
        if (set == null) {
            return new zzelh(zzeng.zza(zza, zzelu.zzcba()), true, false);
        }
        zzenn zzcco = zzene.zzcco();
        for (zzemq next : set) {
            zzcco = zzcco.zze(next, zza.zzm(next));
        }
        return new zzelh(zzeng.zza(zzcco, zzelu.zzcba()), z, true);
    }

    public final void zzg(zzelu zzelu) {
        this.zznkh.zzg(zzelu);
    }

    public final void zzh(zzelu zzelu) {
        this.zznkh.zzh(zzelu);
    }

    public final void zzi(zzelu zzelu) {
        if (zzelu.zzcbe()) {
            this.zznkh.zzz(zzelu.zzbvh());
        } else {
            this.zznkh.zzl(zzelu);
        }
    }

    public final void zzk(zzegu zzegu, zzenn zzenn) {
        if (!this.zznkh.zzac(zzegu)) {
            this.zznkg.zza(zzegu, zzenn);
            this.zznkh.zzab(zzegu);
        }
    }
}
