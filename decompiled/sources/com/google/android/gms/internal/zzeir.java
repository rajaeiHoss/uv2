package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class zzeir {
    /* access modifiers changed from: private */
    public final zzemm zzmxz;
    /* access modifiers changed from: private */
    public final zzeki zznhs;
    /* access modifiers changed from: private */
    public zzekw<zzeiq> zznht = zzekw.zzcaf();
    /* access modifiers changed from: private */
    public final zzejq zznhu = new zzejq();
    /* access modifiers changed from: private */
    public final Map<zzejk, zzelu> zznhv = new HashMap();
    /* access modifiers changed from: private */
    public final Map<zzelu, zzejk> zznhw = new HashMap();
    private final Set<zzelu> zznhx = new HashSet();
    /* access modifiers changed from: private */
    public final zzejj zznhy;
    private long zznhz = 1;

    public zzeir(zzegm zzegm, zzeki zzeki, zzejj zzejj) {
        this.zznhy = zzejj;
        this.zznhs = zzeki;
        this.zzmxz = zzegm.zzqb("SyncTree");
    }

    /* access modifiers changed from: private */
    public final List<zzell> zza(zzejy zzejy) {
        return zza(zzejy, this.zznht, (zzenn) null, this.zznhu.zzt(zzegu.zzbyn()));
    }

    private final List<zzell> zza(zzejy zzejy, zzekw<zzeiq> zzekw, zzenn zzenn, zzejt zzejt) {
        if (zzejy.zzbvh().isEmpty()) {
            return zzb(zzejy, zzekw, zzenn, zzejt);
        }
        zzeiq value = zzekw.getValue();
        if (zzenn == null && value != null) {
            zzenn = value.zzr(zzegu.zzbyn());
        }
        ArrayList arrayList = new ArrayList();
        zzemq zzbyq = zzejy.zzbvh().zzbyq();
        zzejy zzc = zzejy.zzc(zzbyq);
        zzekw zzekw2 = zzekw.zzcag().get(zzbyq);
        if (!(zzekw2 == null || zzc == null)) {
            arrayList.addAll(zza(zzc, (zzekw<zzeiq>) zzekw2, zzenn != null ? zzenn.zzm(zzbyq) : null, zzejt.zzb(zzbyq)));
        }
        if (value != null) {
            arrayList.addAll(value.zza(zzejy, zzejt, zzenn));
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public final List<zzelv> zza(zzekw<zzeiq> zzekw) {
        ArrayList arrayList = new ArrayList();
        zza(zzekw, (List<zzelv>) arrayList);
        return arrayList;
    }

    /* access modifiers changed from: private */
    public final List<? extends zzell> zza(zzelu zzelu, zzejy zzejy) {
        zzegu zzbvh = zzelu.zzbvh();
        return this.zznht.zzaj(zzbvh).zza(zzejy, this.zznhu.zzt(zzbvh), (zzenn) null);
    }

    private final void zza(zzekw<zzeiq> zzekw, List<zzelv> list) {
        zzeiq value = zzekw.getValue();
        if (value == null || !value.zzbzc()) {
            if (value != null) {
                list.addAll(value.zzbzb());
            }
            Iterator<Map.Entry<zzemq, zzekw<zzeiq>>> it = zzekw.zzcag().iterator();
            while (it.hasNext()) {
                zza((zzekw<zzeiq>) (zzekw) it.next().getValue(), list);
            }
            return;
        }
        list.add(value.zzbzd());
    }

    /* access modifiers changed from: private */
    public final void zza(zzelu zzelu, zzelv zzelv) {
        zzegu zzbvh = zzelu.zzbvh();
        zzejk zze = zze(zzelu);
        zzeji zzeji = new zzeji(this, zzelv);
        this.zznhy.zza(zzd(zzelu), zze, zzeji, zzeji);
        zzekw<zzeiq> zzah = this.zznht.zzah(zzbvh);
        if (zze == null) {
            zzah.zza(new zzeiw(this));
        }
    }

    /* access modifiers changed from: private */
    public final void zzax(List<zzelu> list) {
        for (zzelu next : list) {
            if (!next.zzcbe()) {
                zzejk zze = zze(next);
                this.zznhw.remove(next);
                this.zznhv.remove(zze);
            }
        }
    }

    /* access modifiers changed from: private */
    public final zzelu zzb(zzejk zzejk) {
        return this.zznhv.get(zzejk);
    }

    /* access modifiers changed from: private */
    public final List<zzell> zzb(zzejy zzejy, zzekw<zzeiq> zzekw, zzenn zzenn, zzejt zzejt) {
        zzeiq value = zzekw.getValue();
        if (zzenn == null && value != null) {
            zzenn = value.zzr(zzegu.zzbyn());
        }
        ArrayList arrayList = new ArrayList();
        zzekw.zzcag().zza(new zzeix(this, zzenn, zzejt, zzejy, arrayList));
        if (value != null) {
            arrayList.addAll(value.zza(zzejy, zzejt, zzenn));
        }
        return arrayList;
    }

    private final List<zzell> zzb(zzelu zzelu, zzegr zzegr, DatabaseError databaseError) {
        return (List) this.zznhs.zze(new zzeiv(this, zzelu, zzegr, databaseError));
    }

    /* access modifiers changed from: private */
    public final zzejk zzbzf() {
        long j = this.zznhz;
        this.zznhz = 1 + j;
        return new zzejk(j);
    }

    /* access modifiers changed from: private */
    public static zzelu zzd(zzelu zzelu) {
        return (!zzelu.zzcbe() || zzelu.isDefault()) ? zzelu : zzelu.zzam(zzelu.zzbvh());
    }

    /* access modifiers changed from: private */
    public final zzejk zze(zzelu zzelu) {
        return this.zznhw.get(zzelu);
    }

    public final boolean isEmpty() {
        return this.zznht.isEmpty();
    }

    public final List<? extends zzell> zza(long j, boolean z, boolean z2, zzeos zzeos) {
        return (List) this.zznhs.zze(new zzeiz(this, z2, j, z, zzeos));
    }

    public final List<? extends zzell> zza(zzegu zzegu, zzegi zzegi, zzegi zzegi2, long j, boolean z) {
        return (List) this.zznhs.zze(new zzeiy(this, z, zzegu, zzegi, j, zzegi2));
    }

    public final List<? extends zzell> zza(zzegu zzegu, zzenn zzenn, zzejk zzejk) {
        return (List) this.zznhs.zze(new zzejf(this, zzejk, zzegu, zzenn));
    }

    public final List<? extends zzell> zza(zzegu zzegu, zzenn zzenn, zzenn zzenn2, long j, boolean z, boolean z2) {
        zzepd.zzb(z || !z2, "We shouldn't be persisting non-visible writes.");
        return (List) this.zznhs.zze(new zzeis(this, z2, zzegu, zzenn, j, zzenn2, z));
    }

    public final List<? extends zzell> zza(zzegu zzegu, List<zzenu> list, zzejk zzejk) {
        zzelu zzb = zzb(zzejk);
        if (zzb == null) {
            return Collections.emptyList();
        }
        zzenn zzcbj = this.zznht.zzaj(zzb.zzbvh()).zzb(zzb).zzcbj();
        for (zzenu zzm : list) {
            zzcbj = zzm.zzm(zzcbj);
        }
        return zza(zzegu, zzcbj, zzejk);
    }

    public final List<? extends zzell> zza(zzegu zzegu, Map<zzegu, zzenn> map) {
        return (List) this.zznhs.zze(new zzejc(this, map, zzegu));
    }

    public final List<? extends zzell> zza(zzegu zzegu, Map<zzegu, zzenn> map, zzejk zzejk) {
        return (List) this.zznhs.zze(new zzeit(this, zzejk, zzegu, map));
    }

    public final List<? extends zzell> zza(zzejk zzejk) {
        return (List) this.zznhs.zze(new zzeje(this, zzejk));
    }

    public final List<zzell> zza(zzelu zzelu, DatabaseError databaseError) {
        return zzb(zzelu, (zzegr) null, databaseError);
    }

    public final void zza(zzelu zzelu, boolean z) {
        if (z && !this.zznhx.contains(zzelu)) {
            zzg((zzegr) new zzejh(zzelu));
            this.zznhx.add(zzelu);
        } else if (!z && this.zznhx.contains(zzelu)) {
            zzh((zzegr) new zzejh(zzelu));
            this.zznhx.remove(zzelu);
        }
    }

    public final List<? extends zzell> zzb(zzegu zzegu, List<zzenu> list) {
        zzeiq zzaj = this.zznht.zzaj(zzegu);
        if (zzaj == null) {
            return Collections.emptyList();
        }
        zzelv zzbzd = zzaj.zzbzd();
        if (zzbzd == null) {
            return Collections.emptyList();
        }
        zzenn zzcbj = zzbzd.zzcbj();
        for (zzenu zzm : list) {
            zzcbj = zzm.zzm(zzcbj);
        }
        return zzi(zzegu, zzcbj);
    }

    public final List<? extends zzell> zzbze() {
        return (List) this.zznhs.zze(new zzeja(this));
    }

    public final zzenn zzc(zzegu zzegu, List<Long> list) {
        zzekw<zzeiq> zzekw = this.zznht;
        zzekw.getValue();
        zzegu zzbyn = zzegu.zzbyn();
        zzenn zzenn = null;
        zzegu zzegu2 = zzegu;
        do {
            zzemq zzbyq = zzegu2.zzbyq();
            zzegu2 = zzegu2.zzbyr();
            zzbyn = zzbyn.zza(zzbyq);
            zzegu zza = zzegu.zza(zzbyn, zzegu);
            zzekw = zzbyq != null ? zzekw.zze(zzbyq) : zzekw.zzcaf();
            zzeiq value = zzekw.getValue();
            if (value != null) {
                zzenn = value.zzr(zza);
            }
            if (zzegu2.isEmpty()) {
                break;
            }
        } while (zzenn == null);
        return this.zznhu.zza(zzegu, zzenn, list, true);
    }

    public final List<? extends zzell> zzg(zzegr zzegr) {
        return (List) this.zznhs.zze(new zzeiu(this, zzegr));
    }

    public final List<zzell> zzh(zzegr zzegr) {
        return zzb(zzegr.zzbxy(), zzegr, (DatabaseError) null);
    }

    public final List<? extends zzell> zzi(zzegu zzegu, zzenn zzenn) {
        return (List) this.zznhs.zze(new zzejb(this, zzegu, zzenn));
    }

    public final List<? extends zzell> zzs(zzegu zzegu) {
        return (List) this.zznhs.zze(new zzejd(this, zzegu));
    }
}
