package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.Snapshots;

final class zzca implements zzbo<Snapshots.CommitSnapshotResult, SnapshotMetadata> {
    zzca() {
    }

    public final SnapshotMetadata zzb(Snapshots.CommitSnapshotResult result) {
        SnapshotMetadata snapshotMetadata;
        if (result == null || (snapshotMetadata = result.getSnapshotMetadata()) == null) {
            return null;
        }
        return (SnapshotMetadata) snapshotMetadata.freeze();
    }
}
