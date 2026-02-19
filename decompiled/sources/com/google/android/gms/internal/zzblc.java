package com.google.android.gms.internal;

import com.google.android.gms.awareness.fence.FenceQueryResult;
import com.google.android.gms.awareness.fence.FenceStateMap;
import com.google.android.gms.common.api.Status;

final class zzblc implements FenceQueryResult {
    private /* synthetic */ Status zzetp;

    zzblc(zzblb zzblb, Status status) {
        this.zzetp = status;
    }

    public final FenceStateMap getFenceStateMap() {
        return null;
    }

    public final Status getStatus() {
        return this.zzetp;
    }
}
