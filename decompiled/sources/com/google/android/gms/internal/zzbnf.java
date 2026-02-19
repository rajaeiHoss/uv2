package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveId;

final class zzbnf implements DriveApi.DriveIdResult {
    private final Status mStatus;
    private final DriveId zzgpe;

    public zzbnf(Status status, DriveId driveId) {
        this.mStatus = status;
        this.zzgpe = driveId;
    }

    public final DriveId getDriveId() {
        return this.zzgpe;
    }

    public final Status getStatus() {
        return this.mStatus;
    }
}
