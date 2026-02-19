package com.google.android.gms.internal;

import com.streamax.config.constant.Constants;

public final class zzejn {
    private final boolean zzjdb;
    private final zzegu zzmxa;
    private final long zzniv;
    private final zzenn zzniw;
    private final zzegi zznix;

    public zzejn(long j, zzegu zzegu, zzegi zzegi) {
        this.zzniv = j;
        this.zzmxa = zzegu;
        this.zzniw = null;
        this.zznix = zzegi;
        this.zzjdb = true;
    }

    public zzejn(long j, zzegu zzegu, zzenn zzenn, boolean z) {
        this.zzniv = j;
        this.zzmxa = zzegu;
        this.zzniw = zzenn;
        this.zznix = null;
        this.zzjdb = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzejn zzejn = (zzejn) obj;
        if (this.zzniv != zzejn.zzniv || !this.zzmxa.equals(zzejn.zzmxa) || this.zzjdb != zzejn.zzjdb) {
            return false;
        }
        zzenn zzenn = this.zzniw;
        if (zzenn == null ? zzejn.zzniw != null : !zzenn.equals(zzejn.zzniw)) {
            return false;
        }
        zzegi zzegi = this.zznix;
        zzegi zzegi2 = zzejn.zznix;
        return zzegi == null ? zzegi2 == null : zzegi.equals(zzegi2);
    }

    public final int hashCode() {
        int hashCode = ((((Long.valueOf(this.zzniv).hashCode() * 31) + Boolean.valueOf(this.zzjdb).hashCode()) * 31) + this.zzmxa.hashCode()) * 31;
        zzenn zzenn = this.zzniw;
        int i = 0;
        int hashCode2 = (hashCode + (zzenn != null ? zzenn.hashCode() : 0)) * 31;
        zzegi zzegi = this.zznix;
        if (zzegi != null) {
            i = zzegi.hashCode();
        }
        return hashCode2 + i;
    }

    public final boolean isVisible() {
        return this.zzjdb;
    }

    public final String toString() {
        long j = this.zzniv;
        String valueOf = String.valueOf(this.zzmxa);
        boolean z = this.zzjdb;
        String valueOf2 = String.valueOf(this.zzniw);
        String valueOf3 = String.valueOf(this.zznix);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 78 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append("UserWriteRecord{id=");
        sb.append(j);
        sb.append(" path=");
        sb.append(valueOf);
        sb.append(" visible=");
        sb.append(z);
        sb.append(" overwrite=");
        sb.append(valueOf2);
        sb.append(" merge=");
        sb.append(valueOf3);
        sb.append(Constants.JsonSstringSuffix);
        return sb.toString();
    }

    public final zzegu zzbvh() {
        return this.zzmxa;
    }

    public final long zzbzh() {
        return this.zzniv;
    }

    public final zzenn zzbzi() {
        zzenn zzenn = this.zzniw;
        if (zzenn != null) {
            return zzenn;
        }
        throw new IllegalArgumentException("Can't access overwrite when write is a merge!");
    }

    public final zzegi zzbzj() {
        zzegi zzegi = this.zznix;
        if (zzegi != null) {
            return zzegi;
        }
        throw new IllegalArgumentException("Can't access merge when write is an overwrite!");
    }

    public final boolean zzbzk() {
        return this.zzniw != null;
    }
}
