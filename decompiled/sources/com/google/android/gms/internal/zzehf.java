package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.zzh;
import java.util.ArrayList;
import java.util.List;

final class zzehf implements zzefo {
    private /* synthetic */ zzegu zzneb;
    private /* synthetic */ zzegx zznfo;
    private /* synthetic */ List<zzehy> zznfv;
    private /* synthetic */ zzegx zznfw;

    zzehf(zzegx zzegx, zzegu zzegu, List<zzehy> list, zzegx zzegx2) {
        this.zznfo = zzegx;
        this.zzneb = zzegu;
        this.zznfv = list;
        this.zznfw = zzegx2;
    }

    public final void zzbc(String str, String str2) {
        DatabaseError zzbe = zzegx.zzbd(str, str2);
        this.zznfo.zza("Transaction", this.zzneb, zzbe);
        ArrayList arrayList = new ArrayList();
        if (zzbe == null) {
            ArrayList arrayList2 = new ArrayList();
            for (zzehy zzehy : this.zznfv) {
                int unused = zzehy.zzngo = zzehz.zzngz;
                arrayList.addAll(this.zznfo.zznfk.zza(zzehy.zzngs, false, false, (zzeos) this.zznfo.zznex));
                arrayList2.add(new zzehg(this, zzehy, zzh.zza(zzh.zza(this.zznfw, zzehy.zzmxa), zzeng.zzj(zzehy.zzngv))));
                zzegx zzegx = this.zznfo;
                zzegx.zze((zzegr) new zzejp(zzegx, zzehy.zzngn, zzelu.zzam(zzehy.zzmxa)));
            }
            zzegx zzegx2 = this.zznfo;
            zzegx2.zzb((zzelc<List<zzehy>>) zzegx2.zznfa.zzak(this.zzneb));
            this.zznfo.zzbyz();
            this.zznfw.zzaw(arrayList);
            for (int i = 0; i < arrayList2.size(); i++) {
                this.zznfo.zzn((Runnable) arrayList2.get(i));
            }
            return;
        }
        if (zzbe.getCode() == -1) {
            for (zzehy zzehy2 : this.zznfv) {
                int unused2 = zzehy2.zzngo = zzehy2.zzngo == zzehz.zznha ? zzehz.zznhb : zzehz.zzngx;
            }
        } else {
            for (zzehy zzehy3 : this.zznfv) {
                int unused3 = zzehy3.zzngo = zzehz.zznhb;
                DatabaseError unused4 = zzehy3.zzngr = zzbe;
            }
        }
        zzegu unused5 = this.zznfo.zzn(this.zzneb);
    }
}
