package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.games.internal.zzc;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class SnapshotEntity extends zzc implements Snapshot {
    public static final Parcelable.Creator<SnapshotEntity> CREATOR = new com.google.android.gms.games.snapshot.zzc();
    private final SnapshotMetadataEntity zzihk;
    private final zza zzihl;

    public SnapshotEntity(SnapshotMetadata snapshotMetadata, zza zza) {
        this.zzihk = new SnapshotMetadataEntity(snapshotMetadata);
        this.zzihl = zza;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Snapshot)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Snapshot snapshot = (Snapshot) obj;
        return zzbg.equal(snapshot.getMetadata(), getMetadata()) && zzbg.equal(snapshot.getSnapshotContents(), getSnapshotContents());
    }

    public final Snapshot freeze() {
        return this;
    }

    public final SnapshotMetadata getMetadata() {
        return this.zzihk;
    }

    public final SnapshotContents getSnapshotContents() {
        if (this.zzihl.isClosed()) {
            return null;
        }
        return this.zzihl;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{getMetadata(), getSnapshotContents()});
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("Metadata", getMetadata()).zzg("HasContents", Boolean.valueOf(getSnapshotContents() != null)).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getMetadata(), i, false);
        zzbgo.zza(parcel, 3, (Parcelable) getSnapshotContents(), i, false);
        zzbgo.zzai(parcel, zze);
    }
}
