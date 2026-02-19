package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFolder;

final class zzbor implements DriveFolder.DriveFolderResult {
    private final Status mStatus;
    private final DriveFolder zzgvc;

    public zzbor(Status status, DriveFolder driveFolder) {
        this.mStatus = status;
        this.zzgvc = driveFolder;
    }

    public final DriveFolder getDriveFolder() {
        return this.zzgvc;
    }

    public final Status getStatus() {
        return this.mStatus;
    }
}
