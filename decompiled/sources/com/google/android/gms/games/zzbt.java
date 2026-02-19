package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.SnapshotsClient;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.Snapshots;

final class zzbt implements zzbo<Snapshots.OpenSnapshotResult, SnapshotsClient.DataOrConflict<Snapshot>> {
    zzbt() {
    }

    public final SnapshotsClient.DataOrConflict<Snapshot> zzb(Snapshots.OpenSnapshotResult result) {
        if (result != null) {
            Snapshot snapshot = result.getSnapshot() != null ? (Snapshot) result.getSnapshot().freeze() : null;
            if (result.getStatus().getStatusCode() == 0) {
                return new SnapshotsClient.DataOrConflict(snapshot, (SnapshotsClient.SnapshotConflict) null);
            }
            if (result.getStatus().getStatusCode() == 4004) {
                SnapshotsClient.SnapshotConflict snapshotConflict = (snapshot == null || result.getConflictId() == null || result.getConflictingSnapshot() == null || result.getResolutionSnapshotContents() == null) ? null : new SnapshotsClient.SnapshotConflict(snapshot, result.getConflictId(), (Snapshot) result.getConflictingSnapshot().freeze(), result.getResolutionSnapshotContents());
                if (snapshotConflict != null) {
                    return new SnapshotsClient.DataOrConflict(null, snapshotConflict);
                }
            }
        }
        return null;
    }
}
