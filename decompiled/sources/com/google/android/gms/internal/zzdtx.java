package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdwg;
import java.security.GeneralSecurityException;

final class zzdtx implements zzdtf<zzdsy> {
    zzdtx() {
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzdsy zza(zzfgs zzfgs) throws GeneralSecurityException {
        try {
            zzdwx zzal = zzdwx.zzal(zzfgs);
            if (zzal instanceof zzdwx) {
                zzdwx zzdwx = zzal;
                zzdyp.zzw(zzdwx.getVersion(), 0);
                String zzbsi = zzdwx.zzbsf().zzbsi();
                return new zzdtw(zzdwx.zzbsf().zzbsj(), zzdti.zzol(zzbsi).zzok(zzbsi));
            }
            throw new GeneralSecurityException("expected KmsEnvelopeAeadKey proto");
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized KmSEnvelopeAeadKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final zzdsy zza(zzfjc zzfjc) throws GeneralSecurityException {
        if (zzfjc instanceof zzdwx) {
            zzdwx zzdwx = (zzdwx) zzfjc;
            zzdyp.zzw(zzdwx.getVersion(), 0);
            String zzbsi = zzdwx.zzbsf().zzbsi();
            return new zzdtw(zzdwx.zzbsf().zzbsj(), zzdti.zzol(zzbsi).zzok(zzbsi));
        }
        throw new GeneralSecurityException("expected KmsEnvelopeAeadKey proto");
    }

    public final zzfjc zzb(zzfgs zzfgs) throws GeneralSecurityException {
        try {
            return zzb((zzfjc) zzdwz.zzam(zzfgs));
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized KmsEnvelopeAeadKeyFormat proto", e);
        }
    }

    public final zzfjc zzb(zzfjc zzfjc) throws GeneralSecurityException {
        if (zzfjc instanceof zzdwz) {
            return zzdwx.zzbsg().zzb((zzdwz) zzfjc).zzgw(0).zzczx();
        }
        throw new GeneralSecurityException("expected KmsEnvelopeAeadKeyFormat proto");
    }

    public final zzdwg zzc(zzfgs zzfgs) throws GeneralSecurityException {
        return (zzdwg) zzdwg.zzbqx().zzop("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey").zzai(((zzdwx) zzb(zzfgs)).toByteString()).zzb(zzdwg.zzb.REMOTE).zzczx();
    }
}
