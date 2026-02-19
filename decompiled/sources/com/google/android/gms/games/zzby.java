package com.google.android.gms.games;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.games.SnapshotsClient;
import com.google.android.gms.games.internal.zzn;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.Snapshots;

final class zzby implements zzn<Snapshots.OpenSnapshotResult> {
    zzby() {
    }

    public final ApiException zza(Status status, Snapshots.OpenSnapshotResult openSnapshotResult) {
        return (status.getStatusCode() != 26572 || openSnapshotResult.getSnapshot() == null || openSnapshotResult.getSnapshot().getMetadata() == null) ? zzb.zzy(status) : new SnapshotsClient.SnapshotContentUnavailableApiException(status, (SnapshotMetadata) openSnapshotResult.getSnapshot().getMetadata().freeze());
    }
}
