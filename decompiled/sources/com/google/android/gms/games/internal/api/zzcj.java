package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.snapshot.Snapshots;

abstract class zzcj extends Games.zza<Snapshots.CommitSnapshotResult> {
    private zzcj(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzcj(GoogleApiClient googleApiClient, zzce zzce) {
        this(googleApiClient);
    }

    public final /* synthetic */ Snapshots.CommitSnapshotResult zzb(Status status) {
        return new zzck(this, status);
    }
}
