package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.wearable.NodeApi;

final class zzgy extends zzgm<NodeApi.GetLocalNodeResult> {
    public zzgy(zzn<NodeApi.GetLocalNodeResult> zzn) {
        super(zzn);
    }

    public final void zza(zzeg zzeg) {
        zzav(new zzfk(zzgd.zzdg(zzeg.statusCode), zzeg.zzlur));
    }
}
