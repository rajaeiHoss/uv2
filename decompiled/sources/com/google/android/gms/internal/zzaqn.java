package com.google.android.gms.internal;

import com.google.android.gms.analytics.zzi;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzaqn extends zzi<zzaqn> {
    private Map<Integer, String> zzdwy = new HashMap(4);

    public final String toString() {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : this.zzdwy.entrySet()) {
            String valueOf = String.valueOf(next.getKey());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 9);
            sb.append("dimension");
            sb.append(valueOf);
            hashMap.put(sb.toString(), next.getValue());
        }
        return zzl(hashMap);
    }

    public final void zzb(zzaqn zzaqn) {
        zzaqn.zzdwy.putAll(this.zzdwy);
    }

    public final Map<Integer, String> zzww() {
        return Collections.unmodifiableMap(this.zzdwy);
    }
}
