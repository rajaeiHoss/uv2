package com.google.android.gms.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class zzdjv extends zzdjq<zzdcp> {
    private static final Map<String, zzdcp> zzlct;
    private zzdcp zzlcv;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("hasOwnProperty", zzdeq.zzlab);
        zzlct = Collections.unmodifiableMap(hashMap);
    }

    public zzdjv(zzdcp zzdcp) {
        this.zzlcv = zzdcp;
    }

    public final String toString() {
        return this.zzlcv.toString();
    }

    public final zzdcp value() {
        return this.zzlcv;
    }

    public final Iterator<zzdjq<?>> zzbko() {
        return zzbkp();
    }

    public final boolean zznk(String str) {
        return zzlct.containsKey(str);
    }

    public final zzdcp zznl(String str) {
        if (zznk(str)) {
            return zzlct.get(str);
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 60);
        sb.append("Native Method ");
        sb.append(str);
        sb.append(" is not defined for type InstructionReference.");
        throw new IllegalStateException(sb.toString());
    }
}
