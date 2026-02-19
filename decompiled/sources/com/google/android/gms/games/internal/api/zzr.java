package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzr extends zzu {
    private /* synthetic */ boolean zziah;
    private /* synthetic */ String[] zziak;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzr(zzq zzq, GoogleApiClient googleApiClient, boolean z, String[] strArr) {
        super(googleApiClient, (zzr) null);
        this.zziah = z;
        this.zziak = strArr;
    }

    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zza((zzn<Events.LoadEventsResult>) this, this.zziah, this.zziak);
    }
}
