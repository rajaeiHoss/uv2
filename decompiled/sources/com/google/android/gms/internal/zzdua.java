package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdxd;
import java.security.GeneralSecurityException;

public final class zzdua {
    public static final zzdxd zzmfe;
    private static zzdxd zzmff;

    static {
        zzdxd zzdxdVar = (zzdxd) ((zzdxd.zza) zzdxd.zzbsn().zza(zzdtp.zzmfe)).zzb(zzdta.zza("TinkHybridDecrypt", "HybridDecrypt", "EciesAeadHkdfPrivateKey", 0, true)).zzb(zzdta.zza("TinkHybridEncrypt", "HybridEncrypt", "EciesAeadHkdfPublicKey", 0, true)).zzox("TINK_HYBRID_1_0_0").zzczx();
        zzmfe = zzdxdVar;
        zzmff = (zzdxd) ((zzdxd.zza) zzdxd.zzbsn().zza(zzdxdVar)).zzox("TINK_HYBRID_1_1_0").zzczx();
        try {
            zzdtn.zza("TinkHybridEncrypt", new zzduc());
            zzdtn.zza("TinkHybridDecrypt", new zzdub());
            zzdtp.zzboi();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
