package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

final class zzbn implements Continuation<Boolean, Task<Void>> {
    final /* synthetic */ String zzhul;
    final /* synthetic */ RoomConfig zzhus;
    final /* synthetic */ zzci zzhut;
    private /* synthetic */ RealTimeMultiplayerClient zzhuu;

    zzbn(RealTimeMultiplayerClient realTimeMultiplayerClient, zzci zzci, String str, RoomConfig roomConfig) {
        this.zzhuu = realTimeMultiplayerClient;
        this.zzhut = zzci;
        this.zzhul = str;
        this.zzhus = roomConfig;
    }

    public final Task<Void> then(Task<Boolean> task) throws Exception {
        return this.zzhuu.zza(new zzbo(this));
    }
}
