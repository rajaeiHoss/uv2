package com.google.android.gms.internal;

public final class zzelx {
    private final zzelh zznna;
    private final zzelh zznnb;

    public zzelx(zzelh zzelh, zzelh zzelh2) {
        this.zznna = zzelh;
        this.zznnb = zzelh2;
    }

    public final zzelx zza(zzeng zzeng, boolean z, boolean z2) {
        return new zzelx(new zzelh(zzeng, z, z2), this.zznnb);
    }

    public final zzelx zzb(zzeng zzeng, boolean z, boolean z2) {
        return new zzelx(this.zznna, new zzelh(zzeng, z, z2));
    }

    public final zzelh zzcbl() {
        return this.zznna;
    }

    public final zzenn zzcbm() {
        if (this.zznna.zzcai()) {
            return this.zznna.zzbve();
        }
        return null;
    }

    public final zzelh zzcbn() {
        return this.zznnb;
    }

    public final zzenn zzcbo() {
        if (this.zznnb.zzcai()) {
            return this.zznnb.zzbve();
        }
        return null;
    }
}
