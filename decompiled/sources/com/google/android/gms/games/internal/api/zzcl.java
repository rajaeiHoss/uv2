package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.snapshot.Snapshots;

abstract class zzcl extends Games.zza<Snapshots.DeleteSnapshotResult> {
    private zzcl(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzcl(GoogleApiClient googleApiClient, zzce zzce) {
        this(googleApiClient);
    }

    public final /* synthetic */ Snapshots.DeleteSnapshotResult zzb(Status status) {
        return new zzcm(this, status);
    }
}
