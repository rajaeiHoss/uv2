package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class zzdjc {
    private String zzfli;
    private final List<zzdjh> zzlcb;
    private final Map<String, zzdje> zzlcc;
    private int zzlcd = 0;

    public zzdjc(List<zzdjh> list, Map<String, zzdje> map, String str, int i) {
        this.zzlcb = Collections.unmodifiableList(list);
        this.zzlcc = Collections.unmodifiableMap(map);
        this.zzfli = str;
    }

    public final String getVersion() {
        return this.zzfli;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzlcb);
        String valueOf2 = String.valueOf(this.zzlcc);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 18 + String.valueOf(valueOf2).length());
        sb.append("Rules: ");
        sb.append(valueOf);
        sb.append("\n  Macros: ");
        sb.append(valueOf2);
        return sb.toString();
    }

    public final List<zzdjh> zzbkb() {
        return this.zzlcb;
    }

    public final zzdje zznf(String str) {
        return this.zzlcc.get(str);
    }
}
