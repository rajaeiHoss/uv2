package com.google.android.gms.internal;

public final class zzens extends zzenf {
    private static final zzens zznpk = new zzens();

    private zzens() {
    }

    public static zzens zzccy() {
        return zznpk;
    }

    public final int compare(zzenm zzenm, zzenm zzenm2) {
        zzenn zzcce = zzenm.zzbve().zzcce();
        zzenn zzcce2 = zzenm2.zzbve().zzcce();
        zzemq zzccx = zzenm.zzccx();
        zzemq zzccx2 = zzenm2.zzccx();
        int compareTo = zzcce.compareTo(zzcce2);
        return compareTo != 0 ? compareTo : zzccx.compareTo(zzccx2);
    }

    public final boolean equals(Object obj) {
        return obj instanceof zzens;
    }

    public final int hashCode() {
        return 3155577;
    }

    public final String toString() {
        return "PriorityIndex";
    }

    public final zzenm zzccp() {
        return zzf(zzemq.zzcbx(), zzenn.zznpf);
    }

    public final String zzccq() {
        throw new IllegalArgumentException("Can't get query definition on priority index!");
    }

    public final zzenm zzf(zzemq zzemq, zzenn zzenn) {
        return new zzenm(zzemq, new zzenv("[PRIORITY-POST]", zzenn));
    }

    public final boolean zzi(zzenn zzenn) {
        return !zzenn.zzcce().isEmpty();
    }
}
