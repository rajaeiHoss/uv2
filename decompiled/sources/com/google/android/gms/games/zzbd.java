package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbd extends zzac<Void> {
    private /* synthetic */ byte[] zzhuk;
    private /* synthetic */ String zzhul;

    zzbd(RealTimeMultiplayerClient realTimeMultiplayerClient, byte[] bArr, String str) {
        this.zzhuk = bArr;
        this.zzhul = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        if (gamesClientImpl.zzd(this.zzhuk, this.zzhul) == 0) {
            taskCompletionSource.setResult(null);
        } else {
            taskCompletionSource.trySetException(zzb.zzy(GamesClientStatusCodes.zzdg(GamesClientStatusCodes.REAL_TIME_MESSAGE_SEND_FAILED)));
        }
    }
}
