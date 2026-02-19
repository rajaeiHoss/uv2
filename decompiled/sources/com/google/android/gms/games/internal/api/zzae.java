package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.multiplayer.Invitations;

final class zzae extends zzaf {
    private /* synthetic */ int zzial;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzae(zzad zzad, GoogleApiClient googleApiClient, int i) {
        super(googleApiClient, (zzae) null);
        this.zzial = i;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zza((zzn<Invitations.LoadInvitationsResult>) this, this.zzial);
    }
}
