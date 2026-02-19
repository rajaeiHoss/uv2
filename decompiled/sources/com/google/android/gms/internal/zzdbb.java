package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.HashMap;
import java.util.Map;

public final class zzdbb {
    private zzdbb zzkxx;
    private Map<String, zzdjq> zzkxy;

    public zzdbb() {
        this((zzdbb) null);
    }

    private zzdbb(zzdbb zzdbb) {
        this.zzkxy = null;
        this.zzkxx = zzdbb;
    }

    public final boolean has(String str) {
        zzdbb zzdbb = this;
        do {
            Map<String, zzdjq> map = zzdbb.zzkxy;
            if (map != null && map.containsKey(str)) {
                return true;
            }
            zzdbb = zzdbb.zzkxx;
        } while (zzdbb != null);
        return false;
    }

    public final void remove(String str) {
        zzdbb zzdbb = this;
        while (true) {
            zzbq.checkState(zzdbb.has(str));
            Map<String, zzdjq> map = zzdbb.zzkxy;
            if (map == null || !map.containsKey(str)) {
                zzdbb = zzdbb.zzkxx;
            } else {
                zzdbb.zzkxy.remove(str);
                return;
            }
        }
    }

    public final void zza(String str, zzdjq<?> zzdjq) {
        if (this.zzkxy == null) {
            this.zzkxy = new HashMap();
        }
        this.zzkxy.put(str, zzdjq);
    }

    public final void zzb(String str, zzdjq<?> zzdjq) {
        zzdbb zzdbb = this;
        do {
            Map<String, zzdjq> map = zzdbb.zzkxy;
            if (map == null || !map.containsKey(str)) {
                zzdbb = zzdbb.zzkxx;
            } else {
                zzdbb.zzkxy.put(str, zzdjq);
                return;
            }
        } while (zzdbb != null);
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() != 0 ? "Trying to modify a non existent symbol: ".concat(valueOf) : new String("Trying to modify a non existent symbol: "));
    }

    public final zzdbb zzbjd() {
        return new zzdbb(this);
    }

    public final zzdjq<?> zzmu(String str) {
        zzdbb zzdbb = this;
        do {
            Map<String, zzdjq> map = zzdbb.zzkxy;
            if (map != null && map.containsKey(str)) {
                return zzdbb.zzkxy.get(str);
            }
            zzdbb = zzdbb.zzkxx;
        } while (zzdbb != null);
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() != 0 ? "Trying to get a non existent symbol: ".concat(valueOf) : new String("Trying to get a non existent symbol: "));
    }
}
