package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

final class zzds implements TurnBasedMultiplayer.UpdateMatchResult {
    private /* synthetic */ Status zzetp;

    zzds(zzdr zzdr, Status status) {
        this.zzetp = status;
    }

    public final TurnBasedMatch getMatch() {
        return null;
    }

    public final Status getStatus() {
        return this.zzetp;
    }
}
