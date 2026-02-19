package com.google.android.gms.games.internal.api;

import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

final class zzdq implements TurnBasedMultiplayer.LoadMatchesResult {
    private /* synthetic */ Status zzetp;

    zzdq(zzdp zzdp, Status status) {
        this.zzetp = status;
    }

    public final LoadMatchesResponse getMatches() {
        return new LoadMatchesResponse(new Bundle());
    }

    public final Status getStatus() {
        return this.zzetp;
    }

    public final void release() {
    }
}
