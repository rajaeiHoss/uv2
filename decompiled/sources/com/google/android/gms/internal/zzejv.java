package com.google.android.gms.internal;

public final class zzejv extends zzejy {
    private final boolean zznjm;
    private final zzekw<Boolean> zznjn;

    public zzejv(zzegu zzegu, zzekw<Boolean> zzekw, boolean z) {
        super(zzejz.AckUserWrite, zzeka.zznjw, zzegu);
        this.zznjn = zzekw;
        this.zznjm = z;
    }

    public final String toString() {
        return String.format("AckUserWrite { path=%s, revert=%s, affectedTree=%s }", new Object[]{this.zzmxa, Boolean.valueOf(this.zznjm), this.zznjn});
    }

    public final zzekw<Boolean> zzbzp() {
        return this.zznjn;
    }

    public final boolean zzbzq() {
        return this.zznjm;
    }

    public final zzejy zzc(zzemq zzemq) {
        if (!this.zzmxa.isEmpty()) {
            zzepd.zzb(this.zzmxa.zzbyq().equals(zzemq), "operationForChild called for unrelated child.");
            return new zzejv(this.zzmxa.zzbyr(), this.zznjn, this.zznjm);
        } else if (this.zznjn.getValue() != null) {
            zzepd.zzb(this.zznjn.zzcag().isEmpty(), "affectedTree should not have overlapping affected paths.");
            return this;
        } else {
            return new zzejv(zzegu.zzbyn(), this.zznjn.zzah(new zzegu(zzemq)), this.zznjm);
        }
    }
}
