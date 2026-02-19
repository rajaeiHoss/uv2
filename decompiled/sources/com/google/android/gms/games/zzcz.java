package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.zzr;
import com.google.android.gms.games.video.Videos;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcz extends zzr<Videos.CaptureOverlayStateListener> {
    zzcz(VideosClient videosClient, zzck zzck) {
        super(zzck);
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException, SecurityException {
        gamesClientImpl.zzaup();
        taskCompletionSource.setResult(true);
    }
}
