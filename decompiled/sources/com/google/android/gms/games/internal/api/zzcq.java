package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.Snapshots;

final class zzcq implements Snapshots.OpenSnapshotResult {
    private /* synthetic */ Status zzetp;

    zzcq(zzcp zzcp, Status status) {
        this.zzetp = status;
    }

    public final String getConflictId() {
        return null;
    }

    public final Snapshot getConflictingSnapshot() {
        return null;
    }

    public final SnapshotContents getResolutionSnapshotContents() {
        return null;
    }

    public final Snapshot getSnapshot() {
        return null;
    }

    public final Status getStatus() {
        return this.zzetp;
    }
}
