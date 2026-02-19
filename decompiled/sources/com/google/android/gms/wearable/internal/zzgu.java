package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.wearable.NodeApi;
import java.util.ArrayList;

final class zzgu extends zzgm<NodeApi.GetConnectedNodesResult> {
    public zzgu(zzn<NodeApi.GetConnectedNodesResult> zzn) {
        super(zzn);
    }

    public final void zza(zzea zzea) {
        ArrayList arrayList = new ArrayList();
        if (zzea.zzlup != null) {
            arrayList.addAll(zzea.zzlup);
        }
        zzav(new zzfj(zzgd.zzdg(zzea.statusCode), arrayList));
    }
}
