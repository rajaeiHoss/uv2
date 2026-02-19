package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public final class zzeng implements Iterable<zzenm> {
    private static final zzedv<zzenm> zznot = new zzedv<>(Collections.emptyList(), (Comparator) null);
    private final zzenf zznme;
    private final zzenn zznou;
    private zzedv<zzenm> zznov;

    private zzeng(zzenn zzenn, zzenf zzenf) {
        this.zznme = zzenf;
        this.zznou = zzenn;
        this.zznov = null;
    }

    private zzeng(zzenn zzenn, zzenf zzenf, zzedv<zzenm> zzedv) {
        this.zznme = zzenf;
        this.zznou = zzenn;
        this.zznov = zzedv;
    }

    public static zzeng zza(zzenn zzenn, zzenf zzenf) {
        return new zzeng(zzenn, zzenf);
    }

    private final void zzccr() {
        if (this.zznov == null) {
            if (!this.zznme.equals(zzenh.zzccu())) {
                ArrayList arrayList = new ArrayList();
                boolean z = false;
                for (zzenm zzenm : this.zznou) {
                    z = z || this.zznme.zzi(zzenm.zzbve());
                    arrayList.add(new zzenm(zzenm.zzccx(), zzenm.zzbve()));
                }
                if (z) {
                    this.zznov = new zzedv<>(arrayList, this.zznme);
                    return;
                }
            }
            this.zznov = zznot;
        }
    }

    public static zzeng zzj(zzenn zzenn) {
        return new zzeng(zzenn, zzens.zzccy());
    }

    public final Iterator<zzenm> iterator() {
        zzccr();
        zzedv<zzenm> zzedv = this.zznov;
        return zzedv == zznot ? this.zznou.iterator() : zzedv.iterator();
    }

    public final zzemq zza(zzemq zzemq, zzenn zzenn, zzenf zzenf) {
        if (this.zznme.equals(zzenh.zzccu()) || this.zznme.equals(zzenf)) {
            zzccr();
            zzedv<zzenm> zzedv = this.zznov;
            if (zzedv == zznot) {
                return this.zznou.zzl(zzemq);
            }
            zzenm zzbr = zzedv.zzbr(new zzenm(zzemq, zzenn));
            if (zzbr != null) {
                return zzbr.zzccx();
            }
            return null;
        }
        throw new IllegalArgumentException("Index not available in IndexedNode!");
    }

    public final zzenn zzbve() {
        return this.zznou;
    }

    public final Iterator<zzenm> zzbvr() {
        zzccr();
        zzedv<zzenm> zzedv = this.zznov;
        return zzedv == zznot ? this.zznou.zzbvr() : zzedv.zzbvr();
    }

    public final zzenm zzccs() {
        if (!(this.zznou instanceof zzems)) {
            return null;
        }
        zzccr();
        zzedv<zzenm> zzedv = this.zznov;
        if (zzedv != zznot) {
            return zzedv.zzbvt();
        }
        zzemq zzccf = ((zzems) this.zznou).zzccf();
        return new zzenm(zzccf, this.zznou.zzm(zzccf));
    }

    public final zzenm zzcct() {
        if (!(this.zznou instanceof zzems)) {
            return null;
        }
        zzccr();
        zzedv<zzenm> zzedv = this.zznov;
        if (zzedv != zznot) {
            return zzedv.zzbvu();
        }
        zzemq zzccg = ((zzems) this.zznou).zzccg();
        return new zzenm(zzccg, this.zznou.zzm(zzccg));
    }

    public final zzeng zzg(zzemq zzemq, zzenn zzenn) {
        zzenn zze = this.zznou.zze(zzemq, zzenn);
        zzedv<zzenm> zzedv = this.zznov;
        zzedv<zzenm> zzedv2 = zznot;
        if (zzedv == zzedv2 && !this.zznme.zzi(zzenn)) {
            return new zzeng(zze, this.zznme, zzedv2);
        }
        zzedv<zzenm> zzedv3 = this.zznov;
        if (zzedv3 == null || zzedv3 == zzedv2) {
            return new zzeng(zze, this.zznme, (zzedv<zzenm>) null);
        }
        zzedv<zzenm> zzbp = this.zznov.zzbp(new zzenm(zzemq, this.zznou.zzm(zzemq)));
        if (!zzenn.isEmpty()) {
            zzbp = zzbp.zzbq(new zzenm(zzemq, zzenn));
        }
        return new zzeng(zze, this.zznme, zzbp);
    }

    public final zzeng zzk(zzenn zzenn) {
        return new zzeng(this.zznou.zzf(zzenn), this.zznme, this.zznov);
    }
}
