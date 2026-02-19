package com.google.android.gms.games.snapshot;

import android.graphics.Bitmap;
import android.net.Uri;
import com.google.android.gms.common.data.BitmapTeleporter;

public interface SnapshotMetadataChange {
    public static final SnapshotMetadataChange EMPTY_CHANGE = new zze();

    public static final class Builder {
        private String zzdxh;
        private Long zzihm;
        private Long zzihn;
        private BitmapTeleporter zziho;
        private Uri zzihp;

        public final SnapshotMetadataChange build() {
            return new zze(this.zzdxh, this.zzihm, this.zziho, this.zzihp, this.zzihn);
        }

        public final Builder fromMetadata(SnapshotMetadata snapshotMetadata) {
            this.zzdxh = snapshotMetadata.getDescription();
            this.zzihm = Long.valueOf(snapshotMetadata.getPlayedTime());
            this.zzihn = Long.valueOf(snapshotMetadata.getProgressValue());
            if (this.zzihm.longValue() == -1) {
                this.zzihm = null;
            }
            Uri coverImageUri = snapshotMetadata.getCoverImageUri();
            this.zzihp = coverImageUri;
            if (coverImageUri != null) {
                this.zziho = null;
            }
            return this;
        }

        public final Builder setCoverImage(Bitmap bitmap) {
            this.zziho = new BitmapTeleporter(bitmap);
            this.zzihp = null;
            return this;
        }

        public final Builder setDescription(String str) {
            this.zzdxh = str;
            return this;
        }

        public final Builder setPlayedTimeMillis(long j) {
            this.zzihm = Long.valueOf(j);
            return this;
        }

        public final Builder setProgressValue(long j) {
            this.zzihn = Long.valueOf(j);
            return this;
        }
    }

    Bitmap getCoverImage();

    String getDescription();

    Long getPlayedTimeMillis();

    Long getProgressValue();

    BitmapTeleporter zzavy();
}
