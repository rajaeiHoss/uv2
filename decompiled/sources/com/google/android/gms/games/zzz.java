package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.zzr;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzz extends zzr<OnInvitationReceivedListener> {
    zzz(InvitationsClient invitationsClient, zzck zzck) {
        super(zzck);
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException, SecurityException {
        gamesClientImpl.zzatp();
        taskCompletionSource.setResult(true);
    }
}
