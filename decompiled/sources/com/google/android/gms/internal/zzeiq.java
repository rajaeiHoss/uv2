package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class zzeiq {
    private final Map<zzelr, zzelv> zznhr = new HashMap();
    private final zzeki zznhs;

    public zzeiq(zzeki zzeki) {
        this.zznhs = zzeki;
    }

    private final List<zzelk> zza(zzelv zzelv, zzejy zzejy, zzejt zzejt, zzenn zzenn) {
        zzelw zzb = zzelv.zzb(zzejy, zzejt, zzenn);
        if (!zzelv.zzcbi().zzcbe()) {
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            for (zzelj next : zzb.zznmz) {
                zzelm zzcan = next.zzcan();
                if (zzcan == zzelm.CHILD_ADDED) {
                    hashSet2.add(next.zzcam());
                } else if (zzcan == zzelm.CHILD_REMOVED) {
                    hashSet.add(next.zzcam());
                }
            }
            if (!hashSet2.isEmpty() || !hashSet.isEmpty()) {
                this.zznhs.zza(zzelv.zzcbi(), (Set<zzemq>) hashSet2, (Set<zzemq>) hashSet);
            }
        }
        return zzb.zznmy;
    }

    public final boolean isEmpty() {
        return this.zznhr.isEmpty();
    }

    public final zzepa<List<zzelu>, List<zzell>> zza(zzelu zzelu, zzegr zzegr, DatabaseError databaseError) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        boolean zzbzc = zzbzc();
        if (zzelu.isDefault()) {
            Iterator<Map.Entry<zzelr, zzelv>> it = this.zznhr.entrySet().iterator();
            while (it.hasNext()) {
                zzelv zzelv = (zzelv) it.next().getValue();
                arrayList2.addAll(zzelv.zza(zzegr, databaseError));
                if (zzelv.isEmpty()) {
                    it.remove();
                    if (!zzelv.zzcbi().zzcbe()) {
                        arrayList.add(zzelv.zzcbi());
                    }
                }
            }
        } else {
            zzelv zzelv2 = this.zznhr.get(zzelu.zzcbh());
            if (zzelv2 != null) {
                arrayList2.addAll(zzelv2.zza(zzegr, databaseError));
                if (zzelv2.isEmpty()) {
                    this.zznhr.remove(zzelu.zzcbh());
                    if (!zzelv2.zzcbi().zzcbe()) {
                        arrayList.add(zzelv2.zzcbi());
                    }
                }
            }
        }
        if (zzbzc && !zzbzc()) {
            arrayList.add(zzelu.zzam(zzelu.zzbvh()));
        }
        return new zzepa<>(arrayList, arrayList2);
    }

    public final List<zzelk> zza(zzegr zzegr, zzejt zzejt, zzelh zzelh) {
        boolean z;
        zzelu zzbxy = zzegr.zzbxy();
        zzelv zzelv = this.zznhr.get(zzbxy.zzcbh());
        if (zzelv == null) {
            zzenn zzc = zzejt.zzc(zzelh.zzcai() ? zzelh.zzbve() : null);
            if (zzc != null) {
                z = true;
            } else {
                zzc = zzejt.zzd(zzelh.zzbve());
                z = false;
            }
            zzelv = new zzelv(zzbxy, new zzelx(new zzelh(zzeng.zza(zzc, zzbxy.zzcba()), z, false), zzelh));
            if (!zzbxy.zzcbe()) {
                HashSet hashSet = new HashSet();
                for (zzenm zzccx : zzelv.zzcbk()) {
                    hashSet.add(zzccx.zzccx());
                }
                this.zznhs.zza(zzbxy, (Set<zzemq>) hashSet);
            }
            this.zznhr.put(zzbxy.zzcbh(), zzelv);
        }
        zzelv.zzb(zzegr);
        return zzelv.zzk(zzegr);
    }

    public final List<zzelk> zza(zzejy zzejy, zzejt zzejt, zzenn zzenn) {
        zzelr zzbzw = zzejy.zzbzs().zzbzw();
        if (zzbzw != null) {
            return zza(this.zznhr.get(zzbzw), zzejy, zzejt, zzenn);
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<zzelr, zzelv> value : this.zznhr.entrySet()) {
            arrayList.addAll(zza((zzelv) value.getValue(), zzejy, zzejt, zzenn));
        }
        return arrayList;
    }

    public final zzelv zzb(zzelu zzelu) {
        return zzelu.zzcbe() ? zzbzd() : this.zznhr.get(zzelu.zzcbh());
    }

    public final List<zzelv> zzbzb() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<zzelr, zzelv> value : this.zznhr.entrySet()) {
            zzelv zzelv = (zzelv) value.getValue();
            if (!zzelv.zzcbi().zzcbe()) {
                arrayList.add(zzelv);
            }
        }
        return arrayList;
    }

    public final boolean zzbzc() {
        return zzbzd() != null;
    }

    public final zzelv zzbzd() {
        for (Map.Entry<zzelr, zzelv> value : this.zznhr.entrySet()) {
            zzelv zzelv = (zzelv) value.getValue();
            if (zzelv.zzcbi().zzcbe()) {
                return zzelv;
            }
        }
        return null;
    }

    public final boolean zzc(zzelu zzelu) {
        return zzb(zzelu) != null;
    }

    public final zzenn zzr(zzegu zzegu) {
        for (zzelv next : this.zznhr.values()) {
            if (next.zzr(zzegu) != null) {
                return next.zzr(zzegu);
            }
        }
        return null;
    }
}
