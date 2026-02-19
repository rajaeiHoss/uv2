package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

final class zzdg extends zzdp {
    private /* synthetic */ int zzibl;
    private /* synthetic */ int[] zzibm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdg(zzcw zzcw, GoogleApiClient googleApiClient, int i, int[] iArr) {
        super(googleApiClient, (zzcx) null);
        this.zzibl = i;
        this.zzibm = iArr;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zza((zzn<TurnBasedMultiplayer.LoadMatchesResult>) this, this.zzibl, this.zzibm);
    }
}
