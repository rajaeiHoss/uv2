package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.snapshot.Snapshots;

abstract class zzcp extends Games.zza<Snapshots.OpenSnapshotResult> {
    private zzcp(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzcp(GoogleApiClient googleApiClient, zzce zzce) {
        this(googleApiClient);
    }

    public final /* synthetic */ Snapshots.OpenSnapshotResult zzb(Status status) {
        return new zzcq(this, status);
    }
}
