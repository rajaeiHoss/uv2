package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzl extends Games.zzd {
    zzl(GoogleApiClient googleApiClient) {
        super(googleApiClient, (zzi) null);
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zzg((zzn<Status>) this);
    }
}
