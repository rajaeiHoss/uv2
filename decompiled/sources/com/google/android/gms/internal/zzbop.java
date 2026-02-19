package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;

final class zzbop implements DriveFolder.DriveFileResult {
    private final Status mStatus;
    private final DriveFile zzgvb;

    public zzbop(Status status, DriveFile driveFile) {
        this.mStatus = status;
        this.zzgvb = driveFile;
    }

    public final DriveFile getDriveFile() {
        return this.zzgvb;
    }

    public final Status getStatus() {
        return this.mStatus;
    }
}
