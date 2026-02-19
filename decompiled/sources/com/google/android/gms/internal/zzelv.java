package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class zzelv {
    private final zzelu zznmd;
    private final zzely zznmu;
    private zzelx zznmv;
    private final List<zzegr> zznmw = new ArrayList();
    private final zzeln zznmx;

    public zzelv(zzelu zzelu, zzelx zzelx) {
        this.zznmd = zzelu;
        zzeme zzeme = new zzeme(zzelu.zzcba());
        zzemg zzcbg = zzelu.zzcbh().zzcbg();
        this.zznmu = new zzely(zzcbg);
        zzelh zzcbn = zzelx.zzcbn();
        zzelh zzcbl = zzelx.zzcbl();
        zzeng zza = zzeng.zza(zzene.zzcco(), zzelu.zzcba());
        zzeng zza2 = zzeme.zza(zza, zzcbn.zzcak(), (zzemd) null);
        zzeng zza3 = zzcbg.zza(zza, zzcbl.zzcak(), (zzemd) null);
        this.zznmv = new zzelx(new zzelh(zza3, zzcbl.zzcai(), zzcbg.zzcbr()), new zzelh(zza2, zzcbn.zzcai(), false));
        this.zznmx = new zzeln(zzelu);
    }

    private final List<zzelk> zza(List<zzelj> list, zzeng zzeng, zzegr zzegr) {
        List<zzegr> list2;
        if (zzegr == null) {
            list2 = this.zznmw;
        } else {
            list2 = Arrays.asList(new zzegr[]{zzegr});
        }
        return this.zznmx.zza(list, zzeng, list2);
    }

    public final boolean isEmpty() {
        return this.zznmw.isEmpty();
    }

    public final List<zzell> zza(zzegr zzegr, DatabaseError databaseError) {
        List<zzell> list;
        if (databaseError != null) {
            list = new ArrayList<>();
            zzegu zzbvh = this.zznmd.zzbvh();
            for (zzegr zzeli : this.zznmw) {
                list.add(new zzeli(zzeli, databaseError, zzbvh));
            }
        } else {
            list = Collections.emptyList();
        }
        if (zzegr != null) {
            int i = 0;
            int i2 = -1;
            while (true) {
                if (i >= this.zznmw.size()) {
                    i = i2;
                    break;
                }
                zzegr zzegr2 = this.zznmw.get(i);
                if (zzegr2.zzc(zzegr)) {
                    if (zzegr2.zzbym()) {
                        break;
                    }
                    i2 = i;
                }
                i++;
            }
            if (i != -1) {
                this.zznmw.remove(i);
                this.zznmw.get(i).zzbyl();
            }
        } else {
            for (zzegr zzbyl : this.zznmw) {
                zzbyl.zzbyl();
            }
            this.zznmw.clear();
        }
        return list;
    }

    public final zzelw zzb(zzejy zzejy, zzejt zzejt, zzenn zzenn) {
        if (zzejy.zzbzt() == zzejz.Merge) {
            zzejy.zzbzs().zzbzw();
        }
        zzemb zza = this.zznmu.zza(this.zznmv, zzejy, zzejt, zzenn);
        this.zznmv = zza.zznmv;
        return new zzelw(zza(zza.zznmz, zza.zznmv.zzcbl().zzcak(), (zzegr) null), zza.zznmz);
    }

    public final void zzb(zzegr zzegr) {
        this.zznmw.add(zzegr);
    }

    public final zzelu zzcbi() {
        return this.zznmd;
    }

    public final zzenn zzcbj() {
        return this.zznmv.zzcbn().zzbve();
    }

    public final zzenn zzcbk() {
        return this.zznmv.zzcbl().zzbve();
    }

    public final List<zzelk> zzk(zzegr zzegr) {
        zzelh zzcbl = this.zznmv.zzcbl();
        ArrayList arrayList = new ArrayList();
        for (zzenm zzenm : zzcbl.zzbve()) {
            arrayList.add(zzelj.zzc(zzenm.zzccx(), zzenm.zzbve()));
        }
        if (zzcbl.zzcai()) {
            arrayList.add(zzelj.zza(zzcbl.zzcak()));
        }
        return zza(arrayList, zzcbl.zzcak(), zzegr);
    }

    public final zzenn zzr(zzegu zzegu) {
        zzenn zzcbo = this.zznmv.zzcbo();
        if (zzcbo == null) {
            return null;
        }
        if (this.zznmd.zzcbe() || (!zzegu.isEmpty() && !zzcbo.zzm(zzegu.zzbyq()).isEmpty())) {
            return zzcbo.zzan(zzegu);
        }
        return null;
    }
}
