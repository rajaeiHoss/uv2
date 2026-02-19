package com.google.android.gms.internal;

public final class zzelh {
    private final zzeng zznlm;
    private final boolean zznln;
    private final boolean zznlo;

    public zzelh(zzeng zzeng, boolean z, boolean z2) {
        this.zznlm = zzeng;
        this.zznln = z;
        this.zznlo = z2;
    }

    public final boolean zzal(zzegu zzegu) {
        return zzegu.isEmpty() ? this.zznln && !this.zznlo : zzf(zzegu.zzbyq());
    }

    public final zzenn zzbve() {
        return this.zznlm.zzbve();
    }

    public final boolean zzcai() {
        return this.zznln;
    }

    public final boolean zzcaj() {
        return this.zznlo;
    }

    public final zzeng zzcak() {
        return this.zznlm;
    }

    public final boolean zzf(zzemq zzemq) {
        return (this.zznln && !this.zznlo) || this.zznlm.zzbve().zzk(zzemq);
    }
}
