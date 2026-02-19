package com.google.android.gms.internal;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Status;

final class zzbcw implements GameManagerClient.GameManagerInstanceResult {
    private final Status mStatus;
    private final GameManagerClient zzflb;

    zzbcw(Status status, GameManagerClient gameManagerClient) {
        this.mStatus = status;
        this.zzflb = gameManagerClient;
    }

    public final GameManagerClient getGameManagerClient() {
        return this.zzflb;
    }

    public final Status getStatus() {
        return this.mStatus;
    }
}
