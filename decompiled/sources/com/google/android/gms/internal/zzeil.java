package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

final class zzeil {
    private zzenn zznhm = null;
    private Map<zzemq, zzeil> zznhn = null;

    public final void zza(zzegu zzegu, zzeip zzeip) {
        zzenn zzenn = this.zznhm;
        if (zzenn != null) {
            zzeip.zzf(zzegu, zzenn);
            return;
        }
        zzein zzein = new zzein(this, zzegu, zzeip);
        Map<zzemq, zzeil> map = this.zznhn;
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                zzein.zza((zzemq) next.getKey(), (zzeil) next.getValue());
            }
        }
    }

    public final void zzh(zzegu zzegu, zzenn zzenn) {
        zzeil zzeil = this;
        while (!zzegu.isEmpty()) {
            zzenn zzenn2 = zzeil.zznhm;
            if (zzenn2 != null) {
                zzeil.zznhm = zzenn2.zzl(zzegu, zzenn);
                return;
            }
            if (zzeil.zznhn == null) {
                zzeil.zznhn = new HashMap();
            }
            zzemq zzbyq = zzegu.zzbyq();
            if (!zzeil.zznhn.containsKey(zzbyq)) {
                zzeil.zznhn.put(zzbyq, new zzeil());
            }
            zzeil = zzeil.zznhn.get(zzbyq);
            zzegu = zzegu.zzbyr();
        }
        zzeil.zznhm = zzenn;
        zzeil.zznhn = null;
    }

    public final boolean zzq(zzegu zzegu) {
        while (!zzegu.isEmpty()) {
            zzenn zzenn = this.zznhm;
            if (zzenn != null) {
                if (zzenn.zzccd()) {
                    return false;
                }
                this.zznhm = null;
                ((zzems) this.zznhm).zza(new zzeim(this, zzegu), false);
            } else if (this.zznhn == null) {
                return true;
            } else {
                zzemq zzbyq = zzegu.zzbyq();
                zzegu zzbyr = zzegu.zzbyr();
                if (this.zznhn.containsKey(zzbyq) && this.zznhn.get(zzbyq).zzq(zzbyr)) {
                    this.zznhn.remove(zzbyq);
                }
                if (!this.zznhn.isEmpty()) {
                    return false;
                }
                this.zznhn = null;
                return true;
            }
        }
        this.zznhm = null;
        this.zznhn = null;
        return true;
    }
}
