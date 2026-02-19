package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdwg;
import java.security.GeneralSecurityException;

final class zzdtu implements zzdtf<zzdsy> {
    zzdtu() {
    }

    private static zzdvg zzboj() throws GeneralSecurityException {
        return (zzdvg) zzdvg.zzbpl().zzgg(0).zzv(zzfgs.zzaz(zzdyl.zzgy(32))).zzczx();
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzdsy zza(zzfgs zzfgs) throws GeneralSecurityException {
        try {
            zzdvg zzu = zzdvg.zzu(zzfgs);
            if (zzu instanceof zzdvg) {
                zzdvg zzdvg = zzu;
                zzdyp.zzw(zzdvg.getVersion(), 0);
                if (zzdvg.zzbot().size() == 32) {
                    return new zzdxl(zzdvg.zzbot().toByteArray());
                }
                throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
            }
            throw new GeneralSecurityException("expected ChaCha20Poly1305Key proto");
        } catch (zzfie e) {
            throw new GeneralSecurityException("invalid ChaCha20Poly1305 key", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final zzdsy zza(zzfjc zzfjc) throws GeneralSecurityException {
        if (zzfjc instanceof zzdvg) {
            zzdvg zzdvg = (zzdvg) zzfjc;
            zzdyp.zzw(zzdvg.getVersion(), 0);
            if (zzdvg.zzbot().size() == 32) {
                return new zzdxl(zzdvg.zzbot().toByteArray());
            }
            throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
        }
        throw new GeneralSecurityException("expected ChaCha20Poly1305Key proto");
    }

    public final zzfjc zzb(zzfgs zzfgs) throws GeneralSecurityException {
        return zzboj();
    }

    public final zzfjc zzb(zzfjc zzfjc) throws GeneralSecurityException {
        return zzboj();
    }

    public final zzdwg zzc(zzfgs zzfgs) throws GeneralSecurityException {
        return (zzdwg) zzdwg.zzbqx().zzop("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key").zzai(zzboj().toByteString()).zzb(zzdwg.zzb.SYMMETRIC).zzczx();
    }
}
