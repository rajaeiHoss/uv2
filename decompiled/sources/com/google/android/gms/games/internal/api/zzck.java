package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.Snapshots;

final class zzck implements Snapshots.CommitSnapshotResult {
    private /* synthetic */ Status zzetp;

    zzck(zzcj zzcj, Status status) {
        this.zzetp = status;
    }

    public final SnapshotMetadata getSnapshotMetadata() {
        return null;
    }

    public final Status getStatus() {
        return this.zzetp;
    }
}
