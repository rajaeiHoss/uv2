package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzbk extends zzbo {
    private /* synthetic */ String zzias;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbk(zzbj zzbj, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient, (zzbk) null);
        this.zzias = str;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zzh(this, this.zzias);
    }
}
