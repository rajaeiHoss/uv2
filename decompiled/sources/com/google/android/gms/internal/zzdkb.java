package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.List;

public final class zzdkb extends zzdjq<String> {
    private final String zzldi;
    private final List<zzdjq<?>> zzldj;

    public zzdkb(String str, List<zzdjq<?>> list) {
        zzbq.checkNotNull(str, "Instruction name must be a string.");
        zzbq.checkNotNull(list);
        this.zzldi = str;
        this.zzldj = list;
    }

    public final String toString() {
        String str = this.zzldi;
        String obj = this.zzldj.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(obj).length());
        sb.append("*");
        sb.append(str);
        sb.append(": ");
        sb.append(obj);
        return sb.toString();
    }

    public final String value() {
        return toString();
    }

    public final String zzbks() {
        return this.zzldi;
    }

    public final List<zzdjq<?>> zzbkt() {
        return this.zzldj;
    }
}
