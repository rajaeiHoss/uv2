package com.google.android.gms.gcm;

import android.os.Bundle;

public final class zzi {
    public static final zzi zzikn = new zzi(0, 30, 3600);
    private static zzi zziko = new zzi(1, 30, 3600);
    private final int zzikp;
    private final int zzikq = 30;
    private final int zzikr = 3600;

    private zzi(int i, int i2, int i3) {
        this.zzikp = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzi)) {
            return false;
        }
        zzi zzi = (zzi) obj;
        return zzi.zzikp == this.zzikp && zzi.zzikq == this.zzikq && zzi.zzikr == this.zzikr;
    }

    public final int hashCode() {
        return (((((this.zzikp + 1) ^ 1000003) * 1000003) ^ this.zzikq) * 1000003) ^ this.zzikr;
    }

    public final String toString() {
        int i = this.zzikp;
        int i2 = this.zzikq;
        int i3 = this.zzikr;
        StringBuilder sb = new StringBuilder(74);
        sb.append("policy=");
        sb.append(i);
        sb.append(" initial_backoff=");
        sb.append(i2);
        sb.append(" maximum_backoff=");
        sb.append(i3);
        return sb.toString();
    }

    public final int zzawi() {
        return this.zzikp;
    }

    public final int zzawj() {
        return this.zzikq;
    }

    public final int zzawk() {
        return this.zzikr;
    }

    public final Bundle zzv(Bundle bundle) {
        bundle.putInt("retry_policy", this.zzikp);
        bundle.putInt("initial_backoff_seconds", this.zzikq);
        bundle.putInt("maximum_backoff_seconds", this.zzikr);
        return bundle;
    }
}
