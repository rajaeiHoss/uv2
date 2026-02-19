package com.google.android.gms.internal;

public final class zzelu {
    private final zzegu zzmxa;
    private final zzelr zzmxe;

    public zzelu(zzegu zzegu, zzelr zzelr) {
        this.zzmxa = zzegu;
        this.zzmxe = zzelr;
    }

    public static zzelu zzam(zzegu zzegu) {
        return new zzelu(zzegu, zzelr.zznmi);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzelu zzelu = (zzelu) obj;
        return this.zzmxa.equals(zzelu.zzmxa) && this.zzmxe.equals(zzelu.zzmxe);
    }

    public final int hashCode() {
        return (this.zzmxa.hashCode() * 31) + this.zzmxe.hashCode();
    }

    public final boolean isDefault() {
        return this.zzmxe.isDefault();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzmxa);
        String valueOf2 = String.valueOf(this.zzmxe);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length());
        sb.append(valueOf);
        sb.append(":");
        sb.append(valueOf2);
        return sb.toString();
    }

    public final zzegu zzbvh() {
        return this.zzmxa;
    }

    public final zzenf zzcba() {
        return this.zzmxe.zzcba();
    }

    public final boolean zzcbe() {
        return this.zzmxe.zzcbe();
    }

    public final zzelr zzcbh() {
        return this.zzmxe;
    }
}
