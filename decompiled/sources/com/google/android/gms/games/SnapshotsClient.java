package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.api.zzp;
import com.google.android.gms.games.internal.zzg;
import com.google.android.gms.games.internal.zzn;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.tasks.Task;

public class SnapshotsClient extends zzp {
    public static final int DISPLAY_LIMIT_NONE = -1;
    public static final String EXTRA_SNAPSHOT_METADATA = "com.google.android.gms.games.SNAPSHOT_METADATA";
    public static final String EXTRA_SNAPSHOT_NEW = "com.google.android.gms.games.SNAPSHOT_NEW";
    public static final int RESOLUTION_POLICY_HIGHEST_PROGRESS = 4;
    public static final int RESOLUTION_POLICY_LAST_KNOWN_GOOD = 2;
    public static final int RESOLUTION_POLICY_LONGEST_PLAYTIME = 1;
    public static final int RESOLUTION_POLICY_MANUAL = -1;
    public static final int RESOLUTION_POLICY_MOST_RECENTLY_MODIFIED = 3;
    private static final zzn<Snapshots.OpenSnapshotResult> zzhux = new zzby();
    private static final zzbo<Snapshots.DeleteSnapshotResult, String> zzhuy = new zzbz();
    private static final zzbo<Snapshots.CommitSnapshotResult, SnapshotMetadata> zzhuz = new zzca();
    private static final zzbo<Snapshots.OpenSnapshotResult, Snapshots.OpenSnapshotResult> zzhva = new zzcb();
    private static final com.google.android.gms.games.internal.zzp zzhvb = new zzcc();
    private static final zzbo<Snapshots.OpenSnapshotResult, DataOrConflict<Snapshot>> zzhvc = new zzbt();
    private static final zzbo<Snapshots.LoadSnapshotsResult, SnapshotMetadataBuffer> zzhvd = new zzbu();

    public static class DataOrConflict<T> {
        private final T data;
        private final SnapshotConflict zzhvj;

        DataOrConflict(T t, SnapshotConflict snapshotConflict) {
            this.data = t;
            this.zzhvj = snapshotConflict;
        }

        public SnapshotConflict getConflict() {
            if (isConflict()) {
                return this.zzhvj;
            }
            throw new IllegalStateException("getConflict called when there is no conflict.");
        }

        public T getData() {
            if (!isConflict()) {
                return this.data;
            }
            throw new IllegalStateException("getData called when there is a conflict.");
        }

        public boolean isConflict() {
            return this.zzhvj != null;
        }
    }

    public static class SnapshotConflict {
        private final Snapshot zzhvk;
        private final String zzhvl;
        private final Snapshot zzhvm;
        private final SnapshotContents zzhvn;

        SnapshotConflict(Snapshot snapshot, String str, Snapshot snapshot2, SnapshotContents snapshotContents) {
            this.zzhvk = snapshot;
            this.zzhvl = str;
            this.zzhvm = snapshot2;
            this.zzhvn = snapshotContents;
        }

        public String getConflictId() {
            return this.zzhvl;
        }

        public Snapshot getConflictingSnapshot() {
            return this.zzhvm;
        }

        public SnapshotContents getResolutionSnapshotContents() {
            return this.zzhvn;
        }

        public Snapshot getSnapshot() {
            return this.zzhvk;
        }
    }

    public static class SnapshotContentUnavailableApiException extends ApiException {
        protected final SnapshotMetadata metadata;

        SnapshotContentUnavailableApiException(Status status, SnapshotMetadata snapshotMetadata) {
            super(status);
            this.metadata = snapshotMetadata;
        }

        public SnapshotMetadata getSnapshotMetadata() {
            return this.metadata;
        }
    }

    SnapshotsClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    SnapshotsClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public static SnapshotMetadata getSnapshotFromBundle(Bundle bundle) {
        return Games.Snapshots.getSnapshotFromBundle(bundle);
    }

    private static Task<DataOrConflict<Snapshot>> zze(PendingResult<Snapshots.OpenSnapshotResult> pendingResult) {
        return zzg.zza(pendingResult, zzhvb, zzhvc, zzhva, zzhux);
    }

    public Task<SnapshotMetadata> commitAndClose(Snapshot snapshot, SnapshotMetadataChange snapshotMetadataChange) {
        return zzg.zza(Games.Snapshots.commitAndClose(zzahw(), snapshot, snapshotMetadataChange), zzhuz);
    }

    public Task<String> delete(SnapshotMetadata snapshotMetadata) {
        return zzg.zza(Games.Snapshots.delete(zzahw(), snapshotMetadata), zzhuy);
    }

    public Task<Void> discardAndClose(Snapshot snapshot) {
        return zzb(new zzbx(this, snapshot));
    }

    public Task<Integer> getMaxCoverImageSize() {
        return zza(new zzbv(this));
    }

    public Task<Integer> getMaxDataSize() {
        return zza(new zzbs(this));
    }

    public Task<Intent> getSelectSnapshotIntent(String str, boolean z, boolean z2, int i) {
        return zza(new zzbw(this, str, z, z2, i));
    }

    public Task<AnnotatedData<SnapshotMetadataBuffer>> load(boolean z) {
        return zzg.zzc(Games.Snapshots.load(zzahw(), z), zzhvd);
    }

    public Task<DataOrConflict<Snapshot>> open(SnapshotMetadata snapshotMetadata) {
        return zze(Games.Snapshots.open(zzahw(), snapshotMetadata));
    }

    public Task<DataOrConflict<Snapshot>> open(SnapshotMetadata snapshotMetadata, int i) {
        return zze(Games.Snapshots.open(zzahw(), snapshotMetadata, i));
    }

    public Task<DataOrConflict<Snapshot>> open(String str, boolean z) {
        return zze(Games.Snapshots.open(zzahw(), str, z));
    }

    public Task<DataOrConflict<Snapshot>> open(String str, boolean z, int i) {
        return zze(Games.Snapshots.open(zzahw(), str, z, i));
    }

    public Task<DataOrConflict<Snapshot>> resolveConflict(String str, Snapshot snapshot) {
        return zze(Games.Snapshots.resolveConflict(zzahw(), str, snapshot));
    }

    public Task<DataOrConflict<Snapshot>> resolveConflict(String str, String str2, SnapshotMetadataChange snapshotMetadataChange, SnapshotContents snapshotContents) {
        return zze(Games.Snapshots.resolveConflict(zzahw(), str, str2, snapshotMetadataChange, snapshotContents));
    }
}
