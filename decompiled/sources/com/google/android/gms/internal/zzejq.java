package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class zzejq {
    private static final zzela<zzejn> zznje = new zzejs();
    private zzegi zznjb = zzegi.zzbxz();
    private List<zzejn> zznjc = new ArrayList();
    private Long zznjd = -1L;

    private static zzegi zza(List<zzejn> list, zzela<zzejn> zzela, zzegu zzegu) {
        zzegi zzbxz = zzegi.zzbxz();
        for (zzejn next : list) {
            if (zzela.zzbw(next)) {
                zzegu zzbvh = next.zzbvh();
                zzenn zzf = null;
                zzegu zzbyn = null;
                zzegu zzbyn2 = null;
                if (!next.zzbzk()) {
                    if (zzegu.zzi(zzbvh)) {
                        zzbyn2 = zzegu.zza(zzegu, zzbvh);
                    } else if (zzbvh.zzi(zzegu)) {
                        zzegu zza = zzegu.zza(zzbvh, zzegu);
                        if (zza.isEmpty()) {
                            zzbyn2 = zzegu.zzbyn();
                        } else {
                            zzf = next.zzbzj().zzf(zza);
                            if (zzf != null) {
                                zzbyn = zzegu.zzbyn();
                            }
                        }
                    }
                    if (zzbyn2 != null) {
                        zzbxz = zzbxz.zzb(zzbyn2, next.zzbzj());
                    }
                } else if (zzegu.zzi(zzbvh)) {
                    zzbyn = zzegu.zza(zzegu, zzbvh);
                    zzf = next.zzbzi();
                } else if (zzbvh.zzi(zzegu)) {
                    zzbxz = zzbxz.zze(zzegu.zzbyn(), next.zzbzi().zzan(zzegu.zza(zzbvh, zzegu)));
                }
                if (zzbyn != null && zzf != null) {
                    zzbxz = zzbxz.zze(zzbyn, zzf);
                }
            }
        }
        return zzbxz;
    }

    public final zzenm zza(zzegu zzegu, zzenn zzenn, zzenm zzenm, boolean z, zzenf zzenf) {
        zzegi zzg = this.zznjb.zzg(zzegu);
        zzenn zzf = zzg.zzf(zzegu.zzbyn());
        zzenm zzenm2 = null;
        if (zzf == null) {
            if (zzenn != null) {
                zzf = zzg.zzb(zzenn);
            }
            return zzenm2;
        }
        for (zzenm zzenm3 : zzf) {
            if (zzenf.zza(zzenm3, zzenm, z) > 0 && (zzenm2 == null || zzenf.zza(zzenm3, zzenm2, z) < 0)) {
                zzenm2 = zzenm3;
            }
        }
        return zzenm2;
    }

    public final zzenn zza(zzegu zzegu, zzegu zzegu2, zzenn zzenn, zzenn zzenn2) {
        zzegu zzh = zzegu.zzh(zzegu2);
        if (this.zznjb.zze(zzh)) {
            return null;
        }
        zzegi zzg = this.zznjb.zzg(zzh);
        return zzg.isEmpty() ? zzenn2.zzan(zzegu2) : zzg.zzb(zzenn2.zzan(zzegu2));
    }

    public final zzenn zza(zzegu zzegu, zzemq zzemq, zzelh zzelh) {
        zzegu zza = zzegu.zza(zzemq);
        zzenn zzf = this.zznjb.zzf(zza);
        if (zzf != null) {
            return zzf;
        }
        if (zzelh.zzf(zzemq)) {
            return this.zznjb.zzg(zza).zzb(zzelh.zzbve().zzm(zzemq));
        }
        return null;
    }

    public final zzenn zza(zzegu zzegu, zzenn zzenn, List<Long> list, boolean z) {
        if (!list.isEmpty() || z) {
            zzegi zzg = this.zznjb.zzg(zzegu);
            if (!z && zzg.isEmpty()) {
                return zzenn;
            }
            if (!z && zzenn == null && !zzg.zze(zzegu.zzbyn())) {
                return null;
            }
            zzegi zza = zza(this.zznjc, (zzela<zzejn>) new zzejr(this, z, list, zzegu), zzegu);
            if (zzenn == null) {
                zzenn = zzene.zzcco();
            }
            return zza.zzb(zzenn);
        }
        zzenn zzf = this.zznjb.zzf(zzegu);
        if (zzf != null) {
            return zzf;
        }
        zzegi zzg2 = this.zznjb.zzg(zzegu);
        if (zzg2.isEmpty()) {
            return zzenn;
        }
        if (zzenn == null && !zzg2.zze(zzegu.zzbyn())) {
            return null;
        }
        if (zzenn == null) {
            zzenn = zzene.zzcco();
        }
        return zzg2.zzb(zzenn);
    }

    public final void zza(zzegu zzegu, zzegi zzegi, Long l) {
        this.zznjc.add(new zzejn(l.longValue(), zzegu, zzegi));
        this.zznjb = this.zznjb.zzb(zzegu, zzegi);
        this.zznjd = l;
    }

    public final void zza(zzegu zzegu, zzenn zzenn, Long l, boolean z) {
        this.zznjc.add(new zzejn(l.longValue(), zzegu, zzenn, z));
        if (z) {
            this.zznjb = this.zznjb.zze(zzegu, zzenn);
        }
        this.zznjd = l;
    }

    public final zzejn zzbw(long j) {
        for (zzejn next : this.zznjc) {
            if (next.zzbzh() == j) {
                return next;
            }
        }
        return null;
    }

    public final boolean zzbx(long j) {
        zzejn zzejn;
        long j2;
        boolean z;
        Iterator<zzejn> it = this.zznjc.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                zzejn = null;
                break;
            }
            zzejn = it.next();
            if (zzejn.zzbzh() == j) {
                break;
            }
            i++;
        }
        this.zznjc.remove(zzejn);
        boolean isVisible = zzejn.isVisible();
        int size = this.zznjc.size() - 1;
        boolean z2 = false;
        while (isVisible && size >= 0) {
            zzejn zzejn2 = this.zznjc.get(size);
            if (zzejn2.isVisible()) {
                if (size >= i) {
                    zzegu zzbvh = zzejn.zzbvh();
                    if (!zzejn2.zzbzk()) {
                        Iterator<Map.Entry<zzegu, zzenn>> it2 = zzejn2.zzbzj().iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                if (zzejn2.zzbvh().zzh((zzegu) it2.next().getKey()).zzi(zzbvh)) {
                                    z = true;
                                    break;
                                }
                            } else {
                                z = false;
                                break;
                            }
                        }
                    } else {
                        z = zzejn2.zzbvh().zzi(zzbvh);
                    }
                    if (z) {
                        isVisible = false;
                    }
                }
                if (zzejn.zzbvh().zzi(zzejn2.zzbvh())) {
                    z2 = true;
                }
            }
            size--;
        }
        if (!isVisible) {
            return false;
        }
        if (z2) {
            this.zznjb = zza(this.zznjc, zznje, zzegu.zzbyn());
            if (this.zznjc.size() > 0) {
                List<zzejn> list = this.zznjc;
                j2 = list.get(list.size() - 1).zzbzh();
            } else {
                j2 = -1;
            }
            this.zznjd = Long.valueOf(j2);
            return true;
        }
        if (zzejn.zzbzk()) {
            this.zznjb = this.zznjb.zzd(zzejn.zzbvh());
        } else {
            Iterator<Map.Entry<zzegu, zzenn>> it3 = zzejn.zzbzj().iterator();
            while (it3.hasNext()) {
                this.zznjb = this.zznjb.zzd(zzejn.zzbvh().zzh((zzegu) it3.next().getKey()));
            }
        }
        return true;
    }

    public final List<zzejn> zzbzn() {
        ArrayList arrayList = new ArrayList(this.zznjc);
        this.zznjb = zzegi.zzbxz();
        this.zznjc = new ArrayList();
        return arrayList;
    }

    public final zzenn zzj(zzegu zzegu, zzenn zzenn) {
        zzenn zzcco = zzene.zzcco();
        zzenn zzf = this.zznjb.zzf(zzegu);
        if (zzf != null) {
            if (!zzf.zzccd()) {
                for (zzenm zzenm : zzf) {
                    zzcco = zzcco.zze(zzenm.zzccx(), zzenm.zzbve());
                }
            }
            return zzcco;
        }
        zzegi zzg = this.zznjb.zzg(zzegu);
        Iterator it = zzenn.iterator();
        while (it.hasNext()) {
            zzenm zzenm2 = (zzenm) it.next();
            zzcco = zzcco.zze(zzenm2.zzccx(), zzg.zzg(new zzegu(zzenm2.zzccx())).zzb(zzenm2.zzbve()));
        }
        for (zzenm next : zzg.zzbyb()) {
            zzcco = zzcco.zze(next.zzccx(), next.zzbve());
        }
        return zzcco;
    }

    public final zzejt zzt(zzegu zzegu) {
        return new zzejt(zzegu, this);
    }

    public final zzenn zzu(zzegu zzegu) {
        return this.zznjb.zzf(zzegu);
    }
}
