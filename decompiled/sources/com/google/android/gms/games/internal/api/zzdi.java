package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

final class zzdi implements TurnBasedMultiplayer.CancelMatchResult {
    private /* synthetic */ Status zzetp;
    private /* synthetic */ zzdh zzibn;

    zzdi(zzdh zzdh, Status status) {
        this.zzibn = zzdh;
        this.zzetp = status;
    }

    public final String getMatchId() {
        return this.zzibn.zzbzd;
    }

    public final Status getStatus() {
        return this.zzetp;
    }
}
