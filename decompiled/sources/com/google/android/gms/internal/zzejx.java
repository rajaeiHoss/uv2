package com.google.android.gms.internal;

public final class zzejx extends zzejy {
    private final zzegi zznjo;

    public zzejx(zzeka zzeka, zzegu zzegu, zzegi zzegi) {
        super(zzejz.Merge, zzeka, zzegu);
        this.zznjo = zzegi;
    }

    public final String toString() {
        return String.format("Merge { path=%s, source=%s, children=%s }", new Object[]{this.zzmxa, this.zznjq, this.zznjo});
    }

    public final zzegi zzbzr() {
        return this.zznjo;
    }

    public final zzejy zzc(zzemq zzemq) {
        if (this.zzmxa.isEmpty()) {
            zzegi zzg = this.zznjo.zzg(new zzegu(zzemq));
            if (zzg.isEmpty()) {
                return null;
            }
            return zzg.zzbya() != null ? new zzekc(this.zznjq, zzegu.zzbyn(), zzg.zzbya()) : new zzejx(this.zznjq, zzegu.zzbyn(), zzg);
        } else if (this.zzmxa.zzbyq().equals(zzemq)) {
            return new zzejx(this.zznjq, this.zzmxa.zzbyr(), this.zznjo);
        } else {
            return null;
        }
    }
}
