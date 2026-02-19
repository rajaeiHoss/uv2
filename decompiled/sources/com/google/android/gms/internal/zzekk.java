package com.google.android.gms.internal;

import com.streamax.config.constant.Constants;

public final class zzekk {
    private static final zzela<Boolean> zznkm = new zzekl();
    private static final zzela<Boolean> zznkn = new zzekm();
    private static final zzekw<Boolean> zznko = new zzekw<>(true);
    private static final zzekw<Boolean> zznkp = new zzekw<>(false);
    private final zzekw<Boolean> zznkl;

    public zzekk() {
        this.zznkl = zzekw.zzcaf();
    }

    private zzekk(zzekw<Boolean> zzekw) {
        this.zznkl = zzekw;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof zzekk) && this.zznkl.equals(((zzekk) obj).zznkl);
    }

    public final int hashCode() {
        return this.zznkl.hashCode();
    }

    public final String toString() {
        String zzekw = this.zznkl.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(zzekw).length() + 14);
        sb.append("{PruneForest:");
        sb.append(zzekw);
        sb.append(Constants.JsonSstringSuffix);
        return sb.toString();
    }

    public final <T> T zza(T t, zzekz<Void, T> zzekz) {
        return this.zznkl.zzb(t, new zzekn<>(this, zzekz));
    }

    public final boolean zzcab() {
        return this.zznkl.zzb(zznkn);
    }

    public final zzekk zzd(zzemq zzemq) {
        zzekw<Boolean> zze = this.zznkl.zze(zzemq);
        if (zze == null) {
            zze = new zzekw<>(this.zznkl.getValue());
        } else if (zze.getValue() == null && this.zznkl.getValue() != null) {
            zze = zze.zzb(zzegu.zzbyn(), this.zznkl.getValue());
        }
        return new zzekk(zze);
    }

    public final boolean zzv(zzegu zzegu) {
        Boolean zzag = this.zznkl.zzag(zzegu);
        return zzag != null && zzag.booleanValue();
    }

    public final boolean zzw(zzegu zzegu) {
        Boolean zzag = this.zznkl.zzag(zzegu);
        return zzag != null && !zzag.booleanValue();
    }

    public final zzekk zzx(zzegu zzegu) {
        if (this.zznkl.zzb(zzegu, zznkm) == null) {
            return this.zznkl.zzb(zzegu, zznkn) != null ? this : new zzekk(this.zznkl.zza(zzegu, zznko));
        }
        throw new IllegalArgumentException("Can't prune path that was kept previously!");
    }

    public final zzekk zzy(zzegu zzegu) {
        return this.zznkl.zzb(zzegu, zznkm) != null ? this : new zzekk(this.zznkl.zza(zzegu, zznkp));
    }
}
