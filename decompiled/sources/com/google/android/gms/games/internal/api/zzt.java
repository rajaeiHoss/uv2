package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzt extends zzw {
    private /* synthetic */ String zzhqv;
    private /* synthetic */ int zzhqw;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzt(zzq zzq, GoogleApiClient googleApiClient, String str, int i) {
        super(googleApiClient, (zzr) null);
        this.zzhqv = str;
        this.zzhqw = i;
    }

    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zzp(this.zzhqv, this.zzhqw);
    }
}
