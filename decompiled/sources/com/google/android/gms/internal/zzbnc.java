package com.google.android.gms.internal;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;

final class zzbnc implements Releasable, DriveApi.DriveContentsResult {
    private final Status mStatus;
    private final DriveContents zzgph;

    public zzbnc(Status status, DriveContents driveContents) {
        this.mStatus = status;
        this.zzgph = driveContents;
    }

    public final DriveContents getDriveContents() {
        return this.zzgph;
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    public final void release() {
        DriveContents driveContents = this.zzgph;
        if (driveContents != null) {
            driveContents.zzapm();
        }
    }
}
