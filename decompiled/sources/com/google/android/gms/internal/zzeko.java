package com.google.android.gms.internal;

import com.streamax.config.constant.Constants;

public final class zzeko {
    public final boolean complete;
    public final long id;
    public final boolean zzjgp;
    public final zzelu zznkr;
    public final long zznks;

    public zzeko(long j, zzelu zzelu, long j2, boolean z, boolean z2) {
        this.id = j;
        if (!zzelu.zzcbe() || zzelu.isDefault()) {
            this.zznkr = zzelu;
            this.zznks = j2;
            this.complete = z;
            this.zzjgp = z2;
            return;
        }
        throw new IllegalArgumentException("Can't create TrackedQuery for a non-default query that loads all data");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            zzeko zzeko = (zzeko) obj;
            return this.id == zzeko.id && this.zznkr.equals(zzeko.zznkr) && this.zznks == zzeko.zznks && this.complete == zzeko.complete && this.zzjgp == zzeko.zzjgp;
        }
        return false;
    }

    public final int hashCode() {
        return (((((((Long.valueOf(this.id).hashCode() * 31) + this.zznkr.hashCode()) * 31) + Long.valueOf(this.zznks).hashCode()) * 31) + Boolean.valueOf(this.complete).hashCode()) * 31) + Boolean.valueOf(this.zzjgp).hashCode();
    }

    public final String toString() {
        long j = this.id;
        String valueOf = String.valueOf(this.zznkr);
        long j2 = this.zznks;
        boolean z = this.complete;
        boolean z2 = this.zzjgp;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 109);
        sb.append("TrackedQuery{id=");
        sb.append(j);
        sb.append(", querySpec=");
        sb.append(valueOf);
        sb.append(", lastUse=");
        sb.append(j2);
        sb.append(", complete=");
        sb.append(z);
        sb.append(", active=");
        sb.append(z2);
        sb.append(Constants.JsonSstringSuffix);
        return sb.toString();
    }

    public final zzeko zzcac() {
        return new zzeko(this.id, this.zznkr, this.zznks, true, this.zzjgp);
    }
}
