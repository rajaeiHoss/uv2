package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.Snapshots;

final class zzcm implements Snapshots.DeleteSnapshotResult {
    private /* synthetic */ Status zzetp;

    zzcm(zzcl zzcl, Status status) {
        this.zzetp = status;
    }

    public final String getSnapshotId() {
        return null;
    }

    public final Status getStatus() {
        return this.zzetp;
    }
}
