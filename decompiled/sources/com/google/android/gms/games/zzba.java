package com.google.android.gms.games;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzba extends zzac<Intent> {
    private /* synthetic */ Room zzhui;
    private /* synthetic */ int zzhuj;

    zzba(RealTimeMultiplayerClient realTimeMultiplayerClient, Room room, int i) {
        this.zzhui = room;
        this.zzhuj = i;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Intent> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(gamesClientImpl.zza(this.zzhui, this.zzhuj));
    }
}
