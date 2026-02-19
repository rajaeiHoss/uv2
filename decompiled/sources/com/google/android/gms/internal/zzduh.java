package com.google.android.gms.internal;

import com.google.android.gms.internal.zzduq;
import com.google.android.gms.internal.zzdvc;
import com.google.android.gms.internal.zzdwa;
import java.security.GeneralSecurityException;
import java.util.Arrays;

final class zzduh implements zzdxm {
    private final String zzmfn;
    private final int zzmfo;
    private zzdvc zzmfp;
    private zzdum zzmfq;
    private int zzmfr;

    zzduh(zzdwl zzdwl) throws GeneralSecurityException {
        String zzbqu = zzdwl.zzbqu();
        this.zzmfn = zzbqu;
        if (zzbqu.equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            try {
                zzdve zzt = zzdve.zzt(zzdwl.zzbqv());
                this.zzmfp = (zzdvc) zzdtn.zzb(zzdwl);
                this.zzmfo = zzt.getKeySize();
            } catch (zzfie e) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e);
            }
        } else if (zzbqu.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            try {
                zzduo zzj = zzduo.zzj(zzdwl.zzbqv());
                this.zzmfq = (zzdum) zzdtn.zzb(zzdwl);
                this.zzmfr = zzj.zzbop().getKeySize();
                this.zzmfo = this.zzmfr + zzj.zzboq().getKeySize();
            } catch (zzfie e2) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e2);
            }
        } else {
            String valueOf = String.valueOf(zzbqu);
            throw new GeneralSecurityException(valueOf.length() != 0 ? "unsupported AEAD DEM key type: ".concat(valueOf) : new String("unsupported AEAD DEM key type: "));
        }
    }

    public final zzdsy zzah(byte[] bArr) throws GeneralSecurityException {
        zzfjc zzfjc;
        if (bArr.length == this.zzmfo) {
            if (this.zzmfn.equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
                zzfjc = (zzdvc) ((zzdvc.zza) zzdvc.zzbpi().zza(this.zzmfp)).zzs(zzfgs.zzf(bArr, 0, this.zzmfo)).zzczx();
            } else if (this.zzmfn.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
                byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, this.zzmfr);
                byte[] copyOfRange2 = Arrays.copyOfRange(bArr, this.zzmfr, this.zzmfo);
                zzfjc = (zzdum) zzdum.zzbon().zzgc(this.zzmfq.getVersion()).zzb((zzduq) ((zzduq.zza) zzduq.zzbou().zza(this.zzmfq.zzbol())).zzm(zzfgs.zzaz(copyOfRange)).zzczx()).zzb((zzdwa) ((zzdwa.zza) zzdwa.zzbql().zza(this.zzmfq.zzbom())).zzaf(zzfgs.zzaz(copyOfRange2)).zzczx()).zzczx();
            } else {
                throw new GeneralSecurityException("unknown DEM key type");
            }
            return (zzdsy) zzdtn.zzb(this.zzmfn, zzfjc);
        }
        throw new GeneralSecurityException("Symmetric key has incorrect length");
    }

    public final int zzbok() {
        return this.zzmfo;
    }
}
