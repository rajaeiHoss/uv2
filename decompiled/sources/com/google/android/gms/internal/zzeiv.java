package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

final class zzeiv implements Callable<List<zzell>> {
    private /* synthetic */ zzelu zzngc;
    private /* synthetic */ zzeir zznie;
    private /* synthetic */ zzegr zznih;
    private /* synthetic */ DatabaseError zznii;

    zzeiv(zzeir zzeir, zzelu zzelu, zzegr zzegr, DatabaseError databaseError) {
        this.zznie = zzeir;
        this.zzngc = zzelu;
        this.zznih = zzegr;
        this.zznii = databaseError;
    }

    public final List<zzell> call() throws Exception {
        boolean z;
        zzegu zzbvh = this.zzngc.zzbvh();
        zzeiq zzeiq = (zzeiq) this.zznie.zznht.zzaj(zzbvh);
        List<zzell> arrayList = new ArrayList<>();
        if (zzeiq != null && (this.zzngc.isDefault() || zzeiq.zzc(this.zzngc))) {
            zzepa<List<zzelu>, List<zzell>> zza = zzeiq.zza(this.zzngc, this.zznih, this.zznii);
            if (zzeiq.isEmpty()) {
                zzeir zzeir = this.zznie;
                zzekw unused = zzeir.zznht = zzeir.zznht.zzai(zzbvh);
            }
            List<zzelu> first = zza.getFirst();
            arrayList = zza.zzcdp();
            Iterator it = first.iterator();
            loop0:
            while (true) {
                z = false;
                while (true) {
                    if (!it.hasNext()) {
                        break loop0;
                    }
                    zzelu zzelu = (zzelu) it.next();
                    this.zznie.zznhs.zzh(this.zzngc);
                    if (z || zzelu.zzcbe()) {
                        z = true;
                    }
                }
            }
            zzekw zzd = this.zznie.zznht;
            boolean z2 = zzd.getValue() != null && ((zzeiq) zzd.getValue()).zzbzc();
            Iterator<zzemq> it2 = zzbvh.iterator();
            while (it2.hasNext()) {
                zzd = zzd.zze(it2.next());
                z2 = z2 || (zzd.getValue() != null && ((zzeiq) zzd.getValue()).zzbzc());
                if (!z2) {
                    if (zzd.isEmpty()) {
                        break;
                    }
                } else {
                    break;
                }
            }
            if (z && !z2) {
                zzekw zzah = this.zznie.zznht.zzah(zzbvh);
                if (!zzah.isEmpty()) {
                    for (zzelv zzelv : this.zznie.zza((zzekw<zzeiq>) zzah)) {
                        zzeji zzeji = new zzeji(this.zznie, zzelv);
                        this.zznie.zznhy.zza(zzeir.zzd(zzelv.zzcbi()), zzeji.zznis, zzeji, zzeji);
                    }
                }
            }
            if (!z2 && !first.isEmpty() && this.zznii == null) {
                if (z) {
                    this.zznie.zznhy.zza(zzeir.zzd(this.zzngc), (zzejk) null);
                } else {
                    for (zzelu zzelu2 : first) {
                        this.zznie.zznhy.zza(zzeir.zzd(zzelu2), this.zznie.zze(zzelu2));
                    }
                }
            }
            this.zznie.zzax(first);
        }
        return arrayList;
    }
}
