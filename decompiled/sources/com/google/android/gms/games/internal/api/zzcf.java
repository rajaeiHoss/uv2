package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.snapshot.Snapshots;

final class zzcf extends zzcp {
    private /* synthetic */ String zziaz;
    private /* synthetic */ boolean zziba;
    private /* synthetic */ int zzibb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcf(zzcd zzcd, GoogleApiClient googleApiClient, String str, boolean z, int i) {
        super(googleApiClient, (zzce) null);
        this.zziaz = str;
        this.zziba = z;
        this.zzibb = i;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zza((zzn<Snapshots.OpenSnapshotResult>) this, this.zziaz, this.zziba, this.zzibb);
    }
}
