package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.games.internal.zzw;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

final class zzbc extends zzac<Void> {
    private /* synthetic */ byte[] zzhuk;
    private /* synthetic */ String zzhul;
    private /* synthetic */ List zzhun;

    zzbc(RealTimeMultiplayerClient realTimeMultiplayerClient, List list, byte[] bArr, String str) {
        this.zzhun = list;
        this.zzhuk = bArr;
        this.zzhul = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zzbq.checkNotNull(this.zzhun, "Participant IDs must not be null");
        List list = this.zzhun;
        if (((zzw) gamesClientImpl.zzalw()).zzb(this.zzhuk, this.zzhul, (String[]) list.toArray(new String[list.size()])) == 0) {
            taskCompletionSource.setResult(null);
        } else {
            taskCompletionSource.trySetException(zzb.zzy(GamesClientStatusCodes.zzdg(GamesClientStatusCodes.REAL_TIME_MESSAGE_SEND_FAILED)));
        }
    }
}
