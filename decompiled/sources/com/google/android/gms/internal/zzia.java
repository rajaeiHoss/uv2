package com.google.android.gms.internal;

public final class zzia {
    final long value;
    final String zzbak;
    final int zzbal;

    zzia(long j, String str, int i) {
        this.value = j;
        this.zzbak = str;
        this.zzbal = i;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof zzia)) {
            zzia zzia = (zzia) obj;
            return zzia.value == this.value && zzia.zzbal == this.zzbal;
        }
        return false;
    }

    public final int hashCode() {
        return (int) this.value;
    }
}
