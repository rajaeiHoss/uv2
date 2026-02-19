package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Map;

public final class zzdje {
    private final Map<String, zzdjn> zzlce;
    private final zzdjn zzlcf;

    zzdje(Map<String, zzdjn> map, zzdjn zzdjn) {
        this.zzlce = Collections.unmodifiableMap(map);
        this.zzlcf = zzdjn;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzlce);
        String valueOf2 = String.valueOf(this.zzlcf);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 32 + String.valueOf(valueOf2).length());
        sb.append("Properties: ");
        sb.append(valueOf);
        sb.append(" pushAfterEvaluate: ");
        sb.append(valueOf2);
        return sb.toString();
    }

    public final Map<String, zzdjn> zzbkd() {
        return this.zzlce;
    }
}
