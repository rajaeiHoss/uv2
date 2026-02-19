package com.google.android.gms.internal;

import java.util.Map;

public final class zzelc<T> {
    private zzemq zznlh;
    private zzelc<T> zznli;
    private zzelg<T> zznlj;

    public zzelc() {
        this((zzemq) null, (zzelc) null, new zzelg());
    }

    private zzelc(zzemq zzemq, zzelc<T> zzelc, zzelg<T> zzelg) {
        this.zznlh = zzemq;
        this.zznli = zzelc;
        this.zznlj = zzelg;
    }

    private final void zzcah() {
        zzelc<T> zzelc = this;
        while (true) {
            zzelc<T> zzelc2 = zzelc.zznli;
            if (zzelc2 != null) {
                zzemq zzemq = zzelc.zznlh;
                boolean z = zzelc.zznlj.value == null && zzelc.zznlj.zznhn.isEmpty();
                boolean containsKey = zzelc2.zznlj.zznhn.containsKey(zzemq);
                if (z && containsKey) {
                    zzelc2.zznlj.zznhn.remove(zzemq);
                } else if (!z && !containsKey) {
                    zzelc2.zznlj.zznhn.put(zzemq, zzelc.zznlj);
                } else {
                    return;
                }
                zzelc = zzelc2;
            } else {
                return;
            }
        }
    }

    public final T getValue() {
        return this.zznlj.value;
    }

    public final boolean hasChildren() {
        return !this.zznlj.zznhn.isEmpty();
    }

    public final void setValue(T t) {
        this.zznlj.value = t;
        zzcah();
    }

    public final String toString() {
        zzemq zzemq = this.zznlh;
        String asString = zzemq == null ? "<anon>" : zzemq.asString();
        String zzelg = this.zznlj.toString("".concat("\t"));
        StringBuilder sb = new StringBuilder("".length() + 1 + String.valueOf(asString).length() + String.valueOf(zzelg).length());
        sb.append("");
        sb.append(asString);
        sb.append("\n");
        sb.append(zzelg);
        return sb.toString();
    }

    public final void zza(zzelf<T> zzelf) {
        Object[] array = this.zznlj.zznhn.entrySet().toArray();
        for (Object obj : array) {
            Map.Entry entry = (Map.Entry) obj;
            zzelf.zzd(new zzelc((zzemq) entry.getKey(), this, (zzelg) entry.getValue()));
        }
    }

    public final void zza(zzelf<T> zzelf, boolean z, boolean z2) {
        if (z && !z2) {
            zzelf.zzd(this);
        }
        zza(new zzeld(this, zzelf, z2));
        if (z && z2) {
            zzelf.zzd(this);
        }
    }

    public final boolean zza(zzele<T> zzele, boolean z) {
        for (zzelc<T> zzelc = this.zznli; zzelc != null; zzelc = zzelc.zznli) {
            zzele.zze(zzelc);
        }
        return false;
    }

    public final zzelc<T> zzak(zzegu zzegu) {
        zzemq zzbyq = zzegu.zzbyq();
        zzelc zzelc = this;
        while (zzbyq != null) {
            zzelg<T> child = zzelc.zznlj.zznhn.containsKey(zzbyq)
                    ? (zzelg<T>) zzelc.zznlj.zznhn.get(zzbyq)
                    : new zzelg<>();
            zzelc<T> zzelc2 = new zzelc(zzbyq, zzelc, child);
            zzegu = zzegu.zzbyr();
            zzbyq = zzegu.zzbyq();
            zzelc = zzelc2;
        }
        return zzelc;
    }

    public final zzegu zzbvh() {
        zzelc<T> zzelc = this.zznli;
        if (zzelc != null) {
            return zzelc.zzbvh().zza(this.zznlh);
        }
        if (this.zznlh == null) {
            return zzegu.zzbyn();
        }
        return new zzegu(this.zznlh);
    }
}
