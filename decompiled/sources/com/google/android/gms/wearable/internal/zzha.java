package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.wearable.ChannelApi;

final class zzha extends zzgm<ChannelApi.OpenChannelResult> {
    public zzha(zzn<ChannelApi.OpenChannelResult> zzn) {
        super(zzn);
    }

    public final void zza(zzfq zzfq) {
        zzav(new zzam(zzgd.zzdg(zzfq.statusCode), zzfq.zzlth));
    }
}
