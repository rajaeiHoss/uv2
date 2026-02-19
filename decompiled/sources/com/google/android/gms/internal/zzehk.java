package com.google.android.gms.internal;

final class zzehk implements zzegf {
    private /* synthetic */ zzegx zznfo;

    zzehk(zzegx zzegx) {
        this.zznfo = zzegx;
    }

    public final void zzqa(String str) {
        this.zznfo.zznfe.zzb("Auth token changed, triggering auth token refresh", (Throwable) null, new Object[0]);
        this.zznfo.zzncx.zzpt(str);
    }
}
