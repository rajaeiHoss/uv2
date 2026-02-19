package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.request.Requests;

final class zzca implements Requests.LoadRequestsResult {
    private /* synthetic */ Status zzetp;

    zzca(zzbz zzbz, Status status) {
        this.zzetp = status;
    }

    public final GameRequestBuffer getRequests(int i) {
        return new GameRequestBuffer(DataHolder.zzbz(this.zzetp.getStatusCode()));
    }

    public final Status getStatus() {
        return this.zzetp;
    }

    public final void release() {
    }
}
