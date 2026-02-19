package com.google.android.gms.internal;

import com.google.android.gms.awareness.fence.FenceQueryResult;
import com.google.android.gms.awareness.fence.FenceStateMap;
import com.google.android.gms.common.api.Status;

final class zzbll implements FenceQueryResult {
    private /* synthetic */ Status zzetp;
    private /* synthetic */ zzbkl zzgow;

    zzbll(zzblj zzblj, zzbkl zzbkl, Status status) {
        this.zzgow = zzbkl;
        this.zzetp = status;
    }

    public final FenceStateMap getFenceStateMap() {
        return this.zzgow;
    }

    public final Status getStatus() {
        return this.zzetp;
    }
}
