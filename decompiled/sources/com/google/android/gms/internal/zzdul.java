package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdxd;
import java.security.GeneralSecurityException;

public final class zzdul {
    public static final zzdxd zzmfe;
    private static zzdxd zzmff;

    static {
        zzdxd zzdxdVar = (zzdxd) zzdxd.zzbsn().zzox("TINK_MAC_1_0_0").zzb(zzdta.zza("TinkMac", "Mac", "HmacKey", 0, true)).zzczx();
        zzmfe = zzdxdVar;
        zzmff = (zzdxd) ((zzdxd.zza) zzdxd.zzbsn().zza(zzdxdVar)).zzox("TINK_MAC_1_1_0").zzczx();
        try {
            zzboi();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void zzboi() throws GeneralSecurityException {
        zzdtn.zza("TinkMac", new zzduk());
    }
}
