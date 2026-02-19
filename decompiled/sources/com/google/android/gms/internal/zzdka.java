package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class zzdka extends zzdjq<Map<String, zzdjq<?>>> {
    private static final Map<String, zzdcp> zzlct;
    private boolean zzldh = false;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("hasOwnProperty", zzdeq.zzlab);
        zzlct = Collections.unmodifiableMap(hashMap);
    }

    public zzdka(Map<String, zzdjq<?>> map) {
        this.zzlcq = (Map) zzbq.checkNotNull(map);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzdka) {
            return this.zzlcq.entrySet().equals(((Map) ((zzdka) obj).value()).entrySet());
        }
        return false;
    }

    public final boolean isImmutable() {
        return this.zzldh;
    }

    public final String toString() {
        return this.zzlcq.toString();
    }

    public final Map<String, zzdjq<?>> value() {
        return this.zzlcq;
    }

    public final Iterator<zzdjq<?>> zzbko() {
        return zzbkp();
    }

    public final void zzbkr() {
        this.zzldh = true;
    }

    public final zzdjq<?> zznj(String str) {
        zzdjq<?> zznj = super.zznj(str);
        return zznj == null ? zzdjw.zzlcz : zznj;
    }

    public final boolean zznk(String str) {
        return zzlct.containsKey(str);
    }

    public final zzdcp zznl(String str) {
        if (zznk(str)) {
            return zzlct.get(str);
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
        sb.append("Native Method ");
        sb.append(str);
        sb.append(" is not defined for type ListWrapper.");
        throw new IllegalStateException(sb.toString());
    }
}
