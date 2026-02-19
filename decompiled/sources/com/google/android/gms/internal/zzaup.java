package com.google.android.gms.internal;

public final class zzaup {
    private zzaua zzegg;
    private long zzegh = -1;
    private int zzegi = -1;
    private zzatx zzegj;
    private boolean zzegk = false;
    private int zzegl = -1;
    private int zzegm = 0;

    public final zzaup zza(zzatx zzatx) {
        this.zzegj = zzatx;
        return this;
    }

    public final zzaup zza(zzaua zzaua) {
        this.zzegg = zzaua;
        return this;
    }

    public final zzauo zzabr() {
        return new zzauo(this.zzegg, this.zzegh, this.zzegi, (String) null, this.zzegj, this.zzegk, this.zzegl, this.zzegm);
    }

    public final zzaup zzas(boolean z) {
        this.zzegk = z;
        return this;
    }

    public final zzaup zzax(int i) {
        this.zzegi = i;
        return this;
    }

    public final zzaup zzay(int i) {
        this.zzegm = i;
        return this;
    }

    public final zzaup zzv(long j) {
        this.zzegh = j;
        return this;
    }
}
