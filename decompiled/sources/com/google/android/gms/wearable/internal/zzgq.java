package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.wearable.CapabilityApi;

final class zzgq extends zzgm<CapabilityApi.GetAllCapabilitiesResult> {
    public zzgq(zzn<CapabilityApi.GetAllCapabilitiesResult> zzn) {
        super(zzn);
    }

    public final void zza(zzdi zzdi) {
        zzav(new zzx(zzgd.zzdg(zzdi.statusCode), zzgk.zzap(zzdi.zzluh)));
    }
}
