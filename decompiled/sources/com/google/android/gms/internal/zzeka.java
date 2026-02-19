package com.google.android.gms.internal;

public final class zzeka {
    public static final zzeka zznjw = new zzeka(zzekb.User, (zzelr) null, false);
    public static final zzeka zznjx = new zzeka(zzekb.Server, (zzelr) null, false);
    private final zzekb zznjy;
    private final zzelr zznjz;
    private final boolean zznka;

    private zzeka(zzekb zzekb, zzelr zzelr, boolean z) {
        this.zznjy = zzekb;
        this.zznjz = zzelr;
        this.zznka = z;
    }

    public static zzeka zzc(zzelr zzelr) {
        return new zzeka(zzekb.Server, zzelr, true);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zznjy);
        String valueOf2 = String.valueOf(this.zznjz);
        boolean z = this.zznka;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 52 + String.valueOf(valueOf2).length());
        sb.append("OperationSource{source=");
        sb.append(valueOf);
        sb.append(", queryParams=");
        sb.append(valueOf2);
        sb.append(", tagged=");
        sb.append(z);
        sb.append('}');
        return sb.toString();
    }

    public final boolean zzbzu() {
        return this.zznjy == zzekb.User;
    }

    public final boolean zzbzv() {
        return this.zznka;
    }

    public final zzelr zzbzw() {
        return this.zznjz;
    }
}
