package com.google.android.gms.internal;

public final class zzelj {
    private final zzeng zznlm;
    private final zzelm zznlr;
    private final zzeng zznls;
    private final zzemq zznlt;
    private final zzemq zznlu;

    private zzelj(zzelm zzelm, zzeng zzeng, zzemq zzemq, zzemq zzemq2, zzeng zzeng2) {
        this.zznlr = zzelm;
        this.zznlm = zzeng;
        this.zznlt = zzemq;
        this.zznlu = zzemq2;
        this.zznls = zzeng2;
    }

    public static zzelj zza(zzemq zzemq, zzeng zzeng) {
        return new zzelj(zzelm.CHILD_ADDED, zzeng, zzemq, (zzemq) null, (zzeng) null);
    }

    public static zzelj zza(zzemq zzemq, zzeng zzeng, zzeng zzeng2) {
        return new zzelj(zzelm.CHILD_CHANGED, zzeng, zzemq, (zzemq) null, zzeng2);
    }

    public static zzelj zza(zzemq zzemq, zzenn zzenn, zzenn zzenn2) {
        return zza(zzemq, zzeng.zzj(zzenn), zzeng.zzj(zzenn2));
    }

    public static zzelj zza(zzeng zzeng) {
        return new zzelj(zzelm.VALUE, zzeng, (zzemq) null, (zzemq) null, (zzeng) null);
    }

    public static zzelj zzb(zzemq zzemq, zzeng zzeng) {
        return new zzelj(zzelm.CHILD_REMOVED, zzeng, zzemq, (zzemq) null, (zzeng) null);
    }

    public static zzelj zzc(zzemq zzemq, zzeng zzeng) {
        return new zzelj(zzelm.CHILD_MOVED, zzeng, zzemq, (zzemq) null, (zzeng) null);
    }

    public static zzelj zzc(zzemq zzemq, zzenn zzenn) {
        return zza(zzemq, zzeng.zzj(zzenn));
    }

    public static zzelj zzd(zzemq zzemq, zzenn zzenn) {
        return zzb(zzemq, zzeng.zzj(zzenn));
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zznlr);
        String valueOf2 = String.valueOf(this.zznlt);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 9 + String.valueOf(valueOf2).length());
        sb.append("Change: ");
        sb.append(valueOf);
        sb.append(" ");
        sb.append(valueOf2);
        return sb.toString();
    }

    public final zzeng zzcak() {
        return this.zznlm;
    }

    public final zzemq zzcam() {
        return this.zznlt;
    }

    public final zzelm zzcan() {
        return this.zznlr;
    }

    public final zzemq zzcao() {
        return this.zznlu;
    }

    public final zzeng zzcap() {
        return this.zznls;
    }

    public final zzelj zzg(zzemq zzemq) {
        return new zzelj(this.zznlr, this.zznlm, this.zznlt, zzemq, this.zznls);
    }
}
