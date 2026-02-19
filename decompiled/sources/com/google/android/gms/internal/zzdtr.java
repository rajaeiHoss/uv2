package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdwg;
import java.security.GeneralSecurityException;

final class zzdtr implements zzdtf<zzdyi> {
    zzdtr() {
    }

    private static void zza(zzduu zzduu) throws GeneralSecurityException {
        if (zzduu.zzboz() < 12 || zzduu.zzboz() > 16) {
            throw new GeneralSecurityException("invalid IV size");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: zze */
    public final zzdxf zza(zzfgs zzfgs) throws GeneralSecurityException {
        try {
            zzduq zzl = zzduq.zzl(zzfgs);
            if (zzl instanceof zzduq) {
                zzduq zzduq = zzl;
                zzdyp.zzw(zzduq.getVersion(), 0);
                zzdyp.zzgz(zzduq.zzbot().size());
                zza(zzduq.zzbos());
                return new zzdxf(zzduq.zzbot().toByteArray(), zzduq.zzbos().zzboz());
            }
            throw new GeneralSecurityException("expected AesCtrKey proto");
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized AesCtrKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final zzdyi zza(zzfjc zzfjc) throws GeneralSecurityException {
        if (zzfjc instanceof zzduq) {
            zzduq zzduq = (zzduq) zzfjc;
            zzdyp.zzw(zzduq.getVersion(), 0);
            zzdyp.zzgz(zzduq.zzbot().size());
            zza(zzduq.zzbos());
            return new zzdxf(zzduq.zzbot().toByteArray(), zzduq.zzbos().zzboz());
        }
        throw new GeneralSecurityException("expected AesCtrKey proto");
    }

    public final zzfjc zzb(zzfgs zzfgs) throws GeneralSecurityException {
        try {
            return zzb((zzfjc) zzdus.zzn(zzfgs));
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized AesCtrKeyFormat proto", e);
        }
    }

    public final zzfjc zzb(zzfjc zzfjc) throws GeneralSecurityException {
        if (zzfjc instanceof zzdus) {
            zzdus zzdus = (zzdus) zzfjc;
            zzdyp.zzgz(zzdus.getKeySize());
            zza(zzdus.zzbos());
            return zzduq.zzbou().zzc(zzdus.zzbos()).zzm(zzfgs.zzaz(zzdyl.zzgy(zzdus.getKeySize()))).zzgd(0).zzczx();
        }
        throw new GeneralSecurityException("expected AesCtrKeyFormat proto");
    }

    public final zzdwg zzc(zzfgs zzfgs) throws GeneralSecurityException {
        return (zzdwg) zzdwg.zzbqx().zzop("type.googleapis.com/google.crypto.tink.AesCtrKey").zzai(((zzduq) zzb(zzfgs)).toByteString()).zzb(zzdwg.zzb.SYMMETRIC).zzczx();
    }
}
