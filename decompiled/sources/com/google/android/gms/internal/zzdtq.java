package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdwg;
import java.security.GeneralSecurityException;
import java.util.logging.Logger;

class zzdtq implements zzdtf<zzdsy> {
    private static final Logger logger = Logger.getLogger(zzdtq.class.getName());

    zzdtq() throws GeneralSecurityException {
        zzdtn.zza("type.googleapis.com/google.crypto.tink.AesCtrKey", new zzdtr());
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzdsy zza(zzfgs zzfgs) throws GeneralSecurityException {
        try {
            zzdum zzi = zzdum.zzi(zzfgs);
            if (zzi instanceof zzdum) {
                zzdum zzdum = zzi;
                zzdyp.zzw(zzdum.getVersion(), 0);
                return new zzdxw((zzdyi) zzdtn.zzb("type.googleapis.com/google.crypto.tink.AesCtrKey", zzdum.zzbol()), (zzdtj) zzdtn.zzb("type.googleapis.com/google.crypto.tink.HmacKey", zzdum.zzbom()), zzdum.zzbom().zzbqk().zzbqr());
            }
            throw new GeneralSecurityException("expected AesCtrHmacAeadKey proto");
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized AesCtrHmacAeadKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final zzdsy zza(zzfjc zzfjc) throws GeneralSecurityException {
        if (zzfjc instanceof zzdum) {
            zzdum zzdum = (zzdum) zzfjc;
            zzdyp.zzw(zzdum.getVersion(), 0);
            return new zzdxw((zzdyi) zzdtn.zzb("type.googleapis.com/google.crypto.tink.AesCtrKey", zzdum.zzbol()), (zzdtj) zzdtn.zzb("type.googleapis.com/google.crypto.tink.HmacKey", zzdum.zzbom()), zzdum.zzbom().zzbqk().zzbqr());
        }
        throw new GeneralSecurityException("expected AesCtrHmacAeadKey proto");
    }

    public final zzfjc zzb(zzfgs zzfgs) throws GeneralSecurityException {
        try {
            return zzb((zzfjc) zzduo.zzj(zzfgs));
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized AesCtrHmacAeadKeyFormat proto", e);
        }
    }

    public final zzfjc zzb(zzfjc zzfjc) throws GeneralSecurityException {
        if (zzfjc instanceof zzduo) {
            zzduo zzduo = (zzduo) zzfjc;
            return zzdum.zzbon().zzb((zzduq) zzdtn.zza("type.googleapis.com/google.crypto.tink.AesCtrKey", (zzfjc) zzduo.zzbop())).zzb((zzdwa) zzdtn.zza("type.googleapis.com/google.crypto.tink.HmacKey", (zzfjc) zzduo.zzboq())).zzgc(0).zzczx();
        }
        throw new GeneralSecurityException("expected AesCtrHmacAeadKeyFormat proto");
    }

    public final zzdwg zzc(zzfgs zzfgs) throws GeneralSecurityException {
        return (zzdwg) zzdwg.zzbqx().zzop("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey").zzai(((zzdum) zzb(zzfgs)).toByteString()).zzb(zzdwg.zzb.SYMMETRIC).zzczx();
    }
}
