package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdwg;
import java.security.GeneralSecurityException;

final class zzdts implements zzdtf<zzdsy> {
    zzdts() {
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzdsy zza(zzfgs zzfgs) throws GeneralSecurityException {
        try {
            zzduw zzo = zzduw.zzo(zzfgs);
            if (zzo instanceof zzduw) {
                zzduw zzduw = zzo;
                zzdyp.zzw(zzduw.getVersion(), 0);
                zzdyp.zzgz(zzduw.zzbot().size());
                if (zzduw.zzbpc().zzboz() != 12) {
                    if (zzduw.zzbpc().zzboz() != 16) {
                        throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
                    }
                }
                return new zzdxg(zzduw.zzbot().toByteArray(), zzduw.zzbpc().zzboz());
            }
            throw new GeneralSecurityException("expected AesEaxKey proto");
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized AesEaxKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final zzdsy zza(zzfjc zzfjc) throws GeneralSecurityException {
        if (zzfjc instanceof zzduw) {
            zzduw zzduw = (zzduw) zzfjc;
            zzdyp.zzw(zzduw.getVersion(), 0);
            zzdyp.zzgz(zzduw.zzbot().size());
            if (zzduw.zzbpc().zzboz() == 12 || zzduw.zzbpc().zzboz() == 16) {
                return new zzdxg(zzduw.zzbot().toByteArray(), zzduw.zzbpc().zzboz());
            }
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
        throw new GeneralSecurityException("expected AesEaxKey proto");
    }

    public final zzfjc zzb(zzfgs zzfgs) throws GeneralSecurityException {
        try {
            return zzb((zzfjc) zzduy.zzq(zzfgs));
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized AesEaxKeyFormat proto", e);
        }
    }

    public final zzfjc zzb(zzfjc zzfjc) throws GeneralSecurityException {
        if (zzfjc instanceof zzduy) {
            zzduy zzduy = (zzduy) zzfjc;
            zzdyp.zzgz(zzduy.getKeySize());
            if (zzduy.zzbpc().zzboz() == 12 || zzduy.zzbpc().zzboz() == 16) {
                return zzduw.zzbpd().zzp(zzfgs.zzaz(zzdyl.zzgy(zzduy.getKeySize()))).zzb(zzduy.zzbpc()).zzge(0).zzczx();
            }
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
        throw new GeneralSecurityException("expected AesEaxKeyFormat proto");
    }

    public final zzdwg zzc(zzfgs zzfgs) throws GeneralSecurityException {
        return (zzdwg) zzdwg.zzbqx().zzop("type.googleapis.com/google.crypto.tink.AesEaxKey").zzai(((zzduw) zzb(zzfgs)).toByteString()).zzb(zzdwg.zzb.SYMMETRIC).zzczx();
    }
}
