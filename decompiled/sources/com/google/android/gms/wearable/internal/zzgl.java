package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.wearable.CapabilityApi;

final class zzgl extends zzgm<CapabilityApi.AddLocalCapabilityResult> {
    public zzgl(zzn<CapabilityApi.AddLocalCapabilityResult> zzn) {
        super(zzn);
    }

    public final void zza(zzf zzf) {
        zzav(new zzu(zzgd.zzdg(zzf.statusCode)));
    }
}
