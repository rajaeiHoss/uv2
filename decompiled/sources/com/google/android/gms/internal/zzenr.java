package com.google.android.gms.internal;

public final class zzenr extends zzenf {
    private final zzegu zznpj;

    public zzenr(zzegu zzegu) {
        if (zzegu.size() != 1 || !zzegu.zzbyq().zzcca()) {
            this.zznpj = zzegu;
            return;
        }
        throw new IllegalArgumentException("Can't create PathIndex with '.priority' as key. Please use PriorityIndex instead!");
    }

    public final int compare(zzenm zzenm, zzenm zzenm2) {
        int compareTo = zzenm.zzbve().zzan(this.zznpj).compareTo(zzenm2.zzbve().zzan(this.zznpj));
        return compareTo == 0 ? zzenm.zzccx().compareTo(zzenm2.zzccx()) : compareTo;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.zznpj.equals(((zzenr) obj).zznpj);
    }

    public final int hashCode() {
        return this.zznpj.hashCode();
    }

    public final zzenm zzccp() {
        return new zzenm(zzemq.zzcbx(), zzene.zzcco().zzl(this.zznpj, zzenn.zznpf));
    }

    public final String zzccq() {
        return this.zznpj.zzbyo();
    }

    public final zzenm zzf(zzemq zzemq, zzenn zzenn) {
        return new zzenm(zzemq, zzene.zzcco().zzl(this.zznpj, zzenn));
    }

    public final boolean zzi(zzenn zzenn) {
        return !zzenn.zzan(this.zznpj).isEmpty();
    }
}
