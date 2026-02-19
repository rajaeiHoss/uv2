package com.google.android.gms.internal;

final class zzdap implements zzdbm {
    private /* synthetic */ zzdao zzkxh;

    zzdap(zzdao zzdao) {
        this.zzkxh = zzdao;
    }

    public final void zza(zzczx zzczx) {
        this.zzkxh.zzp(zzczx.zzbgr());
    }

    public final void zzb(zzczx zzczx) {
        this.zzkxh.zzp(zzczx.zzbgr());
        long zzbgr = zzczx.zzbgr();
        StringBuilder sb = new StringBuilder(57);
        sb.append("Permanent failure dispatching hitId: ");
        sb.append(zzbgr);
        zzdal.v(sb.toString());
    }

    public final void zzc(zzczx zzczx) {
        long zzbgs = zzczx.zzbgs();
        if (zzbgs == 0) {
            this.zzkxh.zzh(zzczx.zzbgr(), this.zzkxh.zzata.currentTimeMillis());
        } else if (zzbgs + 14400000 < this.zzkxh.zzata.currentTimeMillis()) {
            this.zzkxh.zzp(zzczx.zzbgr());
            long zzbgr = zzczx.zzbgr();
            StringBuilder sb = new StringBuilder(47);
            sb.append("Giving up on failed hitId: ");
            sb.append(zzbgr);
            zzdal.v(sb.toString());
        }
    }
}
