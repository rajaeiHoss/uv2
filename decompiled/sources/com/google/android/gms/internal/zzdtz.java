package com.google.android.gms.internal;

import java.security.GeneralSecurityException;

final class zzdtz implements zzdtf<zzdte> {
    zzdtz() {
    }

    /* access modifiers changed from: private */
    /* renamed from: zzg */
    public final zzdte zza(zzfgs zzfgs) throws GeneralSecurityException {
        try {
            zzdvs zzab = zzdvs.zzab(zzfgs);
            if (zzab instanceof zzdvs) {
                zzdvs zzdvs = zzab;
                zzdyp.zzw(zzdvs.getVersion(), 0);
                zzduf.zza(zzdvs.zzbpq());
                zzdvo zzbpq = zzdvs.zzbpq();
                zzdvu zzbps = zzbpq.zzbps();
                return new zzdxo(zzdxs.zza(zzduf.zza(zzbps.zzbqf()), zzdvs.zzbqa().toByteArray(), zzdvs.zzbqb().toByteArray()), zzbps.zzbqh().toByteArray(), zzduf.zza(zzbps.zzbqg()), zzduf.zza(zzbpq.zzbpu()), new zzduh(zzbpq.zzbpt().zzbpn()));
            }
            throw new GeneralSecurityException("expected EciesAeadHkdfPublicKey proto");
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized EciesAeadHkdfPublicKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final zzdte zza(zzfjc zzfjc) throws GeneralSecurityException {
        if (zzfjc instanceof zzdvs) {
            zzdvs zzdvs = (zzdvs) zzfjc;
            zzdyp.zzw(zzdvs.getVersion(), 0);
            zzduf.zza(zzdvs.zzbpq());
            zzdvo zzbpq = zzdvs.zzbpq();
            zzdvu zzbps = zzbpq.zzbps();
            return new zzdxo(zzdxs.zza(zzduf.zza(zzbps.zzbqf()), zzdvs.zzbqa().toByteArray(), zzdvs.zzbqb().toByteArray()), zzbps.zzbqh().toByteArray(), zzduf.zza(zzbps.zzbqg()), zzduf.zza(zzbpq.zzbpu()), new zzduh(zzbpq.zzbpt().zzbpn()));
        }
        throw new GeneralSecurityException("expected EciesAeadHkdfPublicKey proto");
    }

    public final zzfjc zzb(zzfgs zzfgs) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }

    public final zzfjc zzb(zzfjc zzfjc) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }

    public final zzdwg zzc(zzfgs zzfgs) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }
}
