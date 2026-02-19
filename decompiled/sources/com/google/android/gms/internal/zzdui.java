package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdwg;
import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;

final class zzdui implements zzdtf<zzdtj> {
    zzdui() {
    }

    private static void zza(zzdwe zzdwe) throws GeneralSecurityException {
        if (zzdwe.zzbqr() >= 10) {
            int i = zzduj.zzmfk[zzdwe.zzbqq().ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        throw new GeneralSecurityException("unknown hash type");
                    } else if (zzdwe.zzbqr() > 64) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                } else if (zzdwe.zzbqr() > 32) {
                    throw new GeneralSecurityException("tag size too big");
                }
            } else if (zzdwe.zzbqr() > 20) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else {
            throw new GeneralSecurityException("tag size too small");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: zzh */
    public final zzdtj zza(zzfgs zzfgs) throws GeneralSecurityException {
        zzdyj zzdyj;
        try {
            zzdwa zzae = zzdwa.zzae(zzfgs);
            if (zzae instanceof zzdwa) {
                zzdwa zzdwa = zzae;
                zzdyp.zzw(zzdwa.getVersion(), 0);
                if (zzdwa.zzbot().size() >= 16) {
                    zza(zzdwa.zzbqk());
                    zzdvy zzbqq = zzdwa.zzbqk().zzbqq();
                    SecretKeySpec secretKeySpec = new SecretKeySpec(zzdwa.zzbot().toByteArray(), "HMAC");
                    int zzbqr = zzdwa.zzbqk().zzbqr();
                    int i = zzduj.zzmfk[zzbqq.ordinal()];
                    if (i == 1) {
                        zzdyj = new zzdyj("HMACSHA1", secretKeySpec, zzbqr);
                    } else if (i == 2) {
                        zzdyj = new zzdyj("HMACSHA256", secretKeySpec, zzbqr);
                    } else if (i == 3) {
                        zzdyj = new zzdyj("HMACSHA512", secretKeySpec, zzbqr);
                    } else {
                        throw new GeneralSecurityException("unknown hash");
                    }
                    return zzdyj;
                }
                throw new GeneralSecurityException("key too short");
            }
            throw new GeneralSecurityException("expected HmacKey proto");
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized HmacKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final zzdtj zza(zzfjc zzfjc) throws GeneralSecurityException {
        if (zzfjc instanceof zzdwa) {
            zzdwa zzdwa = (zzdwa) zzfjc;
            zzdyp.zzw(zzdwa.getVersion(), 0);
            if (zzdwa.zzbot().size() >= 16) {
                zza(zzdwa.zzbqk());
                zzdvy zzbqq = zzdwa.zzbqk().zzbqq();
                SecretKeySpec secretKeySpec = new SecretKeySpec(zzdwa.zzbot().toByteArray(), "HMAC");
                int zzbqr = zzdwa.zzbqk().zzbqr();
                int i = zzduj.zzmfk[zzbqq.ordinal()];
                if (i == 1) {
                    return new zzdyj("HMACSHA1", secretKeySpec, zzbqr);
                }
                if (i == 2) {
                    return new zzdyj("HMACSHA256", secretKeySpec, zzbqr);
                }
                if (i == 3) {
                    return new zzdyj("HMACSHA512", secretKeySpec, zzbqr);
                }
                throw new GeneralSecurityException("unknown hash");
            }
            throw new GeneralSecurityException("key too short");
        }
        throw new GeneralSecurityException("expected HmacKey proto");
    }

    public final zzfjc zzb(zzfgs zzfgs) throws GeneralSecurityException {
        try {
            return zzb((zzfjc) zzdwc.zzag(zzfgs));
        } catch (zzfie e) {
            throw new GeneralSecurityException("expected serialized HmacKeyFormat proto", e);
        }
    }

    public final zzfjc zzb(zzfjc zzfjc) throws GeneralSecurityException {
        if (zzfjc instanceof zzdwc) {
            zzdwc zzdwc = (zzdwc) zzfjc;
            if (zzdwc.getKeySize() >= 16) {
                zza(zzdwc.zzbqk());
                return zzdwa.zzbql().zzgm(0).zzc(zzdwc.zzbqk()).zzaf(zzfgs.zzaz(zzdyl.zzgy(zzdwc.getKeySize()))).zzczx();
            }
            throw new GeneralSecurityException("key too short");
        }
        throw new GeneralSecurityException("expected HmacKeyFormat proto");
    }

    public final zzdwg zzc(zzfgs zzfgs) throws GeneralSecurityException {
        return (zzdwg) zzdwg.zzbqx().zzop("type.googleapis.com/google.crypto.tink.HmacKey").zzai(((zzdwa) zzb(zzfgs)).toByteString()).zzb(zzdwg.zzb.SYMMETRIC).zzczx();
    }
}
