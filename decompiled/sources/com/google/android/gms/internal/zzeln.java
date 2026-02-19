package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class zzeln {
    private final zzelu zznmd;
    /* access modifiers changed from: private */
    public final zzenf zznme;

    public zzeln(zzelu zzelu) {
        this.zznmd = zzelu;
        this.zznme = zzelu.zzcba();
    }

    private final void zza(List<zzelk> list, zzelm zzelm, List<zzelj> list2, List<zzegr> list3, zzeng zzeng) {
        ArrayList arrayList = new ArrayList();
        for (zzelj next : list2) {
            if (next.zzcan().equals(zzelm)) {
                arrayList.add(next);
            }
        }
        Collections.sort(arrayList, new zzelo(this));
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            zzelj zzelj = (zzelj) obj;
            for (zzegr next2 : list3) {
                if (next2.zza(zzelm)) {
                    list.add(next2.zza((zzelj.zzcan().equals(zzelm.VALUE) || zzelj.zzcan().equals(zzelm.CHILD_REMOVED)) ? zzelj : zzelj.zzg(zzeng.zza(zzelj.zzcam(), zzelj.zzcak().zzbve(), this.zznme)), this.zznmd));
                }
            }
        }
    }

    public final List<zzelk> zza(List<zzelj> list, zzeng zzeng, List<zzegr> list2) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (zzelj next : list) {
            if (next.zzcan().equals(zzelm.CHILD_CHANGED)) {
                if (this.zznme.compare(new zzenm(zzemq.zzcbw(), next.zzcap().zzbve()), new zzenm(zzemq.zzcbw(), next.zzcak().zzbve())) != 0) {
                    arrayList2.add(zzelj.zzc(next.zzcam(), next.zzcak()));
                }
            }
        }
        ArrayList arrayList3 = arrayList;
        List<zzelj> list3 = list;
        List<zzegr> list4 = list2;
        zzeng zzeng2 = zzeng;
        zza(arrayList3, zzelm.CHILD_REMOVED, list3, list4, zzeng2);
        zza(arrayList3, zzelm.CHILD_ADDED, list3, list4, zzeng2);
        zza(arrayList3, zzelm.CHILD_MOVED, arrayList2, list4, zzeng2);
        List<zzelj> list5 = list;
        zza(arrayList3, zzelm.CHILD_CHANGED, list5, list4, zzeng2);
        zza(arrayList3, zzelm.VALUE, list5, list4, zzeng2);
        return arrayList;
    }
}
