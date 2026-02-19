package com.google.android.gms.internal;

import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.zzbq;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzaqp extends zzi<zzaqp> {
    private final Map<String, Object> zzbwu = new HashMap();

    public final void set(String str, String str2) {
        zzbq.zzgv(str);
        if (str != null && str.startsWith("&")) {
            str = str.substring(1);
        }
        zzbq.zzh(str, "Name can not be empty or \"&\"");
        this.zzbwu.put(str, str2);
    }

    public final String toString() {
        return zzl(this.zzbwu);
    }

    public final void zzb(zzaqp zzaqp) {
        zzbq.checkNotNull(zzaqp);
        zzaqp.zzbwu.putAll(this.zzbwu);
    }

    public final Map<String, Object> zzwy() {
        return Collections.unmodifiableMap(this.zzbwu);
    }
}
