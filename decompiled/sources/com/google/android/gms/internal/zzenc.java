package com.google.android.gms.internal;

import java.util.Map;

public final class zzenc extends zzeni<zzenc> {
    private Map<Object, Object> zznoq;

    public zzenc(Map<Object, Object> map, zzenn zzenn) {
        super(zzenn);
        this.zznoq = map;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzenc)) {
            return false;
        }
        zzenc zzenc = (zzenc) obj;
        return this.zznoq.equals(zzenc.zznoq) && this.zznob.equals(zzenc.zznob);
    }

    public final Object getValue() {
        return this.zznoq;
    }

    public final int hashCode() {
        return this.zznoq.hashCode() + this.zznob.hashCode();
    }

    /* access modifiers changed from: protected */
    public final int zza(zzenc zzenc) {
        return 0;
    }

    public final String zza(zzenp zzenp) {
        String zzb = zzb(zzenp);
        String valueOf = String.valueOf(this.zznoq);
        StringBuilder sb = new StringBuilder(String.valueOf(zzb).length() + 14 + String.valueOf(valueOf).length());
        sb.append(zzb);
        sb.append("deferredValue:");
        sb.append(valueOf);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final zzenk zzcbv() {
        return zzenk.DeferredValue;
    }

    public final /* synthetic */ zzenn zzf(zzenn zzenn) {
        return new zzenc(this.zznoq, zzenn);
    }
}
