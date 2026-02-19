package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdwg;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;

final class zzdty implements zzdtf<zzdtd> {
    zzdty() {
    }

    /* access modifiers changed from: private */
    /* renamed from: zzf */
    public final zzdtd zza(zzfgs zzfgs) throws GeneralSecurityException {
        try {
            zzdvq zzx = zzdvq.zzx(zzfgs);
            if (zzx instanceof zzdvq) {
                zzdvq zzdvq = zzx;
                zzdyp.zzw(zzdvq.getVersion(), 0);
                zzduf.zza(zzdvq.zzbpx().zzbpq());
                zzdvo zzbpq = zzdvq.zzbpx().zzbpq();
                zzdvu zzbps = zzbpq.zzbps();
                zzdxu zza = zzduf.zza(zzbps.zzbqf());
                byte[] byteArray = zzdvq.zzbot().toByteArray();
                ECPrivateKeySpec eCPrivateKeySpec = new ECPrivateKeySpec(new BigInteger(1, byteArray), zzdxs.zza(zza));
                return new zzdxn((ECPrivateKey) zzdxx.zzmlq.zzoy("EC").generatePrivate(eCPrivateKeySpec), zzbps.zzbqh().toByteArray(), zzduf.zza(zzbps.zzbqg()), zzduf.zza(zzbpq.zzbpu()), new zzduh(zzbpq.zzbpt().zzbpn()));
            }
            throw new GeneralSecurityException("expected EciesAeadHkdfPrivateKey proto");
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized EciesAeadHkdfPrivateKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final zzdtd zza(zzfjc zzfjc) throws GeneralSecurityException {
        if (zzfjc instanceof zzdvq) {
            zzdvq zzdvq = (zzdvq) zzfjc;
            zzdyp.zzw(zzdvq.getVersion(), 0);
            zzduf.zza(zzdvq.zzbpx().zzbpq());
            zzdvo zzbpq = zzdvq.zzbpx().zzbpq();
            zzdvu zzbps = zzbpq.zzbps();
            zzdxu zza = zzduf.zza(zzbps.zzbqf());
            byte[] byteArray = zzdvq.zzbot().toByteArray();
            ECPrivateKeySpec eCPrivateKeySpec = new ECPrivateKeySpec(new BigInteger(1, byteArray), zzdxs.zza(zza));
            return new zzdxn((ECPrivateKey) zzdxx.zzmlq.zzoy("EC").generatePrivate(eCPrivateKeySpec), zzbps.zzbqh().toByteArray(), zzduf.zza(zzbps.zzbqg()), zzduf.zza(zzbpq.zzbpu()), new zzduh(zzbpq.zzbpt().zzbpn()));
        }
        throw new GeneralSecurityException("expected EciesAeadHkdfPrivateKey proto");
    }

    public final zzfjc zzb(zzfgs zzfgs) throws GeneralSecurityException {
        try {
            return zzb((zzfjc) zzdvm.zzw(zzfgs));
        } catch (zzfie e) {
            throw new GeneralSecurityException("invalid EciesAeadHkdf key format", e);
        }
    }

    public final zzfjc zzb(zzfjc zzfjc) throws GeneralSecurityException {
        if (zzfjc instanceof zzdvm) {
            zzdvm zzdvm = (zzdvm) zzfjc;
            zzduf.zza(zzdvm.zzbpq());
            KeyPair zza = zzdxs.zza(zzdxs.zza(zzduf.zza(zzdvm.zzbpq().zzbps().zzbqf())));
            ECPoint w = ((ECPublicKey) zza.getPublic()).getW();
            return zzdvq.zzbpy().zzgi(0).zzb((zzdvs) zzdvs.zzbqc().zzgj(0).zzc(zzdvm.zzbpq()).zzac(zzfgs.zzaz(w.getAffineX().toByteArray())).zzad(zzfgs.zzaz(w.getAffineY().toByteArray())).zzczx()).zzy(zzfgs.zzaz(((ECPrivateKey) zza.getPrivate()).getS().toByteArray())).zzczx();
        }
        throw new GeneralSecurityException("expected EciesAeadHkdfKeyFormat proto");
    }

    public final zzdwg zzc(zzfgs zzfgs) throws GeneralSecurityException {
        return (zzdwg) zzdwg.zzbqx().zzop("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey").zzai(((zzdvq) zzb(zzfgs)).toByteString()).zzb(zzdwg.zzb.ASYMMETRIC_PRIVATE).zzczx();
    }
}
