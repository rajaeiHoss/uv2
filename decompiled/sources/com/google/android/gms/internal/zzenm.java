package com.google.android.gms.internal;

public final class zzenm {
    private static final zzenm zznpd = new zzenm(zzemq.zzcbw(), zzene.zzcco());
    private static final zzenm zznpe = new zzenm(zzemq.zzcbx(), zzenn.zznpf);
    private final zzemq zznlh;
    private final zzenn zznou;

    public zzenm(zzemq zzemq, zzenn zzenn) {
        this.zznlh = zzemq;
        this.zznou = zzenn;
    }

    public static zzenm zzccv() {
        return zznpd;
    }

    public static zzenm zzccw() {
        return zznpe;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzenm zzenm = (zzenm) obj;
        return this.zznlh.equals(zzenm.zznlh) && this.zznou.equals(zzenm.zznou);
    }

    public final int hashCode() {
        return (this.zznlh.hashCode() * 31) + this.zznou.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zznlh);
        String valueOf2 = String.valueOf(this.zznou);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 23 + String.valueOf(valueOf2).length());
        sb.append("NamedNode{name=");
        sb.append(valueOf);
        sb.append(", node=");
        sb.append(valueOf2);
        sb.append('}');
        return sb.toString();
    }

    public final zzenn zzbve() {
        return this.zznou;
    }

    public final zzemq zzccx() {
        return this.zznlh;
    }
}
