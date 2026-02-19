package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class zzdjq<T> {
    protected Map<String, zzdjq<?>> zzlcq;

    public abstract String toString();

    public abstract T value();

    public Iterator<zzdjq<?>> zzbko() {
        return new zzdjs((zzdjr) null);
    }

    /* access modifiers changed from: protected */
    public final Iterator<zzdjq<?>> zzbkp() {
        Map<String, zzdjq<?>> map = this.zzlcq;
        return map == null ? new zzdjs((zzdjr) null) : new zzdjr(this, map.keySet().iterator());
    }

    public final void zzc(String str, zzdjq<?> zzdjq) {
        if (this.zzlcq == null) {
            this.zzlcq = new HashMap();
        }
        this.zzlcq.put(str, zzdjq);
    }

    public final boolean zzni(String str) {
        Map<String, zzdjq<?>> map = this.zzlcq;
        return map != null && map.containsKey(str);
    }

    public zzdjq<?> zznj(String str) {
        Map<String, zzdjq<?>> map = this.zzlcq;
        return map != null ? map.get(str) : zzdjw.zzlcz;
    }

    public boolean zznk(String str) {
        return false;
    }

    public zzdcp zznl(String str) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 56);
        sb.append("Attempting to access Native Method ");
        sb.append(str);
        sb.append(" on unsupported type.");
        throw new IllegalStateException(sb.toString());
    }
}
