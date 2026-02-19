package com.google.android.gms.internal;

public final class zzenx extends zzenf {
    private static final zzenx zznpo = new zzenx();

    private zzenx() {
    }

    public static zzenx zzccz() {
        return zznpo;
    }

    public final int compare(zzenm zzenm, zzenm zzenm2) {
        int compareTo = zzenm.zzbve().compareTo(zzenm2.zzbve());
        return compareTo == 0 ? zzenm.zzccx().compareTo(zzenm2.zzccx()) : compareTo;
    }

    public final boolean equals(Object obj) {
        return obj instanceof zzenx;
    }

    public final int hashCode() {
        return 4;
    }

    public final String toString() {
        return "ValueIndex";
    }

    public final zzenm zzccp() {
        return new zzenm(zzemq.zzcbx(), zzenn.zznpf);
    }

    public final String zzccq() {
        return ".value";
    }

    public final zzenm zzf(zzemq zzemq, zzenn zzenn) {
        return new zzenm(zzemq, zzenn);
    }

    public final boolean zzi(zzenn zzenn) {
        return true;
    }
}
