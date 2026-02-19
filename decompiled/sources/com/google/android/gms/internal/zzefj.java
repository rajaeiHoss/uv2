package com.google.android.gms.internal;

import java.util.List;
import java.util.Map;

final class zzefj {
    /* access modifiers changed from: private */
    public final List<String> zznbn;
    /* access modifiers changed from: private */
    public final Map<String, Object> zznbo;

    public zzefj(List<String> list, Map<String, Object> map) {
        this.zznbn = list;
        this.zznbo = map;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzefj)) {
            return false;
        }
        zzefj zzefj = (zzefj) obj;
        if (!this.zznbn.equals(zzefj.zznbn)) {
            return false;
        }
        return this.zznbo.equals(zzefj.zznbo);
    }

    public final int hashCode() {
        return (this.zznbn.hashCode() * 31) + this.zznbo.hashCode();
    }

    public final String toString() {
        String zzau = zzeet.zzau(this.zznbn);
        String valueOf = String.valueOf(this.zznbo);
        StringBuilder sb = new StringBuilder(String.valueOf(zzau).length() + 11 + String.valueOf(valueOf).length());
        sb.append(zzau);
        sb.append(" (params: ");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }
}
