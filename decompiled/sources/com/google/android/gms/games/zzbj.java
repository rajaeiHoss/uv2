package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.zzq;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.zzh;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbj extends zzq<zzh> {
    private /* synthetic */ zzci zzhsp;
    private /* synthetic */ RoomConfig zzhus;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbj(RealTimeMultiplayerClient realTimeMultiplayerClient, zzci zzci, zzci zzci2, RoomConfig roomConfig) {
        super(zzci);
        this.zzhsp = zzci2;
        this.zzhus = roomConfig;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException, SecurityException {
        zzci zzci = this.zzhsp;
        gamesClientImpl.zza((zzci<? extends RoomUpdateListener>) zzci, (zzci<? extends RoomStatusUpdateListener>) zzci, (zzci<? extends RealTimeMessageReceivedListener>) zzci, this.zzhus);
        taskCompletionSource.setResult(null);
    }
}
