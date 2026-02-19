package com.google.android.gms.internal;

import com.google.android.gms.analytics.zzi;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzaqo extends zzi<zzaqo> {
    private Map<Integer, Double> zzdwz = new HashMap(4);

    public final String toString() {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : this.zzdwz.entrySet()) {
            String valueOf = String.valueOf(next.getKey());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 6);
            sb.append("metric");
            sb.append(valueOf);
            hashMap.put(sb.toString(), next.getValue());
        }
        return zzl(hashMap);
    }

    public final void zzb(zzaqo zzaqo) {
        zzaqo.zzdwz.putAll(this.zzdwz);
    }

    public final Map<Integer, Double> zzwx() {
        return Collections.unmodifiableMap(this.zzdwz);
    }
}
