package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.zzq;
import com.google.android.gms.games.video.Videos;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcy extends zzq<Videos.CaptureOverlayStateListener> {
    private /* synthetic */ zzci zzhsp;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcy(VideosClient videosClient, zzci zzci, zzci zzci2) {
        super(zzci);
        this.zzhsp = zzci2;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException, SecurityException {
        gamesClientImpl.zzk(this.zzhsp);
        taskCompletionSource.setResult(null);
    }
}
