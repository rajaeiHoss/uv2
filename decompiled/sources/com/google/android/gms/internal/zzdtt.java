package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdwg;
import java.security.GeneralSecurityException;

final class zzdtt implements zzdtf<zzdsy> {
    zzdtt() {
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzdsy zza(zzfgs zzfgs) throws GeneralSecurityException {
        try {
            zzdvc zzr = zzdvc.zzr(zzfgs);
            if (zzr instanceof zzdvc) {
                zzdvc zzdvc = zzr;
                zzdyp.zzw(zzdvc.getVersion(), 0);
                zzdyp.zzgz(zzdvc.zzbot().size());
                return new zzdxh(zzdvc.zzbot().toByteArray());
            }
            throw new GeneralSecurityException("expected AesGcmKey proto");
        } catch (zzfie unused) {
            throw new GeneralSecurityException("expected AesGcmKey proto");
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final zzdsy zza(zzfjc zzfjc) throws GeneralSecurityException {
        if (zzfjc instanceof zzdvc) {
            zzdvc zzdvc = (zzdvc) zzfjc;
            zzdyp.zzw(zzdvc.getVersion(), 0);
            zzdyp.zzgz(zzdvc.zzbot().size());
            return new zzdxh(zzdvc.zzbot().toByteArray());
        }
        throw new GeneralSecurityException("expected AesGcmKey proto");
    }

    public final zzfjc zzb(zzfgs zzfgs) throws GeneralSecurityException {
        try {
            return zzb((zzfjc) zzdve.zzt(zzfgs));
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized AesGcmKeyFormat proto", e);
        }
    }

    public final zzfjc zzb(zzfjc zzfjc) throws GeneralSecurityException {
        if (zzfjc instanceof zzdve) {
            zzdve zzdve = (zzdve) zzfjc;
            zzdyp.zzgz(zzdve.getKeySize());
            return zzdvc.zzbpi().zzs(zzfgs.zzaz(zzdyl.zzgy(zzdve.getKeySize()))).zzgf(0).zzczx();
        }
        throw new GeneralSecurityException("expected AesGcmKeyFormat proto");
    }

    public final zzdwg zzc(zzfgs zzfgs) throws GeneralSecurityException {
        return (zzdwg) zzdwg.zzbqx().zzop("type.googleapis.com/google.crypto.tink.AesGcmKey").zzai(((zzdvc) zzb(zzfgs)).toByteString()).zzb(zzdwg.zzb.SYMMETRIC).zzczx();
    }
}
