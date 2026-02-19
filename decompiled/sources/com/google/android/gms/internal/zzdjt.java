package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzdjt extends zzdjq<Boolean> {
    private static final Map<String, zzdcp> zzlct;
    private final Boolean zzlcs;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("hasOwnProperty", zzdeq.zzlab);
        hashMap.put("toString", new zzdfs());
        zzlct = Collections.unmodifiableMap(hashMap);
    }

    public zzdjt(Boolean bool) {
        zzbq.checkNotNull(bool);
        this.zzlcs = bool;
    }

    public final boolean equals(Object obj) {
        return this == obj || ((obj instanceof zzdjt) && ((Boolean) ((zzdjt) obj).value()) == this.zzlcs);
    }

    public final String toString() {
        return this.zzlcs.toString();
    }

    public final Boolean value() {
        return this.zzlcs;
    }

    public final boolean zznk(String str) {
        return zzlct.containsKey(str);
    }

    public final zzdcp zznl(String str) {
        if (zznk(str)) {
            return zzlct.get(str);
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 54);
        sb.append("Native Method ");
        sb.append(str);
        sb.append(" is not defined for type BooleanWrapper.");
        throw new IllegalStateException(sb.toString());
    }
}
