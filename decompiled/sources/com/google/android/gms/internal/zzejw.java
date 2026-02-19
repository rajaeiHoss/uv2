package com.google.android.gms.internal;

public final class zzejw extends zzejy {
    public zzejw(zzeka zzeka, zzegu zzegu) {
        super(zzejz.ListenComplete, zzeka, zzegu);
    }

    public final String toString() {
        return String.format("ListenComplete { path=%s, source=%s }", new Object[]{this.zzmxa, this.zznjq});
    }

    public final zzejy zzc(zzemq zzemq) {
        return this.zzmxa.isEmpty() ? new zzejw(this.zznjq, zzegu.zzbyn()) : new zzejw(this.zznjq, this.zzmxa.zzbyr());
    }
}
