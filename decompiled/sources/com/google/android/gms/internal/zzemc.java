package com.google.android.gms.internal;

final class zzemc implements zzemh {
    private final zzelx zznmv;
    private final zzejt zznnf;
    private final zzenn zznng;

    public zzemc(zzejt zzejt, zzelx zzelx, zzenn zzenn) {
        this.zznnf = zzejt;
        this.zznmv = zzelx;
        this.zznng = zzenn;
    }

    public final zzenm zza(zzenf zzenf, zzenm zzenm, boolean z) {
        zzenn zzenn = this.zznng;
        if (zzenn == null) {
            zzenn = this.zznmv.zzcbo();
        }
        return this.zznnf.zza(zzenn, zzenm, z, zzenf);
    }

    public final zzenn zzh(zzemq zzemq) {
        zzelh zzcbl = this.zznmv.zzcbl();
        if (zzcbl.zzf(zzemq)) {
            return zzcbl.zzbve().zzm(zzemq);
        }
        zzenn zzenn = this.zznng;
        return this.zznnf.zza(zzemq, zzenn != null ? new zzelh(zzeng.zza(zzenn, zzenh.zzccu()), true, false) : this.zznmv.zzcbn());
    }
}
