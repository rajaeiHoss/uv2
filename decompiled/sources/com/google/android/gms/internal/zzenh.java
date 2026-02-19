package com.google.android.gms.internal;

public final class zzenh extends zzenf {
    private static final zzenh zznow = new zzenh();

    private zzenh() {
    }

    public static zzenh zzccu() {
        return zznow;
    }

    public final int compare(zzenm zzenm, zzenm zzenm2) {
        return zzenm.zzccx().compareTo(zzenm2.zzccx());
    }

    public final boolean equals(Object obj) {
        return obj instanceof zzenh;
    }

    public final int hashCode() {
        return 37;
    }

    public final String toString() {
        return "KeyIndex";
    }

    public final zzenm zzccp() {
        return zzenm.zzccw();
    }

    public final String zzccq() {
        return ".key";
    }

    public final zzenm zzf(zzemq zzemq, zzenn zzenn) {
        return new zzenm(zzemq.zzqf((String) zzenn.getValue()), zzene.zzcco());
    }

    public final boolean zzi(zzenn zzenn) {
        return true;
    }
}
