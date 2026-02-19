package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.snapshot.Snapshots;

final class zzbz implements zzbo<Snapshots.DeleteSnapshotResult, String> {
    zzbz() {
    }

    public final String zzb(Snapshots.DeleteSnapshotResult result) {
        if (result == null) {
            return null;
        }
        return result.getSnapshotId();
    }
}
