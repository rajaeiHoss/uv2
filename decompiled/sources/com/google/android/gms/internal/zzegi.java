package com.google.android.gms.internal;

import com.streamax.config.constant.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class zzegi implements Iterable<Map.Entry<zzegu, zzenn>> {
    private static final zzegi zzndz = new zzegi(new zzekw(null));
    private final zzekw<zzenn> zznea;

    private zzegi(zzekw<zzenn> zzekw) {
        this.zznea = zzekw;
    }

    private final zzenn zza(zzegu zzegu, zzekw<zzenn> zzekw, zzenn zzenn) {
        if (zzekw.getValue() != null) {
            return zzenn.zzl(zzegu, zzekw.getValue());
        }
        zzenn zzenn2 = null;
        Iterator<Map.Entry<zzemq, zzekw<zzenn>>> it = zzekw.zzcag().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            zzekw zzekw2 = (zzekw) next.getValue();
            zzemq zzemq = (zzemq) next.getKey();
            if (zzemq.zzcca()) {
                zzenn2 = (zzenn) zzekw2.getValue();
            } else {
                zzenn = zza(zzegu.zza(zzemq), zzekw2, zzenn);
            }
        }
        return (zzenn.zzan(zzegu).isEmpty() || zzenn2 == null) ? zzenn : zzenn.zzl(zzegu.zza(zzemq.zzcby()), zzenn2);
    }

    public static zzegi zzam(Map<String, Object> map) {
        zzekw zzcaf = zzekw.zzcaf();
        for (Map.Entry next : map.entrySet()) {
            zzcaf = zzcaf.zza(new zzegu((String) next.getKey()), new zzekw(zzenq.zza(next.getValue(), zzene.zzcco())));
        }
        return new zzegi(zzcaf);
    }

    public static zzegi zzan(Map<zzegu, zzenn> map) {
        zzekw zzcaf = zzekw.zzcaf();
        for (Map.Entry next : map.entrySet()) {
            zzcaf = zzcaf.zza((zzegu) next.getKey(), new zzekw((zzenn) next.getValue()));
        }
        return new zzegi(zzcaf);
    }

    public static zzegi zzbxz() {
        return zzndz;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return ((zzegi) obj).zzcu(true).equals(zzcu(true));
    }

    public final int hashCode() {
        return zzcu(true).hashCode();
    }

    public final boolean isEmpty() {
        return this.zznea.isEmpty();
    }

    public final Iterator<Map.Entry<zzegu, zzenn>> iterator() {
        return this.zznea.iterator();
    }

    public final String toString() {
        String obj = zzcu(true).toString();
        StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 15);
        sb.append("CompoundWrite{");
        sb.append(obj);
        sb.append(Constants.JsonSstringSuffix);
        return sb.toString();
    }

    public final zzegi zza(zzemq zzemq, zzenn zzenn) {
        return zze(new zzegu(zzemq), zzenn);
    }

    public final zzegi zzb(zzegu zzegu, zzegi zzegi) {
        return (zzegi) zzegi.zznea.zzb(this, new zzegj(this, zzegu));
    }

    public final zzenn zzb(zzenn zzenn) {
        return zza(zzegu.zzbyn(), this.zznea, zzenn);
    }

    public final zzenn zzbya() {
        return this.zznea.getValue();
    }

    public final List<zzenm> zzbyb() {
        ArrayList arrayList = new ArrayList();
        if (this.zznea.getValue() != null) {
            for (zzenm zzenm : this.zznea.getValue()) {
                arrayList.add(new zzenm(zzenm.zzccx(), zzenm.zzbve()));
            }
        } else {
            Iterator<Map.Entry<zzemq, zzekw<zzenn>>> it = this.zznea.zzcag().iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                zzekw zzekw = (zzekw) next.getValue();
                if (zzekw.getValue() != null) {
                    arrayList.add(new zzenm((zzemq) next.getKey(), (zzenn) zzekw.getValue()));
                }
            }
        }
        return arrayList;
    }

    public final Map<zzemq, zzegi> zzbyc() {
        HashMap hashMap = new HashMap();
        Iterator<Map.Entry<zzemq, zzekw<zzenn>>> it = this.zznea.zzcag().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            hashMap.put((zzemq) next.getKey(), new zzegi((zzekw) next.getValue()));
        }
        return hashMap;
    }

    public final Map<String, Object> zzcu(boolean z) {
        HashMap hashMap = new HashMap();
        this.zznea.zza(new zzegk(this, hashMap, true));
        return hashMap;
    }

    public final zzegi zzd(zzegu zzegu) {
        return zzegu.isEmpty() ? zzndz : new zzegi(this.zznea.zza(zzegu, zzekw.zzcaf()));
    }

    public final zzegi zze(zzegu zzegu, zzenn zzenn) {
        if (zzegu.isEmpty()) {
            return new zzegi(new zzekw(zzenn));
        }
        zzegu zzaf = this.zznea.zzaf(zzegu);
        if (zzaf != null) {
            zzegu zza = zzegu.zza(zzaf, zzegu);
            zzenn zzaj = this.zznea.zzaj(zzaf);
            zzemq zzbyt = zza.zzbyt();
            if (zzbyt != null && zzbyt.zzcca() && zzaj.zzan(zza.zzbys()).isEmpty()) {
                return this;
            }
            return new zzegi(this.zznea.zzb(zzaf, zzaj.zzl(zza, zzenn)));
        }
        return new zzegi(this.zznea.zza(zzegu, new zzekw(zzenn)));
    }

    public final boolean zze(zzegu zzegu) {
        return zzf(zzegu) != null;
    }

    public final zzenn zzf(zzegu zzegu) {
        zzegu zzaf = this.zznea.zzaf(zzegu);
        if (zzaf != null) {
            return this.zznea.zzaj(zzaf).zzan(zzegu.zza(zzaf, zzegu));
        }
        return null;
    }

    public final zzegi zzg(zzegu zzegu) {
        if (zzegu.isEmpty()) {
            return this;
        }
        zzenn zzf = zzf(zzegu);
        return zzf != null ? new zzegi(new zzekw(zzf)) : new zzegi(this.zznea.zzah(zzegu));
    }
}
