package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbr extends zzac<Integer> {
    private /* synthetic */ zzci zzgvn;
    private /* synthetic */ byte[] zzhuk;
    private /* synthetic */ String zzhul;
    private /* synthetic */ String zzhum;

    zzbr(RealTimeMultiplayerClient realTimeMultiplayerClient, zzci zzci, byte[] bArr, String str, String str2) {
        this.zzgvn = zzci;
        this.zzhuk = bArr;
        this.zzhul = str;
        this.zzhum = str2;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Integer> taskCompletionSource) throws RemoteException {
        Integer valueOf = Integer.valueOf(gamesClientImpl.zza((zzci<RealTimeMultiplayer.ReliableMessageSentCallback>) this.zzgvn, this.zzhuk, this.zzhul, this.zzhum));
        if (valueOf.intValue() == -1) {
            taskCompletionSource.trySetException(zzb.zzy(GamesClientStatusCodes.zzdg(GamesClientStatusCodes.REAL_TIME_MESSAGE_SEND_FAILED)));
        } else {
            taskCompletionSource.setResult(valueOf);
        }
    }
}
