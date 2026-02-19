package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshots;

final class zzci extends zzcp {
    private /* synthetic */ SnapshotMetadataChange zzibc;
    private /* synthetic */ String zzibe;
    private /* synthetic */ String zzibf;
    private /* synthetic */ SnapshotContents zzibg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzci(zzcd zzcd, GoogleApiClient googleApiClient, String str, String str2, SnapshotMetadataChange snapshotMetadataChange, SnapshotContents snapshotContents) {
        super(googleApiClient, (zzce) null);
        this.zzibe = str;
        this.zzibf = str2;
        this.zzibc = snapshotMetadataChange;
        this.zzibg = snapshotContents;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zza((zzn<Snapshots.OpenSnapshotResult>) this, this.zzibe, this.zzibf, this.zzibc, this.zzibg);
    }
}
