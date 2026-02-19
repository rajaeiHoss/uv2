package com.google.android.gms.internal;

import java.util.Iterator;

public final class zzemi implements zzemg {
    private final zzenf zznme;
    private final zzeme zznnk;
    private final zzenm zznnl;
    private final zzenm zznnm;

    public zzemi(zzelr zzelr) {
        zzenm zzenmVar;
        zzenm zzenmVar2;
        this.zznnk = new zzeme(zzelr.zzcba());
        this.zznme = zzelr.zzcba();
        if (zzelr.zzcas()) {
            zzenmVar = zzelr.zzcba().zzf(zzelr.zzcau(), zzelr.zzcat());
        } else {
            zzenmVar = zzenm.zzccv();
        }
        this.zznnl = zzenmVar;
        if (zzelr.zzcav()) {
            zzenmVar2 = zzelr.zzcba().zzf(zzelr.zzcax(), zzelr.zzcaw());
        } else {
            zzenmVar2 = zzelr.zzcba().zzccp();
        }
        this.zznnm = zzenmVar2;
    }

    public final zzeng zza(zzeng zzeng, zzemq zzemq, zzenn zzenn, zzegu zzegu, zzemh zzemh, zzemd zzemd) {
        if (!zza(new zzenm(zzemq, zzenn))) {
            zzenn = zzene.zzcco();
        }
        return this.zznnk.zza(zzeng, zzemq, zzenn, zzegu, zzemh, zzemd);
    }

    public final zzeng zza(zzeng zzeng, zzeng zzeng2, zzemd zzemd) {
        zzeng zzeng3;
        if (zzeng2.zzbve().zzccd()) {
            zzeng3 = zzeng.zza(zzene.zzcco(), this.zznme);
        } else {
            zzeng zzk = zzeng2.zzk(zzene.zzcco());
            Iterator<zzenm> it = zzeng2.iterator();
            while (it.hasNext()) {
                zzenm next = it.next();
                if (!zza(next)) {
                    zzk = zzk.zzg(next.zzccx(), zzene.zzcco());
                }
            }
            zzeng3 = zzk;
        }
        return this.zznnk.zza(zzeng, zzeng3, zzemd);
    }

    public final zzeng zza(zzeng zzeng, zzenn zzenn) {
        return zzeng;
    }

    public final boolean zza(zzenm zzenm) {
        return this.zznme.compare(this.zznnl, zzenm) <= 0 && this.zznme.compare(zzenm, this.zznnm) <= 0;
    }

    public final zzenf zzcba() {
        return this.zznme;
    }

    public final zzemg zzcbq() {
        return this.zznnk;
    }

    public final boolean zzcbr() {
        return true;
    }

    public final zzenm zzcbs() {
        return this.zznnl;
    }

    public final zzenm zzcbt() {
        return this.zznnm;
    }
}
