package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.snapshot.Snapshots;

final class zzce extends zzcn {
    private /* synthetic */ boolean zziah;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzce(zzcd zzcd, GoogleApiClient googleApiClient, boolean z) {
        super(googleApiClient, (zzce) null);
        this.zziah = z;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zzf((zzn<Snapshots.LoadSnapshotsResult>) this, this.zziah);
    }
}
