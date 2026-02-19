package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.games.internal.zzw;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbb extends zzac<Void> {
    private /* synthetic */ byte[] zzhuk;
    private /* synthetic */ String zzhul;
    private /* synthetic */ String zzhum;

    zzbb(RealTimeMultiplayerClient realTimeMultiplayerClient, byte[] bArr, String str, String str2) {
        this.zzhuk = bArr;
        this.zzhul = str;
        this.zzhum = str2;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        if (((zzw) gamesClientImpl.zzalw()).zzb(this.zzhuk, this.zzhul, new String[]{this.zzhum}) == 0) {
            taskCompletionSource.setResult(null);
        } else {
            taskCompletionSource.trySetException(zzb.zzy(GamesClientStatusCodes.zzdg(GamesClientStatusCodes.REAL_TIME_MESSAGE_SEND_FAILED)));
        }
    }
}
