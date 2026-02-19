package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.wearable.NodeApi;

final /* synthetic */ class zzfn implements zzbo {
    static final zzbo zzgui = new zzfn();

    private zzfn() {
    }

    public final Object zzb(Result result) {
        return ((NodeApi.GetConnectedNodesResult) result).getNodes();
    }
}
