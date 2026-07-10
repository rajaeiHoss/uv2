package com.google.android.gms.games.snapshot

import android.os.Parcelable
import com.google.android.gms.common.data.Freezable

interface Snapshot : Parcelable, Freezable<Snapshot> {
    val metadata: SnapshotMetadata

    val snapshotContents: SnapshotContents
}
