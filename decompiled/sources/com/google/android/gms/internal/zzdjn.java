package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;

public final class zzdjn {
    private static final String zzkuo = new String("");
    private static final Integer zzlcl = 0;
    private final Object mValue;
    private final int zzenu;
    private final List<Integer> zzlcm;
    private final boolean zzlcn;

    zzdjn(Integer num, Object obj, List<Integer> list, boolean z) {
        this.zzenu = num.intValue();
        this.mValue = obj;
        this.zzlcm = Collections.unmodifiableList(list);
        this.zzlcn = z;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof zzdjn) && ((zzdjn) obj).mValue.equals(this.mValue);
    }

    public final int getType() {
        return this.zzenu;
    }

    public final Object getValue() {
        return this.mValue;
    }

    public final int hashCode() {
        return this.mValue.hashCode();
    }

    public final String toString() {
        Object obj = this.mValue;
        if (obj != null) {
            return obj.toString();
        }
        zzdal.e("Fail to convert a null object to string");
        return zzkuo;
    }

    public final List<Integer> zzbkm() {
        return this.zzlcm;
    }
}
