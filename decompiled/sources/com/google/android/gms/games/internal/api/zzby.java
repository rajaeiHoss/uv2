package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.request.Requests;

final class zzby extends zzbz {
    private /* synthetic */ int zzial;
    private /* synthetic */ int zziax;
    private /* synthetic */ int zziay;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzby(zzbv zzbv, GoogleApiClient googleApiClient, int i, int i2, int i3) {
        super(googleApiClient, (zzbw) null);
        this.zziax = i;
        this.zziay = i2;
        this.zzial = i3;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zza((zzn<Requests.LoadRequestsResult>) this, this.zziax, this.zziay, this.zzial);
    }
}
