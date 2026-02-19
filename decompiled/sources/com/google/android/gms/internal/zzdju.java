package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzdju extends zzdjq<Double> {
    private static final Map<String, zzdcp> zzlct;
    private Double zzlcu;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("hasOwnProperty", zzdeq.zzlab);
        hashMap.put("toString", new zzdfs());
        zzlct = Collections.unmodifiableMap(hashMap);
    }

    public zzdju(Double d) {
        zzbq.checkNotNull(d);
        this.zzlcu = d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzdju) {
            return this.zzlcu.equals((Double) ((zzdju) obj).value());
        }
        return false;
    }

    public final String toString() {
        return this.zzlcu.toString();
    }

    public final Double value() {
        return this.zzlcu;
    }

    public final boolean zznk(String str) {
        return zzlct.containsKey(str);
    }

    public final zzdcp zznl(String str) {
        if (zznk(str)) {
            return zzlct.get(str);
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 53);
        sb.append("Native Method ");
        sb.append(str);
        sb.append(" is not defined for type DoubleWrapper.");
        throw new IllegalStateException(sb.toString());
    }
}
