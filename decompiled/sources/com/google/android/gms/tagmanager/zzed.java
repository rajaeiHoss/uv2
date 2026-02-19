package com.google.android.gms.tagmanager;

final class zzed implements zzfx {
    private /* synthetic */ zzec zzkrg;

    zzed(zzec zzec) {
        this.zzkrg = zzec;
    }

    public final void zza(zzbx zzbx) {
        this.zzkrg.zzp(zzbx.zzbgr());
    }

    public final void zzb(zzbx zzbx) {
        this.zzkrg.zzp(zzbx.zzbgr());
        long zzbgr = zzbx.zzbgr();
        StringBuilder sb = new StringBuilder(57);
        sb.append("Permanent failure dispatching hitId: ");
        sb.append(zzbgr);
        zzdj.v(sb.toString());
    }

    public final void zzc(zzbx zzbx) {
        long zzbgs = zzbx.zzbgs();
        if (zzbgs == 0) {
            this.zzkrg.zzh(zzbx.zzbgr(), this.zzkrg.zzata.currentTimeMillis());
        } else if (zzbgs + 14400000 < this.zzkrg.zzata.currentTimeMillis()) {
            this.zzkrg.zzp(zzbx.zzbgr());
            long zzbgr = zzbx.zzbgr();
            StringBuilder sb = new StringBuilder(47);
            sb.append("Giving up on failed hitId: ");
            sb.append(zzbgr);
            zzdj.v(sb.toString());
        }
    }
}
