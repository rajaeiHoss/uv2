package com.google.android.gms.internal;

final class zzefl {
    /* access modifiers changed from: private */
    public final zzefo zznbr;
    /* access modifiers changed from: private */
    public final zzefj zznbs;
    private final zzeev zznbt;
    private final Long zznbu;

    private zzefl(zzefo zzefo, zzefj zzefj, Long l, zzeev zzeev) {
        this.zznbr = zzefo;
        this.zznbs = zzefj;
        this.zznbt = zzeev;
        this.zznbu = l;
    }

    /* synthetic */ zzefl(zzefo zzefo, zzefj zzefj, Long l, zzeev zzeev, zzeez zzeez) {
        this(zzefo, zzefj, l, zzeev);
    }

    public final String toString() {
        String zzefj = this.zznbs.toString();
        String valueOf = String.valueOf(this.zznbu);
        StringBuilder sb = new StringBuilder(String.valueOf(zzefj).length() + 8 + String.valueOf(valueOf).length());
        sb.append(zzefj);
        sb.append(" (Tag: ");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }

    public final zzefj zzbxd() {
        return this.zznbs;
    }

    public final Long zzbxe() {
        return this.zznbu;
    }

    public final zzeev zzbxf() {
        return this.zznbt;
    }
}
