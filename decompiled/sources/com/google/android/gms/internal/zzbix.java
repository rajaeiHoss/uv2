package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import java.util.Map;
import java.util.TreeMap;

final class zzbix extends zzbiu {
    private /* synthetic */ zzbiw zzgmf;

    zzbix(zzbiw zzbiw) {
        this.zzgmf = zzbiw;
    }

    public final void zza(Status status, zzbjh zzbjh) {
        if (zzbjh.getStatusCode() == 6502 || zzbjh.getStatusCode() == 6507) {
            this.zzgmf.setResult(new zzbiy(zzbis.zzcm(zzbjh.getStatusCode()), zzbis.zza(zzbjh), zzbjh.getThrottleEndTimeMillis(), zzbis.zzb(zzbjh)));
        } else {
            this.zzgmf.setResult(new zzbiy(zzbis.zzcm(zzbjh.getStatusCode()), (Map<String, TreeMap<String, byte[]>>) zzbis.zza(zzbjh), zzbis.zzb(zzbjh)));
        }
    }
}
