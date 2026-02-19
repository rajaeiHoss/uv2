package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.snapshot.SnapshotMetadata;

final class zzch extends zzcl {
    private /* synthetic */ SnapshotMetadata zzibd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzch(zzcd zzcd, GoogleApiClient googleApiClient, SnapshotMetadata snapshotMetadata) {
        super(googleApiClient, (zzce) null);
        this.zzibd = snapshotMetadata;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zzi(this, this.zzibd.getSnapshotId());
    }
}
