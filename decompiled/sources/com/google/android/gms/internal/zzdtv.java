package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdwg;
import java.security.GeneralSecurityException;

final class zzdtv implements zzdtf<zzdsy> {
    zzdtv() {
    }

    private static zzdsy zzc(zzfjc zzfjc) throws GeneralSecurityException {
        if (zzfjc instanceof zzdwt) {
            zzdwt zzdwt = (zzdwt) zzfjc;
            zzdyp.zzw(zzdwt.getVersion(), 0);
            String zzbsc = zzdwt.zzbrz().zzbsc();
            return zzdti.zzol(zzbsc).zzok(zzbsc);
        }
        throw new GeneralSecurityException("expected KmsAeadKey proto");
    }

    private static zzdsy zzd(zzfgs zzfgs) throws GeneralSecurityException {
        try {
            return zzc((zzfjc) zzdwt.zzaj(zzfgs));
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected KmsAeadKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final zzdsy zza(zzfgs zzfgs) throws GeneralSecurityException {
        return zzd(zzfgs);
    }

    public final zzdsy zza(zzfjc zzfjc) throws GeneralSecurityException {
        return zzc(zzfjc);
    }

    public final zzfjc zzb(zzfgs zzfgs) throws GeneralSecurityException {
        try {
            return zzb((zzfjc) zzdwv.zzak(zzfgs));
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized KmsAeadKeyFormat proto", e);
        }
    }

    public final zzfjc zzb(zzfjc zzfjc) throws GeneralSecurityException {
        if (zzfjc instanceof zzdwv) {
            return zzdwt.zzbsa().zzb((zzdwv) zzfjc).zzgv(0).zzczx();
        }
        throw new GeneralSecurityException("expected KmsAeadKeyFormat proto");
    }

    public final zzdwg zzc(zzfgs zzfgs) throws GeneralSecurityException {
        return (zzdwg) zzdwg.zzbqx().zzop("type.googleapis.com/google.crypto.tink.KmsAeadKey").zzai(((zzdwt) zzb(zzfgs)).toByteString()).zzb(zzdwg.zzb.REMOTE).zzczx();
    }
}
