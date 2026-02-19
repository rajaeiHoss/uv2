package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzs extends zzu {
    private /* synthetic */ boolean zziah;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzs(zzq zzq, GoogleApiClient googleApiClient, boolean z) {
        super(googleApiClient, (zzr) null);
        this.zziah = z;
    }

    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zzd((zzn<Events.LoadEventsResult>) this, this.zziah);
    }
}
