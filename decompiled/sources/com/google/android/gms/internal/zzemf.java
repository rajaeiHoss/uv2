package com.google.android.gms.internal;

import java.util.Iterator;

public final class zzemf implements zzemg {
    private final int limit;
    private final zzenf zznme;
    private final zzemi zznni;
    private final boolean zznnj;

    public zzemf(zzelr zzelr) {
        this.zznni = new zzemi(zzelr);
        this.zznme = zzelr.zzcba();
        this.limit = zzelr.getLimit();
        this.zznnj = !zzelr.zzcbc();
    }

    public final zzeng zza(zzeng zzeng, zzemq zzemq, zzenn zzenn, zzegu zzegu, zzemh zzemh, zzemd zzemd) {
        if (!this.zznni.zza(new zzenm(zzemq, zzenn))) {
            zzenn = zzene.zzcco();
        }
        zzenn zzenn2 = zzenn;
        if (zzeng.zzbve().zzm(zzemq).equals(zzenn2)) {
            return zzeng;
        }
        if (zzeng.zzbve().getChildCount() < this.limit) {
            return this.zznni.zzcbq().zza(zzeng, zzemq, zzenn2, zzegu, zzemh, zzemd);
        }
        zzenm zzenm = new zzenm(zzemq, zzenn2);
        zzenm zzccs = this.zznnj ? zzeng.zzccs() : zzeng.zzcct();
        boolean zza = this.zznni.zza(zzenm);
        if (zzeng.zzbve().zzk(zzemq)) {
            zzenn zzm = zzeng.zzbve().zzm(zzemq);
            while (true) {
                zzccs = zzemh.zza(this.zznme, zzccs, this.zznnj);
                if (zzccs == null || (!zzccs.zzccx().equals(zzemq) && !zzeng.zzbve().zzk(zzccs.zzccx()))) {
                    break;
                }
            }
            boolean z2 = true;
            if (zza && !zzenn2.isEmpty() && (zzccs == null ? 1 : this.zznme.zza(zzccs, zzenm, this.zznnj)) >= 0) {
                if (zzemd != null) {
                    zzemd.zza(zzelj.zza(zzemq, zzenn2, zzm));
                }
                return zzeng.zzg(zzemq, zzenn2);
            }
            if (zzemd != null) {
                zzemd.zza(zzelj.zzd(zzemq, zzm));
            }
            zzeng zzg = zzeng.zzg(zzemq, zzene.zzcco());
            if (zzccs == null || !this.zznni.zza(zzccs)) {
                z2 = false;
            }
            if (!z2) {
                return zzg;
            }
            if (zzemd != null) {
                zzemd.zza(zzelj.zzc(zzccs.zzccx(), zzccs.zzbve()));
            }
            return zzg.zzg(zzccs.zzccx(), zzccs.zzbve());
        } else if (zzenn2.isEmpty() || !zza || this.zznme.zza(zzccs, zzenm, this.zznnj) < 0) {
            return zzeng;
        } else {
            if (zzemd != null) {
                zzemd.zza(zzelj.zzd(zzccs.zzccx(), zzccs.zzbve()));
                zzemd.zza(zzelj.zzc(zzemq, zzenn2));
            }
            return zzeng.zzg(zzemq, zzenn2).zzg(zzccs.zzccx(), zzene.zzcco());
        }
    }

    public final zzeng zza(zzeng zzeng, zzeng zzeng2, zzemd zzemd) {
        zzeng zzeng3;
        Iterator<zzenm> it;
        int i;
        zzenm zzenm;
        zzenm zzenm2;
        if (zzeng2.zzbve().zzccd() || zzeng2.zzbve().isEmpty()) {
            zzeng3 = zzeng.zza(zzene.zzcco(), this.zznme);
        } else {
            zzeng3 = zzeng2.zzk(zzene.zzcco());
            if (this.zznnj) {
                it = zzeng2.zzbvr();
                zzenm2 = this.zznni.zzcbt();
                zzenm = this.zznni.zzcbs();
                i = -1;
            } else {
                it = zzeng2.iterator();
                zzenm2 = this.zznni.zzcbs();
                zzenm = this.zznni.zzcbt();
                i = 1;
            }
            boolean z = false;
            int i2 = 0;
            while (it.hasNext()) {
                zzenm next = it.next();
                if (!z && this.zznme.compare(zzenm2, next) * i <= 0) {
                    z = true;
                }
                if (z && i2 < this.limit && this.zznme.compare(next, zzenm) * i <= 0) {
                    i2++;
                } else {
                    zzeng3 = zzeng3.zzg(next.zzccx(), zzene.zzcco());
                }
            }
        }
        return this.zznni.zzcbq().zza(zzeng, zzeng3, zzemd);
    }

    public final zzeng zza(zzeng zzeng, zzenn zzenn) {
        return zzeng;
    }

    public final zzenf zzcba() {
        return this.zznme;
    }

    public final zzemg zzcbq() {
        return this.zznni.zzcbq();
    }

    public final boolean zzcbr() {
        return true;
    }
}
