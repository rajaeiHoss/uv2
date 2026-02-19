package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.Snapshots;

final class zzbu implements zzbo<Snapshots.LoadSnapshotsResult, SnapshotMetadataBuffer> {
    zzbu() {
    }

    public final SnapshotMetadataBuffer zzb(Snapshots.LoadSnapshotsResult result) {
        if (result == null) {
            return null;
        }
        return result.getSnapshots();
    }
}
