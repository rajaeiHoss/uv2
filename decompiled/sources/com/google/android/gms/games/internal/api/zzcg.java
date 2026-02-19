package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshots;

final class zzcg extends zzcj {
    private /* synthetic */ Snapshot zzhvi;
    private /* synthetic */ SnapshotMetadataChange zzibc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcg(zzcd zzcd, GoogleApiClient googleApiClient, Snapshot snapshot, SnapshotMetadataChange snapshotMetadataChange) {
        super(googleApiClient, (zzce) null);
        this.zzhvi = snapshot;
        this.zzibc = snapshotMetadataChange;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zza((zzn<Snapshots.CommitSnapshotResult>) this, this.zzhvi, this.zzibc);
    }
}
