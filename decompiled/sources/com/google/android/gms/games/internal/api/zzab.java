package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.GamesMetadata;

final class zzab implements GamesMetadata.LoadGamesResult {
    private /* synthetic */ Status zzetp;

    zzab(zzaa zzaa, Status status) {
        this.zzetp = status;
    }

    public final GameBuffer getGames() {
        return new GameBuffer(DataHolder.zzbz(14));
    }

    public final Status getStatus() {
        return this.zzetp;
    }

    public final void release() {
    }
}
