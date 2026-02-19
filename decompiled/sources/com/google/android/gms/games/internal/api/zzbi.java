package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.Players;

final class zzbi implements Players.LoadPlayersResult {
    private /* synthetic */ Status zzetp;

    zzbi(zzbh zzbh, Status status) {
        this.zzetp = status;
    }

    public final PlayerBuffer getPlayers() {
        return new PlayerBuffer(DataHolder.zzbz(14));
    }

    public final Status getStatus() {
        return this.zzetp;
    }

    public final void release() {
    }
}
