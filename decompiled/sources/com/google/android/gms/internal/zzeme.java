package com.google.android.gms.internal;

public final class zzeme implements zzemg {
    private final zzenf zznme;

    public zzeme(zzenf zzenf) {
        this.zznme = zzenf;
    }

    public final zzeng zza(zzeng zzeng, zzemq zzemq, zzenn zzenn, zzegu zzegu, zzemh zzemh, zzemd zzemd) {
        zzelj zzeljVar = null;
        zzenn zzbve = zzeng.zzbve();
        zzenn zzm = zzbve.zzm(zzemq);
        if (zzm.zzan(zzegu).equals(zzenn.zzan(zzegu)) && zzm.isEmpty() == zzenn.isEmpty()) {
            return zzeng;
        }
        if (zzemd != null) {
            if (!zzenn.isEmpty()) {
                zzeljVar = zzm.isEmpty() ? zzelj.zzc(zzemq, zzenn) : zzelj.zza(zzemq, zzenn, zzm);
            } else if (zzbve.zzk(zzemq)) {
                zzeljVar = zzelj.zzd(zzemq, zzm);
            }
            if (zzeljVar != null) {
                zzemd.zza(zzeljVar);
            }
        }
        return (!zzbve.zzccd() || !zzenn.isEmpty()) ? zzeng.zzg(zzemq, zzenn) : zzeng;
    }

    public final zzeng zza(zzeng zzeng, zzeng zzeng2, zzemd zzemd) {
        zzelj zzeljVar;
        if (zzemd != null) {
            for (zzenm zzenm : zzeng.zzbve()) {
                if (!zzeng2.zzbve().zzk(zzenm.zzccx())) {
                    zzemd.zza(zzelj.zzd(zzenm.zzccx(), zzenm.zzbve()));
                }
            }
            if (!zzeng2.zzbve().zzccd()) {
                for (zzenm zzenm2 : zzeng2.zzbve()) {
                    if (zzeng.zzbve().zzk(zzenm2.zzccx())) {
                        zzenn zzm = zzeng.zzbve().zzm(zzenm2.zzccx());
                        if (!zzm.equals(zzenm2.zzbve())) {
                            zzeljVar = zzelj.zza(zzenm2.zzccx(), zzenm2.zzbve(), zzm);
                        } else {
                            zzeljVar = null;
                        }
                    } else {
                        zzeljVar = zzelj.zzc(zzenm2.zzccx(), zzenm2.zzbve());
                    }
                    if (zzeljVar != null) {
                        zzemd.zza(zzeljVar);
                    }
                }
            }
        }
        return zzeng2;
    }

    public final zzeng zza(zzeng zzeng, zzenn zzenn) {
        return zzeng.zzbve().isEmpty() ? zzeng : zzeng.zzk(zzenn);
    }

    public final zzenf zzcba() {
        return this.zznme;
    }

    public final zzemg zzcbq() {
        return this;
    }

    public final boolean zzcbr() {
        return false;
    }
}
